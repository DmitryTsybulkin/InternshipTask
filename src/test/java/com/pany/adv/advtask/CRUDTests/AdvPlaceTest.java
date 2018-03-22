package com.pany.adv.advtask.CRUDTests;

import com.pany.adv.advtask.AdvTaskApplication;
import com.pany.adv.advtask.domain.*;
import com.pany.adv.advtask.domain.builders.UserBuilder;
import com.pany.adv.advtask.repository.AdvConstructionRep;
import com.pany.adv.advtask.repository.AdvPlaceRep;
import com.pany.adv.advtask.repository.MunicipalityRep;
import com.pany.adv.advtask.repository.UserRep;
import com.pany.adv.advtask.service.convertors.AdvConstructionDTOConverter;
import com.pany.adv.advtask.service.convertors.AdvPlaceDTOConverter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static com.pany.adv.advtask.service.utils.JsonFormatter.asJsonString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdvTaskApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class AdvPlaceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AdvPlaceRep advPlaceRep;

    @Autowired
    private UserRep userRep;

    @Autowired
    private MunicipalityRep municipalityRep;

    @Autowired
    private AdvPlaceDTOConverter converter;

    private User user;

    private AdvPlace place;

    private List<Municipality> municipalities = new ArrayList<>();

    @Before
    public void setup() throws Exception {
        advPlaceRep.deleteAllInBatch();
        userRep.deleteAllInBatch();
        municipalityRep.deleteAllInBatch();

        municipalities.add(municipalityRep.save(new Municipality("name")));

        user = userRep.save(new UserBuilder().withLogin("admin").withName("name").withSurname("sur").withPatronymic("patron")
                .withMunicipality(municipalities).withRole(Roles.ADMIN).withPassword("admin").build());

        place = advPlaceRep.save(new AdvPlace("owner", "address", municipalities.get(0)));
    }

    @Test
    public void createPlace() throws Exception {
        advPlaceRep.deleteAllInBatch();
        mockMvc.perform(MockMvcRequestBuilders.post("/places")
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole()))
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(place)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(converter.toDto(place))));
    }

    @Test
    public void badCreatePlace() throws Exception {
        AdvPlace advPlace = new AdvPlace(place.getOwner(), place.getAddress(), place.getMunicipality());

        mockMvc.perform(MockMvcRequestBuilders.post("/places")
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole()))
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(advPlace)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void placeIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/places/" + place.getId())
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(converter.toDto(place))));
    }

    @Test
    public void placesIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/places")
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void placeIsNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/places/" + 2)
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void placesIsNotFound() throws Exception {
        advPlaceRep.deleteAllInBatch();
        mockMvc.perform(MockMvcRequestBuilders.get("/places")
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void updatePlace() throws Exception {
        AdvPlace advPlace = new AdvPlace("newOwner", "newAddress", place.getMunicipality());

        mockMvc.perform(MockMvcRequestBuilders.put("/places/" + place.getId())
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(advPlace)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(converter.toDto(advPlace))));
    }

    @Test
    public void badUpdatePlace() throws Exception {
        advPlaceRep.deleteAllInBatch();
        AdvPlace advPlace = new AdvPlace("newOwner", "newAddress", place.getMunicipality());

        mockMvc.perform(MockMvcRequestBuilders.put("/places/" + place.getId())
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(advPlace)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deletePlace() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/places/" + place.getId())
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/constructions/" + user.getId())
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void accessDeniedToUsers() throws Exception {
        User simpleUser = userRep.save(new UserBuilder().withLogin("user").withName("user")
                .withPassword("user").withSurname("surname").withRole(Roles.USER)
                .withMunicipality(municipalities).withPatronymic("patron").build());

        mockMvc.perform(MockMvcRequestBuilders.get("/places")
                .with(user(simpleUser.getLogin()).password(simpleUser.getPassword()).roles(simpleUser.getRole().name())
                        .authorities(simpleUser.getRole())))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @After
    public void dropDb() throws Exception {
        advPlaceRep.deleteAllInBatch();
        userRep.deleteAllInBatch();
        municipalityRep.deleteAllInBatch();
    }

}

package com.pany.adv.advtask.CRUDTests;

import com.pany.adv.advtask.AdvTaskApplication;
import com.pany.adv.advtask.domain.*;
import com.pany.adv.advtask.domain.builders.UserBuilder;
import com.pany.adv.advtask.repository.AdvConstructionRep;
import com.pany.adv.advtask.repository.AdvPlaceRep;
import com.pany.adv.advtask.repository.MunicipalityRep;
import com.pany.adv.advtask.repository.UserRep;
import com.pany.adv.advtask.service.convertors.AdvConstructionDTOConverter;
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
import java.util.Date;
import java.util.List;

import static com.pany.adv.advtask.service.utils.JsonFormatter.asJsonString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdvTaskApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class AdvConstructionTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AdvConstructionRep advConstructionRep;

    @Autowired
    private AdvPlaceRep advPlaceRep;

    @Autowired
    private AdvConstructionDTOConverter advConstructionDTOConverter;

    @Autowired
    private UserRep userRep;

    @Autowired
    private MunicipalityRep municipalityRep;

    private User user;

    private AdvConstruction construction;

    private AdvPlace place;

    private List<Municipality> municipalities = new ArrayList<>();

    @Before
    public void setup() throws Exception {
        advConstructionRep.deleteAllInBatch();
        advPlaceRep.deleteAllInBatch();
        userRep.deleteAllInBatch();
        municipalityRep.deleteAllInBatch();

        municipalities.add(municipalityRep.save(new Municipality("name")));

        user = userRep.save(new UserBuilder().withLogin("admin").withName("name").withSurname("sur").withPatronymic("patron")
                                    .withMunicipality(municipalities).withRole(Roles.ADMIN).withPassword("admin").build());

        place = advPlaceRep.save(new AdvPlace("owner", "address", municipalities.get(0)));

        construction = advConstructionRep.save(new AdvConstruction(place, "owner", 1, "type", "status", new Date()));

    }

    @Test
    public void createConstruction() throws Exception {
        advConstructionRep.deleteAllInBatch();
        mockMvc.perform(MockMvcRequestBuilders.post("/constructions")
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole()))
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(construction)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(advConstructionDTOConverter.toDto(construction))));
    }

    @Test
    public void constructionIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/constructions/" + construction.getId())
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(advConstructionDTOConverter.toDto(construction))));
    }

    @Test
    public void constructionsIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/constructions")
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void constructionIsNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/constructions/" + 2)
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void constructionsIsNotFound() throws Exception {
        advConstructionRep.deleteAllInBatch();
        mockMvc.perform(MockMvcRequestBuilders.get("/constructions")
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void updateConstruction() throws Exception {
        AdvConstruction construct = new AdvConstruction(place, "newOwner", 1, "newType", "newStatus", new Date());

        mockMvc.perform(MockMvcRequestBuilders.put("/constructions/" + construction.getId())
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(construct)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(advConstructionDTOConverter.toDto(construct))));
    }

    @Test
    public void badUpdateConstruction() throws Exception {
        advConstructionRep.deleteAllInBatch();
        AdvConstruction construct = new AdvConstruction(place, "newOwner", 1, "newType", "newStatus", new Date());

        mockMvc.perform(MockMvcRequestBuilders.put("/constructions/" + construction.getId())
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(construct)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deleteConstruction() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/constructions/" + user.getId())
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

        mockMvc.perform(MockMvcRequestBuilders.get("/constructions")
                .with(user(simpleUser.getLogin()).password(simpleUser.getPassword()).roles(simpleUser.getRole().name())
                        .authorities(simpleUser.getRole())))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

}

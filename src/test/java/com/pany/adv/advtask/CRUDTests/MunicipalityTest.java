package com.pany.adv.advtask.CRUDTests;

import com.pany.adv.advtask.AdvTaskApplication;
import com.pany.adv.advtask.domain.Municipality;
import com.pany.adv.advtask.domain.Roles;
import com.pany.adv.advtask.domain.User;
import com.pany.adv.advtask.domain.builders.UserBuilder;
import com.pany.adv.advtask.repository.MunicipalityRep;
import com.pany.adv.advtask.repository.UserRep;
import com.pany.adv.advtask.service.convertors.MunicipalityDTOConverter;
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

import java.util.Collections;

import static com.pany.adv.advtask.service.utils.JsonFormatter.asJsonString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdvTaskApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class MunicipalityTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MunicipalityRep municipalityRep;

    @Autowired
    private MunicipalityDTOConverter converter;

    @Autowired
    private UserRep userRep;

    private Municipality municipality;

    private User user;

    @Before
    public void setup() throws Exception {
        userRep.deleteAllInBatch();
        municipalityRep.deleteAllInBatch();

        municipality = municipalityRep.save(new Municipality("name"));

        user = userRep.save(new UserBuilder().withLogin("admin").withPassword("admin").withName("admin").withSurname("admin")
                .withPatronymic("admin").withRole(Roles.ADMIN).withMunicipality(Collections.singletonList(municipality)).build());
    }

    @Test
    public void createMunicipality() throws Exception {
        Municipality mun = new Municipality("Municipal");

        mockMvc.perform(MockMvcRequestBuilders.post("/municipalities")
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(mun)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/municipalities/" + municipalityRep.findMunicipalityByName(mun.getName()).getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void badCreateMunicipality() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/municipalities")
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(municipality)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void municipalityIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/municipalities/" + municipality.getId())
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(converter.toDto(municipality))));
    }

    @Test
    public void municipalitiesIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/municipalities")
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void updateMunicipality() throws Exception {
        Municipality mun = new Municipality("newName");

        mockMvc.perform(MockMvcRequestBuilders.put("/municipalities/" + municipality.getId())
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(mun)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(converter.toDto(mun))));
    }

    @Test
    public void deleteMunicipality() throws Exception {
        Municipality mun = municipalityRep.save(new Municipality("bad_mun"));
        mockMvc.perform(MockMvcRequestBuilders.delete("/municipalities/" + mun.getId())
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/municipalities/" + mun.getId())
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void accessDeniedForUser() throws Exception {
        User simpleUser = userRep.save(new UserBuilder().withLogin("user").withName("user")
                .withPassword("user").withSurname("surname").withRole(Roles.USER)
                .withMunicipality(Collections.singletonList(municipality)).withPatronymic("patron").build());

        mockMvc.perform(MockMvcRequestBuilders.get("/municipalities")
                .with(user(simpleUser.getLogin()).password(simpleUser.getPassword()).roles(simpleUser.getRole().name())
                        .authorities(simpleUser.getRole())))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

}

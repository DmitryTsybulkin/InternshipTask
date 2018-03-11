package com.pany.adv.advtask.CRUDTests;

import com.pany.adv.advtask.AdvTaskApplication;
import com.pany.adv.advtask.domain.Municipality;
import com.pany.adv.advtask.domain.Roles;
import com.pany.adv.advtask.domain.User;
import com.pany.adv.advtask.domain.builders.UserBuilder;
import com.pany.adv.advtask.repository.MunicipalityRep;
import com.pany.adv.advtask.repository.RequestRep;
import com.pany.adv.advtask.repository.UserRep;
import com.pany.adv.advtask.service.convertors.UserDTOConverter;
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

import static com.pany.adv.advtask.service.JsonFormatter.asJsonString;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdvTaskApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class UserTest {

    @Autowired
    private MockMvc mockMvc;

    private User user;

    private List<Municipality> municipalities = new ArrayList<>();

    @Autowired
    private UserRep userRep;

    @Autowired
    private RequestRep requestRep;

    @Autowired
    private MunicipalityRep municipalityRep;

    @Autowired
    private UserDTOConverter converter;

    @Before
    public void setup() throws Exception {
        requestRep.deleteAllInBatch();
        userRep.deleteAllInBatch();
        municipalityRep.deleteAllInBatch();

        municipalities.add(municipalityRep.save(new Municipality("municipalityN1")));
        municipalities.add(municipalityRep.save(new Municipality("municipalityN2")));

        user = userRep.save(new UserBuilder().withLogin("login").withName("Name").withPassword("Password").withSurname("surname")
        .withRole(Roles.ADMIN).withMunicipality(municipalities).withPatronymic("patron").build());
    }

    @Test
    public void createUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(converter.toDto(user))));
    }

    @Test
    public void createUserFailedBecauseMissParameter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content("bad_info"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    // TODO: 11.03.2018 test that entity is duplicate + update user
//    @Test
//    public void createUserFailedBecauseUserIsExists() throws Exception {
//        User newUser = new UserBuilder().withLogin(user.getLogin()).withRole(user.getRole())
//                .withMunicipality(user.getMunicipality()).withName(user.getName()).withPassword(user.getPassword())
//                .withSurname(user.getSurname()).withPatronymic(user.getPatronymic()).build();
//
//        newUser.setId(user.getId());
//        assertEquals(user, newUser);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/users")
//        .contentType(MediaType.APPLICATION_JSON_UTF8)
//        .content(asJsonString(newUser)))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest());
//    }

    @Test
    public void usersIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void userIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + user.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(converter.toDto(user))));
    }

    @Test
    public void userNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + 2))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}

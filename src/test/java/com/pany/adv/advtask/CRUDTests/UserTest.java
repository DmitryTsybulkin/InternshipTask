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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.pany.adv.advtask.service.utils.JsonFormatter.asJsonString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;


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
    private WebApplicationContext context;

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

        user = userRep.save(new UserBuilder().withLogin("admin").withName("Name").withPassword("admin").withSurname("surname")
                .withRole(Roles.ADMIN).withMunicipality(municipalities).withPatronymic("patron").build());
    }

    @Test
    public void createUser() throws Exception {
        userRep.deleteAllInBatch();
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(converter.toDto(user))));
    }

    @Test
    public void createUserFailedBecauseMissParameter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("bad_info"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createUserFailedBecauseUserIsExists() throws Exception {
        User newUser = new UserBuilder().withLogin(user.getLogin()).withRole(user.getRole())
                .withMunicipality(user.getMunicipalities()).withName(user.getName()).withPassword(user.getPassword())
                .withSurname(user.getSurname()).withPatronymic(user.getPatronymic()).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(newUser)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void usersIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void userIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + user.getId())
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(converter.toDto(user))));
    }

    @Test
    public void userNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + 2)
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void updateUser() throws Exception {
        User newUser = new UserBuilder().withLogin("NewLogin").withRole(user.getRole())
                .withMunicipality(user.getMunicipalities()).withName(user.getName()).withPassword("NewPassword")
                .withSurname(user.getSurname()).withPatronymic(user.getPatronymic()).build();


        mockMvc.perform(MockMvcRequestBuilders.put("/users/" + user.getId())
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(newUser)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(converter.toDto(newUser))));
    }

    @Test
    public void badUpdateUserBecauseMissParameter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/users/" + user.getId())
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("bad_info"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void badUpdateUserBecauseUserNotExists() throws Exception {
        userRep.deleteAllInBatch();
        User newUser = new UserBuilder().withLogin("NewLogin").withRole(user.getRole())
                .withMunicipality(user.getMunicipalities()).withName(user.getName()).withPassword("NewPassword")
                .withSurname(user.getSurname()).withPatronymic(user.getPatronymic()).build();


        mockMvc.perform(MockMvcRequestBuilders.put("/users/" + user.getId())
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(newUser)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/" + user.getId())
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + user.getId())
                .with(user(user.getLogin()).password(user.getPassword()).roles(user.getRole().name()).authorities(user.getRole())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void accessDeniedToUsers() throws Exception {
            User simpleUser = userRep.save(new UserBuilder().withLogin("user").withName("user")
                    .withPassword("user").withSurname("surname").withRole(Roles.USER)
                    .withMunicipality(municipalities).withPatronymic("patron").build());

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .with(user(simpleUser.getLogin()).password(simpleUser.getPassword()).roles(simpleUser.getRole().name())
                        .authorities(simpleUser.getRole())))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

}

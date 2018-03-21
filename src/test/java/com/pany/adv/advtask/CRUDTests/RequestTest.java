package com.pany.adv.advtask.CRUDTests;


import com.pany.adv.advtask.AdvTaskApplication;
import com.pany.adv.advtask.domain.*;
import com.pany.adv.advtask.domain.builders.RequestBuilder;
import com.pany.adv.advtask.domain.builders.UserBuilder;
import com.pany.adv.advtask.repository.*;
import com.pany.adv.advtask.service.convertors.RequestDTOConverter;
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
public class RequestTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RequestRep requestRep;

    @Autowired
    private UserRep userRep;

    @Autowired
    private AdvPlaceRep placeRep;

    @Autowired
    private AdvConstructionRep constructionRep;

    @Autowired
    private MunicipalityRep municipalityRep;

    @Autowired
    private PhotoRep photoRep;

    @Autowired
    private ArchiveRep archiveRep;

    @Autowired
    private RequestDTOConverter converter;

    private User applicant;

    private User editor;

    private User admin;

    private Request emptyRequest;

    private Request notConfirmedRequest;

    private Request confirmedRequest;

    private Photo photo;

    private AdvPlace place;

    private AdvConstruction construction;

    private List<Municipality> municipalities = new ArrayList<>();

    @Before
    public void setup() throws Exception {
        List<Request> requests = requestRep.findAll();
        for (Request request : requests) {
            request.setPhoto(null);
        }

        List<Photo> photos = photoRep.findAll();
        for (Photo photo : photos) {
            photo.setRequest(null);
        }

        photoRep.deleteAllInBatch();
        requestRep.deleteAll();
        constructionRep.deleteAllInBatch();
        placeRep.deleteAllInBatch();
        userRep.deleteAllInBatch();
        municipalityRep.deleteAllInBatch();

        municipalities.add(municipalityRep.save(new Municipality("name")));

        admin = userRep.save(new UserBuilder().withLogin("admin").withPassword("admin").withName("admin").withSurname("admin")
                .withPatronymic("admin").withMunicipality(municipalities).withRole(Roles.ADMIN).build());

        editor = userRep.save(new UserBuilder().withLogin("editor").withPassword("editor").withName("editor").withSurname("editor")
                .withPatronymic("editor").withMunicipality(municipalities).withRole(Roles.EDITOR).build());

        applicant = userRep.save(new UserBuilder().withLogin("applicant").withPassword("applicant").withName("applicant")
                .withSurname("applicant").withPatronymic("applicant").withRole(Roles.USER).withMunicipality(municipalities).build());

        place = placeRep.save(new AdvPlace("owner", "address", municipalities.get(0)));

        construction = constructionRep.save(new AdvConstruction(place, "owner", 1, "type", "status", new Date()));

        photo = photoRep.save(new Photo(null, "name"));

        emptyRequest = requestRep.save(new RequestBuilder().withDate(new Date()).withActuality("actuality").withAdvConstruction(construction)
                .withAdvPlace(place).withApplicant(applicant).withDateProcessed(null).withHandler(null).withReason(null).withPhoto(photo)
                .withVersion(0).withStatus("Отправлено на обработку").build());

        notConfirmedRequest = requestRep.save(new RequestBuilder().withDate(new Date()).withActuality("actuality").withAdvConstruction(construction)
                .withAdvPlace(place).withApplicant(applicant).withDateProcessed(new Date()).withHandler(editor).withReason("reason").withPhoto(photo)
                .withVersion(1).withStatus("Отклонено").build());

        confirmedRequest = requestRep.save(new RequestBuilder().withDate(new Date()).withActuality("actuality").withAdvConstruction(construction)
                .withAdvPlace(place).withApplicant(applicant).withDateProcessed(new Date()).withHandler(editor).withReason(null).withPhoto(photo)
                .withVersion(0).withStatus("Согласовано").build());

    }

    @Test
    public void createRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/requests")
                .with(user(applicant.getLogin()).password(applicant.getPassword()).roles(applicant.getRole().name()).authorities(applicant.getRole()))
                .content(asJsonString(emptyRequest))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(converter.toDto(emptyRequest))));
    }

    @Test
    public void requestIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/requests/" + emptyRequest.getId())
                .with(user(applicant.getLogin()).password(applicant.getPassword()).roles(applicant.getRole().name()).authorities(applicant.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(converter.toDto(emptyRequest))));
    }

}

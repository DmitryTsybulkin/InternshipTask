package com.pany.adv.advtask.CRUDTests;


import com.pany.adv.advtask.AdvTaskApplication;
import com.pany.adv.advtask.domain.*;
import com.pany.adv.advtask.domain.builders.RequestBuilder;
import com.pany.adv.advtask.domain.builders.UserBuilder;
import com.pany.adv.advtask.repository.*;
import com.pany.adv.advtask.service.convertors.RequestDTOConverter;
import com.pany.adv.advtask.service.utils.FileSaver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.pany.adv.advtask.service.utils.JsonFormatter.asJsonString;
import static org.junit.Assert.assertFalse;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdvTaskApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class RequestTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FileSaver fileSaver;

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

    private Municipality municipality = new Municipality("name");

    private List<Municipality> municipalities = new ArrayList<>();

    private AdvPlace place = new AdvPlace("owner", "address", municipality);

    private AdvConstruction construction = new AdvConstruction(place, "owner", 1, "type", "status", new Date());

    private User editor = new UserBuilder().withLogin("editor").withPassword("editor").withName("editor").withSurname("editor")
                .withPatronymic("editor").withMunicipality(municipalities).withRole(Roles.EDITOR).build();

    private User applicant = new UserBuilder().withLogin("applicant").withPassword("applicant").withName("applicant")
                .withSurname("applicant").withPatronymic("applicant").withMunicipality(municipalities).withRole(Roles.USER).build();

    private Photo photo;

    private Request emptyRequest;

    @Before
    public void setup() throws Exception {

        photoRep.deleteAllInBatch();
        requestRep.deleteAll();
        constructionRep.deleteAllInBatch();
        placeRep.deleteAllInBatch();
        userRep.deleteAllInBatch();
        municipalityRep.deleteAllInBatch();

        municipalities.add(municipality);
        municipalityRep.save(municipality);

        userRep.save(applicant);
        userRep.save(editor);

        placeRep.save(place);

        constructionRep.save(construction);

        emptyRequest = requestRep.save(new RequestBuilder().withDate(new Date()).withActuality("actuality").withAdvConstruction(construction)
                .withAdvPlace(place).withApplicant(applicant).withDateProcessed(null).withHandler(null).withReason(null).withPhoto(photo)
                .withVersion(0).withStatus("Отправлено на обработку").build());

        photo = photoRep.save(new Photo(emptyRequest, "name"));

    }

    private Request request = new RequestBuilder().withDate(new Date()).withActuality("actuality").withAdvConstruction(construction)
            .withAdvPlace(place).withApplicant(applicant).withDateProcessed(null).withHandler(null).withReason(null).withPhoto(photo)
            .withVersion(0).withStatus("Отправлено на обработку").build();

    @Test
    public void createRequest() throws Exception {

        photoRep.deleteAllInBatch();
        requestRep.deleteAllInBatch();

        requestRep.save(request);

        photo = photoRep.save(new Photo(request, "name"));


        mockMvc.perform(MockMvcRequestBuilders.post("/requests")
                .with(user(applicant.getLogin()).password(applicant.getPassword()).roles(applicant.getRole().name()).authorities(applicant.getRole()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(converter.toDto(request))));
    }

    @Test
    public void requestIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/requests/" + emptyRequest.getId())
                .with(user(applicant.getLogin()).password(applicant.getPassword()).roles(applicant.getRole().name()).authorities(applicant.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(converter.toDto(emptyRequest))));
    }

    @Test
    public void requestsIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/requests")
                .with(user(applicant.getLogin()).password(applicant.getPassword()).roles(applicant.getRole().name()).authorities(applicant.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void requestIsNotFound() throws Exception {
        photoRep.deleteAllInBatch();
        requestRep.deleteAllInBatch();
        mockMvc.perform(MockMvcRequestBuilders.get("/requests/" + emptyRequest.getId())
                .with(user(applicant.getLogin()).password(applicant.getPassword()).roles(applicant.getRole().name()).authorities(applicant.getRole())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void requestsIsNotFound() throws Exception {
        photoRep.deleteAllInBatch();
        requestRep.deleteAllInBatch();
        mockMvc.perform(MockMvcRequestBuilders.get("/requests")
                .with(user(applicant.getLogin()).password(applicant.getPassword()).roles(applicant.getRole().name()).authorities(applicant.getRole())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void updateConfirmedRequest() throws Exception {
        requestRep.save(request);

        Request confirmedRequest = request;
        confirmedRequest.setStatus("Согласовано");
        confirmedRequest.setHandler(editor);
        confirmedRequest.setDateProcessed(new Date());

        mockMvc.perform(MockMvcRequestBuilders.put("/requests/" + request.getId())
                .with(user(applicant.getLogin()).password(applicant.getPassword()).roles(applicant.getRole().name()).authorities(applicant.getRole()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(confirmedRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(converter.toDto(confirmedRequest))));

    }

    @Test
    public void updateNotConfirmedRequest() throws Exception {
        Request notConfirmedRequest = request;
        notConfirmedRequest.setHandler(editor);
        notConfirmedRequest.setStatus("Отклонено");
        notConfirmedRequest.setReason("bad");
        notConfirmedRequest.setDateProcessed(new Date());
    }

    @Test
    public void deleteRequest() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("filen", "filen.jpeg", MediaType.IMAGE_JPEG_VALUE, "ladudilabudai".getBytes());

        String fullName = fileSaver.store(multipartFile);

        //Photo photo1 = new Photo(request, fullName);

        requestRep.save(request);
        photoRep.save(new Photo(request, fullName));

        mockMvc.perform(MockMvcRequestBuilders.delete("/requests/" + request.getId())
                .with(user(applicant.getLogin()).password(applicant.getPassword()).roles(applicant.getRole().name()).authorities(applicant.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/requests/" + request.getId())
                .with(user(applicant.getLogin()).password(applicant.getPassword()).roles(applicant.getRole().name()).authorities(applicant.getRole())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        mockMvc.perform(MockMvcRequestBuilders.get("/photos/" + photoRep.findPhotoByRequest(request.getId()))
                .with(user(applicant.getLogin()).password(applicant.getPassword()).roles(applicant.getRole().name()).authorities(applicant.getRole())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        assertFalse(fileSaver.isExists(fullName));
    }

    @After
    public void dropDb() throws Exception {

        List<Request> requests = requestRep.findAll();
        for (Request request : requests) {
            request.setPhoto(null);
        }

        List<Photo> photos = photoRep.findAll();
        for (Photo photo : photos) {
            photo.setRequest(null);
        }

        photoRep.deleteAllInBatch();
        requestRep.deleteAllInBatch();
        constructionRep.deleteAllInBatch();
        placeRep.deleteAllInBatch();
        userRep.deleteAllInBatch();
        municipalityRep.deleteAllInBatch();
    }

}

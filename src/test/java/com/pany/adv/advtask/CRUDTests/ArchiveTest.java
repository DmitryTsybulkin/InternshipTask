package com.pany.adv.advtask.CRUDTests;

import com.pany.adv.advtask.AdvTaskApplication;
import com.pany.adv.advtask.domain.*;
import com.pany.adv.advtask.domain.builders.ArchiveBuilder;
import com.pany.adv.advtask.domain.builders.RequestBuilder;
import com.pany.adv.advtask.domain.builders.UserBuilder;
import com.pany.adv.advtask.repository.*;
import com.pany.adv.advtask.service.convertors.ArchiveDTOConverter;
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
import java.util.Date;
import java.util.List;

import static com.pany.adv.advtask.service.utils.JsonFormatter.asJsonString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdvTaskApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class ArchiveTest {

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
    private ArchiveDTOConverter converter;

    private Municipality municipality = new Municipality("name");

    private List<Municipality> municipalities = new ArrayList<>();

    private AdvPlace place = new AdvPlace("owner", "address", municipality);

    private AdvConstruction construction = new AdvConstruction(place, "owner", 1, "type", "status", new Date());

    private Photo photo = new Photo(null, "name");

    private User editor = new UserBuilder().withLogin("editor").withPassword("editor").withName("editor").withSurname("editor")
            .withPatronymic("editor").withMunicipality(municipalities).withRole(Roles.EDITOR).build();

    private User applicant = new UserBuilder().withLogin("applicant").withPassword("applicant").withName("applicant")
            .withSurname("applicant").withPatronymic("applicant").withRole(Roles.USER).withMunicipality(municipalities).build();

    private Request request = new RequestBuilder().withDate(new Date()).withActuality("actuality").withAdvConstruction(construction)
            .withAdvPlace(place).withApplicant(applicant).withDateProcessed(new Date()).withHandler(editor).withReason("reason").withPhoto(photo)
            .withVersion(1).withStatus("Отклонено").build();

    private Archive archive = new ArchiveBuilder().withActuality(request.getActuality()).withAdvConstruction(request.getAdvConstruction())
            .withAdvPlace(request.getAdvPlace()).withApplicant(request.getApplicant()).withDate(request.getDate())
            .withDateProcessed(request.getDateProcessed()).withHandler(request.getHandler()).withReason(request.getReason())
            .withRequestId(request).withVersion(request.getVersion()).build();

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

        photoRep.save(photo);

        requestRep.save(request);

        photo.setRequest(request);

        archiveRep.save(archive);
    }

    @Test
    public void createArchive() throws Exception {
        archiveRep.deleteAllInBatch();

        mockMvc.perform(MockMvcRequestBuilders.post("/archive")
                .with(user(applicant.getLogin()).password(applicant.getPassword()).roles(applicant.getRole().name()).authorities(applicant.getRole()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(archive)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(converter.toDto(archive))));
    }

    @Test
    public void archiveIsOk() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/archive/" + archive.getId())
                .with(user(applicant.getLogin()).password(applicant.getPassword()).roles(applicant.getRole().name()).authorities(applicant.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(converter.toDto(archive))));
    }

    @Test
    public void archivesIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/archive")
                .with(user(applicant.getLogin()).password(applicant.getPassword()).roles(applicant.getRole().name()).authorities(applicant.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void archiveIsNotFound() throws Exception {
        archiveRep.deleteAllInBatch();
        mockMvc.perform(MockMvcRequestBuilders.get("/archive/" + archive.getId())
                .with(user(applicant.getLogin()).password(applicant.getPassword()).roles(applicant.getRole().name()).authorities(applicant.getRole())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void archivesIsNotFound() throws Exception {
        archiveRep.deleteAllInBatch();
        mockMvc.perform(MockMvcRequestBuilders.get("/archive")
                .with(user(applicant.getLogin()).password(applicant.getPassword()).roles(applicant.getRole().name()).authorities(applicant.getRole())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void updateArchive() throws Exception {
        Archive arch = new ArchiveBuilder().withActuality("newActuality").withAdvConstruction(archive.getAdvConstruction())
                .withAdvPlace(archive.getAdvPlace()).withApplicant(archive.getApplicant()).withDate(archive.getDate())
                .withDateProcessed(new Date()).withHandler(archive.getHandler()).withReason("new reason").withRequestId(archive.getRequestId())
                .withVersion(2).build();

        mockMvc.perform(MockMvcRequestBuilders.put("/archive/" + archive.getId())
                .with(user(editor.getLogin()).password(editor.getPassword()).roles(editor.getRole().name()).authorities(editor.getRole()))
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(arch)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(converter.toDto(arch))));
    }

    @Test
    public void deleteArchive() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/archive/" + archive.getId())
                .with(user(editor.getLogin()).password(editor.getPassword()).roles(editor.getRole().name()).authorities(editor.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/archive/" + archive.getId())
                .with(user(editor.getLogin()).password(editor.getPassword()).roles(editor.getRole().name()).authorities(editor.getRole())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @After
    public void dropDb() throws Exception {
        archiveRep.deleteAllInBatch();
        requestRep.deleteAllInBatch();
        photoRep.deleteAllInBatch();
        constructionRep.deleteAllInBatch();
        placeRep.deleteAllInBatch();
        userRep.deleteAllInBatch();
        municipalityRep.deleteAllInBatch();
    }

}

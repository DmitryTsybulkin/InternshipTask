package com.pany.adv.advtask.CRUDTests;


import com.pany.adv.advtask.AdvTaskApplication;
import com.pany.adv.advtask.domain.*;
import com.pany.adv.advtask.domain.builders.RequestBuilder;
import com.pany.adv.advtask.domain.builders.UserBuilder;
import com.pany.adv.advtask.repository.*;
import com.pany.adv.advtask.service.utils.FileSaver;
import com.pany.adv.advtask.service.convertors.PhotoDTOConverter;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.pany.adv.advtask.service.utils.JsonFormatter.asJsonString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdvTaskApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class PhotoTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${upload.path}")
    String path;

    private Photo photo;

    @Autowired
    private PhotoRep photoRep;

    @Autowired
    FileSaver fileSaver;

    @Autowired
    private RequestRep requestRep;

    @Autowired
    private UserRep userRep;

    @Autowired
    private MunicipalityRep municipalityRep;

    @Autowired
    private PhotoDTOConverter converter;

    @Autowired
    private AdvPlaceRep placeRep;

    @Autowired
    private AdvConstructionRep constructionRep;

    private MockMultipartFile file;

    private Municipality municipality = new Municipality();

    private List<Municipality> municipalities = new ArrayList<>();

    private User applicant;

    private User admin;

    private User handler;

    private AdvPlace place = new AdvPlace("owner", "address", municipality);

    private AdvConstruction construction = new AdvConstruction(place, "bestOwner", 5, "bestType", "status", new Date());

    private Request request = new RequestBuilder().withActuality("actuality").withAdvConstruction(construction).withAdvPlace(place)
            .withApplicant(applicant).withDate(new Date()).withDateProcessed(new Date()).withHandler(handler).withReason("reason")
            .withStatus("status").withVersion(1).withPhoto(photo).build();

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

        municipalities.add(municipality);

        municipalityRep.save(municipality);
        applicant = userRep.save(new UserBuilder().withLogin("user").withName("Name").withPassword("user").withSurname("surname")
                .withRole(Roles.USER).withMunicipality(municipalities).withPatronymic("patron").build());
        admin = userRep.save(new UserBuilder().withLogin("admin").withName("Name").withPassword("admin").withSurname("surname")
                .withRole(Roles.ADMIN).withMunicipality(municipalities).withPatronymic("patron").build());
        handler = userRep.save(new UserBuilder().withLogin("edit").withName("Name").withPassword("edit").withSurname("surname")
                .withRole(Roles.EDITOR).withMunicipality(municipalities).withPatronymic("patron").build());
        placeRep.save(place);
        constructionRep.save(construction);
        requestRep.save(request);

        file = new MockMultipartFile("file", "file.jpeg", MediaType.IMAGE_JPEG_VALUE, "labadabadabda".getBytes());

        photo = photoRep.save(new Photo(request, file.getOriginalFilename()));
    }

    @Test
    public void createPhoto() throws Exception {
        photoRep.deleteAllInBatch();

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/photos").file(file).contentType(MediaType.MULTIPART_FORM_DATA)
        .content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(user(applicant.getLogin()).password(applicant.getPassword()).roles(applicant.getRole().name()).authorities(applicant.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("id", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("id", Matchers.isA(Number.class)))
                .andExpect(MockMvcResultMatchers.
                        jsonPath("fileName", Matchers.containsString(photo.getFileName())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createPhotoFailedEntityIsExists() throws Exception {
        photoRep.deleteAllInBatch();
        photoRep.save(photo);

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/photos").file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .with(user(admin.getLogin()).password(admin.getPassword()).roles(admin.getRole().name()).authorities(admin.getRole()))
                .content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        fileSaver.deleteFile(file.getOriginalFilename());
    }

    @Test
    public void photosIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/photos")
                .with(user(admin.getLogin()).password(admin.getPassword()).roles(admin.getRole().name()).authorities(admin.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void photosIsNotFound() throws Exception {
        photoRep.deleteAllInBatch();
        mockMvc.perform(MockMvcRequestBuilders.get("/photos")
                .with(user(admin.getLogin()).password(admin.getPassword()).roles(admin.getRole().name()).authorities(admin.getRole())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void photoIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/photos/" + photo.getId())
                .with(user(admin.getLogin()).password(admin.getPassword()).roles(admin.getRole().name()).authorities(admin.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(converter.toDto(photo))));
    }

    @Test
    public void photoIsNotFound() throws Exception {
        photoRep.deleteAllInBatch();
        mockMvc.perform(MockMvcRequestBuilders.get("/photos/" + photo.getId())
                .with(user(admin.getLogin()).password(admin.getPassword()).roles(admin.getRole().name()).authorities(admin.getRole())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deletePhoto() throws Exception {

        MockMultipartFile multipartFile = new MockMultipartFile("filen", "filen.jpeg", MediaType.IMAGE_JPEG_VALUE, "ladudilabudai".getBytes());

        String fullName = fileSaver.store(multipartFile);

        Photo photo1 = new Photo(request, fullName);
        photoRep.save(photo1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/photos/" + photo1.getId())
                .with(user(admin.getLogin()).password(admin.getPassword()).roles(admin.getRole().name()).authorities(admin.getRole())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/photos/" + photo1.getId())
                .with(user(admin.getLogin()).password(admin.getPassword()).roles(admin.getRole().name()).authorities(admin.getRole())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        assertFalse(fileSaver.isExists(fullName));
    }

}

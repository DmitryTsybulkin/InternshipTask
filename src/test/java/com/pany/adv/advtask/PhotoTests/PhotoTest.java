package com.pany.adv.advtask.PhotoTests;


import com.pany.adv.advtask.AdvTaskApplication;
import com.pany.adv.advtask.domain.*;
import com.pany.adv.advtask.domain.builders.RequestBuilder;
import com.pany.adv.advtask.domain.builders.UserBuilder;
import com.pany.adv.advtask.repository.MunicipalityRep;
import com.pany.adv.advtask.repository.PhotoRep;
import com.pany.adv.advtask.repository.RequestRep;
import com.pany.adv.advtask.repository.UserRep;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.pany.adv.advtask.service.JsonFormatter.asJsonString;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdvTaskApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class PhotoTest {

    @Autowired
    private MockMvc mockMvc;

    private MockMultipartFile file = new MockMultipartFile("image", "simple_image_data_bytes".getBytes());

    @Value("${upload.path}")
    String path;

    private Photo photo;

    @Autowired
    private PhotoRep photoRep;

    @Autowired
    private RequestRep requestRep;

    @Autowired
    private UserRep userRep;

    @Autowired
    private MunicipalityRep municipalityRep;

    private Municipality municipality = new Municipality("municipality");

    private List<Municipality> municipalities = new ArrayList<>();

    private User applicant = new UserBuilder().withLogin("login").withName("Name").withPassword("Password").withSurname("surname")
            .withRole(Roles.ADMIN).withMunicipality(municipalities).withPatronymic("patron").build();

    private User handler = new UserBuilder().withLogin("login").withName("Name").withPassword("Password").withSurname("surname")
            .withRole(Roles.EDITOR).withMunicipality(municipalities).withPatronymic("patron").build();

    private AdvPlace place = new AdvPlace("owner", "address", municipality);

    private AdvConstruction construction = new AdvConstruction(place, "bestOwner", 5, "bestType", "status", new Date());

    private Request request = new RequestBuilder().withActuality("actuality").withAdvConstruction(construction).withAdvPlace(place)
            .withApplicant(applicant).withDate(new Date()).withDateProcessed(new Date()).withHandler(handler).withReason("reason")
            .withStatus("status").withVersion("1").withPhoto(photo).build();

    @Before
    public void setup() throws Exception {
        requestRep.deleteAllInBatch();
        userRep.deleteAllInBatch();
        municipalityRep.deleteAllInBatch();

        municipalities.add(municipality);

        //photo = photoRep.save(new Photo(request, path + file.getOriginalFilename()));
    }

    // bad request???
    @Test
    public void createPhoto() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/photos").file(file).contentType(MediaType.MULTIPART_FORM_DATA)
//        .content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}

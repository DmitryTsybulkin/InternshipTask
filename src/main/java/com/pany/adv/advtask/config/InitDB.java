package com.pany.adv.advtask.config;

import com.pany.adv.advtask.domain.*;
import com.pany.adv.advtask.domain.builders.RequestBuilder;
import com.pany.adv.advtask.domain.builders.UserBuilder;
import com.pany.adv.advtask.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Date;

@Component
@Profile(value = {"prod"})
public class InitDB {

    private final AdvConstructionRep constructionRep;

    private final AdvPlaceRep advPlaceRep;

    private final AdvPlaceRep placeRep;

    private final MunicipalityRep municipalityRep;

    private final PhotoRep photoRep;

    private final RequestRep requestRep;

    private final UserRep userRep;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitDB(AdvConstructionRep constructionRep, AdvPlaceRep advPlaceRep,
                  AdvPlaceRep placeRep, MunicipalityRep municipalityRep, PhotoRep photoRep,
                  RequestRep requestRep, UserRep userRep, PasswordEncoder passwordEncoder) {
        this.constructionRep = constructionRep;
        this.advPlaceRep = advPlaceRep;
        this.placeRep = placeRep;
        this.municipalityRep = municipalityRep;
        this.photoRep = photoRep;
        this.requestRep = requestRep;
        this.userRep = userRep;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void init() {
        insertData();
    }

    public void insertData() {

        municipalityRep.save(new Municipality("bestMunicipality"));
        municipalityRep.save(new Municipality("secondMunicipality"));

        placeRep.save(new AdvPlace("owner", "address", municipalityRep.findOne(1L)));

        constructionRep.save(new AdvConstruction(advPlaceRep.findOne(1L), "owner", 1, "type",
                "status", new Date()));

        userRep.save(new UserBuilder().withLogin("edit").withPassword(passwordEncoder
                .encode("edit")).withName("BestName").withSurname("BestSurname").withPatronymic("SuperPatron")
                .withMunicipality(Collections.singletonList(municipalityRep.findOne(1L))).withRole(Roles.EDITOR).build());

        userRep.save(new UserBuilder().withLogin("user").withPassword(passwordEncoder
                .encode("user")).withName("BestName").withSurname("BestSurname").withPatronymic("SuperPatron")
                .withMunicipality(municipalityRep.findAll()).withRole(Roles.USER).build());

        userRep.save(new UserBuilder().withLogin("admin").withPassword(passwordEncoder
                .encode("admin")).withName("name").withSurname("sur").withPatronymic("patron")
                .withMunicipality(municipalityRep.findAll()).withRole(Roles.ADMIN).build());

        Photo photo = new Photo(null, "name");
        photoRep.save(photo);

        Request request = requestRep.save(new RequestBuilder().withDate(new Date()).withApplicant(userRep.findOne(1L)).
                withStatus("BestStatus").withAdvPlace(placeRep.findOne(1L)).
                withAdvConstruction(constructionRep.findOne(1L)).withHandler(userRep.findOne(1L)).
                withDateProcessed(new Date()).withVersion(1).withReason("kek").withActuality("best").
                withPhoto(photo).build());

        photo.setRequest(request);

    }

}

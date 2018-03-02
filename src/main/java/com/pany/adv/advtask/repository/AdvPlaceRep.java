package com.pany.adv.advtask.repository;

import com.pany.adv.advtask.domain.AdvPlace;
import com.pany.adv.advtask.domain.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvPlaceRep extends JpaRepository<AdvPlace, Long> {

    AdvPlace findByOwner(String owner);

    AdvPlace findByMunicipality(Municipality municipality);

    AdvPlace findByAddress(String address);

}

package com.pany.adv.advtask.repository;

import com.pany.adv.advtask.domain.AdvConstruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface AdvConstructionRep extends JpaRepository<AdvConstruction, Long> {

    AdvConstruction findByAdvPlace_Id(long id);

    AdvConstruction findByStatus(String status);

    AdvConstruction findByOwner(String owner);

    AdvConstruction findByDate(Date date);

    AdvConstruction findByNumber(int number);

    AdvConstruction findByType(String type);

}

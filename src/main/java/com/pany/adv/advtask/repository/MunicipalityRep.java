package com.pany.adv.advtask.repository;

import com.pany.adv.advtask.domain.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipalityRep extends JpaRepository<Municipality, Long> {
    Municipality findByName(String name);
}

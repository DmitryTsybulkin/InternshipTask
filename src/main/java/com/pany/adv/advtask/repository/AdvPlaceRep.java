package com.pany.adv.advtask.repository;

import com.pany.adv.advtask.domain.AdvPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvPlaceRep extends JpaRepository<AdvPlace, Long> {
}

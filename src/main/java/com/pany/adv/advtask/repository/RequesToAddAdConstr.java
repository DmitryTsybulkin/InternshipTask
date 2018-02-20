package com.pany.adv.advtask.repository;

import com.pany.adv.advtask.domain.ConstructionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequesToAddAdConstr extends JpaRepository<ConstructionRequest, Long> {
}

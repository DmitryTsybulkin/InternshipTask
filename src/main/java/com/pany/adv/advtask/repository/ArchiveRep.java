package com.pany.adv.advtask.repository;

import com.pany.adv.advtask.domain.RequestsArchive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchiveRep extends JpaRepository<RequestsArchive, Long> {
}

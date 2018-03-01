package com.pany.adv.advtask.repository;

import com.pany.adv.advtask.domain.Archive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveRep extends JpaRepository<Archive, Long> {
}

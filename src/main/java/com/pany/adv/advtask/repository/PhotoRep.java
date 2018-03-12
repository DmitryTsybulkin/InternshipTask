package com.pany.adv.advtask.repository;

import com.pany.adv.advtask.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRep extends JpaRepository<Photo, Long> {
    boolean findByAddress(String address);
}

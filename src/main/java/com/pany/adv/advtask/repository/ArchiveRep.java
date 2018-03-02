package com.pany.adv.advtask.repository;

import com.pany.adv.advtask.domain.Archive;
import com.pany.adv.advtask.domain.Archive;
import com.pany.adv.advtask.domain.Request;
import com.pany.adv.advtask.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ArchiveRep extends JpaRepository<Archive, Long> {

    Archive findByRequestId(Request requestId);

    List<Archive> findByHandler(User handler);

    List<Archive> findByApplicant(User applicant);

    List<Archive> findByDateProcessed(Date date);

    List<Archive> findByActuality(String actuality);

    List<Archive> findByAdvConstruction_Id(long id);

    List<Archive> findByAdvPlace_Id(long id);

    List<Archive> findByDate(Date date);
    
}

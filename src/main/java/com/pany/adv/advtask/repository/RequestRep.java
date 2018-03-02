package com.pany.adv.advtask.repository;

import com.pany.adv.advtask.domain.Photo;
import com.pany.adv.advtask.domain.Request;
import com.pany.adv.advtask.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RequestRep extends JpaRepository<Request, Long> {

    List<Request> findByStatus(String status);

    List<Request> findByHandler(User handler);

    List<Request> findByApplicant(User applicant);

    List<Request> findByDateProcessed(Date date);

    List<Request> findByActuality(String actuality);

    List<Request> findByAdvConstruction_Id(long id);

    List<Request> findByAdvPlace_Id(long id);

    List<Request> findByDate(Date date);

    List<Request> findByPhoto(Photo photo);

}

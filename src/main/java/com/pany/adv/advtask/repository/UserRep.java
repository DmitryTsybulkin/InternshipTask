package com.pany.adv.advtask.repository;

import com.pany.adv.advtask.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRep extends JpaRepository<User, Long> {
    boolean findByLogin(String login);
}

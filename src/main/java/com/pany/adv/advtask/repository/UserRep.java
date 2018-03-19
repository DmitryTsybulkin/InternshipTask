package com.pany.adv.advtask.repository;

import com.pany.adv.advtask.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRep extends JpaRepository<User, Long> {
    long countUsersByLogin(String login);
    Optional<User> findUserByLogin(String login);
    Optional<User> findUserById(long id);
}

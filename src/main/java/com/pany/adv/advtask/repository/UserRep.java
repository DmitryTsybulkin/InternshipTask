package com.pany.adv.advtask.repository;

import com.pany.adv.advtask.domain.Municipality;
import com.pany.adv.advtask.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRep extends JpaRepository<User, Long> {

    User findUserByLogin(String login);

    User findByName(String name);

    User findBySurname(String surname);

    User findByEditorIsTrue();

    User findByMunicipalityContains(List<Municipality> municipality);

}

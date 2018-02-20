package com.pany.adv.advtask.repository;

import com.pany.adv.advtask.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{

//    List<User> findUserById(int id);
//
//    @Query("select us from User where us.name like 'fas'")
//    List<User> findByCustomQuery(String word);



}

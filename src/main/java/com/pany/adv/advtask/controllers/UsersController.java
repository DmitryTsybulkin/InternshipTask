package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.User;
import com.pany.adv.advtask.dto.UserDTO;
import com.pany.adv.advtask.repository.UserRep;
import com.pany.adv.advtask.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UsersController {

    private final UserService userService;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/users")
    public UserDTO createUser(@RequestBody User user) {
        userService.createUser(user);
        return userToDTO(user);
    }

    @GetMapping(value = "/users")
    public void showUsers() {
        List<User> users = userService.findAll();                   // TODO
    }

    @GetMapping(value = "/users/{id}")
    public UserDTO getUserById(@PathVariable("id") long id) {
        User user = userService.findById(id);
        return userToDTO(user);
    }

    @PutMapping(value = "/users/{id}")
    public UserDTO updateUser(@RequestBody User user, @RequestParam long id) {
        userService.updateUser(id, user);
        user.setId(id);
        return userToDTO(user);
    }

    @DeleteMapping(value = "/users/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    private UserDTO userToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setAdmin(user.isAdmin());
        userDTO.setEditor(user.isEditor());
        userDTO.setLogin(user.getLogin());
        userDTO.setMunicipalities(user.getMunicipality());
        userDTO.setName(user.getName());
        userDTO.setPatronymic(user.getPatronymic());
        userDTO.setSurname(user.getSurname());
        return userDTO;
    }

}

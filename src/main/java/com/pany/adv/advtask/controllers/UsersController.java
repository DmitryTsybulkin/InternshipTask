package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.User;
import com.pany.adv.advtask.dto.UserDTO;
import com.pany.adv.advtask.service.convertors.UserDTOConverter;
import com.pany.adv.advtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsersController {

    private final UserService userService;

    private final UserDTOConverter userDtoConverter;

    @Autowired
    public UsersController(UserService userService, UserDTOConverter userDtoConverter) {
        this.userService = userService;
        this.userDtoConverter = userDtoConverter;
    }

    @PostMapping(value = "/users")
    public UserDTO createUser(@RequestBody User user) {
        userService.createUser(user);
        return userDtoConverter.toDto(user);
    }

    @GetMapping(value = "/users")
    public List<UserDTO> showUsers() {
        List<User> users = userService.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(userDtoConverter.toDto(user));
        }
        return userDTOS;
    }

    @GetMapping(value = "/users/{id}")
    public UserDTO showUser(@PathVariable(value = "id") long id) {
        return userDtoConverter.toDto(userService.findById(id));
    }

    @PutMapping(value = "/users/{id}")
    public UserDTO updateUser(@PathVariable long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return userDtoConverter.toDto(user);
    }

    @DeleteMapping(value = "/users/{id}")
    public void deleteUser(@PathVariable(value = "id") long id) {
        userService.deleteUser(id);
    }

}

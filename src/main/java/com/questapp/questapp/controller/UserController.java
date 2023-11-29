package com.questapp.questapp.controller;

import com.questapp.questapp.entities.User;
import com.questapp.questapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<User> getAllUser(){
        return   userService.getAllUser();
    }
    @PostMapping
    public User createUser(@RequestBody User newUser){
        return userService.createUser(newUser);
    }
    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        return    userService.getOneUSer(userId);

    }
    @PutMapping("/{userId}")
    public User updeteOneUser(@PathVariable Long userId,@RequestBody User newUser){
        return userService.updateOneUser(userId,newUser);
    }
    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
         userService.deteleOneUser(userId);
    }
}

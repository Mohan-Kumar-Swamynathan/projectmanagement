package com.project.management.projectmanagement.controller;

import com.project.management.projectmanagement.biz.UserService;
import com.project.management.projectmanagement.model.CommonResponseWrapper;
import com.project.management.projectmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/option")
    public String index() {
        return "Service is up";
    }

    @PostMapping("/create")
    public CommonResponseWrapper createUser(@Validated @RequestBody User user) {
        return userService.createOrUpdateUser(user);
    }

    @PutMapping("/update")
    public CommonResponseWrapper updateUser(@Validated @RequestBody User user) {
        return userService.createOrUpdateUser(user);
    }

    @GetMapping("/{id}")
    public CommonResponseWrapper<User> getUser(@PathVariable(value = "id") long id) {
        return userService.fetchUserById(id);
    }

    @GetMapping("/delete/{id}")
    public CommonResponseWrapper<User> deleteUser(@PathVariable(value = "id") long id) {
        return userService.deleteById(id);
    }

}


package com.project.management.projectmanagement.controller;

import com.project.management.projectmanagement.UserThreadLocale;
import com.project.management.projectmanagement.biz.UserRoleValidatorBiz;
import com.project.management.projectmanagement.biz.UserService;
import com.project.management.projectmanagement.model.CommonResponseWrapper;
import com.project.management.projectmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRoleValidatorBiz userRoleValidatorBiz;

    @Autowired
    private UserService userService;

    @GetMapping("/option")
    public String index() {
        return "Service is up";
    }

    // only admin can create

    @PostMapping("/create")
    public CommonResponseWrapper createUser(@Validated @RequestBody User user) {
        if (!userRoleValidatorBiz.isAdmin(UserThreadLocale.getUserRole())) {
            return new CommonResponseWrapper(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
        return userService.createOrUpdateUser(user);
    }
    // only admin can update

    @PutMapping("/update")
    public CommonResponseWrapper updateUser(@Validated @RequestBody User user) {
        if (!userRoleValidatorBiz.isAdmin(UserThreadLocale.getUserRole())) {
            return new CommonResponseWrapper(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
        return userService.createOrUpdateUser(user);
    }

    @GetMapping("/{id}")
    public CommonResponseWrapper<User> getUser(@PathVariable(value = "id") long id) {

        return userService.fetchUserById(id);
    }
    // only admin can delete

    @GetMapping("/delete/{id}")
    public CommonResponseWrapper<User> deleteUser(@PathVariable(value = "id") long id) {
        if (!userRoleValidatorBiz.isAdmin(UserThreadLocale.getUserRole())) {
            return new CommonResponseWrapper(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
        return userService.deleteById(id);
    }

    @GetMapping("/login/{id}")
    public CommonResponseWrapper<User> login(@PathVariable(value = "id") long id) {
        return userService.login(id);
    }

}


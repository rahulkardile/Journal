package com.storyin.Journal.controller;

import com.storyin.Journal.model.Response;
import com.storyin.Journal.model.Users;
import com.storyin.Journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UAMController {

    @Autowired
    private Response response;

    @Autowired
    private UserService service;

    @GetMapping("/health")
    public ResponseEntity<?> healthCheck() {
        response.setMessage("Everything is okay here.");
        response.setStatusCode(200);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/user/create")
    public ResponseEntity<?> createUser(@RequestBody Users user) {
        Users res = service.createUsers(user);
        response.setMessage("Welcome " + user.getFirstname() + "!");
        response.setStatusCode(201);
        response.setSuccess(true);
        response.setData(res);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        return service.loginUser(user);
    }

    @GetMapping("/user/all")
    public ResponseEntity<?> GetAll(){
        response.setSuccess(true);
        response.setMessage("Successfully fetched all users!");
        response.setData(service.findAll());
        response.setStatusCode(200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    };

}


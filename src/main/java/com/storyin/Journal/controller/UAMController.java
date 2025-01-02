package com.storyin.Journal.controller;

import com.storyin.Journal.model.Response;
import com.storyin.Journal.model.Users;
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

    private Map<String, Users> users = new HashMap<>();

    UAMController(){
        Users sampleUser = new Users(
                "rahulkardile",
                "Rahul",
                "Kardile",
                "rahulkardile@example.com",
                "r@123",  // Replace with actual hashed password
                Users.Role.ADMIN,
                null,
                true,
                null,
                null,
                "123-456-7890"
        );
        users.put(sampleUser.getUsername(), sampleUser);
    }

    @GetMapping("/health")
    public ResponseEntity<?> healthCheck() {
        response.setMessage("Everything is okay here.");
        response.setStatusCode(200);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/user/create")
    public ResponseEntity<?> createUser(@RequestBody Users user) {
        users.put(user.getUsername(), user);
        response.setMessage("Welcome " + user.getFirstname() + "!");
        response.setStatusCode(201);
        response.setSuccess(true);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        Response response = new Response();

        if (user == null) {
            response.setMessage("User not found.");
            response.setStatusCode(404);
            response.setSuccess(false);
            return ResponseEntity.status(404).body(response);
        }

        Users users1 = users.get(user.getUsername());

        if (users1 == null || !user.getPassword().equals(users1.getPassword())) {
            response.setMessage("User authentication failed.");
            response.setStatusCode(403);
            response.setSuccess(false);
            return ResponseEntity.status(403).body(response);
        }

        if (!users1.isActive()) {
            response.setMessage("User account is not active.");
            response.setStatusCode(403);
            response.setSuccess(false);
            return ResponseEntity.status(403).body(response);
        }

        ResponseCookie cookie = ResponseCookie.from("userSession", user.getUsername())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(24 * 60 * 60) // 1 day
                .build();

        response.setMessage("Login successful.");
        response.setStatusCode(200);
        response.setSuccess(true);

        return ResponseEntity.ok()
                .header("Set-Cookie", cookie.toString())
                .body(response);
    }

}


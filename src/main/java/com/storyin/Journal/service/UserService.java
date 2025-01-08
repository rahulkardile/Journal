package com.storyin.Journal.service;

import com.storyin.Journal.model.Response;
import com.storyin.Journal.model.Users;
import com.storyin.Journal.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private Response response;

    public Users createUsers(Users users) {
        return repo.save(users);
    }

    public List<Users> findAll() {
        return repo.findAll();
    }

    public ResponseEntity<?> loginUser(Users user) {

        if (user == null) {
            response.setMessage("BAD REQUEST!");
            response.setStatusCode(404);
            response.setSuccess(false);
            return ResponseEntity.status(404).body(response);
        }

        Optional<Users> loginUser = repo.findById(user.getUsername());

        if (!user.getPassword().equals(loginUser.get().getUsername())) {
            response.setMessage("User authentication failed.");
            response.setStatusCode(403);
            response.setSuccess(false);
            return ResponseEntity.status(403).body(response);
        }

        if (!loginUser.get().isActive()) {
            response.setMessage("User account is not active.");
            response.setStatusCode(403);
            response.setSuccess(false);
            return ResponseEntity.status(403).body(response);
        }

        response.setMessage("Welcome Back " + loginUser.get().getUsername());
        response.setData(loginUser.get());
        response.setStatusCode(200);
        response.setSuccess(true);

        ResponseCookie cookie = ResponseCookie.from("userSession", user.getUsername())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(24 * 60 * 60) // 1 day
                .build();

        return  ResponseEntity.ok()
                .header("Set-Cookie", cookie.toString())
                .body(response);
    }

};


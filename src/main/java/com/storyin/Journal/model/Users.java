package com.storyin.Journal.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Users {

    @MongoId
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
    private LocalDate dateOfBirth;
    private boolean isActive;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String phoneNumber;

    public enum Role {
        ADMIN,
        USER,
        MODERATOR,
        GUEST
    }

}

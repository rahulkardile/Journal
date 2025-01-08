package com.storyin.Journal.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class Users {

    @Id
    @NotBlank(message = "Username is required.")
    private String username;

    @NotBlank(message = "First name is required.")
    private String firstname;

    @NotBlank(message = "Last name is required.")
    private String lastname;

    @NotBlank(message = "Email is required.")
    @Indexed(unique = true)
    private String email;

    @NotBlank(message = "Password is required.")
    private String password;

    private Role role;

    private LocalDate dateOfBirth;

    private boolean isActive;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    @NotBlank(message = "Phone number is required.")
    @Indexed(unique = true)
    private String phoneNumber;

    public enum Role {
        ADMIN,
        USER,
        MODERATOR,
        GUEST
    }
}

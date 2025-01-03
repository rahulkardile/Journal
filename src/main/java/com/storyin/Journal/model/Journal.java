package com.storyin.Journal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Journal {
    private int id;
    private String title;
    private String description;
    private String createdBy;
}

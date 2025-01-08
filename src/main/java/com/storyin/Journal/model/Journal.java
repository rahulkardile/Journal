package com.storyin.Journal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "journal")
public class Journal {

    @Id
    private String id;
    private String title;
    private String description;
    private String createdBy;
}

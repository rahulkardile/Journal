package com.storyin.Journal.controller;

import com.storyin.Journal.model.Journal;
import com.storyin.Journal.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/journal")
public class JournalController {

    private Map<Integer, Journal> journalDB = new HashMap<>();
    @Autowired
    private Response response;

    JournalController(){
        Journal journal = new Journal(1, "Need to buy milk!", "Go to shop and purchase the milk and after that return to home.", "Rahul Kardile");
        journalDB.put(journal.getId(), journal);
    }

    @PostMapping
    public ResponseEntity<?> createJournal(@RequestBody Journal journal){

        if (journalDB.containsKey(journal.getId())) {
            response.setMessage("Id already exists!");
            response.setSuccess(false);
            response.setStatusCode(409);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        journalDB.put(journal.getId(), journal);

        response.setMessage("Journal created successfully!");
        response.setSuccess(true);
        response.setStatusCode(201);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    };

}

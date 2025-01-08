package com.storyin.Journal.controller;

import com.storyin.Journal.model.Journal;
import com.storyin.Journal.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/journal")
public class JournalController {

    private Map<String, Journal> journalDB = new HashMap<>();
    @Autowired
    private Response response;

    JournalController() {
        Journal journal = new Journal("1", "Need to buy milk!", "Go to shop and purchase the milk and after that return to home.", "Rahul Kardile");
        journalDB.put(journal.getId(), journal);
    }

    @PostMapping
    public ResponseEntity<?> createJournal(@RequestBody Journal journal) {

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
    }

    ;

    @GetMapping("/{id}")
    public ResponseEntity getById(@RequestParam int id) {
        if (journalDB.containsKey(id)) {
            response.setMessage("Entry Find Successfully!");
            response.setStatusCode(200);
            response.setSuccess(true);
            response.setData(journalDB.get(id));
            return new ResponseEntity(response, HttpStatus.OK);
        }

        response.setMessage("Could not found the the journal!");
        response.setStatusCode(404);
        response.setSuccess(false);
        response.setData(null);
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<Journal> journals = new ArrayList<>();

        for (Map.Entry<String, Journal> entry : journalDB.entrySet()) {
            journals.add(entry.getValue());
        }

        return new ResponseEntity<>(journals, HttpStatus.OK);
    };

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateJournal(@RequestBody Journal journal, @PathVariable int id) {

        if (journalDB.containsKey(id)) {
            journalDB.put(journal.getId(), journal);
            response.setMessage("Journal updated successfully!");
            response.setStatusCode(201);
            response.setSuccess(true);
            response.setData(journalDB.get(id));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        response.setMessage("Could not found the the journal!");
        response.setStatusCode(404);
        response.setSuccess(false);
        response.setData(null);
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJournal(@PathVariable int id) {

        if (journalDB.containsKey(id)) {
            response.setData(journalDB.get(id));
            journalDB.remove(id);
            response.setMessage("Journal deleted successfully!");
            response.setStatusCode(201);
            response.setSuccess(true);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        response.setMessage("Could not found the the journal!");
        response.setStatusCode(404);
        response.setSuccess(false);
        response.setData(null);
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }
}


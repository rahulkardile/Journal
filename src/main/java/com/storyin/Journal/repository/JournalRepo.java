package com.storyin.Journal.repository;

import com.storyin.Journal.model.Journal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepo extends MongoRepository<Journal, String> {

}

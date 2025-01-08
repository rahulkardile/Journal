package com.storyin.Journal.repository;

import com.storyin.Journal.model.Journal;
import com.storyin.Journal.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<Users, String> {
}

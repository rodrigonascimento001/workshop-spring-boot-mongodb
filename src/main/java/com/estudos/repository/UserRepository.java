package com.estudos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.estudos.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}

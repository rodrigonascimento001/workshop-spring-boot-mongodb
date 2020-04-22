package com.estudos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.estudos.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}

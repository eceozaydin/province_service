package com.il.il_service.repository;

import com.il.il_service.model.Il;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IlRepository extends MongoRepository<Il,String> {

    List<Il> findAllByName(String name);
    Optional<Il> findByName(String name);
}

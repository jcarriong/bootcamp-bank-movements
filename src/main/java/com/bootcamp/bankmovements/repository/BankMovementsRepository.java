package com.bootcamp.bankmovements.repository;

import com.bootcamp.bankmovements.model.BankMovements;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankMovementsRepository extends ReactiveMongoRepository<BankMovements, String> {
}

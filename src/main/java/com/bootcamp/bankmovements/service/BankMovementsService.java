package com.bootcamp.bankmovements.service;

import com.bootcamp.bankmovements.model.BankMovements;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankMovementsService {
    Flux<BankMovements> findAll();

    Mono<BankMovements> findById(String id);

    Mono<BankMovements> save(BankMovements bankMovements);
}

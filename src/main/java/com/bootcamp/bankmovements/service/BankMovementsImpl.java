package com.bootcamp.bankmovements.service;

import com.bootcamp.bankmovements.model.BankMovements;
import com.bootcamp.bankmovements.repository.BankMovementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BankMovementsImpl implements BankMovementsService {
    @Autowired
    BankMovementsRepository bankMovementsRepository;

    @Override
    public Flux<BankMovements> findAll() {
        return bankMovementsRepository.findAll();
    }

    @Override
    public Mono<BankMovements> findById(String id) {
        return bankMovementsRepository.findById(id);
    }

    @Override
    public Mono<BankMovements> save(BankMovements bankMovements) {
        return bankMovementsRepository.save(bankMovements);
    }
}

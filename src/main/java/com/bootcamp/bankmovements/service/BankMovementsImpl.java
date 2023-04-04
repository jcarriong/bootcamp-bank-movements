package com.bootcamp.bankmovements.service;

import com.bootcamp.bankmovements.model.BankMovements;
import com.bootcamp.bankmovements.repository.BankMovementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

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
        return bankMovementsRepository.findById(id)
                .switchIfEmpty(Mono.error(() -> new RuntimeException("Datos de movimiento bancario no encontrado")));
    }

    @Override
    public Mono<BankMovements> save(BankMovements bankMovements) {
        return bankMovementsRepository.save(bankMovements);
    }

    @Override
    public Mono<BankMovements> updateMovement(BankMovements bankMovements, String idMovement) {
        return bankMovementsRepository.findById(idMovement)
                .flatMap(currentMovement -> {
                    currentMovement.setCurrencyType(bankMovements.getCurrencyType()); //actualizar el tipo de moneda a ver
                    currentMovement.setUpdateDatetime(LocalDateTime.now());
                    return bankMovementsRepository.save(currentMovement);
                });
    }

    @Override
    public Mono<BankMovements> deleteMovement(String idMovement) {
        return bankMovementsRepository.findById(idMovement)
                .flatMap(existingMovement -> bankMovementsRepository.delete(existingMovement)
                        .then(Mono.just(existingMovement)));
    }
}

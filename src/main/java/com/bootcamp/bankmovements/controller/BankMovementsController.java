package com.bootcamp.bankmovements.controller;

import com.bootcamp.bankmovements.model.BankMovements;
import com.bootcamp.bankmovements.service.BankMovementsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1")
@Slf4j
public class BankMovementsController {

    @Autowired
    private BankMovementsService bankMovementsService;

    @GetMapping("/findAll")
    public Flux<BankMovements> findAll() {
        log.info("All bank movements were consulted");
        return bankMovementsService.findAll()
                .doOnNext(bankMovements -> bankMovements.toString());
    }

    @GetMapping("/findById/{id}")
    public Mono<BankMovements> findById(@PathVariable("id") String id) {
        log.info("Bank movement consulted by id " + id);
        return bankMovementsService.findById(id);

    }

    @PostMapping("/save")
    public Mono<ResponseEntity<BankMovements>> save(@RequestBody BankMovements bankMovements) {
        log.info("A bank movement was created");
        bankMovements.setCreationDatetime(LocalDateTime.now());
        return bankMovementsService.save(bankMovements)
                .map(bm -> new ResponseEntity<>(bm, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.CONFLICT));
    }
}

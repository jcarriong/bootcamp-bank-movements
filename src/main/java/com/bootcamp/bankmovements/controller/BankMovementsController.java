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

    /**
     * Consultar todos los movimientos bancarios existentes
     **/
    @GetMapping("/findAll")
    public Flux<BankMovements> findAll() {
        log.info("All bank movements were consulted");
        return bankMovementsService.findAll()
                .doOnNext(bankMovements -> bankMovements.toString());
    }

    /**
     * Consultar por Id de movimiento bancario
     **/
    @GetMapping("/findById/{id}")
    public Mono<BankMovements> findById(@PathVariable("id") String id) {
        log.info("Bank movement consulted by id " + id);
        return bankMovementsService.findById(id);

    }

    /**
     * Registrar un movimiento bancario
     **/
    @PostMapping("/saveMovement")
    public Mono<ResponseEntity<BankMovements>> save(@RequestBody BankMovements bankMovements) {
        log.info("A bank movement was created");
        bankMovements.setCreationDatetime(LocalDateTime.now());
        return bankMovementsService.save(bankMovements)
                .map(bm -> new ResponseEntity<>(bm, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    /**
     * Actualizar un tipo de movimiento bancario
     **/
    @PutMapping("/updateMovement/{idMovement}")
    public Mono<ResponseEntity<BankMovements>> update(@RequestBody BankMovements bankMovements,
                                                      @PathVariable("idMovement") String idMovement) {
        log.info("A bank movement was changed");
        return bankMovementsService.updateMovement(bankMovements, idMovement)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    /**
     * Eliminar un tipo de movimiento bancario
     **/
    @DeleteMapping("/deleteMovement/{idMovement}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("idMovement") String idMovement) {
        log.info("A bank movement was deleted");
        return bankMovementsService.deleteMovement(idMovement)
                .map(banMovement -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}

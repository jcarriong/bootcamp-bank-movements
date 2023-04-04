package com.bootcamp.bankmovements.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bankMovements")
public class BankMovements extends BaseAuditDto{

    @Id
    private String idMovement;
    @NonNull
    private String operations; //Operaciones: Transferir dinero, Pagar servicios, Pagar tarjetas de credito
    @NonNull
    private String movementType; //DEPOSITO, RETIRO, PAGO, TRAN.CTAS.PROP, TRAN.CTAS.TERC
    @NonNull
    private String sourceAccount; //numero de cuenta de origen (14 digits)
    @NonNull
    private String targetAccount; //numero de cuenta de destino (14 digits)
    private String currencyType; //tipo de moneda (S/. $/)
    private Float amount; //monto

}

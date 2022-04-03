package com.safra.open.cashless.model;

import com.safra.open.cashless.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tb_transaction")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Transaction
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long storeId;
    private Long userId;
    private String clientRfid;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private Status transactionStatus;
    private LocalDate date;
}
package com.example.demo.model;

import com.example.demo.enums.Status;
import lombok.*;

import javax.persistence.*;

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
}
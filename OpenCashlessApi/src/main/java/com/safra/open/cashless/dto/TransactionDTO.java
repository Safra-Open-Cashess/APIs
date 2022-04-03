package com.safra.open.cashless.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TransactionDTO
{
    @JsonProperty("partnerId")
    private Long storeId;
    private String clientRfid;
    private Double amount;
}

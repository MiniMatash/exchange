package com.minimatash.exchangetest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRequestDto {

  private Double amountFrom;

  private Double amountTo;

  private String currencyFrom;

  private String currencyTo;

  private String operationType;

}

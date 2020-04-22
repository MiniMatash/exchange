package com.minimatash.exchangetest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateDto {

  private Double rate;

  private String from;

  private String to;

}

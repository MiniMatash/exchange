
package com.minimatash.exchangetest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommissionDto {

  private Double commissionPt;

  private String from;

  private String to;

}

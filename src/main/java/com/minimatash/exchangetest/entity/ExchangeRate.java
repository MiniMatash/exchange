package com.minimatash.exchangetest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rates")
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRate {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "from_currency")
    private String from;

    @Column(name = "to_currency")
    private String to;

}

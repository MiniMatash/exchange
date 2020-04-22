package com.minimatash.exchangetest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "commission")
public class Commission {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "commission_pt")
    private Double commissionPt;

    @Column(name = "from_currency")
    private String from;

    @Column(name = "to_currency")
    private String to;

}

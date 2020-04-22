package com.minimatash.exchangetest.repository;

import com.minimatash.exchangetest.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    ExchangeRate getByFromAndTo(String from, String to);
}

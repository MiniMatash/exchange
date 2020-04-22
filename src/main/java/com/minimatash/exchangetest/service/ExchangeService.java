package com.minimatash.exchangetest.service;

import com.minimatash.exchangetest.dto.ExchangeRequestDto;
import com.minimatash.exchangetest.entity.Commission;
import com.minimatash.exchangetest.entity.ExchangeRate;
import com.minimatash.exchangetest.repository.CommissionRepository;
import com.minimatash.exchangetest.repository.ExchangeRateRepository;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {

    private CommissionRepository commissionRepository;
    private ExchangeRateRepository exchangeRateRepository;

    public ExchangeRequestDto exchange(ExchangeRequestDto exchangeRequestDto) {
        Commission commission = commissionRepository.getByFromAndTo(exchangeRequestDto.getCurrencyFrom(), exchangeRequestDto.getCurrencyTo());
        ExchangeRate rate = exchangeRateRepository.getByFromAndTo(exchangeRequestDto.getCurrencyFrom(), exchangeRequestDto.getCurrencyTo());

        if (exchangeRequestDto.getOperationType().equals("GET")) {
            exchangeRequestDto.setAmountFrom(Precision.round(exchangeRequestDto.getAmountTo() / (1 - commission.getCommissionPt()) / rate.getRate(), 2));
        } else if (exchangeRequestDto.getOperationType().equals("GIVE")) {
            exchangeRequestDto.setAmountTo(Precision.round(exchangeRequestDto.getAmountFrom() * (1 - commission.getCommissionPt()) * rate.getRate(), 2));
        }
        return exchangeRequestDto;
    }

    @Autowired
    public ExchangeService(CommissionRepository commissionRepository, ExchangeRateRepository exchangeRateRepository) {
        this.commissionRepository = commissionRepository;
        this.exchangeRateRepository = exchangeRateRepository;
    }
}

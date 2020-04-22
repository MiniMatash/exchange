package com.minimatash.exchangetest.service;

import com.minimatash.exchangetest.dto.ExchangeRateDto;
import com.minimatash.exchangetest.entity.ExchangeRate;
import com.minimatash.exchangetest.repository.ExchangeRateRepository;
import com.minimatash.exchangetest.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeRateService {

    private ExchangeRateRepository exchangeRateRepository;

    public List<ExchangeRateDto> getRates() throws InvocationTargetException, IllegalAccessException {

        List<ExchangeRateDto> exchangeRates = new ArrayList<>();

        for (ExchangeRate  rate : exchangeRateRepository.findAll()) {
            exchangeRates.add(DtoConverter.convert(new ExchangeRateDto(), rate));
        }

        return exchangeRates;
    }


    public Object setRate(ExchangeRateDto exchangeRateDto) throws InvocationTargetException, IllegalAccessException {
        ExchangeRate currentExchangeRate = exchangeRateRepository.getByFromAndTo(exchangeRateDto.getFrom(), exchangeRateDto.getTo());
        if(currentExchangeRate!=null) {
            currentExchangeRate.setRate(exchangeRateDto.getRate());
            return exchangeRateRepository.save(currentExchangeRate).getId() == null ?
                    new Error("exchange rate not set") : exchangeRateDto;
        } else {
            return exchangeRateRepository.save(DtoConverter.convert(new ExchangeRate(), exchangeRateDto)).getId() == null ?
                    new Error("exchange rate not set") : exchangeRateDto;
        }
    }

    @Autowired
    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }
}

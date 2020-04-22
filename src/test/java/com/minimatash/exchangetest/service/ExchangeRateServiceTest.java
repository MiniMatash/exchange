package com.minimatash.exchangetest.service;


import com.minimatash.exchangetest.dto.CommissionDto;
import com.minimatash.exchangetest.dto.ExchangeRateDto;
import com.minimatash.exchangetest.entity.Commission;
import com.minimatash.exchangetest.entity.ExchangeRate;
import com.minimatash.exchangetest.repository.ExchangeRateRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExchangeRateServiceTest {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @MockBean
    private ExchangeRateRepository exchangeRateRepository;


    @Before
    public void setUp() {
        ExchangeRate rate = new ExchangeRate(1L, 1.1, "EUR", "USD");
        ExchangeRate rateWithoutId = new ExchangeRate(1L, 1.1, "EUR", "USD");
        rateWithoutId.setId(null);

        Mockito.when(exchangeRateRepository.findAll())
                .thenReturn(Collections.singletonList(rate));

        Mockito.when(exchangeRateRepository.save(rateWithoutId)).thenReturn(rate);
    }


    @Test
    public void whenGetRates_thenReturnListWithSingleRate() throws InvocationTargetException, IllegalAccessException {
        ExchangeRateDto rateDto = new ExchangeRateDto( 1.1, "EUR", "USD");

        assertEquals(exchangeRateService.getRates().get(0), rateDto);
    }

    @Test
    public void whenSetRate_thenReturnRate() throws InvocationTargetException, IllegalAccessException {
        ExchangeRateDto rateDto = new ExchangeRateDto( 1.1, "EUR", "USD");

        assertEquals(exchangeRateService.setRate(rateDto), rateDto);
    }
}

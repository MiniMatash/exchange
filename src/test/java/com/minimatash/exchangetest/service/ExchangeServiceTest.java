package com.minimatash.exchangetest.service;

import com.minimatash.exchangetest.dto.CommissionDto;
import com.minimatash.exchangetest.dto.ExchangeRequestDto;
import com.minimatash.exchangetest.entity.Commission;
import com.minimatash.exchangetest.entity.ExchangeRate;
import com.minimatash.exchangetest.repository.CommissionRepository;
import com.minimatash.exchangetest.repository.ExchangeRateRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExchangeServiceTest {

    @Autowired
    private ExchangeService exchangeService;

    @MockBean
    private CommissionRepository commissionRepository;
    @MockBean
    private ExchangeRateRepository exchangeRateRepository;

    @Before
    public void setUp() {
        ExchangeRate rate = new ExchangeRate(1L, 1.1, "EUR", "USD");
        Commission commission = new Commission(1L, 0.05, "EUR", "USD");

        Mockito.when(exchangeRateRepository.getByFromAndTo(rate.getFrom(), rate.getTo()))
                .thenReturn(rate);

        Mockito.when(commissionRepository.getByFromAndTo(commission.getFrom(), commission.getTo()))
                .thenReturn(commission);

    }

    @Test
    public void whenExchangeFrom_thenReturnExchangeRequest() {
        ExchangeRequestDto exchangeTo = new ExchangeRequestDto(100.0, null, "EUR", "USD", "GIVE");
        ExchangeRequestDto exchangeResult = new ExchangeRequestDto(100.0, 104.5, "EUR", "USD", "GIVE");

        assertEquals(exchangeService.exchange(exchangeTo), exchangeResult);
    }

    @Test
    public void whenExchangeTo_thenReturnExchangeRequest() {
        ExchangeRequestDto exchangeTo = new ExchangeRequestDto(null, 104.5, "EUR", "USD", "GET");
        ExchangeRequestDto exchangeResult = new ExchangeRequestDto(100.0, 104.5, "EUR", "USD", "GET");

        assertEquals(exchangeService.exchange(exchangeTo), exchangeResult);
    }
}

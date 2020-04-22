package com.minimatash.exchangetest.service;

import com.minimatash.exchangetest.dto.CommissionDto;
import com.minimatash.exchangetest.entity.Commission;
import com.minimatash.exchangetest.repository.CommissionRepository;
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
public class CommissionServiceTest {

    @Autowired
    private CommissionService commissionService;

    @MockBean
    private CommissionRepository commissionRepository;

    @Before
    public void setUp() {
        Commission commission = new Commission(1L, 0.05, "USD", "EUR");
        Commission commissionWithoutId = new Commission();
        commissionWithoutId.setCommissionPt(0.05);
        commissionWithoutId.setFrom("USD");
        commissionWithoutId.setTo("EUR");

        Mockito.when(commissionRepository.findAll())
                .thenReturn(Collections.singletonList(commission));

        Mockito.when(commissionRepository.save(commissionWithoutId)).thenReturn(commission);
    }

    @Test
    public void whenGetCommissions_thenReturnListWithSingleCommission() throws InvocationTargetException, IllegalAccessException {
        CommissionDto commissionDto = new CommissionDto(0.05, "USD", "EUR");

        assertEquals(commissionService.getCommissions().get(0), commissionDto);
    }

    @Test
    public void whenSetCommissions_thenReturnCommission() throws InvocationTargetException, IllegalAccessException {
        CommissionDto commission = new CommissionDto(0.05, "USD", "EUR");

        assertEquals(commissionService.setCommission(commission), commission);
    }
}
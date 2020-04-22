package com.minimatash.exchangetest.controllers;

import com.minimatash.exchangetest.dto.CommissionDto;
import com.minimatash.exchangetest.dto.ErrorDto;
import com.minimatash.exchangetest.dto.ExchangeRateDto;
import com.minimatash.exchangetest.service.ExchangeRateService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("/api/exchange-rates")
@Api(value = "Commission", description = "Значение курсов обмена валют. Ползволяют устанавлаивать и получать список" +
        " курсов обмена валют для всех валютных пар. При установке курса обмена по любой из пар обратный курс должен" +
        " быть установлен автоматически.")
public class ExchangeRateController {

    private ExchangeRateService exchangeRateService;

    @ApiOperation(value = "Получить все курсы обмена валют")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", responseContainer = "List", response = ExchangeRateDto.class),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @GetMapping
    public List<ExchangeRateDto> getCommissions() throws InvocationTargetException, IllegalAccessException {
        return exchangeRateService.getRates();
    }

    @ApiOperation(value = "Запрос обмена валют", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ExchangeRateDto.class),
            @ApiResponse(code = 400, message = "Error", response = ErrorDto.class),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @PostMapping
    public Object setCommission(@ApiParam(
            name = "exchangeRate",
            type = "object",
            value = "exchangeRate",
            required = true)
                                    @RequestBody ExchangeRateDto exchangeRateDto) throws InvocationTargetException, IllegalAccessException {
        return exchangeRateService.setRate(exchangeRateDto);
    }

    @Autowired
    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }
}

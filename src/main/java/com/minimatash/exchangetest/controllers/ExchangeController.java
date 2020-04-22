package com.minimatash.exchangetest.controllers;

import com.minimatash.exchangetest.dto.ErrorDto;
import com.minimatash.exchangetest.dto.ExchangeRequestDto;
import com.minimatash.exchangetest.service.ExchangeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exchange")
@Api(value = "Commission", description = "Обмен валют. Позволяет получать информацию по суммам при прямом и обратном " +
        "обмене валют с учетом комисии. Пример прямого обмена: обменять 100 USD на EUR, в этом случае запрос должен " +
        "соержать объект вида: {\"amountFrom\": 100.00,\"currencyFrom\": \"USD\",\"currencyTo\": \"EUR\",\"operationType" +
        "\":\"GIVE\"}. В ответ должен вернуться полностью заполненый объект. Пример обратного обмена: узнать сколько " +
        "нужно USD для того чтобы получить в результате обмена 100 EUR, в этом случае запрос должен содержать объект " +
        "вида: {\"amountTo\": 100.00,\"currencyFrom\": \"USD\",\"currencyTo\": \"EUR\",\"operationType\":\"GET\"}")
public class ExchangeController {

    private ExchangeService exchangeService;

    @ApiOperation(value = "Запрос обмена валют")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ExchangeRequestDto.class),
            @ApiResponse(code = 400, message = "Error", response = ErrorDto.class),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @PostMapping
    public ExchangeRequestDto exchange(@ApiParam(
            name = "exchangeRequest",
            type = "object",
            value = "exchangeRequest",
            required = true) @RequestBody ExchangeRequestDto exchangeRequestDto) {
        return exchangeService.exchange(exchangeRequestDto);
    }

    @Autowired
    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

}

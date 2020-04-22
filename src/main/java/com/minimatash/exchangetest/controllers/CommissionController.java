package com.minimatash.exchangetest.controllers;

import com.minimatash.exchangetest.dto.CommissionDto;
import com.minimatash.exchangetest.dto.ErrorDto;
import com.minimatash.exchangetest.service.CommissionService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("/api/commissions")
@Api(value = "Commission", description = "Значения комиссий взимаемых при обмене валют. Значение процента комиссии можно задать " +
        "для кажой влаютной пары. Валидные значения в диапазоне от 0.00 до 100.00.")
public class CommissionController {

    private CommissionService commissionService;

    @ApiOperation(value = "Получить список установленных комиссий")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", responseContainer = "List", response = CommissionDto.class),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @GetMapping
    public List<CommissionDto> getCommissions() throws InvocationTargetException, IllegalAccessException {
        return commissionService.getCommissions();
    }

    @ApiOperation(value = "Установить значение комиссии для валютной пары", response = CommissionDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CommissionDto.class),
            @ApiResponse(code = 400, message = "Error", response = ErrorDto.class),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @PostMapping
    public Object setCommission(
            @ApiParam(
                    name = "commission",
                    type = "object",
                    value = "commission",
                    required = true)
                    @RequestBody CommissionDto commissionDto) throws InvocationTargetException, IllegalAccessException {
        return commissionService.setCommission(commissionDto);
    }

    @Autowired
    public CommissionController(CommissionService commissionService) {
        this.commissionService = commissionService;
    }
}

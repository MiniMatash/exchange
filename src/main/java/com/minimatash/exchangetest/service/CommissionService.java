package com.minimatash.exchangetest.service;

import com.minimatash.exchangetest.dto.CommissionDto;
import com.minimatash.exchangetest.entity.Commission;
import com.minimatash.exchangetest.repository.CommissionRepository;
import com.minimatash.exchangetest.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommissionService {

    private CommissionRepository commissionRepository;

    public List<CommissionDto> getCommissions() throws InvocationTargetException, IllegalAccessException {

        List<CommissionDto> commissions = new ArrayList<>();

        for (Commission commission : commissionRepository.findAll()) {
            commissions.add(DtoConverter.convert(new CommissionDto(), commission));
        }

        return commissions;
    }

    public Object setCommission(CommissionDto commissionDto) throws InvocationTargetException, IllegalAccessException {
        Commission currentCommission = commissionRepository.getByFromAndTo(commissionDto.getFrom(), commissionDto.getTo());
        if(currentCommission!=null) {
            currentCommission.setCommissionPt(commissionDto.getCommissionPt());
            return commissionRepository.save(currentCommission).getId() == null ?
                    new Error("commission not set") : commissionDto;
        } else {
            return commissionRepository.save(DtoConverter.convert(new Commission(), commissionDto)).getId()==null ?
                    "commission not set" : commissionDto;
        }
    }

    @Autowired
    public CommissionService(CommissionRepository commissionRepository) {
        this.commissionRepository = commissionRepository;
    }
}

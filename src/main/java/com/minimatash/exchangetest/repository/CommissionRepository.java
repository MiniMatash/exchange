package com.minimatash.exchangetest.repository;

import com.minimatash.exchangetest.entity.Commission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommissionRepository extends JpaRepository<Commission, Long> {

    Commission getByFromAndTo(String from, String to);

}

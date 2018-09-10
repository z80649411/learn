package com.dr.sell.dao;

import com.dr.sell.dateObj.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, Integer> {
}

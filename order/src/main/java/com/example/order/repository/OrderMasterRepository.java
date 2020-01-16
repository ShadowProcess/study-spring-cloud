package com.example.order.repository;

import com.example.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

//<实体类型,主键类型>
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

}

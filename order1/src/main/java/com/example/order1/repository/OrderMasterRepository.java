package com.example.order1.repository;

import com.example.order1.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

//<实体类型,主键类型>
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

}

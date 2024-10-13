package com.firefly.bankapp.dao;

import com.firefly.bankapp.entity.PaymentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentDao extends CrudRepository<PaymentEntity, Integer> {
    List<PaymentEntity> findAll();
}

package com.firefly.bankapp.dao;


import com.firefly.bankapp.entity.BillEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BillDao extends CrudRepository<BillEntity, String> {
    List<BillEntity> findAll();

    Optional<BillEntity> findByCode(String code);

    Optional<BillEntity> findByCodeAndCategory(String code, String category);
}

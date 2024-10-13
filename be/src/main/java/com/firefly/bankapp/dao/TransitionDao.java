package com.firefly.bankapp.dao;

import com.firefly.bankapp.entity.TransitionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransitionDao extends CrudRepository<TransitionEntity, Integer> {
    List<TransitionEntity> findByFromUser(String fromUser);
}

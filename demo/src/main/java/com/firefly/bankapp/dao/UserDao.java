package com.firefly.bankapp.dao;

import com.firefly.bankapp.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDao extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByCardNumber(String cardNumber);
}

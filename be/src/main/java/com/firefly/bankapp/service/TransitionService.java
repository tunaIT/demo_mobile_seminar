package com.firefly.bankapp.service;

import com.firefly.bankapp.dao.TransitionDao;
import com.firefly.bankapp.dao.UserDao;
import com.firefly.bankapp.dto.TransitionDto;
import com.firefly.bankapp.entity.TransitionEntity;
import com.firefly.bankapp.entity.UserEntity;
import com.firefly.bankapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransitionService {
    private final TransitionDao transitionDao;
    private final UserDao userDao;
    private final UserService userService;

    public void performTransfer(TransitionDto transitionDto) {
        UserEntity sender = userDao.findByCardNumber(transitionDto.getSender()).orElseThrow(
                () -> new IllegalArgumentException("Sender not found")
        );
        UserEntity receiver = userDao.findByCardNumber(transitionDto.getReceiver()).orElseThrow(
                () -> new IllegalArgumentException("Receiver not found")
        );

        if (sender.getEmail().equals(receiver.getEmail())) {
            throw new IllegalArgumentException("Sender and receiver cannot be the same");
        }

        if (transitionDto.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        if (sender.getBalance() < transitionDto.getAmount()) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        sender.setBalance(sender.getBalance() - transitionDto.getAmount());
        receiver.setBalance(receiver.getBalance() + transitionDto.getAmount());

        userDao.save(sender);
        userDao.save(receiver);

        TransitionEntity transitionEntity = new TransitionEntity();
        transitionEntity.setFromUser(sender.getCardNumber());
        transitionEntity.setToUser(receiver.getCardNumber());
        transitionEntity.setAmount(transitionDto.getAmount());
        transitionEntity.setFee(0.0);
        transitionEntity.setCreated(LocalDateTime.now());

        transitionDao.save(transitionEntity);
    }

    public List<TransitionEntity> getMyTransition(String token) {
        String myCardNumber = userService.getCardNumberfromToken(token);
        return transitionDao.findByFromUser(myCardNumber).stream().sorted(
                (t1, t2) -> t2.getCreated().compareTo(t1.getCreated())
        ).toList();
    }
}

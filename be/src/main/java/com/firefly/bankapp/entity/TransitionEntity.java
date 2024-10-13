package com.firefly.bankapp.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transition")
@Data
@NoArgsConstructor
public class TransitionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transition_id")
    private Integer id;

    @Column(name = "from_user")
    private String fromUser;

    @Column(name = "to_user")
    private String toUser;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "fee")
    private Double fee;

    @Column(name = "created_at")
    private LocalDateTime created;
}

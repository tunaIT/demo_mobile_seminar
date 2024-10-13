package com.firefly.bankapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "bill")
@Data
@NoArgsConstructor
public class BillEntity {
    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "category")
    private String category;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "from_date")
    private LocalDateTime fromDate;

    @Column(name = "to_date")
    private LocalDateTime toDate;

    @Column(name = "fee")
    private Double fee;

    @Column(name = "tax")
    private Double tax;
}

package com.firefly.bankapp.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class GetUserInfoDto implements Serializable {
    /** UID */
    @Serial
    private static final long serialVersionUID = 1L;

    /** Name of user */
    private String name;

    /** Email of user */
    private String email;

    /* Balance of user */
    private Double balance;

    /* Card number of user */
    private String cardNumber;

    /* Bank of user */
    private String bank;

    /** Created at */
    private LocalDateTime created;

    /** Updated at */
    private LocalDateTime updated;
}

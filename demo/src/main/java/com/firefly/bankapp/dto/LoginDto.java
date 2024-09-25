package com.firefly.bankapp.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class LoginDto implements Serializable {
    /** UID */
    @Serial
    private static final long serialVersionUID = 1L;

    /** Email of user */
    private String email;

    /** Password of user */
    private String password;

}

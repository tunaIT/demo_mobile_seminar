package com.firefly.bankapp.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class RegisterDto implements Serializable {

    /** UID */
    @Serial
    private static final long serialVersionUID = 1L;

    /** Name of user */
    private String name;

    /** Email of user */
    private String email;

    /** Password of user */
    private String password;

}

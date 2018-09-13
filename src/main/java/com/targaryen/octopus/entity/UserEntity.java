package com.targaryen.octopus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserEntity {
    private int userId;
    private String userName;
    private String userPassword;
    private String newUserPassword1;
    private String newUserPassword2;
    private int dpt;
    private String userRole;
}

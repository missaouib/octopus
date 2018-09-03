package com.targaryen.octopus.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVo {
    private int userId;
    private String userName;
    private String userPassword;
    private String userRole;
}

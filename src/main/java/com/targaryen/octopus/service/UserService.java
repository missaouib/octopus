package com.targaryen.octopus.service;

import com.targaryen.octopus.vo.UserVo;

public interface UserService {
    int saveUser(UserVo userVo);
    UserVo getUserByUserName(String userName);
    boolean checkPassword(UserVo userVo);
    int editPassword(UserVo userVo);
}

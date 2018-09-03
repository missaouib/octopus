package com.targaryen.octopus.service;

import com.targaryen.octopus.vo.UserVo;

public interface UserService {
    void saveUser(UserVo userVo);
    UserVo getUserByUserName(String userName);
}

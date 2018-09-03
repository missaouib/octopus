package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.DaoFactory;
import com.targaryen.octopus.dao.RoleDtoRepository;
import com.targaryen.octopus.dao.UserDtoRepository;
import com.targaryen.octopus.dto.RoleDto;
import com.targaryen.octopus.dto.UserDto;
import com.targaryen.octopus.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserDtoRepository userDtoRepository;
    private RoleDtoRepository roleDtoRepository;

    @Autowired
    public UserServiceImpl(DaoFactory daoFactory) {
        this.userDtoRepository = daoFactory.getUserDtoRepository();
        this.roleDtoRepository = daoFactory.getRoleDtoRepository();
    }

    /**
     *
     * @param userVo
     * "userId" and "userRole" fields are empty.
     */
    public void saveUser(UserVo userVo) {
        UserDto userDto = new UserDto();
        RoleDto roleDto = new RoleDto();

        userDto.setUserName(userVo.getUserName());
        userDto.setUserPassword(userVo.getUserPassword());
        userDtoRepository.save(userDto);

        roleDto.setRole("ROLE_USER");
        roleDto.setUser(userDto);
        roleDtoRepository.save(roleDto);
    }

    public UserVo getUserByUserName(String userName) {
        UserVo userVo = new UserVo();
        UserDto userDto = userDtoRepository.findUserDtoByUserName(userName);
        RoleDto roleDto = userDto.getRole();
        userVo.setUserId(userDto.getUserId());
        userVo.setUserName(userDto.getUserName());
        userVo.setUserPassword(userDto.getUserPassword());
        userVo.setUserRole(roleDto.getRole());

        return userVo;
    }

}

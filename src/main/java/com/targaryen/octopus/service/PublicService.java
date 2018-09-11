package com.targaryen.octopus.service;

import com.targaryen.octopus.vo.DepartmentVo;
import com.targaryen.octopus.vo.PostVo;
import javafx.geometry.Pos;

import java.util.List;

/**
 *  Created by Liu Mengyang on 2018/09/04
 */
public interface PublicService {
    List<PostVo> listPostsByStatus(int status);
    PostVo findPostById(int id);
    List<DepartmentVo> findAllDepartments();
}

package com.targaryen.octopus.service;

import com.targaryen.octopus.vo.PostVo;

import java.util.List;

/**
 *  Created by Liu Mengyang on 2018/09/04
 */
public interface PublicService {
    List<PostVo> listPostsByStatus(int status);
}

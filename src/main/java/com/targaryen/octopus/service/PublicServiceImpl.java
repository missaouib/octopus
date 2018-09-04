package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.DaoFactory;
import com.targaryen.octopus.dao.PostDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  Created by Liu Mengyang on 2018/09/04
 */
@Service
public class PublicServiceImpl {
    private PostDtoRepository postDtoRepository;

    @Autowired
    public PublicServiceImpl(DaoFactory daoFactory) {
        this.postDtoRepository = daoFactory.getPostDtoRepository();
    }
}

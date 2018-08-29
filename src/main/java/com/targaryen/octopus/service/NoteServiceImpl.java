package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.DaoFactory;
import com.targaryen.octopus.dao.NoteDao;
import com.targaryen.octopus.vo.NoteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoteServiceImpl implements NoteService {
    private final DaoFactory daoFactory;
    private final NoteDao noteDao;

    @Autowired
    public NoteServiceImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        this.noteDao = daoFactory.getNoteDao();
    }

    @Override
    public List<NoteVo> list() {
        return noteDao.list();
    }
}

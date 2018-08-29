package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.DaoFactory;
import com.targaryen.octopus.dao.NoteRepository;
import com.targaryen.octopus.vo.NoteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoteServiceImpl implements NoteService {
    private final DaoFactory daoFactory;
    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        this.noteRepository = daoFactory.getNoteRepository();
    }

    @Override
    public List<NoteVo> list() {
        return noteRepository.findAll();
    }
}

package com.targaryen.octopus.dao;

import com.targaryen.octopus.repository.NoteRepository;
import com.targaryen.octopus.vo.NoteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoteDaoImpl implements NoteDao {
    @Autowired
    NoteRepository noteRepository;

    @Override
    public List<NoteVo> list() {
        return noteRepository.findAll();
    }
}

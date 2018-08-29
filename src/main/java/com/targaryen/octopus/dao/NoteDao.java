package com.targaryen.octopus.dao;

import com.targaryen.octopus.vo.NoteVo;

import java.util.List;

public interface NoteDao {
    /**
     * List notes
     *
     */
    List<NoteVo> list();
}

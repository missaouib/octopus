package com.targaryen.octopus.service;

import com.targaryen.octopus.vo.NoteVo;

import java.util.List;

public interface NoteService {
    /**
     * List notes
     *
     */
    List<NoteVo> list();
}

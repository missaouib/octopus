package com.targaryen.octopus.controller;

import com.targaryen.octopus.service.NoteService;
import com.targaryen.octopus.service.ServiceFactory;
import com.targaryen.octopus.vo.NoteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private final ServiceFactory serviceFactory;
    private final NoteService noteService;

    @Autowired
    public NoteController(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
        this.noteService = serviceFactory.getNoteService();
    }

    // Get All Notes
    @GetMapping("/list")
    public List<NoteVo> getAllNotes() {
        return noteService.list();
    }

}

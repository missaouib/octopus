package com.targaryen.octopus.repository;

import com.targaryen.octopus.vo.NoteVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<NoteVo, Long> {

}

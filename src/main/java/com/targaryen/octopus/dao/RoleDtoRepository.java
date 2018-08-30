package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.RoleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  @author Liu Mengyang
 * */
@Repository
public interface RoleDtoRepository extends JpaRepository<RoleDto, Integer> {
}

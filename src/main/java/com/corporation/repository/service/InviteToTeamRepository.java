package com.corporation.repository.service;

import com.corporation.model.service.InviteToTeam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface InviteToTeamRepository extends CrudRepository<InviteToTeam, Long> {

    Optional<InviteToTeam> findByCode(String code);
}

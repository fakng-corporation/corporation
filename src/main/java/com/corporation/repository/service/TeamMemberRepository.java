package com.corporation.repository.service;

import com.corporation.model.service.InviteToTeam;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TeamMemberRepository extends CrudRepository<InviteToTeam, Long> {

    Optional<InviteToTeam> findByCode(String code);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO user_team (user_id, team_id) VALUES (:userId, :teamId)")
    void addToTeam(long userId, long teamId);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM user_team WHERE user_id = :userId AND team_id = :teamId")
    void removeFromTeam(long userId, long teamId);
}

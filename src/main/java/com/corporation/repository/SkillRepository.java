package com.corporation.repository;

import com.corporation.model.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@Repository
public interface SkillRepository extends CrudRepository<Skill, Long> {
    Optional<Skill> findSkillById(long skillId);
    Optional<Skill> findSkillByTitle(String title);
    List<Skill> findSkillsByUsers_Id(long userId);
}

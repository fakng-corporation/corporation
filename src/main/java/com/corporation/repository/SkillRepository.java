package com.corporation.repository;

import com.corporation.model.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@Repository
public interface SkillRepository extends CrudRepository<Skill, Long> {

    Optional<Skill> findSkillByTitle(String title);
}

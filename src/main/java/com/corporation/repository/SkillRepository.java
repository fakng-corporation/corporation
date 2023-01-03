package com.corporation.repository;

import com.corporation.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@Repository
public interface SkillRepository extends CrudRepository<Skill, Long> {
    List<Skill> findSkillByIdIn(List<Long> skillIdList);
    Optional<Skill> findSkillByTitle(String title);
    Page<Skill> findSkillsByUsersId(long userId, Pageable pageable);
}

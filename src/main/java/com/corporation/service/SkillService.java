package com.corporation.service;

import com.corporation.exception.NotUniqueSkillException;
import com.corporation.model.Skill;
import com.corporation.repository.SkillRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@Data
@Service
public class SkillService {

    private final SkillRepository skillRepository;

    private Optional<Skill> findSkillByTitle(String title) {
        return skillRepository.findSkillByTitle(title);
    }

    public Skill save(Skill skill) throws NotUniqueSkillException {
        Optional<Skill> optionalSkill = findSkillByTitle(skill.getTitle());

        if (optionalSkill.isPresent())
            throw new NotUniqueSkillException();

        return skillRepository.save(skill);
    }
}

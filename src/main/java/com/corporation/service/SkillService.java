package com.corporation.service;

import com.corporation.exception.NotUniqueEntityException;
import com.corporation.model.Skill;
import com.corporation.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@RequiredArgsConstructor
@Service
public class SkillService {

    private final SkillRepository skillRepository;

    private Optional<Skill> findSkillByTitle(String title) {
        return skillRepository.findSkillByTitle(title);
    }

    public Skill save(Skill skill) {
        findSkillByTitle(skill.getTitle()).ifPresent(s -> {
            throw new NotUniqueEntityException(
                    String.format("Skill with title %s already exists", s.getTitle())
            );
        });

        return skillRepository.save(skill);
    }
}

package com.corporation.service;

import com.corporation.exception.NotFoundEntityException;
import com.corporation.exception.NotUniqueEntityException;
import com.corporation.model.Skill;
import com.corporation.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@RequiredArgsConstructor
@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public Skill findSkillById(long skillId) {
        return skillRepository.findSkillById(skillId)
                .orElseThrow(() -> new NotFoundEntityException(
                        String.format("Skill with id %d does not exist.", skillId)
                ));
    }

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

    @Transactional
    public List<Skill> findSkillsByUserId(long userId) {
        return skillRepository.findSkillsByUsers_Id(userId);
    }
}

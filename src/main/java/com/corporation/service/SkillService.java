package com.corporation.service;

import com.corporation.exception.BusinessException;
import com.corporation.exception.NotUniqueEntityException;
import com.corporation.model.Skill;
import com.corporation.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public List<Skill> findSkillByIdIn(List<Long> skillIdList) {
        List<Skill> skills = skillRepository.findSkillByIdIn(skillIdList);

        if (skills.size() != skillIdList.size()) {
            throw new BusinessException("The number of retrieved skills does not match the number of queried.");
        }

        return skills;
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
    public Page<Skill> findSkillsByUserId(long userId, int page, int pageSize) {
        return skillRepository.findSkillsByUsersId(userId, PageRequest.of(page, pageSize));
    }
}

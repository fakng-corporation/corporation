package com.corporation.service;

import com.corporation.exception.BusinessException;
import com.corporation.exception.NotUniqueEntityException;
import com.corporation.model.Skill;
import com.corporation.repository.SkillRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
public class SkillServiceTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillService skillService;

    @Test
    public void shouldReturnCreatedSkill() {

        long id = 1;
        String title = "java";

        Skill skill = Skill.builder().title(title).build();
        Skill skillWithId = Skill.builder().id(id).title(title).build();

        Mockito.when(skillRepository.findSkillByTitle(skill.getTitle()))
                .thenReturn(Optional.empty());

        Mockito.when(skillRepository.save(skill)).thenReturn(skillWithId);

        Skill createdSkill = skillService.save(skill);

        Assertions.assertEquals(id, createdSkill.getId());
        Assertions.assertEquals(title, createdSkill.getTitle());
    }

    @Test
    public void shouldReturnSkillList() {
        long skillId = 1;
        List<Long> skillIdList = Collections.singletonList(skillId);
        String skillTitle = "skill";
        Skill skill = Skill.builder().id(skillId).title(skillTitle).build();
        Mockito.when(skillRepository.findSkillByIdIn(skillIdList))
                .thenReturn(Collections.singletonList(skill));

        List<Skill> returnedSkills = skillService.findSkillByIdIn(skillIdList);

        Assertions.assertEquals(1, returnedSkills.size());
        Assertions.assertEquals(skill.getId(), returnedSkills.get(0).getId());
        Assertions.assertEquals(skill.getTitle(), returnedSkills.get(0).getTitle());
    }

    @Test
    public void shouldThrowBusinessException() {
        long skillId = 100;
        List<Long> skillIdList = Collections.singletonList(skillId);
        Mockito.when(skillRepository.findSkillByIdIn(skillIdList))
                .thenReturn(Collections.emptyList());

        Assertions.assertThrows(BusinessException.class, () -> skillService.findSkillByIdIn(skillIdList));
    }

    @Test
    public void shouldThrowNotUniqueEntityException() {
        String title = "java";

        Skill skill = Skill.builder().title(title).build();

        Mockito.when(skillRepository.findSkillByTitle(skill.getTitle()))
                .thenReturn(Optional.of(skill));

        Assertions.assertThrows(NotUniqueEntityException.class, () -> skillService.save(skill));
    }
}

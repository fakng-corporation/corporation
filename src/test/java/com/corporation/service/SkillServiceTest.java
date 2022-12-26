package com.corporation.service;

import com.corporation.exception.EntityNotUniqueException;
import com.corporation.model.Skill;
import com.corporation.repository.SkillRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
    public void shouldThrowNotUniqueSkillException() {
        String title = "java";

        Skill skill = Skill.builder().title(title).build();

        Mockito.when(skillRepository.findSkillByTitle(skill.getTitle()))
                .thenReturn(Optional.of(skill));

        Assertions.assertThrows(EntityNotUniqueException.class, () -> skillService.save(skill));
    }
}

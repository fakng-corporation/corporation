package com.corporation.controller;

import com.corporation.dto.SkillDto;
import com.corporation.exception.NotUniqueSkillException;
import com.corporation.model.Skill;
import com.corporation.service.SkillService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
public class SkillControllerTest {

    @Mock
    private SkillService skillService;

    @InjectMocks
    private SkillController skillController;

    @Test
    public void shouldReturnCreatedSkillDtoAndStatus200() throws NotUniqueSkillException {

        long id = 1;
        String title = "java";

        Skill skill = Skill.builder().title(title).build();
        Skill skillWithId = Skill.builder().id(id).title(title).build();

        Mockito.when(skillService.save(skill)).thenReturn(skillWithId);

        ResponseEntity<SkillDto> responseEntity = skillController.createSkill(skill);

        SkillDto createdSkillDto = responseEntity.getBody();

        Assertions.assertEquals(id, createdSkillDto.getId());
        Assertions.assertEquals(title, createdSkillDto.getTitle());
    }

    @Test
    public void shouldThrowNotUniqueSkillException() throws NotUniqueSkillException {
        String title = "java";

        Skill skill = Skill.builder().title(title).build();

        Mockito.when(skillService.save(skill)).thenThrow(NotUniqueSkillException.class);

        Assertions.assertThrows(NotUniqueSkillException.class, () -> skillController.createSkill(skill));
    }
}

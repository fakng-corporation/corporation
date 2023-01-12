package com.corporation.controller;

import com.corporation.dto.SkillDto;
import com.corporation.exception.NotUniqueEntityException;
import com.corporation.mapper.SkillMapperImpl;
import com.corporation.model.Skill;
import com.corporation.service.SkillService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
public class SkillControllerTest {

    @Mock
    private SkillService skillService;

    @Spy
    private SkillMapperImpl skillMapper;

    @InjectMocks
    private SkillController skillController;

    @Test
    public void shouldReturnCreatedSkillDtoAndStatus200() {

        long id = 1;
        String title = "java";

        Skill skill = Skill.builder().title(title).build();
        SkillDto skillDto = skillMapper.toDto(skill);
        Skill skillWithId = Skill.builder().id(id).title(title).build();

        Mockito.when(skillService.save(skill)).thenReturn(skillWithId);

        ResponseEntity<SkillDto> responseEntity = skillController.createSkill(skillDto);

        Mockito.verify(skillMapper).toEntity(skillDto);

        Assertions.assertEquals(200, responseEntity.getStatusCode().value());

        SkillDto createdSkillDto = responseEntity.getBody();

        Assertions.assertEquals(id, createdSkillDto.getId());
        Assertions.assertEquals(title, createdSkillDto.getTitle());
    }

    @Test
    public void shouldThrowNotUniqueEntityException() {
        String title = "java";

        Skill skill = Skill.builder().title(title).build();
        SkillDto skillDto = skillMapper.toDto(skill);

        Mockito.when(skillService.save(skill)).thenThrow(NotUniqueEntityException.class);

        Assertions.assertThrows(NotUniqueEntityException.class, () -> skillController.createSkill(skillDto));
    }

    @Test
    public void shouldReturnSkillDtoPage() {
        long userId = 1;
        long skillId = 1;
        int page = 1;
        int pageSize = 10;
        String skillTitle = "skill";
        Skill skill = Skill.builder().id(skillId).title(skillTitle).build();
        Page<Skill> skillPage = new PageImpl<>(Collections.singletonList(skill));
        Page<SkillDto> skillDtoList = new PageImpl<>(Collections.singletonList(skillMapper.toDto(skill)));
        Mockito.when(skillService.findSkillsByUserId(userId, page, pageSize))
                .thenReturn(skillPage);

        Assertions.assertEquals(skillDtoList, skillController.getSkillsByUserId(userId, page, pageSize));
    }
}

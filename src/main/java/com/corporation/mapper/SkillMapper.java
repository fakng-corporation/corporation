package com.corporation.mapper;

import com.corporation.dto.SkillDto;
import com.corporation.model.Skill;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Bleschunov Dmitry
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface SkillMapper {
    SkillDto toDto(Skill skill);

    Skill toEntity(SkillDto skillDto);
}

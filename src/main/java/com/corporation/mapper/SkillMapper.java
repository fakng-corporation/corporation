package com.corporation.mapper;

import com.corporation.dto.SkillDto;
import com.corporation.model.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Bleschunov Dmitry
 */
@Mapper
public interface SkillMapper {

    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    SkillDto skillToSkillDto(Skill skill);
}

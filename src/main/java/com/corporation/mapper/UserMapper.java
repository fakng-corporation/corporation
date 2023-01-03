package com.corporation.mapper;

import com.corporation.dto.UserDto;
import com.corporation.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author Bleschunov Dmitry
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface UserMapper {

    @Mapping(target = "id", ignore = false)
    @Mapping(target = "nickname", ignore = false)
    @Mapping(target = "email", ignore = false)
    @Mapping(target = "aboutMe", ignore = false)
    @BeanMapping(ignoreByDefault = true)
    void updateEntity(UserDto userDto, @MappingTarget User user);
    UserDto toDto(User user);
    @Mapping(target = "id", ignore = false)
    @Mapping(target = "nickname", ignore = false)
    @Mapping(target = "email", ignore = false)
    @Mapping(target = "aboutMe", ignore = false)
    @BeanMapping(ignoreByDefault = true)

    User toEntity(UserDto userDto);
}

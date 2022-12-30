package com.corporation.mapper;

import com.corporation.dto.UserDto;
import com.corporation.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * @author Bleschunov Dmitry
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface UserMapper {

    void updateEntity(UserDto userDto, @MappingTarget User user);
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}

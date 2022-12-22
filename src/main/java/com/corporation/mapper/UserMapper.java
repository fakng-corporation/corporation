package com.corporation.mapper;

import com.corporation.dto.UserDto;
import com.corporation.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Bleschunov Dmitry
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface UserMapper {
    UserDto toDto(User user);
}

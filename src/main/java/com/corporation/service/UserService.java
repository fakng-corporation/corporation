package com.corporation.service;

import com.corporation.dto.UserDto;
import com.corporation.exception.UserNotFoundException;
import com.corporation.mapper.UserMapper;
import com.corporation.model.User;
import com.corporation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Transactional
    public User findById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with id %d does not exist.", id)
                ));
    }

    @Transactional
    public User update(UserDto userDto) {
        User user = findById(userDto.getId());
        userMapper.updateEntity(userDto, user);
        userRepository.save(user);
        return user;
    }
}

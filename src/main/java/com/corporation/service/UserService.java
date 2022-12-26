package com.corporation.service;

import com.corporation.dto.AuthDto;
import com.corporation.dto.UserDto;
import com.corporation.exception.EntityNotFoundException;
import com.corporation.exception.EntityNotUniqueException;
import com.corporation.mapper.UserMapper;
import com.corporation.model.Authority;
import com.corporation.model.User;
import com.corporation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("User with id %d does not exist.", id)
                ));
    }

    public Optional<User> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void register(AuthDto authDto) {
        findByNickname(authDto.getUsername())
                .ifPresent(user -> {
                    throw new EntityNotUniqueException(
                            String.format("User with username %s already exists.", user.getUsername())
                    );
                });

        findByEmail(authDto.getEmail())
                .ifPresent(user -> {
                    throw new EntityNotUniqueException(
                            String.format("User with email %s already exists.", user.getEmail())
                    );
                });

        String hashedPassword = passwordEncoder.encode(authDto.getPassword());

        User user = User
                .builder()
                .nickname(authDto.getUsername())
                .email(authDto.getEmail())
                .password(hashedPassword)
                .enabled(true)
                .authority(Authority.builder().id((short) 1).build())
                .build();

        userRepository.save(user);
    }

    @Transactional
    public User update(UserDto userDto) {
        User user = findById(userDto.getId());
        userMapper.updateEntity(userDto, user);
        userRepository.save(user);
        return user;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByNickname(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with nickname %s does not exist.", username)
                ));
    }
}

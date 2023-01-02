package com.corporation.service;

import com.corporation.dto.UserDto;
import com.corporation.exception.NotFoundEntityException;
import com.corporation.mapper.UserMapper;
import com.corporation.model.User;
import com.corporation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

/**
 * @author Bleschunov Dmitry
 */
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Page<User> findUsersByNickname(String query, int page, int pageSize) {
        return userRepository.findByNicknameContainingIgnoreCase(query, PageRequest.of(page, pageSize));
    }

    @Transactional
    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(
                        String.format("User with id %d does not exist.", id)
                ));
    }

    public Optional<User> findByNicknameOrEmail(String nickname, String email) {
        return userRepository.findByNicknameOrEmail(nickname, email);
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

    @Transactional
    public User followUser(Long userId, Long followingUserId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundEntityException(
                String.format("User %d does not exist", userId)
        ));
        User followingUser = userRepository.findById(followingUserId).orElseThrow(() -> new NotFoundEntityException(
                String.format("Following user %d does not exist", followingUserId)
        ));
        Set<User> following = user.getFollowing();
        following.add(followingUser);
        user.setFollowing(following);
        return userRepository.save(user);
    }
}
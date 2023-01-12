package com.corporation.service;

import com.corporation.dto.UserDto;
import com.corporation.exception.NotFoundEntityException;
import com.corporation.mapper.UserMapper;
import com.corporation.model.Skill;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final S3Service s3Service;
    private final UserMapper userMapper;

    private final SkillService skillService;

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
    public void updateUserSkillList(long userId, List<Long> skillIdList) {
        User user = findById(userId);
        List<Skill> skillsToAssign = skillService.findSkillByIdIn(skillIdList);
        user.setSkills(skillsToAssign);
    }

    @Transactional
    public User update(UserDto userDto) {
        User user = findById(userDto.getId());
        userMapper.updateEntity(userDto, user);
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void updateUserAvatar(long id, MultipartFile userAvatar) {
        String url = s3Service.upload(userAvatar);
        userRepository.updateUserAvatarById(id, url);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByNickname(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with nickname %s does not exist.", username)
                ));
    }
    public List<User> findByProjectIdAndFieldName(long projectId, String searchValue) {
        return userRepository.findByProjectIdAndFollowersNickname(projectId, searchValue);
    }
}

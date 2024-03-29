package com.corporation.service;

import com.corporation.dto.UserDto;
import com.corporation.exception.NotFoundEntityException;
import com.corporation.mapper.UserMapperImpl;
import com.corporation.model.Skill;
import com.corporation.model.User;
import com.corporation.repository.UserRepository;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private SkillService skillService;

    @Spy
    private UserMapperImpl userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldReturnUserPage() {
        int page = 0;
        int pageSize = 3;
        String query = "";
        Page<User> users = new PageImpl<>(new ArrayList<>() {{
            add(new User());
            add(new User());
            add(new User());
        }});
        Pageable pageable = PageRequest.of(page, pageSize);
        Mockito.when(userRepository.findByNicknameContainingIgnoreCase(query, pageable))
                .thenReturn(users);

        Page<User> returnedUsers = userService.findUsersByNickname(query, page, pageSize);

        Assertions.assertEquals(3, returnedUsers.getSize());
    }

    @Test
    public void shouldReturnUserById() {
        long desiredId = 1;
        String nickname = "boba";
        String email = "boba@boba.com";
        String password = "1234";
        String aboutMe = "I am boba!";

        User mockUser = User
                .builder()
                .id(desiredId)
                .nickname(nickname)
                .email(email)
                .password(password)
                .aboutMe(aboutMe)
                .build();

        Mockito.when(userRepository.findById(desiredId))
                .thenReturn(Optional.of(mockUser));

        User user = userService.findById(desiredId);

        Assertions.assertEquals(desiredId, user.getId());
        Assertions.assertEquals(nickname, user.getNickname());
        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals(password, user.getPassword());
        Assertions.assertEquals(aboutMe, user.getAboutMe());
    }

    @Test
    public void shouldUpdateUserSkillList() {
        long userId = 1;
        long skillIdA = 1L;
        long skillIdB = 2L;
        List<Long> skillIdList = new ArrayList<>();
        skillIdList.add(skillIdA);
        skillIdList.add(skillIdB);
        List<Skill> skills = new ArrayList<>();
        skills.add(Skill.builder().id(skillIdA).build());
        skills.add(Skill.builder().id(skillIdB).build());
        User mockUser = Mockito.mock(User.class);
        Mockito.when(userRepository.findById(userId))
                .thenReturn(Optional.of(mockUser));
        Mockito.when(skillService.findSkillByIdIn(skillIdList))
                .thenReturn(skills);

        userService.updateUserSkillList(userId, skillIdList);

        Mockito.verify(userRepository).findById(userId);
        Mockito.verify(skillService).findSkillByIdIn(skillIdList);
    }

    @Test
    public void shouldUpdateUser() {
        long desiredId = 1;
        String oldNickname = "boba";
        String oldEmail = "boba@boba.com";
        String oldAboutMe = "I am boba!";
        String newNickname = "new boba";
        String newEmail = "new_boba@boba.com";
        String newAboutMe = "I am new boba!";

        User oldUser = User
                .builder()
                .id(desiredId)
                .nickname(oldNickname)
                .email(oldEmail)
                .aboutMe(oldAboutMe)
                .build();

        UserDto newUserDto = UserDto
                .builder()
                .id(desiredId)
                .nickname(newNickname)
                .email(newEmail)
                .aboutMe(newAboutMe)
                .build();

        Mockito.when(userRepository.findById(desiredId))
                .thenReturn(Optional.of(oldUser));
        User user = userService.update(newUserDto);

        Assertions.assertEquals(desiredId, user.getId());
        Assertions.assertEquals(newNickname, user.getNickname());
        Assertions.assertEquals(newEmail, user.getEmail());
        Assertions.assertEquals(newAboutMe, user.getAboutMe());
    }

    @Test
    public void shouldReturnUserDetails() {
        long desiredId = 1;
        String nickname = "boba";
        String email = "boba@boba.com";
        String password = "1234";
        String aboutMe = "I am boba!";

        User mockUser = User
                .builder()
                .id(desiredId)
                .nickname(nickname)
                .email(email)
                .password(password)
                .aboutMe(aboutMe)
                .build();

        Mockito.when(userRepository.findByNickname(nickname))
                .thenReturn(Optional.of(mockUser));

        User user = (User) userService.loadUserByUsername(nickname);

        Assertions.assertEquals(desiredId, user.getId());
        Assertions.assertEquals(nickname, user.getNickname());
        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals(password, user.getPassword());
        Assertions.assertEquals(aboutMe, user.getAboutMe());
    }

    @Test
    public void shouldThrowUserNotFoundException() {
        long desiredId = 100;

        Mockito.when(userRepository.findById(desiredId))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundEntityException.class, () -> userService.findById(desiredId));
    }

    @Test
    public void shouldReturnFollowersAmountById() {
        long desiredId = 1;
        long followersAmount = 3;

        Mockito.when(userRepository.getUserFollowersAmount(desiredId))
                .thenReturn(followersAmount);

        long followersReceived = userService.getUserFollowersAmount(desiredId);

        Assertions.assertEquals(followersAmount, followersReceived);
    }

    @Test
    public void shouldReturnUserFolloweesPage() {
        long desireId = 2;
        int page = 0;
        int pageSize = 5;
        Page<User> newUserFollowees = new PageImpl<>(new ArrayList<>() {{
            add(new User());
            add(new User());
            add(new User());
            add(new User());
            add(new User());
        }});
        Pageable pageable = PageRequest.of(page, pageSize);

        Mockito.when(userRepository.getUserFollowees(desireId, pageable))
                .thenReturn(newUserFollowees);

        Page<UserDto> receivedFollowees = userService.getUserFollowees(desireId, page, pageSize);

        Assertions.assertEquals(newUserFollowees.map(userMapper::toDto), receivedFollowees);
    }
}
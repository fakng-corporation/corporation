package com.corporation.service;

import com.corporation.dto.UserDto;
import com.corporation.exception.NotFoundEntityException;
import com.corporation.mapper.UserMapperImpl;
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
    public void shouldFollow() {
        long followingUserId = 2L;

        User follower = User.builder()
                .id(1L)
                .nickname("User1")
                .email("user@domain.com")
                .password("$2a$12$ZqBcuPyawuOEWm/Fo78Hte9DGrHl9fauMBLpfvWECAaO/Paat74kq")
                .enabled(true)
                .followees(new ArrayList<>())
                .build();
        User followee = User.builder()
                .nickname("User2")
                .email("user2@domain.com")
                .password("$2a$12$ZqBcuPyawuOEWm/Fo78Hte9DGrHl9fauMBLpfvWECAaO/Paat74kq")
                .enabled(true)
                .build();

        List<User> followeesList = new ArrayList<>();
        followeesList.add(followee);
        User afterFollowingUser = User.builder()
                .id(2L)
                .nickname("User1")
                .email("user@domain.com")
                .password("$2a$12$ZqBcuPyawuOEWm/Fo78Hte9DGrHl9fauMBLpfvWECAaO/Paat74kq")
                .enabled(true)
                .followees(followeesList)
                .build();

        UserDto assertionUserDtoo = UserDto.builder()
                .id(2L)
                .nickname("User1")
                .email("user@domain.com")
                .build();

        List<User> users = new ArrayList<>();
        users.add(follower);
        users.add(followee);

        Mockito.when(userRepository.findFollowerAndFolloweeById(follower.getId(), followee.getId())).thenReturn(users);
        Mockito.when(userRepository.save(follower)).thenReturn(afterFollowingUser);
        UserDto assertionUserDto = userService.followUser(follower.getId(), followee.getId());
        Assertions.assertEquals(assertionUserDto, assertionUserDtoo);
    }

    @Test
    public void followShouldThrowUserNotFoundException() {
        long wrongFollowerId = 102L;
        long wrongFolloweeId = 203L;
        Mockito.when(userRepository.findFollowerAndFolloweeById(wrongFollowerId, wrongFolloweeId)).thenReturn(new ArrayList<>());
        Assertions.assertThrows(NotFoundEntityException.class, () -> userService.followUser(wrongFollowerId, wrongFolloweeId));
    }
}

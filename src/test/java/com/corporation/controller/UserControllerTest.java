package com.corporation.controller;

import com.corporation.dto.UserDto;
import com.corporation.exception.NotFoundEntityException;
import com.corporation.mapper.SkillMapperImpl;
import com.corporation.mapper.UserMapperImpl;
import com.corporation.model.User;
import com.corporation.service.SkillService;
import com.corporation.service.UserService;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @Spy
    private UserMapperImpl userMapper;

    @Mock
    private SkillService skillService;

    @Spy
    private SkillMapperImpl skillMapper;

    @InjectMocks
    private UserController userController;

    @Test
    public void shouldReturnUserDtoPage() {
        int page = 0;
        int pageSize = 3;
        String query = "";
        Page<User> users = new PageImpl<>(new ArrayList<>() {{
            add(new User());
            add(new User());
            add(new User());
        }});
        Mockito.when(userService.findUsersByNickname(query, page, pageSize))
                .thenReturn(users);

        Page<UserDto> userDtos = userController.getUsersByNickname(query, page, pageSize);

        Assertions.assertEquals(pageSize, userDtos.getSize());
    }

    @Test
    public void shouldReturnUserDtoById() {
        int desiredId = 1;
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

        Mockito.when(userService.findById(desiredId))
                .thenReturn(mockUser);

        UserDto userDto = userController.getUserById(desiredId);

        Mockito.verify(userMapper).toDto(mockUser);

        Assertions.assertEquals(nickname, userDto.getNickname());
        Assertions.assertEquals(aboutMe, userDto.getAboutMe());
        Assertions.assertEquals(email, userDto.getEmail());
        Assertions.assertEquals(desiredId, userDto.getId());
    }

    @Test
    public void shouldReturnUpdatedUserDto() {
        long desiredId = 1;
        String newNickname = "new boba";
        String newEmail = "new_boba@boba.com";
        String newAboutMe = "I am new boba!";
        User newUser = User
                .builder()
                .id(desiredId)
                .nickname(newNickname)
                .email(newEmail)
                .aboutMe(newAboutMe)
                .build();
        UserDto newUserDto = userMapper.toDto(newUser);

        Mockito.when(userService.update(newUserDto))
                .thenReturn(newUser);
        UserDto returnedUserDto = userController.updateUser(newUserDto);

        Assertions.assertEquals(newNickname, returnedUserDto.getNickname());
        Assertions.assertEquals(newAboutMe, returnedUserDto.getAboutMe());
        Assertions.assertEquals(newEmail, returnedUserDto.getEmail());
        Assertions.assertEquals(desiredId, returnedUserDto.getId());
    }

    @Test
    public void shouldUseUserService() {
        long id = 10;
        MultipartFile file = new MockMultipartFile("avatar.png", "avatar.png", "image/png", new byte[]{1, 2, 3});

        userController.uploadUserAvatar(id, file);

        Mockito.verify(userService).updateUserAvatar(id, file);
    }

    @Test
    public void shouldAssignSkillList() {
        long userId = 1;
        List<Long> skillIdList = new ArrayList<>();

        userController.assignSkillList(userId, skillIdList);

        Mockito.verify(userService).updateUserSkillList(userId, skillIdList);
    }

    @Test
    public void shouldThrowUserNotFoundException() {

        int desiredId = 100;

        Mockito.when(userService.findById(desiredId))
                .thenThrow(NotFoundEntityException.class);

        Assertions.assertThrows(NotFoundEntityException.class, () -> userController.getUserById(desiredId));
    }

    @Test
    public void shouldReturnFollowersAmountById() {
        long desiredId = 1;
        long followersAmount = 3;

        Mockito.when(userService.getUserFollowersAmount(desiredId))
                .thenReturn(followersAmount);

        long followersReceived = userController.getUserFollowersAmount(desiredId);

        Mockito.verify(userService).getUserFollowersAmount(desiredId);

        Assertions.assertEquals(followersAmount, followersReceived);
    }

    @Test
    public void shouldReturnUserFolloweesPage() {
        long desiredId = 2;
        int page = 0;
        int pageSize = 5;
        Page<UserDto> newUserFollowees = new PageImpl<>(new ArrayList<>() {{
            add(userMapper.toDto(new User()));
            add(userMapper.toDto(new User()));
            add(userMapper.toDto(new User()));
            add(userMapper.toDto(new User()));
            add(userMapper.toDto(new User()));
        }});

        Mockito.when(userService.getUserFollowees(desiredId, page, pageSize))
                .thenReturn(newUserFollowees);

        Page<UserDto> receivedFollowees = userController.getUserFollowees(desiredId, page, pageSize);

        Mockito.verify(userService).getUserFollowees(desiredId, page, pageSize);

        Assertions.assertEquals(newUserFollowees, receivedFollowees);
    }
}
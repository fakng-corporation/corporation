package com.corporation.controller;

import com.corporation.dto.SkillDto;
import com.corporation.dto.UserDto;
import com.corporation.mapper.SkillMapper;
import com.corporation.mapper.UserMapper;
import com.corporation.model.User;
import com.corporation.service.SkillService;
import com.corporation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author Bleschunov Dmitry
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    private final SkillService skillService;
    private final SkillMapper skillMapper;

    @GetMapping
    public Page<UserDto> getUsersByNickname(
            @RequestParam("query") String query,
            @RequestParam("page") int page,
            @RequestParam("page_size") int pageSize) {
        return userService.findUsersByNickname(query, page, pageSize).map(userMapper::toDto);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") long id) {
        User user = userService.findById(id);
        return userMapper.toDto(user);
    }

    @GetMapping("/{id}/skill")
    public List<SkillDto> getSkillsByUserId(@PathVariable("id") long id) {
        return skillService.findSkillsByUserId(id).stream().map(skillMapper::toDto).toList();
    }

    @PostMapping("/{id}/skill")
    public void assignSkillList(@PathVariable("id") long id, @RequestBody List<Long> skillIdList) {
        userService.updateUserSkillList(id, skillIdList);
    }

    @PostMapping("/{id}")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userMapper.toDto(
                userService.update(userDto)
        );
    }
}

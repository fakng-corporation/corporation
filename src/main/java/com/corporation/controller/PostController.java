package com.corporation.controller;

import com.corporation.dto.PostDto;
import com.corporation.exception.UserNotFoundException;
import com.corporation.mapper.PostMapper;
import com.corporation.model.Post;
import com.corporation.model.User;
import com.corporation.repository.UserRepository;
import com.corporation.service.PostService;
import com.corporation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final PostMapper postMapper;


    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        Post post = postMapper.toEntity(postDto);
        //Получаем User со всеми полями по Id
        Optional<User> userOptional = userService.findById((int) postDto.getUser().getId());
        // Вставляем все поля по Foreign Key существующего User (чтобы не писать все строки руками)
        // Если такого нет, то кидаем UserNotFoundException, чтобы пост не остался без автора
        // userOptional.ifPresentOrElse(post::setUser, () -> { throw new UserNotFoundException(postDto.getUser().getId()); });
        userOptional.ifPresent(post::setUser);
        post = postService.save(post);
        return ResponseEntity.ok(postMapper.toDto(post));
    }

}

package com.corporation.service;

import com.corporation.exception.NotUniquePostException;
import com.corporation.model.Post;
import com.corporation.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    public void shouldReturnCreatedPost() {

        long id = 322;
        String title = "Some Title";
        String description = "Давай-ка в старом стайле, так мне надо\n" +
                "Снова собралась послушать менторства моего бригада\n" +
                "О да, кода уходящего года\n" +
                "Какого только не видал за микро прода\n" +
                "Вроде бы получше стало, вроде я сеньерю\n" +
                "В базу загляну порою, по-другому не умею\n" +
                "Вижу я накоденных, я их понимаю\n" +
                "Поэтому последнее с Calendly отрываю\n" +
                "Бывай, джун, пособесим мы с тобой ещё\n" +
                "Ты меня не забывай, помни, кто есть чё\n" +
                "С утра мы все красивы, у кого большие пешки\n" +
                "Мы плясали все, устали и стоптали чешки\n" +
                "Эх, жизнь — хоть за оффер держись!\n" +
                "Прогноз моих пацанов: в дальнейшем — релокнись\n" +
                "Порешать компы, встать крепко на ноги\n" +
                "Боже, помоги, пальцы правильно веди";

        Post mockPost = Post.builder().id(id).title(title).description(description).build();

        Mockito.when(postRepository.findPostByTitle(title)).thenReturn(Optional.of(mockPost));

        Optional<Post> optionalPost = postService.findPostByTitle(title);

        Assertions.assertTrue(optionalPost.isPresent());

        Mockito.when(postRepository.save(mockPost)).thenReturn(mockPost);

        Post createdPost = postService.save(mockPost);

        Assertions.assertEquals(id, createdPost.getId());
        Assertions.assertEquals(title, createdPost.getTitle());
        Assertions.assertEquals(description, createdPost.getDescription());

    }

    @Test
    public void shouldThrowNotUniquePostException() {
        String title = "Some Title";

        Post post = Post.builder().title(title).build();

        Mockito.when(postRepository.findPostByTitle(post.getTitle()))
                .thenReturn(Optional.of(post));

        Assertions.assertThrows(NotUniquePostException.class, () -> postService.save(post));
    }
}
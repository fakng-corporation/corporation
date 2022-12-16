package com.corporation.service;

import com.corporation.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void deleteById (Long id) throws EmptyResultDataAccessException {
        postRepository.deleteById(id);
    }
}
package com.corporation.service;

import com.corporation.model.service.Like;
import com.corporation.repository.service.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;

    public Like save(Like like) {
        return likeRepository.save(like);
    }
}

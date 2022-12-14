package com.corporation.repository;

import com.corporation.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositroy extends CrudRepository<Post, Long> {
}

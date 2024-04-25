package com.ll.daily.domain.study.post.repository;

import com.ll.daily.domain.study.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {


}

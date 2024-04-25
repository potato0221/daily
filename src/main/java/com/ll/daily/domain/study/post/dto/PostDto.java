package com.ll.daily.domain.study.post.dto;

import com.ll.daily.domain.study.post.entity.Post;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
public class PostDto {
    @NonNull
    private Long id;
    @NonNull
    private LocalDateTime createDate;
    @NonNull
    private String title;
    @NonNull
    private String content;
    @NonNull
    private String writer;
    @NonNull
    private Long writerId;

    public PostDto(Post post){
        this.id = post.getId();
        this.createDate = post.getCreateDate();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.writer = post.getWriter().getNickname();
        this.writerId = post.getWriter().getId();
    }
}

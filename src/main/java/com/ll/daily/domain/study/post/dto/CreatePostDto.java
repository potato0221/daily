package com.ll.daily.domain.study.post.dto;

import com.ll.daily.domain.study.post.entity.Post;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class CreatePostDto {
    @NonNull
    private String title;
    @NonNull
    private String content;

    public CreatePostDto(Post post){
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}

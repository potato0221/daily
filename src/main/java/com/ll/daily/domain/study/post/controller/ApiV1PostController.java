package com.ll.daily.domain.study.post.controller;

import com.ll.daily.domain.study.post.dto.CreatePostDto;
import com.ll.daily.domain.study.post.dto.PostDto;
import com.ll.daily.domain.study.post.entity.Post;
import com.ll.daily.domain.study.post.service.PostService;
import com.ll.daily.global.app.AppConfig;
import com.ll.daily.global.msg.Msg;
import com.ll.daily.global.rsData.RsData;
import com.ll.daily.standard.base.Empty;
import com.ll.daily.standard.base.PageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Tag(name = "ApiV1MemberController", description = "요약 글 컨트롤러")
public class ApiV1PostController {

    private final PostService postService;

    @PostMapping("")
    @Operation(summary = "글 생성")
    public RsData<CreatePostDto> createPost(@Valid @RequestBody CreatePostDto createPostDto){

        Post post = postService.create(createPostDto);
        CreatePostDto createdPost = new CreatePostDto(post);

        return RsData.of(
                Msg.E200_0_CREATE_SUCCEED.getCode(),
                Msg.E200_0_CREATE_SUCCEED.getMsg(),
                createdPost
                );
    }

    @PutMapping("")
    @Operation(summary = "글 수정")
    public RsData<PostDto> modifyPost(@RequestBody PostDto postDto){

        Post post = postService.modify(postDto);
        PostDto modifyPost = this.postToDto(post);

        return RsData.of(
                Msg.E200_2_MODIFY_SUCCEED.getCode(),
                Msg.E200_2_MODIFY_SUCCEED.getMsg(),
                modifyPost
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "글 삭제")
    public RsData<Empty> deletePost(@PathVariable("id") Long id){

        postService.delete(id);

        return RsData.of(
                Msg.E200_3_DELETE_SUCCEED.getCode(),
                Msg.E200_3_DELETE_SUCCEED.getMsg()
        );
    }

    public record GetPostsResponseBody(@NonNull PageDto<PostDto> itemPage) {
    }

    @GetMapping("")
    @Operation(summary = "글 목록 조회")
    public RsData<GetPostsResponseBody> getPosts(@RequestParam(defaultValue = "1") int page){

        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize(), Sort.by(sorts));

        Page<Post> posts = postService.findAll(pageable);

        Page<PostDto> postDtos = posts.map(this::postToDto);

        return RsData.of(
                Msg.E200_1_INQUIRY_SUCCEED.getCode(),
                Msg.E200_1_INQUIRY_SUCCEED.getMsg(),
                new GetPostsResponseBody(
                        new PageDto<>(postDtos)
                )
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "글 상세 조회")
    public RsData<PostDto> getPost(@PathVariable("id") Long id){
        Post post = postService.getPost(id);
        PostDto postDto = this.postToDto(post);

        return RsData.of(
                Msg.E200_1_INQUIRY_SUCCEED.getCode(),
                Msg.E200_1_INQUIRY_SUCCEED.getMsg(),
                postDto
        );
    }

    public PostDto postToDto(Post post){
        return new PostDto(post);
    }

}

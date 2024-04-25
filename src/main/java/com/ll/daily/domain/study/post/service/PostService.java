package com.ll.daily.domain.study.post.service;

import com.ll.daily.domain.study.post.dto.CreatePostDto;
import com.ll.daily.domain.study.post.dto.PostDto;
import com.ll.daily.domain.study.post.entity.Post;
import com.ll.daily.domain.study.post.repository.PostRepository;
import com.ll.daily.global.exceptions.ExceptionMsg;
import com.ll.daily.global.exceptions.GlobalException;
import com.ll.daily.global.rq.Rq;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final Rq rq;

    @Transactional
    public Post create(CreatePostDto createPostDto){
        Post post = Post.builder()
                .title(createPostDto.getTitle())
                .content(createPostDto.getContent())
                .writer(rq.getMember())
                .build();

        return postRepository.save(post);

    }

    @Transactional
    public Post modify(PostDto postDto){

        if(!haveAuth(postDto.getWriterId())){
            throw new GlobalException(
                    ExceptionMsg.E403_1_FORBIDDEN.getCode(),
                    ExceptionMsg.E403_1_FORBIDDEN.getMsg()
            );
        }

        Post post = this.getPost(postDto.getId());

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        return postRepository.save(post);
    }

    @Transactional
    public void delete(Long id) {
        Post post = this.getPost(id);

        postRepository.delete(post);
    }

    public Post getPost(Long id){

        Optional<Post> post = postRepository.findById(id);

        if(post.isPresent()){
            return post.get();
        }else{
            throw new GlobalException(
                    ExceptionMsg.E404_1_DATA_NOT_FOUND.getCode(),
                    ExceptionMsg.E404_1_DATA_NOT_FOUND.getMsg()
            );
        }
    }

    public boolean haveAuth(Long id){
        return rq.getMember().getId().equals(id);
    }

    public Page<Post> findAll(Pageable pageable) {

        return postRepository.findAll(pageable);
    }
}

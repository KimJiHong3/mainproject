package com.main.posts.service;

import com.main.posts.dto.PostPatchDto;
import com.main.posts.dto.PostResponseDto;
import com.main.posts.repository.Posts;
import com.main.posts.dto.PostDto;
import com.main.posts.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostRepository postRepository;


    @Transactional
    public Posts save(PostDto postDto) {

        Posts posts = Posts.builder()
                .title(postDto.getTitle())
                .text(postDto.getText())
                .price(postDto.getPrice())
                .end_date(postDto.getEnd_date())
                .sheet_number(postDto.getSheet_number())
                .build();


            Posts posts1 = postRepository.save(posts);
            return posts1;

    }

    public List<Posts> findPosts() {return postRepository.findAll();}


    /**
     * 게시글 삭제 완료
     */
    public void delete(Long id){
        Posts posts = postRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("없는 글 입니다."));
                postRepository.delete(posts);
    }

    /**
     * 게시글 수정 완료
     */
    @Transactional
    public PostResponseDto editPosts(Long id, PostPatchDto postPatchDto) {
        Posts posts = postRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 게시글입니다."));
            posts.changePosts(postPatchDto.getTitle(),postPatchDto.getText(),
                              postPatchDto.getPrice(),postPatchDto.getEnd_date(),
                              postPatchDto.getSheet_number());
            return PostResponseDto.builder()
                    .title(posts.getTitle())
                    .text(posts.getText())
                    .price(posts.getPrice())
                    .end_date(posts.getEnd_date())
                    .sheet_number(posts.getSheet_number())
                    .build();
    }




}

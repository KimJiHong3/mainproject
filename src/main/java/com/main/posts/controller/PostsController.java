package com.main.posts.controller;


import com.main.posts.dto.PostPatchDto;
import com.main.posts.dto.PostResponseDto;
import com.main.posts.repository.Posts;
import com.main.posts.dto.PostDto;
import com.main.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class PostsController {
    @GetMapping("/")
    public @ResponseBody String index() {
        return "hello World!";
    }
    private final PostsService postsService;

    /**
    * 게시글 생성
     */
    @PostMapping("/board")
    @CrossOrigin(origins = "*")
    public ResponseEntity posts(@RequestBody @Valid PostDto postDto) {
        Posts posts = postsService.save(postDto);
            return new ResponseEntity<>(posts,HttpStatus.CREATED);
    }

    /**
     * 게시글 전체 조회
     */
    @GetMapping("/board")
    @CrossOrigin(origins = "*")
    public ResponseEntity listPosts(){
        List<Posts> postsList = postsService.findPosts();
            return new ResponseEntity<>(postsList,HttpStatus.OK);
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/board/{id}")
    @CrossOrigin(origins = "*")
    public String deletePosts(@PathVariable Long id) {
         postsService.delete(id);
         return "삭제 되었습니다.";
    }

    /**
     * 게시글 수정
     */
    @PutMapping("/board/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity editPosts(@PathVariable Long id, @RequestBody PostPatchDto postPatchDto) {
        PostResponseDto postResponseDto = postsService.editPosts(id,postPatchDto);
        return new ResponseEntity<>(postResponseDto,HttpStatus.ACCEPTED);
    }




}

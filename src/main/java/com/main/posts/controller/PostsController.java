package com.main.posts.controller;


import com.main.posts.dto.PostPatchDto;
import com.main.posts.dto.PostResponseDto;
import com.main.posts.entity.Posts;
import com.main.posts.dto.PostDto;
import com.main.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
    //@GetMapping("/board")
    //@CrossOrigin(origins = "localhost:3003")
     //public ResponseEntity listPosts(){
        //List<Posts> postsList = postsService.findPosts();
            //return new ResponseEntity<>(postsList,HttpStatus.OK);
    //}

    @GetMapping("/board")
    List<Posts> findPosts(String status, String title, String body) {

        List<Posts> list = null;
        if(title == null && body == null) {
            list = postsService.findPosts();
        } else {
            list = postsService.findPosts14(status,title,body);
        }
         return list;
    }

    //@GetMapping("/board/list")
    //@CrossOrigin(origins = "localhost:3003")
    //public String findPosts2(Model model, @PageableDefault(page = 0, size = 8 , sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
       // model.addAttribute("list",postsService.findPosts2(pageable));
        //return "findPosts2";
    //}

    /**
     * 최신순 게시글 검색 기능 및 8페이지네이션(판매중 판매완료 모두)
     */
    @GetMapping("/board/list-up")
    @CrossOrigin(origins = "localhost:3003")
    Page<Posts> findPosts2(@PageableDefault(page = 0, size = 8 , sort = "createdDate", direction = Sort.Direction.ASC)
                     Pageable pageable, String status, String title, String body) {

        Page<Posts> list = null;
        if(title == null && body == null) {
            list = postsService.findPosts2(pageable);
        } else {
            list = postsService.findPosts3(status,title,body,pageable);
        }
        return  list;
    }

    /**
     * 최신순 전체 검색 기능 (판매중 판매완료) 및 검색기능포함
     */
    @GetMapping("/board/full-up")
    Page<Posts> findPosts4(@PageableDefault(page = 0, sort = "createdDate", direction = Sort.Direction.DESC)
                           Pageable pageable, String status, String title, String body) {

        Page<Posts> list = null;
        if(title == null && body == null) {
            list = postsService.findPosts4(pageable);
        } else {
            list = postsService.findPosts5(status,title,body,pageable);
        }
        return  list;
    }

    /**
     * 조회수순 8페이지네이션(판매중 판매완료 모두)
     */
    @GetMapping("/board/view-list")
    Page<Posts> findPosts6(@PageableDefault(page = 0, size = 8 , sort = "view", direction = Sort.Direction.DESC)
                           Pageable pageable, String status, String title, String body) {

        Page<Posts> list = null;
        if(title == null && body == null) {
            list = postsService.findPosts6(pageable);
        } else {
            list = postsService.findPosts7(status,title,body,pageable);
        }
        return  list;
    }

    /**
     * 조회수순 전체페이지네이션(판매중 판매완료 모두)
     */
    @GetMapping("/board/view-full")
    Page<Posts> findPosts8(@PageableDefault(page = 0,sort = "view", direction = Sort.Direction.DESC)
                           Pageable pageable, String status, String title, String body) {

        Page<Posts> list = null;
        if(title == null && body == null) {
            list = postsService.findPosts8(pageable);
        } else {
            list = postsService.findPosts9(status,title,body,pageable);
        }
        return  list;
    }

    /**
     * 오래된순 8페이지네이션(판매중 판매완료 모두)
     */
    @GetMapping("/board/list-down")
    Page<Posts> findPosts10(@PageableDefault(page = 0, size = 8 , sort = "createdDate", direction = Sort.Direction.DESC)
                           Pageable pageable, String status, String title, String body) {

        Page<Posts> list = null;
        if(title == null && body == null) {
            list = postsService.findPosts10(pageable);
        } else {
            list = postsService.findPosts11(status,title,body,pageable);
        }
        return  list;
    }

    /**
     * 오래된순 전체페이지네이션(판매중 판매완료 모두)
     */
    @GetMapping("/board/full-down")
    Page<Posts> findPosts12(@PageableDefault(page = 0, sort = "createdDate", direction = Sort.Direction.DESC)
                            Pageable pageable, String status, String title, String body) {

        Page<Posts> list = null;
        if(title == null && body == null) {
            list = postsService.findPosts12(pageable);
        } else {
            list = postsService.findPosts13(status,title,body,pageable);
        }
        return  list;
    }


    /**
     * 게시글 조회수 기능
     */
    @GetMapping("/board/read/{id}")
    public String read(@PathVariable Long id, Model model) {
         postsService.updateView(id);
        //postsService.updateView(id); // views ++//
       // model.addAttribute("posts",   postsService.updateView(id));
        return "posts-read";
    }

    /**
     * 한개 게시물 조회
     */
    @GetMapping("/board/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity onePosts(@PathVariable Long id) {
        PostResponseDto postResponseDto = postsService.findOnePost(id);
        return new ResponseEntity<>(postResponseDto,HttpStatus.OK);
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
    @PatchMapping("/board/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity editPosts(@PathVariable Long id, @RequestBody PostPatchDto postPatchDto) {
        PostResponseDto postResponseDto = postsService.editPosts(id,postPatchDto);
        return new ResponseEntity<>(postResponseDto,HttpStatus.ACCEPTED);
    }




}

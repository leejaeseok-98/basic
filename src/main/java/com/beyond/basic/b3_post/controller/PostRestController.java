package com.beyond.basic.b3_post.controller;

import com.beyond.basic.b3_post.domain.Post;
import com.beyond.basic.b3_post.dtos.CommonDto;
import com.beyond.basic.b3_post.dtos.PostCreateDto;
import com.beyond.basic.b3_post.dtos.PostDetailDto;
import com.beyond.basic.b3_post.dtos.PostListDto;
import com.beyond.basic.b3_post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post/rest")
public class PostRestController {

    private final PostService postService;
    public PostRestController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> postList(){
        List<PostListDto> postListDto = postService.findAll();
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(),"list is found",postListDto),HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> postDetailList(@PathVariable Long id){
        PostDetailDto postDetailDto = postService.findById(id);
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(),"post is found",postDetailDto),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> postCreate(@Valid @RequestBody PostCreateDto postCreateDto){
        postService.save(postCreateDto);
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(),"post is posted",null),HttpStatus.OK);
    }

}

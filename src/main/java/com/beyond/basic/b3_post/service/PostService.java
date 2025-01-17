package com.beyond.basic.b3_post.service;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.repository.MemberRepository;
import com.beyond.basic.b3_post.domain.Post;
import com.beyond.basic.b3_post.dtos.PostCreateDto;
import com.beyond.basic.b3_post.dtos.PostDetailDto;
import com.beyond.basic.b3_post.dtos.PostListDto;
import com.beyond.basic.b3_post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService {
    private final MemberRepository memberRepository;


    private final PostRepository postRepository;
    public PostService(MemberRepository memberRepository, PostRepository postRepository){
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    public List<PostListDto> findAll(){
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(p -> p.listFromEntity()).collect(Collectors.toList());
    }
//    제일 어려운부분
    public void save(PostCreateDto postCreateDto){
        Member member = memberRepository.findById(postCreateDto.getMemberId()).orElseThrow(()->new EntityNotFoundException("member is not found"));
        Post post = postCreateDto.toEntity(member);
        postRepository.save(post);
    }

    public PostDetailDto findById(Long id){
        Post post = postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("post is not found"));
        return post.toDetailDto(post.getMember().getEmail());
    }
}

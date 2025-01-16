package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import com.beyond.basic.b2_board.dtos.MemberUpdateDto;
import com.beyond.basic.b2_board.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//모든 메서드에 ResponseBody붙음
@RestController
@RequestMapping("/member/rest")
public class MemberRestController {

    private final MemberService memberService;
    public MemberRestController(MemberService memberService){
        this.memberService = memberService;
    }

    //    회원목록조회
    @GetMapping("/list")
    public List<MemberListRes> memberList() {
        List<MemberListRes> memberListRes = memberService.findAll();
        return memberListRes;
    }

    //    회원상세조회
    @GetMapping("/detail/{id}")
    public MemberDetailDto memberDetail(@PathVariable Long id) {
        MemberDetailDto dto = memberService.findById(id);
        return dto;
    }

    @PostMapping("/create")
    public String memberCreatePost(@RequestBody MemberCreateDto dto) {
        memberService.save(dto);
        return "ok";
    }

//    get:조회, post:등록, patch:부분수정, put:대체, delete:삭제
//    axios.patch
    @PatchMapping("/update/pw")
    public String updatePw(@RequestBody MemberUpdateDto dto){
        memberService.updatePw(dto);
        return null;
    }
}
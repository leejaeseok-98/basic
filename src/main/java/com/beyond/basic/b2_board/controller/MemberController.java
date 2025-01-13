package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import com.beyond.basic.b2_board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {
//    의존성주입(DI) 방법1. Autowired 어노테이션 사용 : 필드주입
    @Autowired
    private MemberService memberService;

    @GetMapping("")
    public String memberHome(){
        return "member/home";
    }
//    회원목록조회
    @GetMapping("/list")
    public String memberList(Model model){
        List<MemberListRes> memberListRes = memberService.findAll();
        model.addAttribute("memberList", memberListRes);
        return "/member/member-list";
    }

//    회원상세조회
    @GetMapping("/detail/{id}")
    public String memberDetail(@PathVariable Long inputId){
        System.out.println(inputId);
        return "/member/detail";
    }

//    회원가입
    @GetMapping("/create")
    public String memberCreate(){
        return "/member/create";
    }

    @PostMapping("/create")
    public String memberCreatePost(MemberCreateDto dto){
        memberService.save(dto);
//       새로운 화면 리턴이 아니니 url재호출을 통해 redirect
        return "redirect:/member/list";
    }
}

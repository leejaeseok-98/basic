package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import com.beyond.basic.b2_board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
////    의존성주입(DI) 방법1. Autowired 어노테이션 사용 : 필드주입
//    @Autowired
//    private MemberService memberService;

//    의존성주입 방법2 . 생성자주입방식(가장많이 사용하는 방식)
//    final을 통해 상수로 사용가능 - 재할당 불가능 - 안전성 향상 2) 다형성 구현 가능
//        2)순환참조컴파일타임에 check

//    private final MemberService memberService;
////    싱글톤객체로 만들어지는 시점에 아래 생성자가 호출. 생성자가 하나밖에 없을때에는 Autowired생략가능
////    @Autowired
//    public MemberController(MemberService memberService){
//        this.memberService = memberService;
//    }
//
//    의존성주입방법. RequiredArgs 어노테이션 사용방법
//        RequiredArgs :반드시 초기화 되어야 하는 필드(final, @NonNull어노테이션)를 대상으로 생성자를 자동으로 만들어주는 어노테이션
private final MemberService memberService;

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
    public String memberDetail(@PathVariable Long id,Model model){
//        name,email,password
        try {
            MemberDetailDto dto = memberService.findById(id);
            model.addAttribute("member",dto);
            return "/member/detail";
        }
        catch (NoSuchElementException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/member-error";
        }
        catch (RuntimeException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/member-error";
        }

    }

//    회원가입
    @GetMapping("/create")
    public String memberCreate(){
        return "/member/create";
    }

    @PostMapping("/create")
    public String memberCreatePost(MemberCreateDto dto, Model model){
        try{
            memberService.save(dto);
//       새로운 화면 리턴이 아니니 url재호출을 통해 redirect
            return "redirect:/member/list";
        }
        catch (IllegalArgumentException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "/member/member-error";
        }
    }
}

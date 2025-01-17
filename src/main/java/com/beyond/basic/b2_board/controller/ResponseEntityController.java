package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.dtos.CommonDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//controller + responsebody(모든메서드에)
@RestController
@RequestMapping("/response/entity")
public class ResponseEntityController {
//    case1. @ResponseStatus어노테이션 사용
    @GetMapping("/annotation1")
    @ResponseStatus(HttpStatus.OK)
    public String annotation1(){
        return "ok";
    }

    @GetMapping("/annotation2")
    public String annotation20(){
        return "ok";
    }
    @PostMapping("/annotation2")
    @ResponseStatus(HttpStatus.CREATED)
    public String annotation2(){
        return "ok";
    }
//    case2.  메서드 체이닝 방식 :ResponseEntity의 클래스 사용
    @GetMapping("chaining1")
    public ResponseEntity<Member> chaining1(){
        Member member = new Member("hongildong", "ho@naver.com","123456789");
//        header부에 200, body에 member형태의 json
//        return ResponseEntity.ok(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }
//    case3. ResponseEntity 객체를 직접 생성하여 cumstuom하는 방식
    @GetMapping("/custom1")
//    Object자리에 Member가능 ?도 가능
        public ResponseEntity<Object> custiom1(){
        Member member = new Member("hong", "hong@1", "12456487");
        return new ResponseEntity<>(member,HttpStatus.CREATED);
    }
    @GetMapping("/custom2")
    public ResponseEntity<?> custom2(){
        Member member = new Member("hong", "hong@1", "12456487");
//        header : 상태코드+상태메시지 , body: 상태코드, 상태메시지, 객체
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(),"member is found", member),HttpStatus.OK);
    }

}


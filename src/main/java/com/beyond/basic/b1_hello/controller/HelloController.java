package com.beyond.basic.b1_hello.controller;

import com.beyond.basic.b1_hello.domain.Hello;
import com.beyond.basic.b1_hello.domain.StudentReqDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//Component 어노테이션을 통해 별도의 객체를 생성할 필요가 없는, 싱글톤 객체를 생성
//Controller 어노테이션을 통해 쉽게 사용자의 http요청을 처리하고 http응답을 줄 수 있음
@Controller
//클래스차원에 URL매핑시에는 RequestMapping사용
@RequestMapping("/hello")
public class HelloController {
//    case1.서버가 사용자에게 단순 String 데이터 return(get요청) - @ResponseBody
//    case2.서버가 사용자에게 화면을 return(get요청) - ResponseBody가 없을때

    @GetMapping("")
//    @ResponseBody
//     @ResponseBody가 없고, return타입이 String인경우 서버는 templates폴더 밑에  hellowrld.html화면을 찾아 리턴
    public String helloWorld(){
        return "helloworld";
    }

//    case3/서버가 사용자에게 json형식의 데이터를 return(get요청)
    @GetMapping("/json")
//    메서드 차원에서도 RequestMapping사용가능
//    @RequestMapping(value = "/json",method = RequestMethod.GET)
    @ResponseBody
    public Hello helloJson() throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        Hello h1 = new Hello("hongilgdong","hongilgdong@naver.com");
//        String value = objectMapper.writeValueAsString(h1);
//        직접 json으로 직렬화 할 필요없이, return타입을 클래스로 지정시에 자동으로 직렬화.
        Hello h1 = new Hello("hongildong","hongildong@naver.com");
        return h1;
    }

//    case4.사용자가 json데이터를 요청하되, parameter형식으로 특정객체 요청(get요청)
//    parameter형식 : /hello/param1?name=hongildong
    @GetMapping("/param1")
    @ResponseBody
    public Hello param1(@RequestParam(value = "name")String inputName){
        Hello h1 = new Hello(inputName,"test@naver.com");
        return h1;
    }

//    parameter 2개 이상 형식 : /hello/param2?name=hongildong&email=hongildong@naver.com

    @GetMapping("/param2")
    @ResponseBody
    public Hello param2(@RequestParam(value = "name")String inputName,
                        @RequestParam(value = "email")String inputEmail)
    {
        Hello h1 = new Hello(inputName,inputEmail);
        return h1;
    }
//    case5.parameter가 많아질 경우, 데이터바인딩을 통해 input값 처리(get요청)
//    parameter 1개 이상 형식 : /hello/param2?name=hongildong&email=hongildong@naver.com
    @GetMapping("/param3")
    @ResponseBody
//    각 파라미터의 값이 Hello클래스의 각변수에 mapping이 된다. : new Hello(hongildong, hong@naver.com
//    public Hello param3(Hello hello) {
//        Hello h1 = new Hello(hello.getName(),hello.getEmail());
    public Hello param3(@ModelAttribute Hello hello) {
        return hello;
    }

//case6.화면을 return해 주되, 특정변수값을 동적으로 세팅 /mvc용임
    @GetMapping("/model-param")
    public String ModelParam(@RequestParam(value = "name")String inputName, Model model){
//        model객체는 특정 데이터를 화면에 전달해주는 역할
//        modelName이라는 키값에 value를 세팅하면 modelName :값 형태로 화면에 전달
        model.addAttribute("modelName",inputName);
        return "helloworld2";
    }

//case7.화면을 return해 주되, 객체를 화면에 동적으로 세팅 /mvc용임
@GetMapping("/model-param2")
public String ModelParam2(@ModelAttribute Hello hello, Model model){
//        model객체는 특정 데이터를 화면에 전달해주는 역할
//        modelName이라는 키값에 value를 세팅하면 modelName :값 형태로 화면에 전달
    model.addAttribute("modelHello",hello);
    return "helloworld3";
}
//case8.pathvariable방식을 통해 사용자로부터 값을 받아 화면 return
//    형식 : /hello/model-path/honglidong
//    예시 : /author/detail/1
//    pathvariable방식은 url을 통해 자원구조를 명확하게 표현할때 사용.(좀 더 restful한 방식
    @GetMapping("/model-path/{inputName}")
    public String modelPath(@PathVariable String inputName, Model model){
        model.addAttribute("modelName",inputName);
        return  "helloworld2";
    }

    @GetMapping("/form-view")
//    사용자에게 name, email을 입력할 수 있는 화면을 주는 메서드 정의
    public String formView(){
        return "form-view";
    }

//    case1.form데이터 형식의 post요청 처리(application/x-www-)//텍스트만 있는
//    형식 : ?name=xxx&email=yyy 데이터가 http body에 들어옴
    @PostMapping("/form-view")
    @ResponseBody
    public String formPost1(Hello hello){
        System.out.println(hello);
        return "ok";
    }

//case2. form데이터 형식의 post요청 처리(file + text 형식)
//    url패턴 : form-file-view
    @GetMapping("/form-file-view")
    public String formFileView(){
        return "form-file-view";
    }
    @PostMapping("/form-file-view")
    @ResponseBody
//    java에서 파일을 처리하는 클래스 : MultipartFile
    public String formPost2(Hello hello, @RequestParam(value = "photo")MultipartFile photo){
        System.out.println(hello);
        System.out.println(photo.getOriginalFilename());
        return "ok";
    }

//    case3. js를 활용한 form데이터 전송(text만)
    @GetMapping("/axios-form-view")
    public String axiosForm(){
        return "axios-form-view";
    }
    @PostMapping("/axios-form-view")
    @ResponseBody
//    js를 통한 form형식도 마찬기로 ?name=xxx&email=yyy
    public String axiosPost(Hello hello){
        System.out.println(hello);
        return "ok";
    }

//case4. js를 활용한 form데이터 전송(text+file)
    @GetMapping("/axios-form-file-view")
    public String axiosFormFile(){
        return "axios-form-file-view";
    }
    @PostMapping("/axios-form-file-view")
    @ResponseBody
    public String axiosFilePost(Hello hello,@RequestParam(value = "photo")MultipartFile photo){
        System.out.println(hello);
        System.out.println(photo.getOriginalFilename());
        return "ok";
    }

//case5. js를 활용한 form데이터 전송(text+firl)
@GetMapping("/axios-form-multi-file-view")
public String axiosFormMultiFile(){
    return "axios-form-multi-file-view";
}

    @PostMapping("/axios-form-multi-file-view")
    @ResponseBody
    public String axiosFileMultiPost(Hello hello,@RequestParam(value = "photos") List<MultipartFile> photos){
        System.out.println(hello);
        for (int i = 0; i< photos.size();i++){
            System.out.println(photos.get(i).getOriginalFilename());
        }
        return "ok";
    }


//case6.js를 활용한 json데이터 전송
//    형식 : {name :"hongildong", email : "hong@naver.com"}
    @GetMapping("/axios-json-view")
    public String axiosJsonView(){
        return "axios-json-view";
    }
    @PostMapping("/axios-json-view")
    @ResponseBody
//    JSON이면 @RequestParam이다.
    public String axiosJsonViewPost(@RequestBody Hello hello){
        System.out.println(hello);
        return "ok";
    }

//case7.중첩된 JSON 데이터 처리
//    예시 데이터(student객레) : {name:'hongildong', email: 'hong@naver.com', scores:[{math :60},{music :60},{english :60}]}

@GetMapping("/axios-nested-json-view")
    public String axiosNestedJsonView(){
        return "axios-nested-json-view";
}

@PostMapping("/axios-nested-json-view")
    @ResponseBody
    public String axiosNestedJsonViewPost(@RequestBody StudentReqDto studentReqDto){
    System.out.println(studentReqDto);
    System.out.println(studentReqDto.getName());
    System.out.println(studentReqDto.getEmail());
    for (StudentReqDto.Score s : studentReqDto.getScores()){
        System.out.println(s.getSubject());
        System.out.println(s.getPoint());
    }
        return "ok";
}

//case8. json과 file처리
//    file처리는 기본적으로 form형식을 통해 처리
//    그래서, json, file을 동시에 처리하려면 form형식안에 json과 파일을 넣어 처리
    @GetMapping("/axios-json-file-view")
    public String axiosJsonFileView(){
        return "/axios-json-file-view";
    }
//    데이터 형식 : ?hello={name:"hongildong",email: "hong@naver.com"}&photo=이미지
    @PostMapping("/axios-json-file-view")
    @ResponseBody
    public String axiosJsonFileViewPost(
//            @RequestParam(value = "hello") String helloString,
//            @RequestParam(value = "photo") MultipartFile photo
//            ㄱㄷ볃ㄴ셈ㄳ는 json과 file함께 처리할 때 많이 사용
            @RequestPart("hello") Hello hello,
            @RequestPart("photo") MultipartFile photo
    ) throws JsonProcessingException {
//        RequestParam으로 받을 때
//        ObjectMapper objectMapper = new ObjectMapper();
//        Hello h1 = objectMapper.readValue(helloString,Hello.class);//역직렬화
//        System.out.println(h1);
//        System.out.println(photo.getOriginalFilename());

//        RequestPart로 받을 때
        System.out.println(hello);
        System.out.println(photo.getOriginalFilename());

        return "ok";
    }




}

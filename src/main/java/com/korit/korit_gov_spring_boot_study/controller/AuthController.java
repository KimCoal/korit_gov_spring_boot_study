package com.korit.korit_gov_spring_boot_study.controller;

import com.korit.korit_gov_spring_boot_study.dto.req.SignInReqDto;
import com.korit.korit_gov_spring_boot_study.dto.req.SignUpReqDto;
import com.korit.korit_gov_spring_boot_study.dto.resp.SignInRespDto;
import com.korit.korit_gov_spring_boot_study.dto.resp.SignUpRespDto;
import com.korit.korit_gov_spring_boot_study.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    private UserService userService;

    public AuthController() {
        userService = UserService.getInstance();
    }

    /*
    * @RequestParam
    * 파라미터가 없으면 400에러
    * 타입이 안맞을때
    * 이름이 불일치
    *
    * 민감한 정보를 쓰지 않도록 해야함
    * */

//    @GetMapping("/get")
//    public String getUser(@RequestParam String userId) {
//        System.out.println("쿼리 파라미터로 들어온 값 : " + userId);
//        return "쿼리 파라미터로 들어온 값 : " + userId;
//    }
//
//    @GetMapping("/get/name")
//    public String getUsername(@RequestParam(value = "name", defaultValue = "홍길동") String username, @RequestParam(required = false) Integer age) {
//        return username + age;
//    }
    /*
    * value = 을 통해 파라미터의 키 이름을 다르게 설정 가능
    * default value = 를 통해 값을 넣지 않았을때 기본값을 설정 가능
    * required = false 를 통해 필수가 아니게 설정 가능
    * required = false > null값이 들어오기때문에 wrapper 클래스 자료형이여야 한다
    * */

//    @PostMapping("/signup")
//    public String signup(@RequestBody SignUpReqDto signUpReqDto) {
//        System.out.println(signUpReqDto);
//        return signUpReqDto.getUsername() + "님 회원가입이 완료되었습니다.";
//    }
    // Get - @ReqParam
    // Post - @ReqBody

    /*
    * @RequestBody
    * HTTP 요청의 바디에 들어있는 Json데이터를 자바 객체(DTO)로 변환해서 주입해주는 어노테이션
    * 클라이언트가 Json형식으로 데이터를 보내면 서버에서 Json을 파싱해 알아서 DTO에 대해서 매핑 후 주입
    * 단, Json데이터의 키와 객체의 멤버변수의 이름과 일치시켜야 한다.
    * */

//    @PostMapping("/signin")
//    public String signin(@RequestBody SignInReqDto signInReqDto) {
//        return "로그인 성공 : " + signInReqDto.getUsername() + "님 반갑습니다.";
//    }

    /*
    * ResponseEntity
    * HTTP 응답 전체를 커스터마이징 해서 응답할 수 있는 스프링 클래스
    * 상태코드. 응답바디. 응답헤더 등등
    * 200
    * 400
    * 401 > 인증실패
    * 403 > 접근권한 없음
    * 404
    * 500
    * */
//    @PostMapping("/signup")
//    public ResponseEntity<SignUpRespDto> signup(@RequestBody SignUpReqDto signUpReqDto) {
//        if (signUpReqDto.getUsername() == null || signUpReqDto.getUsername().trim().isBlank()) {
//            SignUpRespDto signUpRespDto = new SignUpRespDto("failed", "username을 입력하세요");
//            return ResponseEntity.badRequest().body(signUpRespDto);
//        } else if (signUpReqDto.getPassword() == null || signUpReqDto.getPassword().trim().isBlank()) {
//            SignUpRespDto signUpRespDto = new SignUpRespDto("failed", "password를 입력하세요");
//            return  ResponseEntity.badRequest().body(signUpRespDto);
//        }
//        SignUpRespDto signUpRespDto = new SignUpRespDto("success", "회원가입 성공");
//        return ResponseEntity.ok(signUpRespDto);
//    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpRespDto> post(@RequestBody SignUpReqDto signUpReqDto) {
        return ResponseEntity.ok(userService.signup(signUpReqDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInRespDto> post(@RequestBody SignInReqDto signInReqDto) {
        return ResponseEntity.ok(userService.signin(signInReqDto));
    }

}

package com.korit.korit_gov_spring_boot_study.service;

import com.korit.korit_gov_spring_boot_study.dto.req.SignInReqDto;
import com.korit.korit_gov_spring_boot_study.dto.req.SignUpReqDto;
import com.korit.korit_gov_spring_boot_study.dto.resp.SignInRespDto;
import com.korit.korit_gov_spring_boot_study.dto.resp.SignUpRespDto;
import com.korit.korit_gov_spring_boot_study.entity.User;
import com.korit.korit_gov_spring_boot_study.repository.UserRepository;

public class UserService {
    private static UserService instance;
    private UserRepository userRepository;

    private UserService() {
        userRepository = UserRepository.getInstance();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public SignUpRespDto signup(SignUpReqDto signUpReqDto) {
        // 중복검사
        if (userRepository.findUserByUsername(signUpReqDto.getUsername()) != null) {
            return new SignUpRespDto("failed", "이미 사용중인 username입니다");
        }
        // 회원가입 처리
        userRepository.addUser(signUpReqDto.toEntity());
        // 이거에 따라서 응답 dto 다르게 반환
        return new SignUpRespDto("success", "회원가입이 완료되었습니다");
    }

    public SignInRespDto signin(SignInReqDto signInReqDto) {
        // 로그인
        // 해당 username된 데이터를 들고와요 > reqDto에 있는 password랑 들고온 password 비교
        User foundUser = userRepository.findUserByUsername(signInReqDto.getUsername());
        if (foundUser == null) {
            return new SignInRespDto("failed", "username이 일치하지 않습니다");
        }

        if (!foundUser.getPassword().equals(signInReqDto.getPassword())) {
            return new SignInRespDto("failed", "password가 일치하지 않습니다");
        }
        return new SignInRespDto("success", "성공적으로 로그인이 되었습니다.");
    }
}

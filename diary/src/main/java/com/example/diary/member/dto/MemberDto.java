package com.example.diary.member.dto;

import com.example.diary.member.entity.Member;
import com.example.diary.validator.NotSpace;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class MemberDto {
    //MemberDto.Post : 회원 가입 기능의 핸들러 메서드에 적용할 Dto
    //memberId, memberStatus 같은 자동으로 생성되는 값 말고는 모두 요청 데이터로 받아야한다.
    @AllArgsConstructor
    @Getter
    public static class Post{
        @NotEmpty(message = "이름을 반드시 입력해야 합니다.")
        @NotEmpty
        private String name;
        @NotEmpty
        @Email
        private String email;
        @NotEmpty //@Pattern 추가하기
        private String password;
        @NotNull(message = "휴대전화 번호를 반드시 입력해야 합니다.")
        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$")
        private String phoneNum;
        @NotEmpty
        private String address;
    }
    //MemberDto.Patch : 회원 정보(프로필) 수정 기능의 핸들러 메서드에 적용할 Dto
    //수정을 할 수 있는 항목을 모두 필드로 넣되, 부분 수정이 가능하도록 null 값을 허용해야 한다.
    //아마도 비즈니스 로직에서 null 인 값과 null 이 아닌 값을 구분하고, 기존 Member 객체의 필드를 수정하는 방식이 필요할 것 같다.
    @AllArgsConstructor
    @Getter
    public static class Patch{
        @NotSpace
        private String name;
        @NotSpace
        private String email;
        @NotSpace
        private String password;
        @NotSpace
        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$")
        private String phoneNum;
        @NotSpace
        private String address;
        private Member.MemberStatus memberStatus; //사용자의 데이터 입력이 아닌 버튼으로 구현할 부분

    }
    @AllArgsConstructor
    @Getter
    public static class Response{
        private Long memberId;
        private String name;
        private String email;
        private String password;
        private String phoneNum;
        private String address;
        private Member.MemberStatus memberStatus;
    }
}

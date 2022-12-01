package com.example.diary.member.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//회원 정보에 대한 엔티티
@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, updatable = false, unique = true)
    private String email; // 이메일은 회원간 중복이 없는 유일무이한 값으로 생각.
    @Column(nullable = false, updatable = false, unique = true)
    private String password; // 패스워드는 회원간 중복이 없는 유일무이한 값으로 생각.
    @Column(nullable = false)
    private String phoneNum;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE; //회원 가입 시, 기본값으로 "활성 계정"의 값을 지정.

    public enum MemberStatus{
        MEMBER_ACTIVE("활성 계정"),
        MEMBER_SLEEP("휴면 계정"),
        MEMBER_QUIT("탈퇴 상태");

        @Getter
        private String status;

        MemberStatus(String status) {
            this.status = status;
        }
    }
}

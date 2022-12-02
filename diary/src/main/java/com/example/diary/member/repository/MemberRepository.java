package com.example.diary.member.repository;

import com.example.diary.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByPassword(String password); //build 후 어떻게 생성되어있는지 분석 필요

}

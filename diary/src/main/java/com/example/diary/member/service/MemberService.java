package com.example.diary.member.service;

import com.example.diary.exception.BusinessLogicException;
import com.example.diary.exception.ExceptionCode;
import com.example.diary.member.entity.Member;
import com.example.diary.member.repository.MemberRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final ApplicationEventPublisher publisher; // 뭔지 파악 다시 하기.

    public MemberService(MemberRepository memberRepository, ApplicationEventPublisher publisher) {
        this.memberRepository = memberRepository;
        this.publisher = publisher;
    }

    public Member createMember(Member member){
        verifyExistMemberInfo(member.getEmail(), member.getPassword());
        Member savedMember = memberRepository.save(member);

        //publisher 이용 로직, 이해먼저하고 작성하기
        return savedMember;
    }

    public Member updateMember(Member member){
        return null;
    }

    public Member findMember(long memberId){
        return null;
    }

    public Page<Member> findPageMembers(int page, int size){
        return null;
    }

    public Member deleteMember(long memberId){
        return null;
    }
    //회원 가입(createMember) 시 사용하며, 해당 이메일과 패스워드가 이미 DB에 존재하는지 확인
    private void verifyExistMemberInfo(String email, String password){
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent()) //Optional.isPresent() : 값이 있으면(=null이 아니면) true, 값이 없으면(=null이면) false 반환
            throw new BusinessLogicException(ExceptionCode.MEMBER_EMAIL_EXIST);
        member = memberRepository.findByPassword(password);
        if(member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_PASSWORD_EXIST);
    }

    //기타 서비스로직들에 사용하며, 요구하는 사용자의 식별자(memberId)를 통해 DB에서 정보를 가져오는 메서드
    private Member findVerifiedMember(long memberId){
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        //Optional.orElseThrow() : 값이 존재하면(null이 아니면) 값을 반환하고, 값이 존재하지 않으면(null이면) 예외를 발생시킴.
        //람다식 분석 필요
        Member findMember = optionalMember.orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findMember;
    }
}

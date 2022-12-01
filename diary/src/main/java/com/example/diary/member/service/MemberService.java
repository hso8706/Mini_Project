package com.example.diary.member.service;

import com.example.diary.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    public Member createMember(Member serviceMember){return null;}
    public Member updateMember(Member serviceMember){return null;}
    public Member findMember(long memberId){return null;}
    public Page<Member> findPageMembers(int page, int size){return null;}
    public Member deleteMember(long memberId){return null;}
}

package com.example.diary.member.mapper;

import com.example.diary.member.dto.MemberDto;
import com.example.diary.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostToMember(MemberDto.Post postRequest);
    Member memberPatchToMember(MemberDto.Patch patchRequest);
    MemberDto.Response memberToMemberResponse (Member member);
    List<MemberDto.Response> membersToMemberList (List<Member> members);
}

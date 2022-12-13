package com.example.diary.member.controller;

import com.example.diary.dtoUtils.MultiResponseDto;
import com.example.diary.dtoUtils.SingleResponseDto;
import com.example.diary.member.dto.MemberDto;
import com.example.diary.member.entity.Member;
import com.example.diary.member.mapper.MemberMapper;
import com.example.diary.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/members")
@Validated
@Slf4j
public class MemberController {
    private MemberService memberService;
    private MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }
    //회원 가입 핸들링 메서드
    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post post){
        Member serviceMember = mapper.memberPostToMember(post);
        Member responseMember = memberService.createMember(serviceMember);
        MemberDto.Response response = mapper.memberToMemberResponse(responseMember);

        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }
    //회원 정보 수정 핸들링 메서드
    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@Valid @RequestBody MemberDto.Patch patch,
                                      @Positive @PathVariable("member-id") long memberId){
        Member serviceMember = mapper.memberPatchToMember(patch);
        serviceMember.setMemberId(memberId);
        Member responseMember = memberService.updateMember(serviceMember);
        MemberDto.Response response = mapper.memberToMemberResponse(responseMember);

        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    //회원 조회 핸들링 메서드; 커뮤니티 기능, 특정한 회원을 조회
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@Positive @PathVariable("member-id") long memberId){
        Member responseMember = memberService.findMember(memberId);
        MemberDto.Response response = mapper.memberToMemberResponse(responseMember);

        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    //회원 조회 핸들링 메서드; 커뮤니티 기능, 회원 전체를 페이지로 조회
    @GetMapping
    public ResponseEntity getPageMembers(@Positive @RequestParam int page,
                                         @Positive @RequestParam int size){
        Page<Member> pageMember = memberService.findPageMembers(page-1, size);
        List<Member> responseMember = pageMember.getContent();
        List<MemberDto.Response> response = mapper.membersToMemberList(responseMember);

        return new ResponseEntity(new MultiResponseDto<>(response, pageMember), HttpStatus.OK);
    }
    //회원 삭제 핸들링 메서드
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@Positive @PathVariable("member-id") long memberId){
        memberService.deleteMember(memberId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

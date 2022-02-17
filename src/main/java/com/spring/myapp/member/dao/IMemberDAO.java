package com.spring.myapp.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.myapp.member.vo.MemberVO;

public interface IMemberDAO {

	void insertMember(MemberVO member); // 회원가입 메서드
	
	List<MemberVO> selectAllMembers(); // 전체회원조회 메서드 
	
	MemberVO checkMemberId(@Param("id") String id); // 아이디중복검사 , 일반자료(단일자료)를 보낼때는 @Param 사용 
	
	void deleteMember(@Param("id") String id, @Param("pw") String pw); // 회원삭제 

	void updateMember(MemberVO member); // 회원수정

}

package com.spring.myapp.member.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.myapp.member.vo.MemberVO;

public interface IMemberService {
	
	   void insertMember(MemberVO member);

	   List<MemberVO> selectAllMembers();
	   
	   MemberVO checkMemberId(String id);
	   
       void deleteMember(String id, String pw); 

       void updateMember(MemberVO member);
}

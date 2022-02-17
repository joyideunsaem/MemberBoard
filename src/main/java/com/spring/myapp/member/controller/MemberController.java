package com.spring.myapp.member.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.myapp.member.service.IMemberService;
import com.spring.myapp.member.vo.MemberVO;

@Controller
public class MemberController {
	
	// 객체 의존성주입 
	@Autowired
	IMemberService memberService; 
	
    // 가입페이지로 이동 
	@RequestMapping(value="member/join", method=RequestMethod.GET)
	public String memberJoin(Model model) {
		
		return "member/join_form";
	}
	
	// 가입페이지에서 가입버튼 눌렀을때 DB에 인서트 쿼리 날림 
	@RequestMapping(value="member/join", method=RequestMethod.POST)
	public String memberJoin(MemberVO member, Model model) {
		MemberVO check = memberService.checkMemberId(member.getId());
		if(check == null) {
			memberService.insertMember(member);
			model.addAttribute("id", member.getId());
			model.addAttribute("name", member.getName());
			return "member/join_ok";
		}else {
			model.addAttribute("id", member.getId());
			return "member/join_fail";
		}
	}
	
	// 전체회원 조회 
	@RequestMapping(value="member/list", method=RequestMethod.GET)
	public String getAllMemberList(Model model) {
		List<MemberVO> members = memberService.selectAllMembers();
		model.addAttribute("members", members);
		return "member/member_list";
	} 
	
	// 로그인 페이지로 이동 
	@RequestMapping(value="member/login", method=RequestMethod.GET)
	public String memberLogin(Model model) {
		return "member/login_form";
	}
	
	// 로그인페이지에서 서브밋 눌렀을때 도는 메소드 
	@RequestMapping(value="member/login", method=RequestMethod.POST)
	public String memberLogin(String id, String pw, HttpSession session, Model model) {
		MemberVO check = memberService.checkMemberId(id);
		if(check != null) {
			if(pw.equals(check.getPw())){
				// 세션 발급 (로그인이 된 상태) 
				session.setAttribute("id", check.getId());
				session.setAttribute("name", check.getName());
				
				model.addAttribute("id", check.getId());
				model.addAttribute("name", check.getName());
				return "member/login_welcome";
			}else {
				model.addAttribute("id_fail",id);
				return "member/login_fail_pw";
			}
		}
		else {
			model.addAttribute("id_fail", id);
			return "member/login_fail_id";
		}
	}
	
	//로그아웃 (세션 비워버리는 메소드)
	@RequestMapping(value="/member/logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		return "redirect:../"; 
	}
	
	// 회원탈퇴 페이지 접속(로그인이 되어있다는 전제하에 사용)
	@RequestMapping(value="member/delete", method=RequestMethod.GET)
	public String memberDelete(HttpSession session, Model model) {
		String id = (String)session.getAttribute("id");
		model.addAttribute("id",id);
		return "member/delete_form";
	}
	
	// 회원탈퇴 (비번 맞게 입력시) 시키는 메소드 
	@RequestMapping(value="member/delete", method=RequestMethod.POST)
	public String memberDelete(String id, String pw, HttpSession session, Model model) {
		
		MemberVO member = memberService.checkMemberId(id);
		if(!pw.equals(member.getId())) {
			// 비번 틀렸을 경우, 자동 로그아웃시킴 
			session.invalidate();
			return "member/delete_fail";
		}
		else {
			// 비번 맞았을 경우, DB에서 회원삭제 진행 후 자동로그아웃 
			memberService.deleteMember(id, pw);
			session.invalidate();
			return "member/delete_ok";
		}
	}
	
	// 회원수정 페이지 접속(로그인이 되어있다는 전제하에 사용)
	@RequestMapping(value="member/update", method=RequestMethod.GET)
    public String memberUpdate(HttpSession session, Model model) {
    	String id = (String)session.getAttribute("id");
		MemberVO member = memberService.checkMemberId(id);
    	model.addAttribute("member", member);
		return "member/update_form";
    }	
	
	// 회원수정 서브밋 했을때 진행되는 메소드 
	@RequestMapping(value="member/update", method=RequestMethod.POST)
    public String memberUpdate(MemberVO member, Model model) {
		memberService.updateMember(member); 
		return "redirect:../";
    }	
	
	
	
}

package com.spring.myapp.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.myapp.board.service.IBoardService;
import com.spring.myapp.board.vo.BoardVO;

@Controller
public class BoardController {

	@Autowired
	IBoardService boardService;

	// 게시글 쓰기 : 화면들어가기
	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public String writeBoard(HttpSession session, Model model) {
		if (session.getAttribute("id") == null) {
			return "redirect:../";
		}
		String id = (String) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		return "/board/board_form";
	}

	// 게시글 쓰기 : form 에서 파라미터 받아오기
	@RequestMapping(value = "/board/write", method = RequestMethod.POST)
	public String writeBoard(String name, String title, String content, Model model) {
		boardService.writeBoard(name, title, content);
		return "redirect:../";
	}

	// 전체글 리스트조회
	@RequestMapping(value = "/board/view", method = RequestMethod.GET)
	public String showBoard(HttpSession session, Model model) {
		if (session.getAttribute("id") == null) {
			return "redirect:../";
		}
		List<BoardVO> boards = boardService.getBoardList();
		model.addAttribute("boards", boards);
		return "board/board_list";
	}

	// 상세글 보기
	@RequestMapping(value = "/board/detail/{boardnum}")
	public String showBoardDetail(@PathVariable int boardnum, HttpSession session, Model model) {
		if (session.getAttribute("id") == null) {
			return "redirect:../";
		}
		BoardVO detail = boardService.getBoardContent(boardnum);
		if (detail == null) {
			return "redirect:../list";
		}
		model.addAttribute("detail", detail);
		return "board/board_detail";
	}

	// 글삭제
	@RequestMapping(value="/board/delete/{boardnum}")
	public String boardDelete( @PathVariable int boardnum, HttpSession session, Model model) {
		if(session.getAttribute("id") == null) {
			return "redirect:../";  //로그인 안되있을때 홈화면으로 보내기 
		}
		BoardVO board =boardService.getBoardContent(boardnum);
		String uname = (String)session.getAttribute("name");
		if(uname.equals(board.getName())){  //세션의 이름과 글쓴이 이름이 같을때만 삭제 가능 
			boardService.deleteBoard(boardnum);
			
			return "redirect:../"; 
		}
		return "redirect:../list";
	}
	
	// 글수정 페이지 들어가기 
	@RequestMapping(value="/board/update/{boardnum}", method=RequestMethod.GET)
	public String updateContent(@PathVariable int boardnum, HttpSession session, Model model) {
	if(session.getAttribute("id") == null) { // 로그인검사
		return "redirect:../";
	}	
	BoardVO board = boardService.getBoardContent(boardnum);
	String uname = (String)session.getAttribute("name");  // 본인확인 
	if(!uname.equals(board.getName())) {
		return "redirect:../";
	}
	model.addAttribute("board", board);  // 글수정 페이지에 뿌릴 데이터 넘기기 
	return "board/board_update";
	
	}
	
	// 글수정 폼에서 날려주는 데이터 받아서 수정 처리 
	@RequestMapping(value="/board/update/", method=RequestMethod.POST)
	public String updateContent(BoardVO board, HttpSession session, Model model) {
	if(session.getAttribute("id") == null) { // 로그인검사
		return "redirect:../";
	}	
	String uname = (String)session.getAttribute("name");  // 본인확인 
	if(!uname.equals(board.getName())) {
		return "redirect:../";
	}
	boardService.updateBoard(board); // 업데이트하기 
    return "redirect:../list";
	
	}

}
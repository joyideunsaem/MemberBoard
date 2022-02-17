package com.spring.myapp.board.service;

import java.util.List;

import com.spring.myapp.board.vo.BoardVO;

public interface IBoardService {

	void writeBoard(String name, String title, String content);

	List<BoardVO> getBoardList(); // 전체글 리스트 조회
	
	BoardVO getBoardContent(int boardnum); // 단일글 조회
	
	void deleteBoard(int boardnum); // 글삭제 
	
	void updateBoard(BoardVO board); // 글수정 
}

package com.spring.myapp.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.myapp.board.vo.BoardVO;

public interface IBoardDAO {
	
	// 글쓰기
	void writeBoard(@Param("name") String name,    
			        @Param("title") String title, 
			        @Param("content") String content);

	// 글 리스트 조회
	List<BoardVO> getBoardList();

	// 단일글 조회
	BoardVO getBoardContent(@Param("boardnum") int boardnum);
	
	// 글삭제 
	void deleteBoard(@Param("boardnum") int boardnum); 
	
	// 글수정
	void updateBoard(BoardVO board); 
}

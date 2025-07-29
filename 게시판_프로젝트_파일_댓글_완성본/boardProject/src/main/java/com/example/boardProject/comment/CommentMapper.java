package com.example.boardProject.comment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
	// 댓글 생성
	int createComment(CommentDTO dto);

	// 댓글 리스트 조회 게시판 번호 기준
	List<CommentDTO> findAllByBoardIdx(int boardIdx);

}

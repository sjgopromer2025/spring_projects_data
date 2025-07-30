package com.example.boardProject.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	private final CommentMapper commentMapper;

	@Autowired
	public CommentService(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}

	// 댓글 입력 결과 응답
	public boolean createComment(CommentDTO commentDto) {
		try {
			int insertResult = commentMapper.createComment(commentDto);
			if (insertResult > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	// 게시글에 달린 댓글 리스트 반환 메서드
	public List<CommentDTO> findAllByBoardIdx(int boardIdx) {
		return commentMapper.findAllByBoardIdx(boardIdx);
	}

}

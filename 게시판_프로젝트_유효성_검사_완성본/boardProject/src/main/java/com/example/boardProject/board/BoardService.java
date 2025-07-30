package com.example.boardProject.board;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BoardService {
	private final BoardMapper boardMapper;

	public BoardService(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

//	List<BoardDTO> findAll();

	// 게시글 전체 조회
	public List<BoardDTO> findAll() {

		return boardMapper.findAll();
	}

	// 게시글 정보 저장
	public boolean createBoard(BoardDTO boardDTO) {
		try {
			// 데이터 처리를 하는 코드를 ~~~~
			int insertReuslt = boardMapper.createBoard(boardDTO);
			return false;
//			return insertReuslt > 0; // true 혹은 false
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

//	BoardDTO findBoardById(int idx);
	// 단일 게시글 조회
	public BoardDTO findBoardById(int idx) {
		try {
			BoardDTO board = boardMapper.findBoardById(idx);

			return board; // null 혹은 게시글 정보
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

//	int updateBoard(BoardDTO boardDTO);
	// 게시글 수정
	public boolean updateBoard(BoardDTO boardDTO) {
		try {
			int updateResult = boardMapper.updateBoard(boardDTO);
			return updateResult > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

//	int deleteBoard(int idx);
	// 게시글 삭제
	public boolean deleteBoard(int idx) {
		try {
			int deleteResult = boardMapper.deleteBoard(idx);
			return deleteResult > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}

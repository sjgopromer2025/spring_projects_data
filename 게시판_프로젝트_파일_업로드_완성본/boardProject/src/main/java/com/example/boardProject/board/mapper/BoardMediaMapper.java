package com.example.boardProject.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.boardProject.board.dto.BoardMediaDTO;

@Mapper
public interface BoardMediaMapper {
	// 파일 정보 저장
	int insertMedia(BoardMediaDTO boardMediaDTO);

	// 게시글에 연결된 모든 미디어 조회
	List<BoardMediaDTO> findMediaByBoardIdx(int boardIdx);

	// 파일 다운로드를 위한 단일 파일 정보 단일 조회
	BoardMediaDTO findMediaByIdx(int mediaIdx);

}

package com.example.boardProject.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.boardProject.board.dto.BoardDTO;

@Mapper
public interface BoardMapper {

//	<select id="findAll" resultType="boarddto">
	// 리턴타입 메서드명();
	// 게시글 전체 조회
	List<BoardDTO> findAll();

//	<insert id="createBoard" parameterType="boarddto">
	// 게시글 생성
	int createBoard(BoardDTO boardDTO);

//	<select id="findBoardById" parameterType="int" resultType="boarddto">
	// 단일 게시글 조회
	BoardDTO findBoardById(int idx);

//	<update id="updateBoard" parameterType="boarddto">
	// 게시글 수정
	int updateBoard(BoardDTO boardDTO);

//	<delete id="deleteBoard" parameterType="int">
	// 게시글 삭제
	int deleteBoard(int idx);

//	<select id = "getTotalCount" resultType="int">
	// 전체 게시물 수 조회
	int getTotalCount();

//	<select id="findPage" resultType="boarddto">
	// 페이지별 게시글 조회
//	List<BoardDTO> findPage(@Param("size") int size, @Param("offset") int offset);
	List<BoardDTO> findPage(int size, int offset);

}

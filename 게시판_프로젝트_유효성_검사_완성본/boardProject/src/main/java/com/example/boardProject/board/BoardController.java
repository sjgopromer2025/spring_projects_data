package com.example.boardProject.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/board")
public class BoardController {

	private final BoardService boardService;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	// 게시글 목록 페이지
	@GetMapping("/list")
	public String listPage(Model model) {
		// 게시판 목록 데이터 조회
		List<BoardDTO> boards = boardService.findAll();
		// view에 전달할 데이터 설정
		model.addAttribute("boards", boards);

		return "board/list_jstl"; // WEB-INF/view/board/list_jstl.jsp
		// return "board/list"; // WEB-INF/view/board/list.jsp
	}

	// 게시글 작성 페이지
	@GetMapping("/write")
	public String writePage() {
		return "board/write";
	}

	// 게시글 작성 정보 저장
	@PostMapping("/create")
	public String createBoard(@Valid BoardDTO boardDTO, BindingResult bindgReuslt,
			RedirectAttributes redirectAttributes) {
		// 유효성 검사
		if (bindgReuslt.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindgReuslt);
			return "redirect:/board/write";
		}

		boolean result = boardService.createBoard(boardDTO);

		if (result) {
			// true 정상적으로 값이 저장됐을 경우
			return "redirect:/board/list";
		} else {
			// false 값이 제대로 저장이 안됐을 경우
			redirectAttributes.addFlashAttribute("error", "게시글 작성에 실패했습니다.");
			return "redirect:/board/write";
		}
	}

	// 상세보기 페이지
	@GetMapping("/detail/{idx}") // /board/detail/
	public String detailPage(@PathVariable int idx, Model model, RedirectAttributes redirectAttributes) {
		BoardDTO board = boardService.findBoardById(idx); // null 값이 있는거

		if (board == null) {
			redirectAttributes.addFlashAttribute("error", "해당 게시글 정보가 없습니다.");
			return "redirect:/board/list";
		}

		model.addAttribute("board", board);
		return "board/detail";

	}

//	/board/edit/${board.idx}
	@GetMapping("/edit/{idx}")
	public String editPage(@PathVariable int idx, Model model, RedirectAttributes redirectAttributes) {
		BoardDTO board = boardService.findBoardById(idx); // null 값이 있는거

		if (board == null) {
			redirectAttributes.addFlashAttribute("error", "해당 게시글 정보가 없습니다.");
			return "redirect:/board/list";
		}

		model.addAttribute("board", board);
		return "board/edit";
	}

//	<form id="edit-form" action="/board/update" method="post">
	@PostMapping("/update")
	public String updateProcess(BoardDTO boardDTO, RedirectAttributes redirectAttributes) {
		boolean result = boardService.updateBoard(boardDTO);
		if (result) {
			return "redirect:/board/detail/" + boardDTO.getIdx();
		} else {
			redirectAttributes.addFlashAttribute("error", "게시글 수정 과정 중 오류가 발생했습니다.");
			return "redirect:/board/detail/" + boardDTO.getIdx();
		}
	}

//	url:"/board/delete/" + idx,
//	method:"POST"
	@PostMapping("/delete/{idx}")
	@ResponseBody
	public String deleteProcess(@PathVariable int idx) {
//		return "fail";
		boolean result = boardService.deleteBoard(idx);
		return result ? "success" : "fail";
	}
}

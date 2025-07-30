package com.example.boardProject.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.boardProject.board.dto.BoardDTO;
import com.example.boardProject.board.dto.PaginationDTO;
import com.example.boardProject.comment.CommentDTO;
import com.example.boardProject.comment.CommentService;

@Controller
@RequestMapping("/board")
public class BoardController {

	private final BoardService boardService;
	private final CommentService commentService;

	public BoardController(BoardService boardService, CommentService commentService) {
		this.boardService = boardService;
		this.commentService = commentService;
	}

//	// 게시글 목록 페이지
//	@GetMapping("/list")
//	public String listPage(Model model) {
//		// 게시판 목록 데이터 조회
//		List<BoardDTO> boards = boardService.findAll();
//		// view에 전달할 데이터 설정
//		model.addAttribute("boards", boards);
//
//		return "board/list_jstl"; // WEB-INF/view/board/list_jstl.jsp
//		// return "board/list"; // WEB-INF/view/board/list.jsp
//	}

	// 게시글 목록 페이지
	@GetMapping("/list")
	public String listPage(@RequestParam(defaultValue = "1") int page, // 현재 페이지 (기본값 1)
			@RequestParam(defaultValue = "10") int size, // 한 페이지당 게시글 수 (기본값 10)
			Model model) {

		// 전체 게시글 수 조회
		int totalCount = boardService.getTotalCount();

		// 페이지네이션 정보 생성 (blockLimit = 10 → 1~10, 11~20)
		PaginationDTO pagination = new PaginationDTO(page, totalCount, size, 10);

		// 현재 페이지에 맞는 게시글 목록 조회
		List<BoardDTO> boards = boardService.findPage(page, size);

		// View로 데이터 전달
		model.addAttribute("boards", boards); // 게시글 목록
		model.addAttribute("pagination", pagination); // 페이지네이션 정보

		return "board/list_jstl"; // /WEB-INF/view/board/list_jstl.jsp
	}

	// 게시글 작성 페이지
	@GetMapping("/write")
	public String writePage() {
		return "board/write";
	}

	// 게시글 작성 정보 저장
	@PostMapping("/create")
	public String createBoard(BoardDTO boardDTO, RedirectAttributes redirectAttributes) {
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
		// 해당 게시글에 연결된 댓글 리스트 조회
		List<CommentDTO> commentList = commentService.findAllByBoardIdx(idx);

		model.addAttribute("board", board);
		model.addAttribute("commentList", commentList);
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

	// 업로드 페이지 (test)
	@GetMapping("/upload")
	public String uploadPage() {
		return "fileUp/upload_ajax_single";
	}

//	// Form 전송:
//	@PostMapping("/upload")
//	public String upload(@ModelAttribute BoardDTO boardDTO) {
//		System.out.println(boardDTO.toString());
//		boardService.uploadFile(boardDTO);
//
//		return "정보 확인용 테스트 컨트롤러";
//	}

	// ajax를 사용한 단일 파일 처리
	@PostMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam("imageFile") MultipartFile file) {
		System.out.println("form 필드 이름: " + file.getName());
		System.out.println("사용자 파일 이름: " + file.getOriginalFilename());
		System.out.println("MIME 타입: " + file.getContentType());
		System.out.println("파일 크기: " + file.getSize() + " bytes");

		return "정보 확인용 테스트 컨트롤러";
	}

	// 다중 파일:
//	@PostMapping("/upload")
////	public String upload(@RequestParam("imageFiles") MultipartFile[] files) {
//	public String upload(@RequestParam("imageFiles") List<MultipartFile> files) {
//
//		return "정보 확인용 테스트 컨트롤러";
//	}

}

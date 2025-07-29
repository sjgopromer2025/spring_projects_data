package com.example.boardProject.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/comment") // "/comment" 경로로 들어오는 요청을 처리
public class CommentController {

	private final CommentService commentService; // 비즈니스 로직을 처리하는 서비스

	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping("/write")
	@ResponseBody
	public String createComment(CommentDTO commentDto) {
		return commentService.createComment(commentDto) ? "success" : "fail";
	}
}

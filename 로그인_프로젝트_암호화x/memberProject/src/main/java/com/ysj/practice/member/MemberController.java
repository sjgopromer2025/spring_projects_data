package com.ysj.practice.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

//회원 관련 요청을 처리하는 컨트롤러 클래스
@Controller // 스프링 MVC에서 컨트롤러 역할을 지정 (JSP 페이지 반환 가능)
@RequestMapping("/member") // 이 컨트롤러는 "/member"로 시작하는 URL을 처리
public class MemberController {

	private final MemberService memberService; // 회원 서비스 계층 의존성

	// 생성자 주입 방식으로 MemberService를 주입받음
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	// 로그인 페이지 요청 처리
	@GetMapping("/login") // GET 방식으로 "/member/login" 요청이 들어왔을 때 실행
	public String showLoginForm(Model model) {
		// 로그인 JSP 페이지로 이동
		// => /WEB-INF/views/member/login.jsp 로 포워딩됨
		return "member/login";
	}

	// 로그인 처리
	@PostMapping("/login") // "/member/login" 경로로 POST 방식 요청이 들어오면 실행됨
	public String login(MemberDTO memberDTO, // 사용자가 입력한 아이디/비밀번호가 자동으로 담김
			RedirectAttributes redirectAttributes, // 리다이렉트 시 메시지 전달용
			HttpSession session) { // 로그인 성공 시 사용자 정보 저장용 세션

		// 서비스에 로그인 요청 (DB 조회)
		MemberDTO loginMember = memberService.loginMember(memberDTO);

		if (loginMember != null) {
			// 로그인 성공 시
			// 세션에 사용자 정보를 저장함 (로그인 상태 유지)
			session.setAttribute("loginMember", loginMember);

			// 메인페이지 또는 마이페이지 등으로 리다이렉트
			return "redirect:/member/myPage";
		} else {
			// 로그인 실패 시
			// 실패 메시지를 리다이렉트로 전달 (1회성 메시지)
			redirectAttributes.addFlashAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");

			// 다시 로그인 페이지로 리다이렉트
			return "redirect:/member/login";
		}
	}

	// 회원가입 페이지 요청 처리
	@GetMapping("/join") // GET 방식으로 "/member/join" 요청이 들어오면
	public String showJoinForm(Model model) {
		// 회원가입 JSP 페이지로 이동
		// => /WEB-INF/views/member/join.jsp 로 포워딩됨
		return "member/join";
	}

	// 회원가입 처리 (POST 요청)
	@PostMapping("/join") // /member/join 경로로 POST 요청이 들어오면 실행됨
	public String createMember(@ModelAttribute MemberDTO memberDTO, Model model,
			RedirectAttributes redirectAttributes) {
		// @ModelAttribute 생략 가능 다음 코드부턴 생략 , 생략해도 된다 → Spring이 자동으로 처리
		// 폼에서 입력한 값을 자바 객체(DTO)에 담아주는 기능

		// 회원가입 요청 데이터를 MemberDTO로 받아서 서비스에 전달
		boolean result = memberService.createMember(memberDTO);

		if (result) {
			// 성공 시: 메시지 전달 + 리다이렉트
			redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다.");
			return "redirect:/member/login";
		} else {
			// 실패 시 에러 메시지를 담고 다시 회원가입 폼으로 이동
			model.addAttribute("error", "회원가입에 실패했습니다. 다시 시도해 주세요.");
			return "member/join"; // → /WEB-INF/views/member/join.jsp
		}
	}

	// 마이페이지 요청
	@GetMapping("/myPage")
	public String showMyPage(HttpSession session, Model model) {

		// 세션에서 로그인 사용자 정보 꺼내오기
		MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");

		// 로그인된 사용자가 없으면 → 로그인 페이지로 리다이렉트
		if (loginMember == null) {
			return "redirect:/member/login";
		}

		// 로그인된 사용자 정보 모델에 담기 (뷰로 전달)
		model.addAttribute("member", loginMember);

		return "member/myPage"; // → /WEB-INF/views/member/myPage.jsp
	}

	// 로그아웃 처리
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 현재 로그인된 사용자 세션 삭제
		session.invalidate();

		// 로그아웃 후 로그인 페이지로 리다이렉트
		return "redirect:/member/login";
	}

	// 회원정보 수정 페이지 요청 처리
	@GetMapping("/edit")
	public String showEditForm(HttpSession session, Model model) {
		MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");

		// 로그인 안 되어 있으면 로그인 페이지로
		if (loginMember == null) {
			return "redirect:/member/login";
		}

		// 로그인한 사용자 정보를 모델에 담아서 edit.jsp로 전달
		model.addAttribute("member", loginMember);

		return "member/edit"; // → /WEB-INF/views/member/edit.jsp 로 forward
	}

	// 회원 정보 수정 처리
	@PostMapping("/edit") // "/member/edit" 경로로 POST 요청이 들어오면 실행됨
	public String updateMember(@ModelAttribute MemberDTO memberDTO, // 수정된 사용자 정보가 DTO로 바인딩됨
			RedirectAttributes redirectAttributes, // 리다이렉트 시 메시지를 전달하기 위한 객체
			HttpSession session) { // 현재 로그인 중인 사용자 세션

		// 서비스로 회원 수정 요청 전달
		boolean result = memberService.updateMember(memberDTO);

		if (result) {
			// 수정 성공 시
			// 세션에 저장된 사용자 정보도 갱신 (마이페이지 등에 반영되도록)
			session.setAttribute("loginMember", memberDTO);

			// 리다이렉트 메시지 설정
			redirectAttributes.addFlashAttribute("message", "회원 정보가 수정되었습니다.");

			// 마이페이지로 리다이렉트
			return "redirect:/member/myPage";
		} else {
			// 수정 실패 시

			// 실패 메시지 설정
			redirectAttributes.addFlashAttribute("message", "회원 정보 수정에 실패했습니다.");

			// 수정 페이지로 다시 리다이렉트
			return "redirect:/member/edit";
		}
	}

	// 회원 탈퇴 요청 처리
	@GetMapping("/delete")
	public String deleteMember(HttpSession session, RedirectAttributes redirectAttributes) {

		// 로그인한 사용자 정보 가져오기
		MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");

		// 로그인 상태가 아니면 로그인 페이지로
		if (loginMember == null) {
			return "redirect:/member/login";
		}

		boolean result = memberService.deleteMember(loginMember.getUserId());

		if (result) {
			// 성공 시: 세션 종료 + 메시지 전달 + 로그인 페이지로 이동
			session.invalidate(); // 세션 초기화
			redirectAttributes.addFlashAttribute("message", "회원 탈퇴가 완료되었습니다.");
			return "redirect:/member/login";
		} else {
			// 실패 시: 메시지 전달 + 마이페이지로 이동
			redirectAttributes.addFlashAttribute("message", "회원 탈퇴에 실패했습니다.");
			return "redirect:/member/myPage";
		}
	}

}

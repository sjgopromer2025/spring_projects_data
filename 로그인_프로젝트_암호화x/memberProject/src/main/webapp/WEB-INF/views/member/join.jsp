<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>회원가입</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="/js/jquery-3.7.1.min.js"></script>
</head>
<body>
	
	

	<!-- JS 알림창으로 변경 -->
	<c:if test="${not empty error}">
	    <script>
	        alert("${error}");
	    </script>
	</c:if>

	<div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
	    <div class="card shadow p-4" style="width: 100%; max-width: 500px;">
	        <h3 class="text-center mb-4">회원가입</h3>
	        <form action="/member/join" method="post" id="joinForm">
	            <div class="mb-3">
	                <label for="userId" class="form-label">아이디</label>
	                <input type="text" class="form-control" id="userId" name="userId" required>
	            </div>
	            <div class="mb-3">
	                <label for="userPw" class="form-label">비밀번호</label>
	                <input type="password" class="form-control" id="userPw" name="userPw" required>
	            </div>
	            <div class="mb-3">
	                <label for="userName" class="form-label">이름</label>
	                <input type="text" class="form-control" id="userName" name="userName" required>
	            </div>
	            <div class="mb-3">
	                <label for="userEmail" class="form-label">이메일</label>
	                <input type="email" class="form-control" id="userEmail" name="userEmail" required>
	            </div>
	            <div class="mb-3">
	                <label for="userRole" class="form-label">역할</label>
	                <select class="form-select" id="userRole" name="userRole">
	                    <option value="0">일반 사용자</option>
	                    <option value="1">관리자</option>
	                </select>
	            </div>
	            <div class="d-grid">
	                <button type="submit" class="btn btn-success">회원가입</button>
	            </div>
	        </form>
	        <div class="mt-3 text-center">
	            <a href="/member/login">로그인 페이지로 돌아가기</a>
	        </div>
	    </div>
	</div>
	
	<!-- 예시: jQuery로 입력값 확인 -->
	<script>
	    $("#joinForm").on("submit", function (e) {
	        const userId = $("#userId").val().trim();
	        if (userId.length < 4) {
	            alert("아이디는 4자 이상이어야 합니다.");
	            e.preventDefault();
	        }
	    });
	</script>

</body>
</html>

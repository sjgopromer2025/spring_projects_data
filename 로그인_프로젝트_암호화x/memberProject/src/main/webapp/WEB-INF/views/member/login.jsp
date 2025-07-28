<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>로그인</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="/js/jquery-3.7.1.min.js"></script>
</head>
<body>
	
	<!-- 로그인 폼 -->
	<div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
	    <div class="card shadow p-4" style="width: 100%; max-width: 400px;">
	        <h3 class="text-center mb-4">로그인</h3>
	        <form action="/member/login" method="post">
	            <div class="mb-3">
	                <label for="userId" class="form-label">아이디</label>
	                <input type="text" class="form-control" id="userId" name="userId" required>
	            </div>
	            <div class="mb-3">
	                <label for="userPw" class="form-label">비밀번호</label>
	                <input type="password" class="form-control" id="userPw" name="userPw" required>
	            </div>
	            <div class="d-grid">
	                <button type="submit" class="btn btn-primary">로그인</button>
	            </div>
	        </form>
	        <div class="mt-3 text-center">
	            <a href="/member/join">회원가입</a>
	        </div>
	    </div>
	</div>

	<!-- JS 알림창으로 변경 -->
	<c:if test="${not empty message}">
	    <script>
	        alert("${message}");
	    </script>
	</c:if>
	
	

	<!-- 메시지 모달 -->
	<div class="modal fade" id="messageModal" tabindex="-1" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header bg-primary text-white">
	        <h5 class="modal-title">알림</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="닫기"></button>
	      </div>
	      <div class="modal-body">
	        <c:if test="${not empty message}">
	            <p>${message}</p>
	        </c:if>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	      </div>
	    </div>
	  </div>
	</div>
	

	<!-- ✅ 메시지 모달 실행 스크립트 -->
	<c:if test="${not empty message}">
	<script>
	    $(document).ready(function() {
	        var myModal = new bootstrap.Modal($('#messageModal'));
	        myModal.show();
	    });
	</script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	</c:if>
	
	
	
</body>
</html>

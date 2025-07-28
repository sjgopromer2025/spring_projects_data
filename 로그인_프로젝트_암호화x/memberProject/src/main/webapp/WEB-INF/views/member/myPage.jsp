<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>마이페이지</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="/js/jquery-3.7.1.min.js"></script>
</head>
<body class="container py-5">

    <h2 class="mb-4">마이페이지</h2>

    <div class="card p-4">
        <p><strong>아이디:</strong> ${member.userId}</p>
        <p><strong>이름:</strong> ${member.userName}</p>
        <p><strong>이메일:</strong> ${member.userEmail}</p>
        <p><strong>가입일:</strong> ${member.createdAt}</p>
        <p><strong>권한:</strong> 
            <c:choose>
                <c:when test="${member.userRole == 1}">관리자</c:when>
                <c:otherwise>일반 사용자</c:otherwise>
            </c:choose>
        </p>
    </div>
	
    <!-- 버튼 영역 -->
    <div class="mt-4">
        <a href="/member/edit" class="btn btn-warning">정보 수정</a>

        <!-- 회원 탈퇴 버튼: jQuery 처리용 id 추가 -->
        <button id="deleteBtn" class="btn btn-danger">회원 탈퇴</button>

        <a href="/member/logout" class="btn btn-secondary">로그아웃</a>
    </div>

	<!-- jQuery 스크립트: 탈퇴 확인 처리 -->
	<script>
	    $(document).ready(function() {
	        $('#deleteBtn').click(function() {
	            if (confirm('정말로 탈퇴하시겠습니까?')) {
	                // 확인 누르면 탈퇴 URL로 이동
	                window.location.href = '/member/delete';
	            }
	        });
	    });
	</script>
	
	
	
	
	

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

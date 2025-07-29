<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 상세 보기</title>
    <script src="/resources/js/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="/resources/css/common.css">
</head>
<body>
	
	<div class="container">
	    <h2>게시글 상세 보기</h2>
	
	    <div>
			<c:if test="${not empty error}">
			  <p class="error">${error}</p>
			</c:if>
			<p class="error"></p>
			
	        <div class="label">제목</div>
	        <div class="value">${board.title}</div>
	
	        <div class="label">내용</div>
	        <div class="value">${board.content}</div>
	
	        <div class="label">작성자</div>
	        <div class="value">${board.writer}</div>
	
	        <div class="label">작성일</div>
	        <div class="value">${board.createdAt}</div>
	    </div>
		<!-- 첨부 미디어 표시 -->
		<div class="label">첨부 미디어</div>
		<div class="media-container">
		    <c:forEach var="media" items="${boardMedia}">
		        <c:choose>
		            <c:when test="${media.mediaType eq 'image'}">
		                <div>
		                    <img src="${media.mediaPath}" alt="${media.originalName}">
		                    <br>
		                    <a href="/board/download/${media.idx}" download>${media.originalName} 다운로드</a>
		                </div>
		            </c:when>
		            <c:when test="${media.mediaType eq 'video'}">
		                <div>
		                    <video controls>
		                        <source src="${media.mediaPath}" type="video/mp4">
		                        브라우저가 video 태그를 지원하지 않습니다.
		                    </video>
		                    <br>
		                    <a href="/board/download/${media.idx}" download>${media.originalName} 다운로드</a>
		                </div>
		            </c:when>
		        </c:choose>
		    </c:forEach>
		</div>
	    <div class="btn-group">
	        <a href="/board/list" class="btn btn-list">목록으로</a>
	        <a href="/board/edit/${board.idx}" class="btn btn-edit">수정</a>
	        <button type="button" class="btn btn-delete" id="btn-delete" data-id="${board.idx}">삭제</button>
	    </div>
	</div>
	
	<script>
	    $(document).ready(function () {
	        $("#btn-delete").on("click", function () {
	            if (!confirm("정말 삭제하시겠습니까?")) {
					return;
				}
	
				<!-- const idx = $(this).data("id"); -->
				const idx = $("#btn-delete").data("id");
	
	         	$.ajax({
					url:"/board/delete/" + idx,
					method:"POST",
					success: function (res) {
						if(res === "success"){
							alert("삭제 완료");
		                    window.location.href = "/board/list";
						}
						
						if(res === "fail"){
							alert("삭제에 실패했습니다.");
		                    $(".error").text("게시글 삭제에 실패했습니다.");
						}
	                },
	                error: function () {
	                    alert("삭제 중 오류가 발생했습니다.");
	                }
				});
		
	        });
	    });
	</script>

</body>
</html>

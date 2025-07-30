<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>게시글 목록</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }
        h2 {
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .btn-write {
            margin-bottom: 20px;
        }
		/* 페이지네이션 스타일 */
		 .pagination {
		     margin-top: 20px;
		     text-align: center;
		 }
		 .pagination a, .pagination span {
		     display: inline-block;
		     margin: 0 5px;
		     padding: 8px 12px;
		     border: 1px solid #ddd;
		     text-decoration: none;
		     color: #333;
		 }
		 .pagination .active {
		     background-color: #007bff;
		     color: white;
		     border-color: #007bff;
		 }
		 .pagination a:hover {
		     background-color: #f2f2f2;
		 }
    </style>
</head>
<body>
	<script>
	    const errorMessage = "${error}";
	    if (errorMessage) { 
	        alert(errorMessage); // JS 경고창
	    }
	</script>

    <h2>📋 게시글 목록</h2>

    <div class="btn-write">
        <a href="/board/write">
            <button>글쓰기</button>
        </a>
    </div>

    <table>
        <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
            </tr>
        </thead>
        <tbody>
			<!--반복될 부분-->
			<c:choose>
			    <c:when test="${not empty boards}">
			        <c:forEach var="board" items="${boards}">
			            <tr>
			                <td>${board.idx}</td>
			                <td><a href="/board/detail/${board.idx}">${board.title}</a></td>
			                <td>${board.writer}</td>
			                <%-- <td><fmt:formatDate value="${board.createdAt}" pattern="yyyy년MM월dd일 HH:mm" /></td>--%>
							<td>${board.createdAt}</td>
			            </tr>
			        </c:forEach>
			    </c:when>
			    <c:otherwise>
			        <tr>
			            <td colspan="4">등록된 게시글이 없습니다.</td>
			        </tr>
			    </c:otherwise>
			</c:choose>
			<!--반복될 부분-->
        </tbody>
    </table>
	
	<!-- 페이지 버튼 -->
<!--	<div>-->
<!--	    <c:if test="${pagination.hasPrev}">-->
<!--	        <a href="?page=${pagination.startPage - 1}">이전</a>-->
<!--	    </c:if>-->

<!--	    <c:forEach var="page" begin="${pagination.startPage}" end="${pagination.endPage}">-->
			
<!--	        <a href="?page=${page}">${page}</a>-->
<!--	    </c:forEach>-->

<!--	    <c:if test="${pagination.hasNext}">-->
<!--	        <a href="?page=${pagination.endPage + 1}">다음</a>-->
<!--	    </c:if>-->
<!--	</div>-->
	
	<!-- 페이지네이션 -->
	<div class="pagination">
	    <!-- 이전 버튼 -->
	    <c:if test="${pagination.hasPrev}">
	        <a href="/board/list?page=${pagination.startPage - 1}">&laquo; 이전</a>
	    </c:if>

	    <!-- 페이지 번호 출력 -->
	    <c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="p">
	        <c:choose>
	            <c:when test="${p == pagination.currentPage}">
	                <span class="active">${p}</span>
	            </c:when>
	            <c:otherwise>
	                <a href="/board/list?page=${p}">${p}</a>
	            </c:otherwise>
	        </c:choose>
	    </c:forEach>

	    <!-- 다음 버튼 -->
	    <c:if test="${pagination.hasNext}">
	        <a href="/board/list?page=${pagination.endPage + 1}">다음 &raquo;</a>
	    </c:if>
	</div>
	
	
	
	
</body>
</html>

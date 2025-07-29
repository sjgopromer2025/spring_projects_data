<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>게시글 수정</title>
    
    <!-- jQuery -->
	<script src="/js/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="/css/common.css">
    
</head>
<body>
 
    <h2>게시글 수정</h2>

    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <!-- 수정 폼 시작 -->
    <form id="edit-form" action="/board/update" method="post">
        <!-- 수정 대상 게시글 번호 -->
        <input type="hidden" name="idx" value="${board.idx}">

        <label for="title">제목</label>
        <input type="text" name="title" id="title" value="${board.title}">

        <label for="content">내용</label>
        <textarea name="content" id="content">${board.content}</textarea>

        <label for="writer">작성자</label>
        <input type="text" name="writer" id="writer" value="${board.writer}">

        <button type="submit" class="btn btn-update">수정 완료</button>
        <a href="/board/detail/${board.idx}" class="btn btn-cancel">취소</a>
    </form>
	
    <script>
        $(document).ready(function () {
            $('#edit-form').on('submit', function (e) {
                const title = $('#title').val().trim();
                const content = $('#content').val().trim();
                const writer = $('#writer').val().trim();

                if (!title || !content || !writer) {
                    alert('제목, 내용, 작성자명을 모두 입력해주세요.');
                    e.preventDefault();
                }
            });
        });
    </script>
</body>
</html>

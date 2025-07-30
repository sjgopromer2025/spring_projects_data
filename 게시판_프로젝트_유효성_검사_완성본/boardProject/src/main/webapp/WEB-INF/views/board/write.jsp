<%@ page contentType="text/html; charset=UTF-8" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <title>게시글 작성</title>

    <!-- jQuery -->
    <script src="/js/jquery-3.7.1.min.js"></script>

    <!-- 기본 스타일 -->
    <style>
      body {
        font-family: Arial, sans-serif;
        margin: 40px;
        background-color: #f9f9f9;
      }

      h2 {
        color: #333;
      }

      form {
        background-color: #fff;
        border: 1px solid #ccc;
        padding: 25px;
        width: 400px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      }

      label {
        font-weight: bold;
        display: block;
        margin-top: 10px;
      }

      input[type="text"],
      textarea {
        width: 100%;
        padding: 8px;
        margin-top: 5px;
        box-sizing: border-box;
      }

      textarea {
        resize: vertical;
        height: 100px;
      }

      button {
        margin-top: 15px;
        padding: 10px 15px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
      }

      button:hover {
        background-color: #0056b3;
      }

      .error {
        color: red;
        margin-bottom: 15px;
      }

      .back-link {
        margin-top: 20px;
        display: inline-block;
        text-decoration: none;
        color: #007bff;
      }

      .back-link:hover {
        text-decoration: underline;
      }
    </style>
  </head>
  <body>
    <h2>게시글 작성</h2>

    <form id="write-form" action="/board/create" method="post">
      <c:if test="${not empty error}">
        <p class="error">${error}</p>
      </c:if>
      <!-- 제목 -->
      <label for="title">제목:</label>
      <input type="text" name="title" id="title" value="" />
	  <c:if test="${not empty errors and errors.hasFieldErrors('title')}"> 
		<div class="error">${errors.getFieldError('title').defaultMessage}</div>
	  </c:if>
      <!-- 내용 -->
      <label for="content">내용:</label>
      <textarea name="content" id="content"></textarea>
	  <c:if test="${not empty errors and errors.hasFieldErrors('content')}"> 
	  	<div class="error">${errors.getFieldError('content').defaultMessage}</div>
	  </c:if>

      <!-- 작성자 -->
      <label for="writer">작성자:</label>
      <input type="text" name="writer" id="writer" value="" />
	  <c:if test="${not empty errors and errors.hasFieldErrors('writer')}"> 
	  	<div class="error">${errors.getFieldError('writer').defaultMessage}</div>
	  </c:if>
	  
      <button type="submit">등록</button>
    </form>

    <a class="back-link" href="/board/list">← 목록으로 돌아가기</a>

    <script>
      $(document).ready(function () {
        $("#write-form").on("submit", function (e) {
          const title = $("#title").val().trim();
          const content = $("#content").val().trim();
          const writer = $("#writer").val().trim();
				  
          if (!title || !content || !writer) {
            alert("제목, 내용, 작성자명을 모두 입력해주세요.");
            e.preventDefault();
          }
        });
      });
    </script>
  </body>
</html>

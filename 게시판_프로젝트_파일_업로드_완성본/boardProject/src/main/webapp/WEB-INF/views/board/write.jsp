<%@ page contentType="text/html; charset=UTF-8" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <title>게시글 작성</title>

    <!-- jQuery -->
    <script src="/resources/js/jquery-3.7.1.min.js"></script>

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

    <form id="write-form" action="/board/create" method="post" enctype="multipart/form-data">
      <c:if test="${not empty error}">
        <p class="error">${error}</p>
      </c:if>
      <!-- 제목 -->
      <label for="title">제목:</label>
      <input type="text" name="title" id="title" value="" />

      <!-- 내용 -->
      <label for="content">내용:</label>
      <textarea name="content" id="content"></textarea>

      <!-- 작성자 -->
      <label for="writer">작성자:</label>
      <input type="text" name="writer" id="writer" value="" />
	  
	  
	  <!-- 단일 파일 업로드 -->
<!--  <label for="uploadFiles">파일 업로드:</label>-->
<!--  <input type="file" id="uploadFile" name="uploadFile" accept="image/*,video/*,audio/*" />-->

	  <!-- 다중 파일 업로드 -->
	  <label for="uploadFiles">파일 업로드:</label>
	  <input type="file" id="uploadFiles" name="uploadFiles" accept="image/*,video/*,audio/*" multiple/>	  
  

      <button type="submit">등록</button>
    </form>

    <a class="back-link" href="/board/list">← 목록으로 돌아가기</a>

	<script src="/resources/js/test.js"></script>

  </body>
</html>

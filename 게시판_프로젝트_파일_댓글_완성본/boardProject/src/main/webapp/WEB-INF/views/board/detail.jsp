<%@ page contentType="text/html; charset=UTF-8" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>게시글 상세 보기</title>
    <script src="/resources/js/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="/resources/css/common.css" />
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

      <div class="btn-group">
        <a href="/board/list" class="btn btn-list">목록으로</a>
        <a href="/board/edit/${board.idx}" class="btn btn-edit">수정</a>
        <button
          type="button"
          class="btn btn-delete"
          id="btn-delete"
          data-id="${board.idx}"
        >
          삭제
        </button>
      </div>
    </div>

    <section class="comment-container">
      <!-- 댓글 작성 영역-->
      <div class="comment-box">
        <h3>댓글 작성</h3>
        <!-- form 태그 제거 -->
        <input type="hidden" id="boardIdx" value="${board.idx}" />

        <div class="form-group">
          <label for="commentUser">작성자</label>
          <input type="text" id="userName" placeholder="작성자 이름" required />
        </div>

        <div class="form-group">
          <label for="commentContent">내용</label>
          <textarea
            id="content"
            rows="4"
            placeholder="댓글을 입력하세요"
            required
          ></textarea>
        </div>

        <button type="button" id="submitCommentBtn" class="submit-btn">
          댓글 등록
        </button>
      </div>
    </section>

    <section class="comment-container">
      <!-- 댓글 목록 영역-->
      <div class="comment-box">
        <h3>댓글 목록</h3>
        <ul class="custom-list">
          <c:choose>
            <c:when test="${empty commentList}">
              <li id="noneComment">작성된 댓글이 없습니다.</li>
            </c:when>
            <c:otherwise>
              <c:forEach var="comment" items="${commentList}">
                <li>
                  <strong>${comment.userName}</strong>
                  (${comment.createdAt})<br />
                  ${comment.content}
                </li>
              </c:forEach>
            </c:otherwise>
          </c:choose>
        </ul>
      </div>
    </section>

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


      $("#submitCommentBtn").click(function () {
          const boardIdx = $("#boardIdx").val();
          const userName = $("#userName").val().trim();
          const content = $("#content").val().trim();

          if (!userName || !content) {
            alert("작성자와 내용을 모두 입력해주세요.");
            return;
          }

          const data = {
            boardIdx: boardIdx,
            userName: userName,
            content: content,
          };

      	// 댓글 등록 AJAX 요청
      	$.ajax({
      	  url: "/comment/write",    // 요청 URL
      	  type: "POST",             // 요청 방식
      	  data: data,               // 폼 데이터 (ex: { userName: "", content: "" })
      	  success: function (res) {
      	    // 서버에서 응답 성공
      	    if (res === "success") {
      			location.reload();
      	    } else {
      	      // 서버 응답이 실패일 경우
      	      alert("댓글 등록 실패: " + res);
      	    }
      	  },

      	  error: function (xhr) {
      	    // AJAX 요청 자체가 실패했을 경우
      	    alert("에러 발생: " + xhr.responseText);
      	  }
      	});
        });
        });
    </script>
  </body>
</html>

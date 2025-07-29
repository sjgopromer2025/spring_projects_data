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
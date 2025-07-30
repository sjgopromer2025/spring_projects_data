<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
    </style>
</head>
<body>

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
            <tr>
                <td>1</td>
                <td><a href="/board/detail/1">첫 번째 게시글입니다</a></td>
                <td>홍길동</td>
                <td>2025-04-10</td>
            </tr>
            <tr>
                <td>2</td>
                <td><a href="/board/detail/2">두 번째 게시글입니다</a></td>
                <td>김영희</td>
                <td>2025-04-10</td>
            </tr>
            <tr>
                <td>3</td>
                <td><a href="/board/detail/3">세 번째 게시글입니다</a></td>
                <td>이철수</td>
                <td>2025-04-10</td>
            </tr>
			<!--반복될 부분-->
        </tbody>
    </table>

</body>
</html>

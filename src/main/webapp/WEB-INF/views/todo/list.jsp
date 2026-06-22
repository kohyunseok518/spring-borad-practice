<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Todo List</title>
</head>
<body>
<div class="container mt-4">

    <h1 class="mb-4">Todo 목록</h1>
    <a class="btn btn-primary mb-3" href="/todo/register">새 할 일 등록</a>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>번호(Tno)</th>
            <th>제목</th>
            <th>작성자</th>
            <th>마감일</th>
            <th>완료여부</th>
        </tr>
        </thead>
        <tbody>
        <%-- Todo 프로젝트에 맞게 responseDTO.dtoList로 변경 --%>
        <c:forEach var="dto" items="${responseDTO.dtoList}">
            <tr>
                <td>${dto.tno}</td>
                <td>
                        <%-- tno로 파라미터 변경 --%>
                    <a href="/todo/read?tno=${dto.tno}">${dto.title}</a>

                        <%-- 첨부파일 아이콘 표시 (이전 단계에서 구현한 기능) --%>
                    <c:if test="${not empty dto.attaches}">
                        <span class="badge bg-secondary">📎</span>
                    </c:if>
                </td>
                <td>${dto.writer}</td>
                <td>${dto.dueDate}</td>
                <td>${dto.finished ? "완료" : "진행중"}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>
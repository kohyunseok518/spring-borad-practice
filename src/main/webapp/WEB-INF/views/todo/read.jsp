<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Todo Read</title>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="row">
            <div class="col">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#">Navbar</a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                            <div class="navbar-nav">
                                <a class="nav-link active" aria-current="page" href="#">Home</a>
                                <a class="nav-link" href="#">Features</a>
                                <a class="nav-link" href="#">Pricing</a>
                                <a class="nav-link disabled">Disabled</a>
                            </div>
                        </div>
                    </div>
                </nav>
            </div>
        </div>

        <div class="row content">
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        Todo Read
                    </div>
                    <div class="card-body">

                        <div class="input-group mb-3">
                            <span class="input-group-text">TNO</span>
                            <input type="text" name="tno" class="form-control" value='<c:out value="${dto.tno}"/>' readonly>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">Title</span>
                            <input type="text" name="title" class="form-control" value='<c:out value="${dto.title}"/>' readonly>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">DueDate</span>
                            <input type="date" name="dueDate" class="form-control" value='<c:out value="${dto.dueDate}"/>' readonly>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">Writer</span>
                            <input type="text" name="writer" class="form-control" value='<c:out value="${dto.writer}"/>' readonly>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label" >Finished &nbsp;</label>
                            <input class="form-check-input" type="checkbox" name="finished" ${dto.finished?"checked":""} disabled >
                        </div>

                        <div class="my-4">
                            <h5>첨부파일</h5>
                            <c:choose>
                                <c:when test="${empty dto.attaches}">
                                    <div class="text-muted border p-3 bg-light rounded">첨부파일이 없습니다.</div>
                                </c:when>
                                <c:otherwise>
                                    <ul class="list-group mb-3">
                                        <c:forEach var="file" items="${dto.attaches}">
                                            <c:if test="${not empty file.ano}">
                                                <li class="list-group-item d-flex justify-content-between align-items-center">
                                                    <a href="/todo/download/${file.ano}" class="text-decoration-none fw-bold">
                                                        📁 ${file.filename}
                                                    </a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <div class="my-4">
                            <div class="float-end">
                                <button type="button" class="btn btn-primary btn-modify">Modify</button>
                                <button type="button" class="btn btn-secondary btn-list">List</button>
                            </div>
                        </div>

                        <script>
                            // 1. Modify 버튼 클릭 시 동작
                            document.querySelector(".btn-modify").addEventListener("click", function(e) {
                                // 현재 글 번호(tno)를 들고 수정(modify) 페이지로 이동
                                self.location = `/todo/modify?tno=${dto.tno}`;
                            }, false);

                            // 2. List 버튼 클릭 시 동작
                            document.querySelector(".btn-list").addEventListener("click", function(e) {
                                // 다시 목록 페이지로 이동
                                self.location = `/todo/list`;
                            }, false);
                        </script>

                    </div>
                </div>
            </div>
        </div>

    </div>

    <div class="row footer">
        <div class="row fixed-bottom" style="z-index: -100">
            <footer class="py-1 my-1">
                <p class="text-center text-muted">Footer</p>
            </footer>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Todo Modify</title>
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
                        Todo Modify
                    </div>
                    <div class="card-body">
                        <form action="/todo/modify" method="post">
                            <div class="input-group mb-3">
                                <span class="input-group-text">TNO</span>
                                <input type="text" name="tno" class="form-control" value='<c:out value="${dto.tno}"/>' readonly>
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">Title</span>
                                <input type="text" name="title" class="form-control" value='<c:out value="${dto.title}"/>'>
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">DueDate</span>
                                <input type="date" name="dueDate" class="form-control" value='<c:out value="${dto.dueDate}"/>'>
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">Writer</span>
                                <input type="text" name="writer" class="form-control" value='<c:out value="${dto.writer}"/>' readonly>
                            </div>
                            <div class="form-check">
                                <label class="form-check-label" >Finished &nbsp;</label>
                                <input class="form-check-input" type="checkbox" name="finished" ${dto.finished ? "checked" : ""}>
                            </div>

                            <div class="my-4">
                                <div class="float-end">
                                    <button type="button" class="btn btn-danger btn-remove">Remove</button>
                                    <button type="button" class="btn btn-primary btn-modify">Modify</button>
                                    <button type="button" class="btn btn-secondary btn-list">List</button>
                                </div>
                            </div>
                        </form>
                        <script>
                            const formObj = document.querySelector("form");

                            // 1. Remove (삭제) 버튼 클릭 시
                            document.querySelector(".btn-remove").addEventListener("click", function(e) {
                                e.preventDefault();
                                e.stopPropagation();
                                formObj.action = "/todo/remove"; // 전송 경로를 remove로 변경
                                formObj.method = "post";
                                formObj.submit(); // 폼 제출!
                            }, false);

                            // 2. Modify (수정) 버튼 클릭 시
                            document.querySelector(".btn-modify").addEventListener("click", function(e) {
                                e.preventDefault();
                                e.stopPropagation();
                                formObj.action = "/todo/modify"; // 전송 경로를 modify로 설정
                                formObj.method = "post";
                                formObj.submit(); // 폼 제출!
                            }, false);

                            // 3. List (목록) 버튼 클릭 시
                            document.querySelector(".btn-list").addEventListener("click", function(e) {
                                e.preventDefault();
                                e.stopPropagation();
                                self.location = "/todo/list"; // 폼을 제출하지 않고 단순 페이지 이동
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
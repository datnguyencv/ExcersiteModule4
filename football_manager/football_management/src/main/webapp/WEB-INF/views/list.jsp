<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Football Management List</title>
</head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
    img {
        width: 69px;
        height: 69px;
    }
</style>
<body>

<h1 class="text-center" style="color: red">Football-Player Management</h1>
<button type="button" class="btn btn-primary" onclick="window.location.href='http://localhost:8080/football-management/create'">
    Add new FootballPlayer
</button>
<table class="table table-striped able-bordered table-hover">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Date Of Birth</th>
        <th>Experience</th>
        <th>Position</th>
        <th>Avatar</th>
<%--        <th>Detail</th>--%>
    </tr>
    <form:form var="player" items="${footballPlayerList}">
        <tr>
            <td>${player.id}</td>
            <td>${player.name}</td>
            <td>${player.dateOfBirth}</td>
            <td>${player.experience}</td>
            <td>${player.position}</td>
            <td><img src="${player.img}" alt=""></td>
            <td><button class="btn btn-primary btn-sm" onclick="window.location.href='http://www.localhost:8080/football-management/detail/${player.id}'">Detail player</button></td>
            <td>                    <button type="button" onclick="infoDelete(${player.id},'${player.name}')"
                                            class="btn btn-danger btn-sm"
                                            data-toggle="modal" data-target="#exampleModal">
                Delete
            </button></td>
        </tr>
    </form:form>
</table>
<!--Modal create-->
<div class="modal fade" id="createModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel1">Create FootballPlayer</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form th:action="@{/smartphone/create}" th:object="${smartphone}" method="post">
                    <div class="mb-3">
                        <input type="hidden" th:field="*{id}" class="form-control" id="exampleInputEmail1"
                               aria-describedby="emailHelp">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Name smartphone</label>
                        <input type="text" th:field="*{name}" class="form-control" id="exampleInputEmail2"
                               aria-describedby="emailHelp">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Brand</label>
                        <input type="text" th:field="*{brand}" class="form-control" id="exampleInputEmail3"
                               aria-describedby="emailHelp">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Prices</label>
                        <input type="text" th:field="*{price}" class="form-control" id="exampleInputEmail0"
                               aria-describedby="emailHelp">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputEmail4" class="form-label">Prices</label>
                        <input type="text" th:field="*{description}" class="form-control" id="exampleInputEmail4"
                               aria-describedby="emailHelp">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-primary">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%--Modal Delete--%>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Delete Box</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="/football-management/delete">
                <div class="modal-body">
                    <input hidden type="text" id="idDelete" name="idDelete">
                    <span>You may want to delete </span><span style="color: red" class="fw-bolder"
                                                              id="nameDelete"></span> ?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                    <button type="submit" class="btn btn-primary">Yes</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    function infoDelete(id, name) {
        document.getElementById("idDelete").value = id;
        document.getElementById("nameDelete").innerText = name;
    }
</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>

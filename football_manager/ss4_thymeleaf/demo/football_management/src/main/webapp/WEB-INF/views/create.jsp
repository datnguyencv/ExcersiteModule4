<%@ taglib prefix="" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
    <title>Create New Player</title>
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
<form:form action="/football-management/create" method="post" modelAttribute="newPlayer">
<div class="form-group">
    <label for="name"></label>
    <form:input type="text" clss="form-control" form="name" id="name" aria-describedby="helpId" placeholder="Please Enter Name" path="name"/>
    <label for="name"></label>
    <form:input type="text" class="form-control" form="name" id="name" aria-describedby="helpId" placeholder="Please Enter Date of birth" path="dateOfBirth"/>
    <label for="name"></label>
    <form:input type="text" class="form-control" form="name" id="name" aria-describedby="helpId" placeholder="Please Enter Experience" path="experience"/>
    <label for="name"></label>
    <form:input type="text" class="form-control" form="name" id="name" aria-describedby="helpId" placeholder="Please Enter Position" path="position"/>
    <label for="name"></label>
    <form:input type="text" class="form-control" form="name" id="name" aria-describedby="helpId" placeholder="Please Enter Url-Img" path="img"/>
</div>
    <button type="submit" class="btn btn-primary">Save</button>
</form:form>
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

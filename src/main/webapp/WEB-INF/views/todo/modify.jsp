<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap demo</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col">
				<nav class="navbar navbar-expand-lg bg-body-tertiary">
					<div class="container-fluid">
						<a class="navbar-brand" href="#">Navbar</a>
						<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav"
							aria-expanded="false" aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
						<div class="collapse navbar-collapse" id="navbarNav">
							<ul class="navbar-nav">
								<li class="nav-item"><a class="nav-link active" aria-current="page" href="#">Home</a></li>
								<li class="nav-item"><a class="nav-link" href="#">Features</a></li>
								<li class="nav-item"><a class="nav-link" href="#">Pricing</a></li>
								<li class="nav-item"><a class="nav-link disabled" aria-disabled="true">Disabled</a></li>
							</ul>
						</div>
					</div>
				</nav>
			</div>
		</div>
		<div class="row content">
			<div class="col">
				<div class="card">
					<div class="card-header">Featured</div>
					<div class="card-body">
						<form action="/todo/modify" method="post">
							<div class="input-group mb-3">
								<span class="input-group-text">Tno</span> <input type="text" class="form-control" name="tno" value=<c:out value="${dto.tno}"/>
									placeholder="tno" readonly>
							</div>

							<div class="input-group mb-3">
								<span class="input-group-text">Title</span> <input type="text" class="form-control" name="title" value=<c:out value="${dto.title}"/>
									placeholder="title">
							</div>

							<div class="input-group mb-3">
								<span class="input-group-text">dueDate</span> <input type="date" class="form-control" name="dueDate" value=<c:out value="${dto.dueDate}"/>
									placeholder="Writer">
							</div>

							<div class="input-group mb-3">
								<span class="input-group-text">Writer</span> <input type="text" class="form-control" name="Writer" value=<c:out value="${dto.writer}"/>
									placeholder="Writer" readonly>
							</div>

							<div class="form-check">
								<label class="form-check-label" for="checkDefault"> Finished &nbsp; </label> <input class="form-check-input" type="checkbox" name="finished"
									${dto.finished?"checked":""}>
							</div>

							<div class="my-4">
								<div class="float-end">
									<button type="button" class="btn btn-danger">Remove</button>
									<button type="button" class="btn btn-primary">Modify</button>
									<button type="button" class="btn btn-secondary">List</button>
								</div>
							</div>
							<input type="hidden" name="page" value="${pageRequestDTO.page}"/>
							<input type="hidden" name="size" value="${pageRequestDTO.size}"/>
						</form>
						<script type="text/javascript">
							const formObj = document.querySelector("form")

							document.querySelector(".btn-danger")
									.addEventListener("click", function(e) {
										e.preventDefault()
										e.stopPropagation()

										formObj.action = "/todo/remove"
										formObj.method = "post"

										formObj.submit()
									}, false)

							document.querySelector(".btn-primary")
									.addEventListener("click", function(e) {
										e.preventDefault()
										e.stopPropagation()

										formObj.submit()
									}, false)
							
							document.querySelector(".btn-secondary")
									.addEventListener("click", function(e) {
										e.preventDefault()
										e.stopPropagation()

										self.location = `/todo/list?${pageRequestDTO.link}`;
									}, false)
						</script>

					</div>
					<script>
					const serverValidResult = {}
					
					<c:forEach items="${errors}" var="error">
						serverValidResult['${error.getField()}'] = '${error.defaultMessage}'
					</c:forEach>
					
					console.log(serverValidResult)
					</script>
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
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
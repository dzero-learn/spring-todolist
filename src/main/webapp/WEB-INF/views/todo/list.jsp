<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap demo</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
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
						<h5 class="card-title">Special titme treatment</h5>
						<!-- 리스트 컨텐츠 -->
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Tno
									</td>
									<th scope="col">Title
									</td>
									<th scope="col">Writer
									</td>
									<th scope="col">DueDate
									</td>
									<th scope="col">Finished
									</td>
								</tr>
							</thead>
							<tbody class="table-group-divider">
								<c:forEach items="${responseDTO.dtoList}" var="dto">
									<tr>
										<th scope="row"><c:out value="${dto.tno}" /></th>
										<td><a href="/todo/read?tno=${dto.tno}" class=""><c:out value="${dto.title}" /></a></td>
										<td><c:out value="${dto.writer}" /></td>
										<td><c:out value="${dto.dueDate}" /></td>
										<td><c:out value="${dto.finished}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- 페이징 네비게이션 -->
						<div class="d-flex justify-content-center mt-3 mb-3">
							<nav aria-label="Page navigation">
								<ul class="pagination flex-wrap">
									<!-- 이전 페이지 버튼 : 이전 페이지 없으면 비활성화 -->
									<c:if test="${responseDTO.prev}">
										<li class="page-item"><a class="page-link" data-num="${responseDTO.start-1}">Previous</a></li>
									</c:if>
									<!-- 페이지 넘버 : 현재 페이지 active -->
									<c:forEach begin="${responseDTO.start}" end="${responseDTO.end}" var="num">
										<li class="page-item ${responseDTO.page == num ? 'active':'' }"><a class="page-link" data-num="${num}">${num}</a></li>
									</c:forEach>
									<!-- 다음 페이지 버튼 : 다음 페이지 없으면 비활성화-->
									<c:if test="${responseDTO.next}">
										<li class="page-item"><a class="page-link" data-num="${responseDTO.end+1}">Next</a></li>
									</c:if>
								</ul>
							</nav>
						</div>
						<script>
							document.querySelector(".pagination").addEventListener("click",function(e){
								e.preventDefault()
								e.stopPropagation()
								
								const target = e.target
								
								// 클릭된 요소가 a태그가 아니면 리턴
								if(target.tagName !== 'A') {
									return
								}
								
								const num = target.getAttribute("data-num")
								
								self.location=`/todo/list?page=\${num}` //jsp의 el ${num}과 구분하기 위해 \붙여줌
							},false)
						</script>
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
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>
</body>
</html>
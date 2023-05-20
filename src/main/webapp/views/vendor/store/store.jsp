<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
	integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

<title>Insert title</title>
</head>
<body>
	<section class="row">
		<div class="col mt-4">
			<div class="card">
				<div class="card-header">Store Information</div>
				<div class="card-body">
					<!-- Hiển thông báo -->
					<c:if test="${message != null}">
						<div class="alert alert-primary" role="alert">
							<i>${message}</i>
						</div>
					</c:if>
					<!-- Hêt thông báo -->
					<table class="table table-striped table-responsive">
						<thead class="thead-inverse">
							<tr>
								<th>Store ID</th>
								<th>Store's name</th>
								<th>Store's bio</th>
								<th>Avatar</th>
								<th>Cover</th>
								<th>Features Images</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td scope="row">${store._id}</td>
								<td>${store.name}</td>
								<td>${store.bio}</td>
								<td><img src="/vendor/store/images/${store.avatar}"></td>
								<td><img src="/vendor/store/images/${store.cover}"></td>

								<td><c:forEach items="${store.featuredImages }" var="temp">
										<img src="/vendor/store/images/${temp}">
									</c:forEach></td>
								<td><a href="/vendor/store/edit"
									class="btn btn-outline-warning"><i class="fa fa-edit"></i></a>
								<td><a href="/vendor/store/delete?_id=${store._id }"
									class="btn btn-outline-danger"><i class="fa fa-trash"></i></a>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</section>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>
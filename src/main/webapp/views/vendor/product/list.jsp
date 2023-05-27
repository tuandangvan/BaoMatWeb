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
	<%@ include file="fragments/header.jsp"%>
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
								<th>Product ID</th>
								<th>Product's name</th>
								<th>Product's description</th>
								<th>Price</th>
								<th>Promotional Price</th>
								<th>List Images</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listProducts }" var="item">
								<tr>
									<td scope="row">${item._id}</td>
									<td>${item.name}</td>
									<td>${item.description}</td>
									<td>${item.price}</td>
									<td>${item.categoryId.name}</td>

									<td><c:forEach items="${item.listImages }" var="temp">
											<img src="/vendor/store/product/images/${temp}">
										</c:forEach></td>
									<td><a href="/vendor/store/product/edit/${item._id}"
										class="btn btn-info btn-circle"> <i
											class="fas fa-info-circle"></i>
									</a></td>

									<td><a href="/vendor/store/product/delete?id=${item._id }"
										class="btn btn-danger btn-circle"> <i class="fas fa-trash"></i>
									</a></td>
								</tr>
							</c:forEach>
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
	<%@ include file="fragments/footer.jsp"%>
</body>
</html>
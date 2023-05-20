<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.2/css/bootstrap.min.css" />
<title>Tạo sản phẩm</title>
</head>
<body>
	<section class="row">
		<div class="col-6 offset-3 mt-4">
			<form action="<c:url value="/vendor/store/product/create"/>" method="post"
				enctype="multipart/form-data">
				<div class="card">
					<div class="card-header">
						<h2>Tạo sản phẩm</h2>
					</div>
					<div class="card-body">
						<div>
							<label for="name" class="form-label">Product's name:</label> <input
								type="text" class="form-control" value="${product.name }"
								name="name" id="name">
						</div>
						<div>
							<label for="description" class="form-label">Description:</label>
							<input type="text" class="form-control"
								value="${product.description}" name="description"
								id="description">
						</div>
						<div>
							<label for="price" class="form-label">Price:</label> <input
								type="text" class="form-control" value="${product.price}"
								name="price" id="price">
						</div>
						<div>
							<label for="cateId" class="form-label">Category:</label> <select
								name="cateId" class="form-control">
								<option value="1">Quần áo nam</option>
								<option value="2">Quần áo nữ</option>
								<option value="3">Quần áo trẻ em</option>
							</select>
						</div>
						<div>
							<label for="listImagesFile" class="form-label">List Images</label> <input
								type="file" class="form-control-file" value="${listImagesFile}"
								name="listImagesFile" id="listImagesFile" multiple="multiple">
						</div>
					</div>
				</div>
				<input type="submit" value="Create">
			</form>
		</div>
	</section>
</body>
</html>
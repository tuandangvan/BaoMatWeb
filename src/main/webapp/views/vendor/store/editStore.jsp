<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.2/css/bootstrap.min.css" />
<title>Chỉnh sửa cửa hàng</title>
</head>
<body>
	<section class="row">
		<div class="col-6 offset-3 mt-4">
			<form action="<c:url value="/vendor/store/update-info"/>"
				method="post" enctype="multipart/form-data">
				<div class="card">
					<div class="card-header">
						<h2>Chỉnh sửa cửa hàng</h2>
					</div>
					<div class="card-body">
						<div>
							<label for="_id" class="form-label">Store ID:</label> <input
								type="text" readonly="readonly" class="form-control"
								value="${store._id}" name="_id" id="_id">
						</div>
						<div>
							<label for="name" class="form-label">Store's name:</label> <input
								type="text" class="form-control" value="${store.name}"
								name="name" id="name">
						</div>
						<div>
							<label for="bio" class="form-label">Store's bio:</label> <input
								type="text" class="form-control" name="bio" value="${store.bio}"
								id="bio">
						</div>
						<div>
							<label for="avatarFile" class="form-label">Avatar:</label> <input
								type="file" class="form-control-file" value="${avatarFile}"
								accept=".jpg, .png" name="avatarFile" id="avatarFile">
						</div>
						<div>
							<label for="coverFile" class="form-label">Cover:</label> <input
								type="file" class="form-control-file" value="${coverFile}"
								name="coverFile" id="coverFile">
						</div>
						<div>
							<label for="featuredImagesFile" class="form-label">Featured
								Images</label> <input type="file" class="form-control-file"
								value="${featuredImagesFile}" name="featuredImagesFile"
								id="featuredImagesFile" multiple="multiple">
						</div>

					</div>
				</div>
				<input type="submit" value="Lưu">
			</form>
		</div>
	</section>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>SUNNY | Trang quản trị</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
	integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="<c:url value='/views/admin/css/base.css'/>" />
<link rel="stylesheet" href="<c:url value='/views/admin/css/main.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/views/admin/css/style.css'/>" />
<script src="//code.jquery.com/jquery-3.2.1.slim.min.js"
	type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- Body -->
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="//fonts.googleapis.com/css?family=Open+Sans:400,300,600"
	rel="stylesheet" type="text/css">
</head>
<body class="no-skin">
	<div class="app">
		<!-- header -->
		<%@ include file="/common/admin/header.jsp"%>
		<!-- header  -->


		<div class="main-container" id="main-container">
			<!-- sidebar -->
			<%@ include file="/common/admin/left.jsp"%>
			<!-- sidebar -->

			<div class="main_content">
				<div class="main_content__body-title">
					<p>${category.isEdit ? 'Edit Category' : 'Create Category' }</p>
				</div>
				<section class="row">
					<div class="col-6 offset-3 mt-4">
						<form action="<c:url value="/admin/category/saveOrUpdate"/>"
							method="POST" enctype="multipart/form-data">
							<input type="hidden" name="isEdit" value="${category.isEdit}"
								class="form-control" readonly='readonly' /> <br /> <input
								type="hidden" name="image" value="${category.image}"
								class="form-control" readonly='readonly' /> <br />
							<div class="form-group">

								<label for="_id">Category ID:</label> <input type="text"
									name="_id" value="${category._id}" class="form-control"
									readonly='readonly' placeholder="id" />

							</div>

							<div class="form-group">

								<label for="name">Category Name:</label> <input type="text"
									class="form-control" name="name" value="${category.name }" />

							</div>

							<div class="form-group">

								<label for="slug">Category Slug:</label> <input type="text"
									class="form-control" name="slug" value="${category.slug }" />

							</div>

							<div class="form-group">

								<label for="imageFile">Images:</label> <input type="file"
									class="form-control" name="imageFile"
									value="${category.imageFile}" />

							</div>
							<div class="mb-3">

								<label for="isDeleted">isDeleted:</label> <select
									class="form-select" name="isDeleted">
									<option ${category.isDeleted==false?'selected':''}
										value="false">false</option>
									<option ${category.isDeleted==true?'selected':''} value="true">true</option>
								</select>

							</div>


							<br />

							<hr>
							<div class="cart-footer text-muted">
								<a href="<c:url value="/admin/category/add"/>"
									class="btn btn-secondary"><i class="fas fa-new"> New</i></a> <a
									href="<c:url value="/admin/category"/>" class="btn btn-success"><i
									class="fas fa-bars">List Categories</i></a>
								<button class="btn btn-primary">
									<i class="fas fa-save"></i>
									<c:if test="${category.isEdit}">
										<span>Update</span>
									</c:if>
									<c:if test="${!category.isEdit}">
										<span>Save</span>
									</c:if>
								</button>
							</div>
						</form>
					</div>
				</section>
			</div>
		</div>

		<!-- script for action in page -->
		<script src="/views/admin/js/page.js">
			
		</script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>SUNNY | Trang quản trị</title>


<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
	integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="<c:url value='/views/admin/css/base.css'/>" />
<link rel="stylesheet" href="<c:url value='/views/admin/css/main.css'/>" />
<script src="//code.jquery.com/jquery-3.2.1.slim.min.js"
	type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

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

				<div class="main_content__body">

					<div class="main_content__body-title">
						<p>Quản lí Categories</p>

					</div>
					<%@ include file="/common/admin/info.jsp"%>
					<table class="table maintable table ">
						<thead>
							<tr>
								<th>Image</th>
								<th>Category name</th>
								<th>Slug</th>
								<th>Status</th>
								<th>CreatedAT</th>
								<th>updatedAt</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${categories}">



								<tr class="odd gradeX">



									<td><img width="130px" height="100px"
										src="/admin/category/images/${item.image}"></td>

									<td>${item.name }</td>

									<td>${item.slug}</td>

									<td><c:if test="${item.isDeleted == true}">

											<span class="label label-sm label-success"> Khóa </span>

										</c:if> <c:if test="${item.isDeleted ==false}">

											<span class="label label-sm label-warning"> Hoạt động
											</span>

										</c:if></td>
									<td>${item.createdAt}</td>
									<td>${item.updatedAt}</td>
									<td><a
										href="<c:url value='/admin/category/edit/${item._id}'/>"
										class="center"><i class="fa-solid fa-pen-to-square"></i></a> |
										<a href="<c:url value='/admin/category/delete/${item._id }'/>"
										class="center"><i
											class="fa-solid fa-trash delete-multi-row grow-btn-when-hover"></i></a></td>

								</tr>

							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="main_content__footer">
					<a href="<c:url value='/admin/category/add'/>"><i
						class="fa-solid fa-circle-plus addbtn grow-btn-when-hover"></i> </a> <a
						href="<c:url value='/admin/category'/>"><i
						class="fa-solid fa-arrows-rotate refreshbtn grow-btn-when-hover"></i>
					</a>
					<div class="pagination">
						<a href="#" id="first-page"><i class="fa-solid fa-angles-left"></i></a>
						<a href="#" id="pre-page"><i class="fa-solid fa-angle-left"></i></a>
						<input id="currentpage" value=1> <a href="#"
							id="next-page"><i class="fa-solid fa-angle-right"></i></a> <a
							href="#" id="last-page"><i class="fa-solid fa-angles-right"></i></a>
					</div>
				</div>
				<!-- script for action in page -->
				<script src="/views/admin/js/page.js">></script>
</body>
</html>

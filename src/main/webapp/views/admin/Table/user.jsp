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
						<p>Danh sách tài khoản người dùng</p>

					</div>
					<table class="table maintable">
						<thead>
							<tr>
								<th>First name</th>
								<th>Last name</th>
								<th>Địa chỉ</th>
								<th>Email</th>
								<th>SĐT</th>
								<th>Point</th>
								<th>Active</th>
								<th>CreatedAt</th>
							</tr>
						</thead>
						<tbody>



							<c:forEach var="item" items="${listuser}">
								<tr class="odd gradeX">

									<td>${item.firstName }</td>

									<td>${item.lastName }</td>
									<td>${item.address}</td>
									<td>${item.email}</td>
									<td>${item.phone}</td>
									<td>${item.point}</td>
									<td></td>
									<td>${item.createdAt}</td>

								</tr>

							</c:forEach>

						</tbody>
					</table>
				</div>
				<div class="main_content__footer">
					<div class="pagination">
						<a href="${pageContext.request.contextPath }/admin/user/1"
							id="first-page"><i class="fa-solid fa-angles-left"></i></a>
						<c:if test="${page.getNumber()+1>1}">
							<a
								href="${pageContext.request.contextPath }/admin/user/${page.getNumber()}"
								id="pre-page"><i class="fa-solid fa-angle-left"></i></a>
						</c:if>
						<c:if test="${page.getNumber()+1==1}">
							<a id="pre-page"><i class="fa-solid fa-angle-left"></i></a>
						</c:if>
						<c:forEach begin="1" end="${page.getTotalPages()}" var="i">
							<a id="currentpage"
							<c:if test="${i==page.getNumber()+1 }"> style='background: rgb(0 0 0/ 20%); color: blue'</c:if>
								href="${pageContext.request.contextPath}/admin/user/${i}">${i}</a>
						</c:forEach>
						<c:if test="${page.getNumber()+1<page.getTotalPages()}">
							<a
								href="${pageContext.request.contextPath}/admin/user/${page.getNumber()+2}"
								id="next-page"><i class="fa-solid fa-angle-right"></i></a>
						</c:if>
						<c:if test="${page.getNumber()+1>=page.getTotalPages()}">
							<a id="next-page"><i class="fa-solid fa-angle-right"></i></i></a>
						</c:if>
						<c:if test="${page.getTotalPages()!=0}">
							<a
								href="${pageContext.request.contextPath }/admin/user/${page.getTotalPages()}"
								id="last-page"><i class="fa-solid fa-angles-right"></i></a>
						</c:if>
						<c:if test="${page.getTotalPages()==0}">
							<a id="last-page"><i class="fa-solid fa-angles-right"></i></a>
						</c:if>
					</div>
				</div>

				<!-- script for action in page -->
				<script src="/views/admin/js/page.js">
					>
				</script>
</body>
</html>

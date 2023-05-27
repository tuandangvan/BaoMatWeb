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
						<p>Đơn vị vận chuyển</p>

					</div>
					<%@ include file="/common/admin/info.jsp"%>
					<table class="table maintable table ">
						<thead>
							<tr>
								<th>Delivery Name</th>
								<th>Description</th>
								<th>Price</th>
								<th>Status</th>
								<th>CreatedAT</th>
								<th>updatedAt</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${listDelivery}">



								<tr class="odd gradeX">



									<td>${item.name }</td>

									<td>${item.description }</td>

									<td>${item.price}</td>

									<td><c:if test="${item.isDeleted == true}">

											<span class="label label-sm label-success"> Khóa </span>

										</c:if> <c:if test="${item.isDeleted ==false}">

											<span class="label label-sm label-warning"> Hoạt động
											</span>

										</c:if></td>
									<td>${item.createdAt}</td>
									<td>${item.updatedAt}</td>
									<td><a
										href="<c:url value='/admin/delivery/edit/${item._id}'/>"
										class="center"><i class="fa-solid fa-pen-to-square"></i></a> |
										<a href="<c:url value='/admin/delivery/delete/${item._id }'/>"
										class="center"><i
											class="fa-solid fa-trash delete-multi-row grow-btn-when-hover"></i></a></td>

								</tr>

							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="main_content__footer">
					<a href="<c:url value='/admin/delivery/add'/>"><i
						class="fa-solid fa-circle-plus addbtn grow-btn-when-hover"></i> </a> <a
						href="<c:url value='/admin/delivery/1'/>"><i
						class="fa-solid fa-arrows-rotate refreshbtn grow-btn-when-hover"></i>
					</a>
					<div class="pagination">
						<a href="${pageContext.request.contextPath }/admin/delivery/1"
							id="first-page"><i class="fa-solid fa-angles-left"></i></a>
						<c:if test="${page.getNumber()+1>1}">
							<a
								href="${pageContext.request.contextPath }/admin/delivery/${page.getNumber()}"
								id="pre-page"><i class="fa-solid fa-angle-left"></i></a>
						</c:if>
						<c:if test="${page.getNumber()+1==1}">
							<a id="pre-page"><i class="fa-solid fa-angle-left"></i></a>
						</c:if>
						<c:forEach begin="1" end="${page.getTotalPages()}" var="i">
							<a id="currentpage"
							<c:if test="${i==page.getNumber()+1 }"> style='background: rgb(0 0 0/ 20%); color: blue'</c:if>
								href="${pageContext.request.contextPath}/admin/delivery/${i}">${i}</a>
						</c:forEach>
						<c:if test="${page.getNumber()+1<page.getTotalPages()}">
							<a
								href="${pageContext.request.contextPath}/admin/delivery/${page.getNumber()+2}"
								id="next-page"><i class="fa-solid fa-angle-right"></i></a>
						</c:if>
						<c:if test="${page.getNumber()+1>=page.getTotalPages()}">
							<a id="next-page"><i class="fa-solid fa-angle-right"></i></i></a>
						</c:if>
						<c:if test="${page.getTotalPages()!=0}">
							<a
								href="${pageContext.request.contextPath }/admin/delivery/${page.getTotalPages()}"
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

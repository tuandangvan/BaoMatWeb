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
<!-- BODY -->
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<!-- BODY -->

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
				<a href="<c:url value="/admin/order/${varstatus}/${index }"/>"
					class="btn btn-success"><i class="fa-solid fa-arrow-left"></i>Back</a>
				<div class="main_content__body-title">
					<p>Chi tiết đơn hàng: Id=${order._id}</p>
				</div>
				<p style="font-size: 150%">Trạng thái: ${order.status}</p>
				<div class="row">

					<div class="col-xl-8 col-lg-7">
						<div class="card shadow mb-4">
							<!-- Card Header - Dropdown -->
							<div
								class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
								<h6 class="m-0 font-weight-bold text-primary">Products</h6>

							</div>
							<!-- Card Body -->

							<div class="card-body">
								<table class="table maintable">
									<thead>
										<tr>
											<th></th>
											<th>Tên sản phẩm</th>
											<th>Số lượng</th>
											<th>Giá Tiền</th>
										</tr>
									</thead>
									<tbody>



										<c:forEach var="item" items="${listOrderItem}">
											<tr class="odd gradeX">

												<td><c:forEach items="${item.productId.listImages }"
														var="temp">
														<img width="130px" height="100px"
															src="/vendor/store/product/images/${temp}">
													</c:forEach></td>

												<td>${item.productId.name }</td>
												<td>${item.count}</td>
												<td>${item.productId.price*item.count}</td>
											</tr>

										</c:forEach>

									</tbody>
								</table>
								<p style="font-size: 150%">Chi phí vận chuyển:
									${order.deliveryId.price}</p>
								<p style="font-size: 150%">Tổng tiền:
									${order.amountFromUser}</p>
							</div>
						</div>

					</div>
					<div class="col-xl-4 col-lg-5">
						<div class="card shadow mb-4">
							<!-- Card Header - Dropdown -->
							<div
								class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
								<h6 class="m-0 font-weight-bold text-primary">Thông tin</h6>

							</div>
							<!-- Card Body -->
							<div class="card-body">
								<p style="font-size: 150%">Tên khách hàng:
									${order.userId.lastName }</p>
								<p style="font-size: 150%">Địa chỉ: ${order.address}</p>
								<p style="font-size: 150%">Số điện thoại:
									${order.phone }</p>
								<p style="font-size: 150%">Cửa hàng: ${order.storeId.name }</p>
								<p style="font-size: 150%">Đơn vị vận chuyển:
									${order.deliveryId.name}</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- script for action in page -->
	<script src="/views/admin/js/page.js">
		
	</script>
</body>
</html>

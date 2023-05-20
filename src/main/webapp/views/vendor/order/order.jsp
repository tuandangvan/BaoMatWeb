<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Tables</title>

<!-- Custom fonts for this template -->
<link href="/css/all.min.css" rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="/css/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="#">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fas fa-laugh-wink"></i>
				</div>
				<div class="sidebar-brand-text mx-3">
					SB Vendor <sup>2</sup>
				</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="/vendor/store/dashboard"> <i
					class="fas fa-fw fa-tachometer-alt"></i> <span>Dashboard</span></a></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<li class="nav-item active"><a class="nav-link"
				href="/vendor/store"><i class="fa-solid fa-store"></i> <span>Store
						Information</span></a></li>

			<hr class="sidebar-divider">

			<li class="nav-item active"><a class="nav-link"
				href="/vendor/store/product"> <i
					class="fa-brands fa-product-hunt"></i> <span>Product</span></a></li>

			<hr class="sidebar-divider">

			<li class="nav-item active"><a class="nav-link"
				href="/vendor/store/orders"> <i
					class="fa-solid fa-cart-shopping"></i> <span>Orders</span></a></li>

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>

		</ul>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Begin Page Content -->
				<div class="container-fluid mt-3">

					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">Quản lý đơn hàng</h1>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div
							class="card-header py-3 d-flex align-items-center justify-content-between">
							<h6 class="m-0 font-weight-bold text-primary ">Danh sách đơn
								hàng</h6>
							<div class="d-flex justify-content-between">
								<a href="#" class="btn btn-primary btn-circle"
									data-toggle="modal" data-target="#addModal"> <i
									class="fa-solid fa-plus"></i>
								</a>
							</div>
							<div class="dropdown">
							<button class="btn btn-primary dropdown-toggle" type="button"
								id="dropdownMenuButton" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">Status: ${status}</button>
							<div id="myDropdown" class="dropdown-menu">
								<a class="dropdown-item"
									href="<c:url value='/vendor/store/orders?status=0'/>">Tất
									cả</a> <a class="dropdown-item"
									href="<c:url value='/vendor/store/orders?status=1'/>">Chưa
									xác nhận</a><a class="dropdown-item"
									href="<c:url value='/vendor/store/orders?status=2'/>">Đã
									xác nhận</a><a class="dropdown-item"
									href="<c:url value='/vendor/store/orders?status=3'/>">Đang
									giao</a><a class="dropdown-item"
									href="<c:url value='/vendor/store/orders?status=4'/>">Đã
									nhận hàng</a><a class="dropdown-item"
									href="<c:url value='/vendor/store/orders?status=5'/>">Bị
									hủy</a>
							</div>
						</div>
						</div>
						
						
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>Order ID</th>
											<th>User name</th>
											<th>Address</th>
											<th>Phone</th>
											<th>Status</th>
											<th>Total Price</th>
											<th>Edit</th>
											<th>Detail</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>Order ID</th>
											<th>User name</th>
											<th>Address</th>
											<th>Phone</th>
											<th>Status</th>
											<th>Total Price</th>
											<th>Edit</th>
											<th>Detail</th>
										</tr>
									</tfoot>
									<tbody>
										<c:forEach items="${listOrders }" var="item">
											<tr>
												<td scope="row">${item._id}</td>
												<td>${item.userId.firstName}${item.userId.lastName}</td>
												<td>${item.address}</td>
												<td>${item.phone}</td>
												<td>${item.status}</td>
												<td>${item.amountFromUser }</td>
												<td><a href="#"
													class="btn btn-warning btn-circle editOrder"
													data-toggle="modal" data-target="#editModal"> <i
														class="fa-regular fa-pen-to-square"></i>
												</a></td>
												<td><a href="/vendor/store/orders-item?id=${item._id}"
													class="btn btn-info btn-circle" id="detailYes"> <i
														class="fa-solid fa-circle-info"></i>
												</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Your Website 2020</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">

					<form action="#" id="editYes" method="post"
						enctype="multipart/form-data">
						<div class="card">
							<div class="card-header">
								<h2>Cập nhật trạng thái</h2>
							</div>
							<div class="card-body">
								<div>
									<input readonly="readonly" type="text" class="form-control id"
										value="${order._id}" name="id" id="id">
								</div>
								<div>
									<label for="name" class="form-label">User's name:</label> <input
										type="text" class="form-control" value="${order.userId.name }"
										id="name">
								</div>
								<div>
									<label for="address" class="form-label">Address:</label> <input
										type="text" class="form-control" value="${order.address}"
										name="address" id="address">
								</div>
								<div>
									<label for="phone" class="form-label">Phone:</label> <input
										type="text" class="form-control" value="${order.phone}"
										name="phone" id="phone">
								</div>
								<div>
									<label for="status" class="form-label">Status:</label> <select
										name="status" id="status" class="form-control">
										<option value="Chưa xác nhận">Chưa xác nhận</option>
										<option value="Đã xác nhận">Đã xác nhận</option>
										<option value="Đang giao">Đang giao</option>
										<option value="Đã nhận hàng">Đã nhận hàng</option>
										<option value="Bị hủy">Bị hủy</option>
									</select>
								</div>
							</div>
						</div>
						<input type="submit" class="btn btn-primary mt-2" value="Save">
					</form>

				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="/js/jquery.min.js"></script>
	<script src="/js/bootstrap.bundle.min.js"></script>

	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
		integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w=="
		crossorigin="anonymous" referrerpolicy="no-referrer" />


	<!-- Core plugin JavaScript-->
	<script src="/js/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="/js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script src="/js/jquery.dataTables.min.js"></script>
	<script src="/js/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="/js/demo/datatables-demo.js"></script>
	<script type="text/javascript">
		$(".editOrder").click(function() {
			let id = $(this).closest("tr").find("td").eq(0).html()
			let name = $(this).closest("tr").find("td").eq(1).html()
			let address = $(this).closest("tr").find("td").eq(2).html()
			let phone = $(this).closest("tr").find("td").eq(3).html()
			let status = $(this).closest("tr").find("td").eq(4).html()
			let test = document.getElementById("editYes")
			test.setAttribute("action", "/vendor/store/orders/edit");
			$("#editModal #id").val(id)
			$("#editModal #name").val(name)
			$("#editModal #address").val(address)
			$("#editModal #phone").val(phone)
			$("#editModal #status").val(status)
		})
		$(".detailOrder").click(function() {
			let id = $(this).closest("tr").find("td").eq(0).html()
			let test = document.getElementById("detailYes")
			test.setAttribute("href", "/vendor/store/orders-item?id" + id);
			$("#detailModal #id").val(id)
		})
	</script>
</body>

</html>
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

<title>SB Vendor 2 - Store</title>

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
			<hr class="sidebar-divider">
			<li class="nav-item active"><a class="nav-link"
				href="/vendor/store"> <i class="fa-solid fa-store"></i> <span>Store
						Information</span></a></li>

			<!-- Divider -->
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

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Thông tin cửa
								hàng</h6>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead class="thead-inverse">
										<tr>
											<th>Store ID</th>
											<th>Store's name</th>
											<th>Store's bio</th>
											<th>Avatar</th>
											<th>Cover</th>
											<th>Features Images</th>
											<th>Edit</th>
											<th>Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td scope="row">${store._id}</td>
											<td>${store.name}</td>
											<td>${store.bio}</td>
											<td><img src="/vendor/store/images/${store.avatar}"
												style="width: 200px"></td>
											<td><img src="/vendor/store/images/${store.cover}"
												style="width: 200px"></td>

											<td><c:forEach items="${store.featuredImages }"
													var="temp">
													<img src="/vendor/store/images/${temp}"
														style="width: 200px">
												</c:forEach></td>
											<td><a href="#" data-toggle="modal"
												data-target="#editModal"
												class="btn btn-outline-warning editStore"><i
													class="fa fa-edit"></i></a>
												<td><a href="#" data-toggle="modal"
												data-target="#deleteModal"
												class="btn btn-outline-danger deleteStore"><i
													class="fa fa-trash"></i></a>
										
										</tr>
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
					to end your current session.
</div>
												<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
														data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
													aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" type="button" data-dismiss="modal"
																	aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Bạn có muốn xóa cửa hàng này không?</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
																	data-dismiss="modal">Hủy</button>
					<a class="btn btn-primary" href="#" id="deleteYes">Có</a>
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
					<form action="<c:url value="/vendor/store/update-info"/>"
																	id="editYes" method="post"
																	enctype="multipart/form-data">
						<div class="card">
							<div class="card-header">
								<h2>Chỉnh sửa cửa hàng</h2>
							</div>
							<div class="card-body">
								<div>
									<label for="_id" class="form-label">Store ID:</label> <input
																					type="text" readonly="readonly"
																					class="form-control" value="${store._id}"
																					name="_id" id="_id">
								</div>
								<div>
									<label for="name" class="form-label">Store's name:</label> <input
																					type="text" class="form-control"
																					value="${store.name}" name="name" id="name">
								</div>
								<div>
									<label for="bio" class="form-label">Store's bio:</label> <input
																					type="text" class="form-control" name="bio"
																					value="${store.bio}" id="bio">
								</div>
								<div>
									<label for="avatarFile" class="form-label">Avatar:</label> <input
																					type="file" class="form-control-file"
																					value="${avatarFile}" accept=".jpg, .png"
																					name="avatarFile" id="avatarFile">
								</div>
								<div>
									<label for="coverFile" class="form-label">Cover:</label> <input
																					type="file" class="form-control-file"
																					value="${coverFile}" name="coverFile"
																					id="coverFile">
								</div>
								<div>
									<label for="featuredImagesFile" class="form-label">Featured
										Images</label> <input type="file" class="form-control-file"
																					value="${featuredImagesFile}"
																					name="featuredImagesFile" id="featuredImagesFile"
																					multiple="multiple">
								</div>

							</div>
						</div>
						<input type="submit" value="Lưu">
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
		$(".editStore").click(function() {
			let id = $(this).closest("tr").find("td").eq(0).html()
			let name = $(this).closest("tr").find("td").eq(1).html()
			let bio = $(this).closest("tr").find("td").eq(2).html()
			$("#editModal #id").val(id)
			$("#editModal #name").val(name)
			$("#editModal #bio").val(bio)
		})
		$(".deleteStore").click(function() {
			let id = $(this).closest("tr").find("td").eq(0).html()
			let test = document.getElementById("deleteYes")
			test.setAttribute("href", "/vendor/store/delete?_id=" + id);
		})
	</script>

											</body>

</html>

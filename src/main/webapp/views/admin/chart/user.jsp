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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- BODY -->
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="//fonts.googleapis.com/css?family=Open+Sans:400,300,600"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<!-- BODY -->

<script type="text/javascript">
	window.onload = function() {

		var dps = [];
		var chart = new CanvasJS.Chart("chartContainer", {
			theme : "light2", // "light1", "dark1", "dark2"
			animationEnabled : true,
			title : {
				title : "Datetime",
				text : "Thống kê người dùng mới"
			},
			axisX : {
				valueFormatString : "DD-MM-YYYY"
			},
			axisY : {
				title : "Số lượng user mới",
				includeZero : true,
				suffix : ""
			},
			data : [ {
				type : "line",
				xValueType : "dateTime",
				xValueFormatString : "DD-MM-YYYY",
				yValueFormatString : "#",
				dataPoints : dps
			} ]
		});

		var xValue;
		var yValue;

		<c:forEach items="${listuser}" var="item">
		xValue = Date.parse("${item.createdAt}");
		yValue = parseInt("${item.point}");
		dps.push({
			x : xValue,
			y : yValue
		});
		</c:forEach>
		chart.render();

	}
</script>

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

				<!-- Content Row -->

				<div class="row">

					<!-- Area Chart -->
					<div class="col-xl-8 col-lg-7">
						<div class="card shadow mb-4">
							<!-- Card Header - Dropdown -->
							<div
								class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
								<h6 class="m-0 font-weight-bold text-primary">Revenue Chart</h6>
								<div class="dropdown no-arrow">
									<a class="dropdown-toggle" href="#" role="button"
										id="dropdownMenuLink" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false"> <i
										class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
									</a>
									<div
										class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
										aria-labelledby="dropdownMenuLink">
										<div class="dropdown-header">Dropdown Header:</div>
										<a class="dropdown-item" href="#">Action</a> <a
											class="dropdown-item" href="#">Another action</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item" href="#">Something else here</a>
									</div>
								</div>
							</div>
							<!-- Card Body -->
							<div class="card-body">
								<div id="chartContainer" style="height: 370px; width: 100%;"></div>
							</div>
						</div>
					</div>

					<!-- Pie Chart -->
					<div class="col-xl-4 col-lg-5">
						<div class="card shadow mb-4">
							<!-- Card Header - Dropdown -->
							<div
								class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
								<h6 class="m-0 font-weight-bold text-primary">Top User</h6>
								<div class="dropdown no-arrow">
									<a class="dropdown-toggle" href="#" role="button"
										id="dropdownMenuLink" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false"> <i
										class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
									</a>
									<div
										class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
										aria-labelledby="dropdownMenuLink">
										<div class="dropdown-header">Dropdown Header:</div>
										<a class="dropdown-item" href="#">Action</a> <a
											class="dropdown-item" href="#">Another action</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item" href="#">Something else here</a>
									</div>
								</div>
							</div>
							<!-- Card Body -->
							<div class="card-body">
								<div class="chart-pie pt-4 pb-2">
									<canvas id="myPieChart"></canvas>
								</div>
								<div class="mt-4 text-center small">
									<span class="mr-2"> <i
										class="fas fa-circle text-primary"></i> Direct
									</span> <span class="mr-2"> <i
										class="fas fa-circle text-success"></i> Social
									</span> <span class="mr-2"> <i class="fas fa-circle text-info"></i>
										Referral
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- Content Row -->

				<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

			</div>
		</div>
	</div>
	<!-- script for action in page -->
	<script>
		// display active item
		let item = document.querySelectorAll(".item");
		let subitem = document.querySelectorAll(".sub-item");
		for (let i = 0; i < item.length; i++) {
			item[i].onclick = function() {

				let j = 0;
				let k = 0;
				let list = document.querySelectorAll(".list");
				while (k < subitem.length) {

					subitem[k++].className = "sub-item";
				}
				while (j < list.length) {

					list[j++].className = "list";
				}
				item[i].closest(".list").className = "list active";
			};
		}

		for (let i = 0; i < subitem.length; i++) {
			subitem[i].onclick = function() {

				let j = 0;
				let k = 0;
				let list = document.querySelectorAll(".list");
				while (k < list.length) {

					list[k++].className = "list";
				}
				while (j < subitem.length) {

					subitem[j++].className = "sub-item";
				}
				subitem[i].className = "sub-item active";
				subitem[i].closest(".list").className = "list active";
			};
		}

		// toggle on menu button

		let menuToggle = document.querySelector(".toggle");
		let navigation = document.querySelector(".navigation");
		let content = document.querySelector(".main_content");
		let menutoggleclick = function() {
			let subbtn = document.querySelectorAll(".sub-btn");
			menuToggle.classList.toggle("active");
			navigation.classList.toggle("active");
			content.classList.toggle("active");
			//disable dropdown
			for (let i = 0; i < subbtn.length; i++) {
				subbtn[i].classList.toggle("hover-btn")
			}

		};
		menutoggleclick();
		menuToggle.onclick = menutoggleclick;
	</script>
	<script>
		// show hide dropdown list
		$(document).ready(function() {
			$('.sub-btn').click(function() {
				if (!this.classList.contains("hover-btn")) {
					$(this).next('.sub-menu').slideToggle();
					$(this).find('.dropdown').toggleClass('rotate')
				}
			})
		})

		test();
		function test() {
			$.get('/sunnyFE/account/getsession', function(ketqua) {
				let u = ketqua.user
				if (u == null
						|| !(u.roleId.roleId == 3 || u.roleId.roleId == 1)) {
					window.location.href = "/sunnyFE/account/login";
				}
				if (u.roleId.roleId == 3) {
					$('.authentication-admin').remove()
				}
			})
		}

		function checkfilter(o) {
			return false;
		}

		function formatmoney(n, currency) {

			const money = n.toString();
			return money.replace(/\B(?=(\d{3})+(?!\d))/g, ',') + currency;
		}
	</script>


</body>
</html>

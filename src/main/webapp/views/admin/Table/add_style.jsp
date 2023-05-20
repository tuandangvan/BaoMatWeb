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
					<p>Add Or Edit Style</p>
				</div>
				<section class="row">
					<div class="col-6 offset-3 mt-4">
						<form action="#"
							method="POST" enctype="multipart/form-data">

							<div class="form-group">

								<label for="_id">Style ID:</label> <input type="text" name="_id"
									value="${style._id}" class="form-control" readonly='readonly'
									placeholder="id" />

							</div>

							<div class="form-group">

								<label for="name">Style Name:</label> <input type="text"
									class="form-control" name="name" value="${style.name }" />

							</div>
							<div class="mb-3">

								<label for="isDeleted">isDeleted:</label> <select
									class="form-select" name="isDeleted">
									<option ${style.isDeleted==false?'selected':''} value="false">false</option>
									<option ${style.isDeleted==true?'selected':''} value="true">true</option>
								</select>

							</div>
							<div class="form-group">
								<label for="categoryId">Category:</label> <select name="categoryId"
									id="category" required="required">
									<c:forEach var="item" items="${categories}">
										<c:if test="${style.categoryId._id == item._id }">
											<option value="${item._id}">${item.name}</option>
										</c:if>
									</c:forEach>
									<c:forEach var="item" items="${categories}">
										<c:if test="${style.categoryId._id != item._id }">
											<option value="${item._id}">${item.name}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
							<br />

							<hr>
							<div class="cart-footer text-muted">
								<a href="<c:url value="/admin/style/add"/>"
									class="btn btn-secondary"><i class="fas fa-new"> New</i></a> <a
									href="<c:url value="/admin/style/0/1"/>" class="btn btn-success"><i
									class="fas fa-bars">List Style</i></a>
								<button class="btn btn-primary" formaction="<c:url value="/admin/style/save"/>">
									<i class="fas fa-save"></i>Save
								</button>
								<button class="btn btn-warning" formaction="<c:url value="/admin/style/edit"/>">
									<i class="fa-regular fa-pen-to-square"></i>Edit
								</button>
							</div>
						</form>
					</div>
				</section>
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
				$
						.get(
								'/sunnyFE/account/getsession',
								function(ketqua) {
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

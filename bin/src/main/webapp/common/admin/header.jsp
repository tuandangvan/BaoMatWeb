<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ page session="true"%>
<div class="header">
	<ul>
		<li style="background-color: #4285F4; font-size: 20px;"><a
			style="color: white; font-weight: 800" href="">Thousand Sunny</a></li>
	</ul>
	<ul>

		<div class="dropdown">
			<button onclick="AcountAdmin()" class="dropbtn">${sessionScope.user.getFirstName()} ${sessionScope.user.getLastName()}</button>
			<div id="Dropdown" class="dropdown-content account-drop">
				<a href="<c:url value='/admin/style/0/1'/>">Đổi Mật Khẩu</a> <a
					href="<c:url value='/account/logout'/>">Đăng xuất</a>
			</div>
		</div>
		<%-- <li style="border-radius: 10px" ><a href="">${sessionScope.accountName}</a></li> --%>
		<img
			src="https://www.google.com/url?sa=i&url=http%3A%2F%2Fconganbackan.vn%2FMang-xa-hoi%2Fcach-an-anh-dai-dien-facebook-49010.html&psig=AOvVaw17F28b-UnQ2O68WQhF6ktQ&ust=1671427351427000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCJi86p-2gvwCFQAAAAAdAAAAABAD"
			width="50" height="50" />


	</ul>


</div>


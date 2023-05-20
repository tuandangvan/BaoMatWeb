<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LTWEB</title>
<link rel='stylesheet' href="/css/web/profile.css">
</head>
<body>
<%@ include file="/common/web/header.jsp"%>
	<br/>
	<div class="container-p">
		<div class="container-p__sidebar">
			<div class="sidebar-item "> 
				<a onclick="location.href='/account/profile'">Tài Khoản Của Tôi</a>
			</div>
			<div class="sidebar-item active"> 
				<a onclick="location.href='/account/profile/edit'">Chỉnh Sửa Hồ Sơ</a>
			</div>
			<div class="sidebar-item"> 
				<a onclick="location.href='/account/profile/change-password'">Đổi Mật Khẩu</a>
			</div>
			<div class="sidebar-item"> 
				<a onclick="location.href='/account/profile/orders'">Lịch Sử Mua Hàng</a>
			</div>
		</div>
		<div class="container-p__body">
			<div class="body-top">
				chỉnh sửa thông tin cá nhân
			</div>
			<div class="body-content">
				
				<div class="form-container">
		            <form action="/account/profile/edit" class="form" method="post">
		            	<!-- <div class="form__row">
		                    <label class="form__label" for="avatar">Ảnh Đại Diện: </label>
		                    <input class="form__input" type="text" name="avatar" id="avatar">
		                </div>
		                <div class="form__row">
		                    <label class="form__label" for="cover">Ảnh Bìa:</label>
		                    <input class="form__input" type="text" name="cover" id="cover">
		                </div> -->
		                
		                <div class="form__row">
		                    <label class="form__label" for="firstName">Họ:</label>
		                    <input class="form__input" type="text" name="firstName" id="firstName" value="${user.firstName}" required>
		                </div>
		                <div class="form__row">
		                    <label class="form__label" for="lastName">Tên:</label>
		                    <input class="form__input" type="text" name="lastName" id="lastName" value="${user.lastName}" required>
		                </div>
		                <div class="form__row">
		                    <label class="form__label" for="idCard">Số CMND:</label>
		                    <input class="form__input" type="text" name="idCard" id="idCard" value="${user.idCard}">
		                </div>
		                <div class="form__row">
		                    <label class="form__label" for="phone">Số Điện Thoại:</label>
		                    <input class="form__input" type="text" name="phone" id="phone" value="${user.phone}">
		                </div>
		                <div class="form__row">
		                    <label class="form__label" for="address">Địa chỉ:</label>
		                  	<input class="form__input" type="text" name="address" id="address" value="${user.address}">
		                </div>
		                <button class="save-info">Lưu</button>
		            </form>
		        </div>
				
				
			</div>
		</div>
	</div>
	
<%@ include file="/common/web/footer.jsp"%>
</body>
</html>
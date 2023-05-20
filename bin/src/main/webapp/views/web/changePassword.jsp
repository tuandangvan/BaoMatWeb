<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LTWEB</title>

<link rel='stylesheet' href="/css/web/profile.css">
<style type="text/css">
.form__label {
    min-width: 160px;   
}
</style>
</head>
<body>

<%@ include file="/common/web/header.jsp"%>
	<br/>
	<div class="container-p">
		<div class="container-p__sidebar">
			<div class="sidebar-item "> 
				<a onclick="location.href='/account/profile'">Tài Khoản Của Tôi</a>
			</div>
			<div class="sidebar-item "> 
				<a onclick="location.href='/account/profile/edit'">Chỉnh Sửa Hồ Sơ</a>
			</div>
			<div class="sidebar-item active"> 
				<a onclick="location.href='/account/profile/change-password'">Đổi Mật Khẩu</a>
			</div>
			<div class="sidebar-item"> 
				<a onclick="location.href='/account/profile/orders'">Lịch Sử Mua Hàng</a>
			</div>
		</div>
		<div class="container-p__body">
			<div class="body-top">
				Đổi Mật Khẩu
			</div>
			<div class="body-content">
				
				<div class="form-container">
		            <form action="/account/profile/change-password" class="form" method="post">
		            	<div class="form__row">
		                    <label class="form__label" for="currentpassword">Mật Khẩu Hiện Tại: </label>
		                    <input class="form__input" type="password" name="currentpassword" id="currentpassword" required>
		                </div>
		                <div class="form__row">
		                    <label class="form__label" for="password">Mật Khẩu Mới:</label>
		                    <input class="form__input" type="password" name="password" id="password" required>
		                </div>
		                
		                <div class="form__row">
		                    <label class="form__label" for="password2">Nhập Lại Mật Khẩu:</label>
		                    <input class="form__input" type="password" name="password2" id="password2" required>
		                </div>
		                
		               <button class="save-info">Xác Nhận</button>
		            </form>
		        </div>
				
				
			</div>
		</div>
	</div>
	
<%@ include file="/common/web/footer.jsp"%>
</body>
</html>
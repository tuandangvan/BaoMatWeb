<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<link rel='stylesheet' href="/css/login/login.css">
<link rel='stylesheet' href="/css/alertMessage.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
	
	<div id="alertMessage">
		<c:if test="${messageError !=null}">
			<div class="alert">
			  <span class="closebtn">&times;</span>  
			 	${messageError}
			</div>
		</c:if>
		
		<c:if test="${messageSuccess !=null}">
			<div class="alert success">
			  <span class="closebtn">&times;</span>  
			 	${messageSuccess}
			</div>
		</c:if>
	</div>
	
	<div class="form-wrap">
      <div class="tabs">
        <h3 class="login-tab"><a class="active" href="/account/login">Đăng Nhập</a></h3>
        <h3 class="signup-tab"><a href="/account/signup">Đăng Ký</a></h3>
      </div><!--.tabs-->
  
      <div class="tabs-content">
        
        <div id="login-tab-content" class="active">
          <form class="login-form" action="/account/login" method="post">
            <input type="email" class="input" id="email" name="email"  value="${user.email}" required placeholder="Email">
            <input type="password" class="input" id="password" name="password" value="${user.password}"  autocomplete="off" required placeholder="Mật khẩu">
            <input type="submit" class="button" value="Đăng nhập">
          </form><!--.login-form-->
          <div class="help-text">
            <p><a href="#">Quên mật khẩu?</a></p>
          </div><!--.help-text-->
        </div><!--.login-tab-content-->
        
        <div id="signup-tab-content" >
          <form class="signup-form" action="/account/signup" method="post">
            <input type="email" class="input" id="email" name="email" value="${user.email}" autocomplete="off" required placeholder="Email">
            <input type="text" class="input" id="firstName" name="firstName" value="${user.firstName}" autocomplete="off" required placeholder="Họ">
            <input type="text" class="input" id="lastName" name="lastName" value="${user.lastName}" autocomplete="off" required placeholder="Tên">
            <input type="text" class="input" id="idCard" name="idCard" value="${user.idCard}" autocomplete="off" required placeholder="Số CMND">
            <input type="text" class="input" id="phone" name="phone" value="${user.phone}" autocomplete="off" required placeholder="Số điện thoại">
           <!--  <input type="text" class="input" id="phone" name="" autocomplete="off" placeholder="Địa chỉ"> -->
            <input type="password" class="input" id="password" name="password" value="${user.password}" required autocomplete="off" placeholder="Mật khẩu">
            <input type="password" class="input" id="password2" name="password2" value="${user.password2}" required autocomplete="off" placeholder="Nhập lại mật khẩu">
            <input type="submit" class="button" value="Đăng Ký">
          </form><!--.login-form-->
        </div><!--.signup-tab-content-->
        
      </div><!--.tabs-content-->
    </div><!--.form-wrap-->
     <script type="text/javascript">
     var close = document.getElementsByClassName("closebtn");
     var i;

     for (i = 0; i < close.length; i++) {
       close[i].onclick = function(){
         var div = this.parentElement;
         div.style.opacity = "0";
         setTimeout(function(){ div.style.display = "none"; }, 600);
       }
     }
     if('${action}' === "signup"){
    	 let tab = $('.tabs h3 a');
    	 tab.removeClass('active');
    	 $(tab).eq(1).addClass('active');
 		 $('div[id$="tab-content"]').removeClass('active');
 		 $("#signup-tab-content").addClass('active');
     }
     
     </script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

 <!-- CSS -->
 	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/brands.min.css" integrity="sha512-G/T7HQJXSeNV7mKMXeJKlYNJ0jrs8RsWzYG7rVACye+qrcUhEAYKYzaa+VFy6eFzM2+/JT1Q+eqBbZFSHmJQew==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/fontawesome.min.css" integrity="sha512-giQeaPns4lQTBMRpOOHsYnGw1tGVzbAIHUyHRgn7+6FmiEgGGjaG0T2LZJmAPMzRCl+Cug0ItQ2xDZpTmEc+CQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/solid.min.css" integrity="sha512-6mc0R607di/biCutMUtU9K7NtNewiGQzrvWX4bWTeqmljZdJrwYvKJtnhgR+Ryvj+NRJ8+NnnCM/biGqMe/iRA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
 	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/regular.min.css" integrity="sha512-k2UAKyvfA7Xd/6FrOv5SG4Qr9h4p2oaeshXF99WO3zIpCsgTJ3YZELDK0gHdlJE5ls+Mbd5HL50b458z3meB/Q==" crossorigin="anonymous" referrerpolicy="no-referrer" />   
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/js/regular.min.js" integrity="sha512-H1PxHAj13z6ipNSlyM5ftUSOSvQXeH0eOsPjEpSOcjSROPLIxhKB0PLGSAbFDqEqe2vd2xPkUnEfVtBS/RNlmw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="//fonts.googleapis.com/css?family=Open+Sans:400,300,600" rel="stylesheet" type="text/css">
    <link rel='stylesheet' href="/css/web/web.css">
    <link rel='stylesheet' href="/css/modal.css">
    <link rel='stylesheet' href="/css/alertMessage.css">
    <!-- script -->
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
	
	<nav class="navbar navbar-expand-md navbar-dark bg-dark">
	    <div class="container">
	        <a class="navbar-brand" href="/">SUNNY</a>
	        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
	            <span class="navbar-toggler-icon"></span>
	        </button>
	
	        <div class="collapse navbar-collapse justify-content-end" id="navbars">
	        
	        	<form class="form-inline my-2 my-lg-0" action="/search" method="post">
	                <div class="input-group input-group-sm searchfield">
	                    <input type="text" class="form-control" id="search-key" name="search-key" placeholder="Search...">
	                    <select name="option" id="option">
						  	<option value="product">Sản phẩm</option>
						  	<option value="category">Danh mục</option>
						  	<!-- <option value="store">Cửa hàng</option> -->
						</select>
	                    <div class="input-group-append">
	                        <button type="button" class="btn btn-secondary btn-number btn-search">
	                            <i class="fa fa-search"></i>
	                        </button>
	                    </div>
	                </div>
	                
	            </form>
	        
	            <ul class="navbar-nav m-auto">
	                <li class="nav-item active" id="home">
	                    <a class="nav-link" href="/">Trang chủ </a>
	                </li>
	                <li class="nav-item">
	                    <a class="nav-link" href="/category-list" id="category">Danh mục</a>
	                </li>
	                <!-- <li class="nav-item">
	                    <a class="nav-link" href="product.html">Cửa hàng</a>
	                </li> -->
	                <c:choose>
					    <c:when test="${sessionScope.user != null}">
					       <li class="nav-item user-name">
			                    <a class="nav-link" >${sessionScope.user.getFirstName()} ${sessionScope.user.getLastName()} </a>
				                <div class="user-dropdown">
				                	<div class="user-dropdown__item" onclick="location.href='/account/profile';">
				                		<a>Tài Khoản Của Tôi</a>
				                	</div>
				                	<div class="user-dropdown__item" onclick="location.href='/account/profile/orders';">
				                		<a>Đơn Mua</a>
				                	</div>
				                	<div class="user-dropdown__item" onclick="location.href='/account/logout';">
				                		<a>Đăng Xuất</a>
				                	</div>
				                </div>
			                </li>
					    </c:when>    
					    <c:otherwise>
					        <li class="nav-item">
			                    <a class="nav-link" href="/account/login">Đăng nhập</a>
			                </li>
					    </c:otherwise>
					</c:choose>
	                
	                 <c:choose>
					    <c:when test="${sessionScope.user != null}">
					       <a class="btn btn-success btn-sm ml-3" href="/cart">
			                    <i class="fa fa-shopping-cart"></i> Giỏ Hàng
			                    <span class="badge badge-light"></span>
			                </a>
					    </c:when>    
					    <c:otherwise>
					        <a class="btn btn-success btn-sm ml-3" href="/account/login">
			                    <i class="fa fa-shopping-cart"></i> Giỏ Hàng
			                    <span class="badge badge-light"></span>
			                </a>
					    </c:otherwise>
					</c:choose>
	                
	                
	            </ul>
	
	            
	        </div>
	    </div>
	</nav>
	
	<!-- thông báo xác nhận muốn thêm vào giỏ hàng -->
	<div id="confirm-add-to-cart" class="modal">
	  <div class="modal-content">
	    <div class="modal-header">
	      <span class="close close-modal">&times;</span>
	      <h2>Thông báo</h2>
	    </div>
	    <div class="modal-body">
	      <p>Bạn có muốn thêm sản phẩm này vào giỏ hàng không?</p>
	    </div>
	    <div class="modal-footer">
	      <button class="close-modal">Hủy</button>
	      <button id="xac-nhan-them-vao-gio" >Xác nhận</button>
	    </div>
	  </div>
	</div>
		
	
	<script type="text/javascript">
	var pid;
	var modal = document.getElementById("confirm-add-to-cart");
	
	let page = '${page}';
	$('#navbars li').removeClass("active");
	$('#' + page).addClass("active");
	
	//chi tiet sp
	$(document).on("click",".product-card",function(e) {
		window.location.href = location.protocol + '//' + location.host +  "/product/" + $(this).find('input').val()
	})
	//thông báo xác nhận muốn thêm vào giỏ hàng
	$(document).on("click",".add-to-cart",function(e) {
		e.preventDefault();
		e.stopPropagation();
		pid = $(this).closest('.product-card').find('input').eq(0).val()
		
		modal.style.display = "block";
	});
	$(document).on("click",".close-modal",function() {
		modal.style.display = "none";
	});
	window.onclick = function(event) {
	  if (event.target == modal) {
	    modal.style.display = "none";
	  }
	}

	$(document).on("click","#xac-nhan-them-vao-gio",function() {
		
		$.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: location.protocol + '//' + location.host +  "/cart/add-to-cart",
	        data: JSON.stringify(pid),
	        /* dataType: 'json', */
	        success: function (data) {
				$('#alertMessage').append(`<div class="alert success">
						  <span class="closebtn">&times;</span>  
					 	\${data}
					</div>`)
				//alert(data)
					modal.style.display = "none";
	        },
	        error: function (e) {
				alert("An error occur!");
	        }
	    });
		
	});
	$(document).on('keypress', '#search-key', function(event){
		if(event.key === "Enter"){
			let searchkey = $('#search-key').val()
			if(searchkey == "" || searchkey == null){
				event.preventDefault();
			}
		}
	})
	
	//close message

	/* $(document).on('click', '.closebtn', function(){
		var div = this.parentElement;
		div.style.opacity = "0";
		setTimeout(function(){ div.style.display = "none"; }, 600);
	}) */
	
       
	</script>
	<script src="/js/alertMessage.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LTWEB</title>
<link rel='stylesheet' href="/css/web/orders.css">
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
			<div class="sidebar-item "> 
				<a onclick="location.href='/account/profile/edit'">Chỉnh Sửa Hồ Sơ</a>
			</div>
			<div class="sidebar-item"> 
				<a onclick="location.href='/account/profile/change-password'">Đổi Mật Khẩu</a>
			</div>
			<div class="sidebar-item active" > 
				<a onclick="location.href='/account/profile/orders'">Lịch Sử Mua Hàng</a>
			</div>
		</div>
		<div class="container-p__body">
			<div class="body-top">
				Lịch Sử Mua Hàng
			</div>
			<div class="body-content">
			
				<div class="store">
	           		<table class="table table-striped">
	                    <thead>
	                        <tr>
	                            <th scope="col">Địa Chỉ</th>
	                            <th scope="col">Số Điện Thoại</th>
	                            <th scope="col">Ngày Đặt</th>
	                            <th scope="col">Tổng Thanh Toán</th>
	                            <th scope="col">Vận Chuyển</th>
	                            <th scope="col">Trạng Thái</th>
	                            <th></th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    	<c:forEach items="${orders}" var ="order">
	                    		<tr>
		                            <td>${order.address}</td>
		                            <td>${order.phone}</td>
		                            <td>${order.createdAt}</td>
		                            <td>${order.amountFromUser}</td>
		                            <td>${order.deliveryId.name}</td>
		                            <td>${order.status}</td>
		                            <td> <a class="order-info"><i class="fa-solid fa-circle-info"></i></a></td>
		                        </tr>
		                        <tr class="subtable-orderitem ">
								  <td colspan=6>
								    <div>
								    	
							    		<table class="table table-striped">
						                    <thead>
						                        <tr>
						                        	<th scope="col"></th>
						                        	<th scope="col"></th>
						                        	<th scope="col"></th>
						                            <th scope="col">Tên Sản Phẩm</th>
						                            <th scope="col">Giá</th>
						                            <th scope="col">Số Lượng</th>
						                        </tr>
						                    </thead>
						                    <tbody>
						                    	<c:forEach items="${orderitems}" var ="orderitem">
							    					<c:if test="${orderitem.orderId._id == order._id}">
							                    		<tr>
							                    			<td></td>
							                    			<td></td>
							                    			<td> <div class="product-image"><img src="/vendor/store/product/images/${orderitem.productId.listImages[0]}" /></div> </td>
								                            <td>${orderitem.productId.name}</td>
								                            <td>${orderitem.productId.price}</td>
								                            <td>${orderitem.count}</td>
								                        </tr>
							                        </c:if>
						    	
						    					</c:forEach>					
						                    </tbody>
					                    </table>
									    	
								    </div>
								  </td>
								</tr>
		                       
	                    	</c:forEach>
	
	                    </tbody>
                    </table>
                </div>
				
			</div>
		</div>
	</div>
	
<%@ include file="/common/web/footer.jsp"%>

<script type="text/javascript">
	$(document).on('click', '.order-info', function(){
		let x = $(this).closest('tr').next()
		$('.subtable-orderitem').removeClass("active");
		x.addClass("active");
	})
</script>
</body>
</html>
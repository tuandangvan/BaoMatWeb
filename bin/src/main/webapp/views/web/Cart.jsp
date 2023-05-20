<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LTWEB</title>

<link rel='stylesheet' href="/css/web/cart.css">
</head>
<body>
	<%@ include file="/common/web/header.jsp"%>
	<br/>
	<div class="container mb-4">
	    <div class="row">
	        <div class="col-12">
	            <div class="table-responsive">
	            	<div class="choose-all">
	            		<input type="checkbox" id="check-all" name="check-all">
	            		<a>CHỌN TẤT CẢ</a>
	            	</div>
	            	
	            	<c:forEach items="${cartItem}" var ="cartitembystore">
		            	<div class="store">
		            		<div class="store-name">
				            	<a href="/store/${cartitembystore[0].cartId.storeId._id}">${cartitembystore[0].cartId.storeId.name}</a>
		            		</div>
		            		
			                <table class="table table-striped">
			                    <thead>
			                        <tr>
			                            <th scope="col"><input type="checkbox" class="check-shop" name="check-shop"></th>
			                            <th scope="col"></th>
			                            <th scope="col">Product</th>
			                            <th scope="col" class="text-left">Price</th>
			                            <th scope="col" class="text-center">Quantity</th>
			                            <th> </th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach items="${cartitembystore}" var ="cartitem">
				                    	<tr data-cartitemid = "${cartitem._id}">
				                        	<td><input type="checkbox" class="check-one" name="check-one" value="${cartitem._id}"></td>
				                            <td> <div class="product-image"><img src="/vendor/store/product/images/${cartitem.productId.listImages[0]}" /></div> </td>
				                            <td>${cartitem.productId.name}</td>
				                            <td class="text-left">${cartitem.productId.price}</td>
				                            
				                            <td class="text-center input-center">
				                            <i class="fa fa-minus decreaseQty"></i>
				                            <input class="form-control text-center product-quantity-cartitem" type="text" value="${cartitem.count}" />
				                            <i class="fa fa-plus increaseQty"></i>
				                            
				                            </td>
				                            <td class="text-center">
				                            <form action="/cart/delete" method="post">
						                		<input type="hidden" name="cartitem" value="${cartitem._id}" multiple >
						                    	<button class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> 
						                    </form>
				                            </td>
				                        </tr>
			                    	</c:forEach>
			                    </tbody>
			                </table>
		                </div>
	            	</c:forEach>
	            	<div class="ThanhToan">
	            		<a>Tổng Tiền (</a> <a class="TongSanPham">0 </a> <a> sản phẩm ): </a>  <a class="TongTien">0</a>
	            	</div>
	            	
	            	<!-- <div class="store">
	            		<div class="store-name">
			            	<a href="">store1</a>
	            		</div>
	            		
		                <table class="table table-striped">
		                    <thead>
		                        <tr>
		                            <th scope="col"><input type="checkbox" name="check-shop"></th>
		                            <th scope="col"></th>
		                            <th scope="col">Product</th>
		                            <th scope="col" class="text-left">Price</th>
		                            <th scope="col" class="text-center">Quantity</th>
		                            <th> </th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                        <tr>
		                        	<td><input type="checkbox" name="check-one"></td>
		                            <td> <div class="product-image"><img src="https://dummyimage.com/80x80/55595c/fff" /></div> </td>
		                            <td>Product Name Dada</td>
		                            <td class="text-left">124,90 €</td>
		                            <td class="text-center input-center"><input class="form-control text-center" type="text" value="1" /></td>
		                            <td class="text-center"><button class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> </td>
		                        </tr>
		                        <tr>
		                        	<td><input type="checkbox" name="check-one"></td>
		                            <td> <div class="product-image"><img src="https://dummyimage.com/80x80/55595c/fff" /></div> </td>
		                            <td>Product Name Dada</td>
		                            <td class="text-left">124,90 €</td>
		                            <td class="text-center input-center"><input class="form-control text-center" type="text" value="1" /></td>
		                            <td class="text-center"><button class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> </td>
		                        </tr>
		                        <tr>
		                        	<td><input type="checkbox" name="check-one"></td>
		                            <td> <div class="product-image"><img src="https://dummyimage.com/80x80/55595c/fff" /></div> </td>
		                            <td>Product Name Dada</td>
		                            <td class="text-left">124,90 €</td>
		                            <td class="text-center input-center"><input class="form-control text-center" type="text" value="1" /></td>
		                            <td class="text-center"><button class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> </td>
		                        </tr>
		                    </tbody>
		                </table>
	                </div> -->
	                
	            </div>
	        </div>
	        <div class="col mb-2">
	            <div class="row">
	                <div class="col-sm-12  col-md-4" onclick="location.href='/'">
	                    <button class="btn btn-block btn-light" id="back-to-homepage">Tiếp tục mua sắm</button>
	                </div>
	                <div class="col-sm-12  col-md-4">
	                	<form action="/cart/delete" method="post">
	                		<input type="hidden" name="cartitem" id="input-cartitem-delete" value="" multiple >
	                    	<button class="btn btn-block btn-light" id="delete-cartitem" disabled>Xóa</button>
	                    </form>
	                </div>
	                <div class="col-sm-12 col-md-4 text-right">
	                    <button class="btn btn-lg btn-block btn-success text-uppercase" id="dat-hang" disabled>Đặt Hàng</button>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	
	<!-- Form điền thông tin đặt hàng -->
	<form action="/order/add" method="post">
		<div id="form-order-info" class="modal">
		  <div class="modal-content">
		    <div class="modal-header">
		      <span class="close close-modal">&times;</span>
		      <h2>Đặt hàng</h2>
		    </div>
		    <div class="modal-body">
		    	<label>Địa chỉ: </label><br/>
		    	<input type="text" name="address" value=""><br/><br/>
		    	
		    	<label>Số Điện thoại: </label><br/>
		    	<input type="text" name="phone" value=""><br/><br/>
		    	
		    	<label>Đơn vị giao hàng: </label>
		    	<select name="delivery" id="delivery">
		    		<c:forEach items="${deliveries}" var ="delivery">
		    			<option value="${delivery._id}" data-monney="${delivery.price}">${delivery.name}</option>
		    		</c:forEach>
				</select>
		    	<br/><br/>
		    	<div class="thongtinhoadon">
		    		<a>Tổng Tiền (</a> <a class="TongSanPham">0 </a> <a> sản phẩm ): </a>  <a class="TongTien">0</a><br/>
		    		<a>Phí Vận Chuyển (</a> <a class="tenvanchuyen">None </a> <a> ): </a> <a class="phivanchuyen">0</a><br/>
		    		<a>Tổng Thanh Toán: <a class="tongthanhtoan">0</a></a>
		    	</div>
		    	<br/><br/>
		    </div>
		    <div class="modal-footer">
		      <button class="close-modal" >Hủy</button>
		      <button id="xac-nhan-dat-hang" >Xác nhận</button>
		    </div>
		  </div>
		</div>
		
		<input type="hidden" name="cartitem" id="input-cartitem" value="" multiple >
		
	</form>
	<%@ include file="/common/web/footer.jsp"%>
	
	<script type="text/javascript">
	var regnumber = /^\d+$/;
	
	// checkbox all
		$(document).on('change', '#check-all', function() {
		    if (this.checked) {
		    	$('.store input[type=checkbox]').each(function () {
		    	    $(this).prop('checked', true);
		    	    
		    	});
		    } else {
		    	$('.store input[type=checkbox]').each(function () {
		    	    $(this).prop('checked', false);
		    	});
		    }
		});
		
	// uncheck checkbox-all
		$(document).on('change', '.store input[type=checkbox]', function(e) {
		    if (!this.checked) {
		    	$('#check-all').prop('checked', false);
		    }
		});
	// checkbox shop
		$(document).on('change', '.check-shop', function() {
		    if (this.checked) {
		    	$(this).closest('table').find('tbody .check-one').prop('checked', true);
		    } else {
		    	$(this).closest('table').find('tbody .check-one').prop('checked', false);
		    }
		});
	// uncheck checkbox-shop
		$(document).on('change', '.store .check-one', function(e) {
			
		    if (!this.checked) {
		    	
		    	$(this).closest('table').find('thead .check-shop').prop('checked', false);
		    }
		});
	
	// chặn submit form khi ấn hủy 	
		$(document).on('click', 'button.close-modal', function(e) {
			e.preventDefault();
		});
		
		
		//form đặt hàng
		var modal = document.getElementById("form-order-info");
		$(document).on("click","#dat-hang",function() {
			let strcartitem = "";
			let checked = $(".check-one:checked");
			//console.log($(checked).val());
			$.each(checked, function( index, value ) {
				strcartitem = strcartitem + $(value).val() + ",";
			});
			strcartitem = strcartitem.slice(0, -1);
			console.log(strcartitem);
			$("#input-cartitem").val(strcartitem);
			modal.style.display = "block";
			
			computePrice();
		});
		$(document).on("click",".close-modal",function() {
			modal.style.display = "none";
		});
		window.onclick = function(event) {
		  if (event.target == modal) {
		    modal.style.display = "none";
		  }
		}
		// dùng để thay đổi các liên quan đến chọn checkbox
		$(document).on('change', '.container input[type=checkbox]',async function() {
			let strcartitem = "";
			let checked = $(".check-one:checked");
			$.each(checked, function( index, value ) {
				strcartitem = strcartitem + $(value).val() + ",";
			});
			strcartitem = strcartitem.slice(0, -1);
			
			//kiểm tra nếu có ít nhất 1 checked thì mới được click đặt hàng hoặc xóa
			if(strcartitem == "" || strcartitem == null){
				$('#delete-cartitem').prop('disabled', true);
				$('#dat-hang').prop('disabled', true);
				$('.TongSanPham').text(0);;
				$('.TongTien').text(0);
			}
			else{
				$('#delete-cartitem').prop('disabled', false);
				$('#dat-hang').prop('disabled', false);
				let array = strcartitem.split(",");
				let totalprice = await getToTalPrice(array);
				let totalitem = array.length;
				console.log(array);
				console.log(array.length);
				$('.TongSanPham').text(totalitem);;
				$('.TongTien').text(totalprice);
				
			}
			
			$('#input-cartitem-delete').val(strcartitem);
			console.log($('#input-cartitem-delete').val())
		})
		//hàm tính tiền
		function getToTalPrice(array, deliveryId = 0){
			return new Promise(resolve => {
				var data = new FormData();
				data.append('cartItem[]', array)
				if(deliveryId > 0){
					data.append('deliveryId', deliveryId)
				}
				$.ajax({
			        type: "POST",
			        //contentType: "application/json",
			        url: location.protocol + '//' + location.host +  "/cart/get-total-price",
			        data: data,
			        cache: false,
			        contentType: false,
			        processData: false,
			        /* dataType: 'json', */
			        success: function (data) {
						//console.log('data: ' + data)
						resolve(data)
			        },
			        error: function (e) {
						alert("An error occur!.");
			        }
			    });  
			});
		}
		//khi delivery change
		$(document).on('change', '#delivery',async function(){
			computePrice();
		});
		async function computePrice(){
			var pvc = $('.phivanchuyen');
			var tvc = $('.tenvanchuyen');
			var ttt = $('.tongthanhtoan');
			var tt = $('.TongTien');
			
			let strcartitem = "";
			let checked = $(".check-one:checked");
			$.each(checked, function( index, value ) {
				strcartitem = strcartitem + $(value).val() + ",";
			});
			strcartitem = strcartitem.slice(0, -1);
			let array = strcartitem.split(",");
			
			tvc.text($('#delivery').find(":selected").text())
			pvc.text($('#delivery').find(":selected").data('monney'))
			let tong = await getToTalPrice(array,$('#delivery').find(":selected").val());
			ttt.text(tong)
		}
		//khi ấn nút +
		$(document).on('click', '.increaseQty',async function(){
			let cartItemId = $(this).closest('tr').data("cartitemid");
			let newvalue = $(this).closest('tr').find('.product-quantity-cartitem').val();
			await changquantity(cartItemId,parseInt(newvalue) + 1);
			newvalue = await loadquantity(cartItemId);
			$(this).closest('tr').find('.product-quantity-cartitem').val(newvalue);
			
		})
		//khi ấn nút -
		$(document).on('click', '.decreaseQty',async function(){
			let cartItemId = $(this).closest('tr').data("cartitemid");
			let newvalue = $(this).closest('tr').find('.product-quantity-cartitem').val();
			if(newvalue > 1){
				await changquantity(cartItemId,parseInt(newvalue) - 1);
				newvalue = await loadquantity(cartItemId);
				$(this).closest('tr').find('.product-quantity-cartitem').val(newvalue);				
			}
		})
		//khi thay đổi số lượng sản phẩm đặt hàng
		$(document).on('change', '.product-quantity-cartitem',async function(){
			
			//kiểm tra > 0 nếu đúng -> thay đổi nếu không load lại
			let cartItemId = $(this).closest('tr').data("cartitemid");
			let newvalue = $(this).val();
			if(regnumber.test(newvalue) && newvalue > 0 ){
				// gọi hàm đổi lại quantity
				await changquantity(cartItemId,newvalue);
				//loadquantity();
			}
			//load lại số lượng
			newvalue = await loadquantity(cartItemId);
			$(this).val(newvalue);
		})
		//hàm load lại số lượng
		function loadquantity(cartItemId){
			return new Promise(resolve => {
				 $.ajax({
			        type: "POST",
			        contentType: "application/json",
			        url: location.protocol + '//' + location.host +  "/cart/get-quantity-cartitem",
			        data: JSON.stringify(cartItemId),
			        /* dataType: 'json', */
			        success: function (data) {
						//console.log('data: ' + data)
						resolve(data)
			        },
			        error: function (e) {
						alert("An error occur!");
			        }
			    });
			 });
		}
		//hàm thay đổi số lượng vào database
		function changquantity(cartItemId, quantity){
			return new Promise(resolve => {
				 $.ajax({
			        type: "POST",
			        contentType: "application/json",
			        url: location.protocol + '//' + location.host +  "/cart/change-quantity-cartitem",
			        data: JSON.stringify({"_id":cartItemId,"count":quantity}),
			        /* dataType: 'json', */
			        success: function (data) {
						//console.log('data: ' + data)
						resolve(data)
			        },
			        error: function (e) {
						alert("An error occur!");
			        }
			    });
			 });
		}
		
		//kiểm tra nhập số
		
	</script>
</body>
</html>
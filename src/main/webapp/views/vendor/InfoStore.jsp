<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LTWEB</title>
<link rel='stylesheet' href="/css/web/product.css">
</head>
<body>
	<%@ include file="/common/web/header.jsp"%>
	<br />
	<div class="container mt-3">
		<div class="row">
			<div class="col-sm">
				<div class="card mb-3">
					<div>
						<img src="/images/${store.avatar}"
							class="img-fluid border border-dark rounded-circle border-3"
							style="position: absolute; top: 160px; left: 0; z-index: 1;width: 80px; height: 80px;">
						<div style="background: rgba(0, 0, 0, 0.5);">
							<img src="/images/${store.cover}" class="img-fluid rounded-3 p-1"
								style="width: 100%; height: 30vh; opacity: 0.5">
						</div>
						<h2 class="text-muted w-50" style="position: relative; left:100px; align-text: center">${store.name}</h2>
					</div>
				</div>
				<div class="card">
					<div class="card-header bg-primary text-white text-uppercase">
						<i class="fa fa-star"></i><span> Tất cả sản phẩm của ${store.name }</span>
					</div>
					<div class="card-body">
						<c:forEach items="${listProducts }" var="item">
							<div class="product-card">
								<div class="product-tumb">
									<img src="/images/${item.listImages[0]}" alt="">
								</div>
								<div class="product-details">
									<p>${item.name }</p>
									<div class="product-bottom-details">
										<div class="product-price">
											<span>${item.price }</span>
										</div>
										<div class="product-links">
											<a href=""><i class="fa fa-shopping-cart"></i></a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/common/web/footer.jsp"%>
</body>
</html>
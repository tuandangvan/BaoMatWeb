<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LTWEB</title>

<link rel='stylesheet' href="/css/web/category.css">
</head>
<body>
	<%@ include file="/common/web/header.jsp"%>
	<br/>
	

<div class="container mt-3">
    <p>Tất cả danh mục</p>
    <div class="card-body">        
        <c:forEach items="${categories}" var ="cate">
        	<div  class="product-card" onclick="location.href='/category-list/${cate.slug}';">
	            <div class="product-tumb">
	                <img src="/vendor/store/product/images/${cate.image}" alt="">
	            </div>
	            <div class="product-details">
	                <p>${cate.name}</p>
	            </div>
	        </div>
        </c:forEach>
    </div>
</div>
	
	<%@ include file="/common/web/footer.jsp"%>
</body>
</html>
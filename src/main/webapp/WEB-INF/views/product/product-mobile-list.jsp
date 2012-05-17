<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<layout:default>
<div class='row'>
	<div class='span6'>
		<h1>Custom request condition - mobile view</h1>
	</div>
	<div class='span6'>
		<div class='btn-group pull-right'>
			<c:forEach items="${productTypes}" var="type">
				<spring:url value="/product?productType=" var="productListUrl"/>
				<a href='${productListUrl}${type.code}' class='btn btn-large'>${type.description}</a>
			</c:forEach>
		</div>
	</div>
</div>
<hr>
<table class='table table-striped table-bordered'>
<thead>
<tr>
	<th>#</th>
	<th>상품유형</th>
	<th>상품명</th>
	<th>가격</th>
	<th>할인율</th>
</tr>
</thead>
<tbody>
</tbody>
<c:forEach items="${products}" var="product" varStatus="status">
<tr>
	<td>${status.count}</td>
	<td>${product.type.description}</td>
	<td>${product.title}</td>
	<td>${product.price}</td>
	<td>${product.discountRate}</td>
</tr>
</c:forEach>
</table>
<div class='row'>
</div>
</layout:default>
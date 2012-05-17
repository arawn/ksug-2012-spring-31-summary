<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>

<layout:default>
<div class='row'>
	<div class='span6'>
		<h1>CRUD Controller</h1>
	</div>
	<div class='span6'>
		<a href='<spring:url value="/movie/new"/>' class='btn pull-right'>신규 등록</a>
	</div>
</div>
<hr>
<table class='table table-striped'>
<thead>
<tr>
	<th>#</th>
	<th>영화명</th>
	<th>감독</th>
	<th>가격</th>
	<th>할인율</th>
</tr>
</thead>
<tbody>
</tbody>
<c:forEach items="${movies}" var="movie" varStatus="status">
<spring:url value="/movie/${movie.id}" var="showUrl"/>
<tr style="cursor: pointer;" onclick='location.href="${showUrl}";'>
	<td>${status.count}</td>
	<td>${movie.title}</td>
	<td>${movie.director}</td>
	<td>${movie.price}</td>
	<td>${movie.discountRate}</td>
</tr>
</c:forEach>
</table>
</layout:default>
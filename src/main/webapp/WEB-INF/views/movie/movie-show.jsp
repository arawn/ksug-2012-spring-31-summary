<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>

<layout:default>
<h1>영화 정보</h1>
<hr>
<div class="row">
	<div class="well" style="margin-left: 30px;">
		<table>
		<colgroup>
		<col style="width: 120px;">
		<col>
		</colgroup>
		<tr>
			<td>영화 제목</td>
			<td>${movie.title}</td>
		</tr>
		<tr>
			<td>감독</td>
			<td>${movie.director}</td>
		</tr>
		<tr>
			<td>가격</td>
			<td>${movie.price}</td>
		</tr>
		<tr>
			<td>할인율</td>
			<td>${movie.discountRate}</td>
		</tr>
		</table>
	</div>
</div>
<div class='form-actions'>
	<a href='<spring:url value="/movie"/>' class='btn'>To List</a>
	<a href='<spring:url value="/movie/edit/${movie.id}"/>' class='btn btn-warning'>To Modify</a>
	<a href='javascript:$("#movieDeleteForm").submit();' class='btn btn-inverse'>Delete</a>
</div>
<c:if test="${not empty alertCode}">
<div class="alert alert-success">
	<button class="close" data-dismiss="alert">×</button>
	<strong>알림 :</strong> <spring:message code="${alertCode}"/>
</div>
</c:if>
<form id='movieDeleteForm' name='movieDeleteForm' method='post' action='<spring:url value="/movie/${movie.id}" />'>
<input type="hidden" name='_method' value='delete'/>
</form>
</layout:default>
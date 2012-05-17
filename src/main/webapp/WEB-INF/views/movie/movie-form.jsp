<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>

<c:if test="${empty movie.id}">
	<spring:url var="action" value="/movie"/>
	<c:set      var="method" value="post"/>
</c:if>
<c:if test="${not empty movie.id}">
	<spring:url var="action" value="/movie/${movie.id}"/>
	<c:set      var="method" value="put"/>
</c:if>

<layout:default>
<h1>영화 등록</h1>
<hr>
<form:form id="movieForm" name='movieForm' action='${action}' method='${method}' modelAttribute='movie' cssClass="form-horizontal well">   
	<fieldset>
		<div class="control-group">
			<form:label path="title" cssClass="control-label">영화 제목</form:label>
			<div class="controls">
				<form:input path="title" cssClass="input-xlarge"/>
				<span class="help-inline"><form:errors path="title"/></span>
			</div>
		</div>
		<div class="control-group">
			<form:label path="director" cssClass="control-label">감독</form:label>
			<div class="controls">
				<form:input path="director" cssClass="input-xlarge"/>
				<span class="help-inline"><form:errors path="director"/></span>
			</div>
		</div>
		<div class="control-group">
			<form:label path="price" cssClass="control-label">가격</form:label>
			<div class="controls">
				<form:input path="price" cssClass="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<form:label path="discountRate" cssClass="control-label">할인율</form:label>
			<div class="controls">
				<form:input path="discountRate" cssClass="input-xlarge"/>
			</div>
		</div>
	</fieldset>
</form:form>
<div class="form-actions" style="padding-left: 290px;">
	<button class="btn btn-primary" type="button" onclick="$('#movieForm').submit();">Save changes</button>
	<button class="btn" onclick="location.href='<spring:url value="/movie"/>';">Cancel</button>
</div>
</layout:default>
<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang='ko'>
<head>
	<meta charset='utf-8'>
	<title>KSUG 세미나 2012 - Part 1</title>
	<meta name='viewport' content='width=device-width, initial-scale=1.0'>
	<meta name='description' content=''>
	<meta name='author' content=''>

	<!-- Le styles -->
	<link href='http://twitter.github.com/bootstrap/assets/css/bootstrap.css' rel='stylesheet'>
	<style type='text/css'>
	body {
		padding-top: 60px;
		padding-bottom: 40px;
	}
	</style>
	<link href='http://twitter.github.com/bootstrap/assets/css/bootstrap-responsive.css' rel='stylesheet'>

	<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	<script src='http://html5shim.googlecode.com/svn/trunk/html5.js'></script>
	<![endif]-->

	<!-- Le fav and touch icons -->
	<link rel='shortcut icon' href='http://twitter.github.com/bootstrap/assets/ico/favicon.ico'>
	<link rel='apple-touch-icon-precomposed' sizes='144x144' href='http://twitter.github.com/bootstrap/assets/ico/apple-touch-icon-144-precomposed.png'>
	<link rel='apple-touch-icon-precomposed' sizes='114x114' href='http://twitter.github.com/bootstrap/assets/ico/apple-touch-icon-114-precomposed.png'>
	<link rel='apple-touch-icon-precomposed' sizes='72x72' href='http://twitter.github.com/bootstrap/assets/ico/apple-touch-icon-72-precomposed.png'>
	<link rel='apple-touch-icon-precomposed' href='http://twitter.github.com/bootstrap/assets/ico/apple-touch-icon-57-precomposed.png'>
</head>

<body>

	<div class='navbar navbar-fixed-top'>
		<div class='navbar-inner'>
			<div class='container'>
				<a class='btn btn-navbar' data-toggle='collapse' data-target='.nav-collapse'>
					<span class='icon-bar'></span> <span class='icon-bar'></span> <span class='icon-bar'></span>
				</a> 
				<a class='brand' href='<spring:url value="/movie"/>'>Spring @MVC 3.1 Demo</a>
				<div class='nav-collapse'>
					<ul class='nav'>
						<li><a href='<spring:url value="/movie"/>'>CRUD Controller</a></li>
						<li><a href='<spring:url value="/product"/>'>Custom request condition</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>

	<div class='container'>

		<jsp:doBody />

		<hr>

		<footer style="text-align: center;">
			<p>KSUG 세미나 2012 Part 1 - <a href='http://arawn.github.com'>arawn</a></p>
		</footer>

	</div>
	<!-- /container -->

	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src='http://twitter.github.com/bootstrap/assets/js/jquery.js'></script>
	<script src='http://twitter.github.com/bootstrap/assets/js/bootstrap-transition.js'></script>
	<script src='http://twitter.github.com/bootstrap/assets/js/bootstrap-alert.js'></script>
	<script src='http://twitter.github.com/bootstrap/assets/js/bootstrap-modal.js'></script>
	<script src='http://twitter.github.com/bootstrap/assets/js/bootstrap-dropdown.js'></script>
	<script src='http://twitter.github.com/bootstrap/assets/js/bootstrap-scrollspy.js'></script>
	<script src='http://twitter.github.com/bootstrap/assets/js/bootstrap-tab.js'></script>
	<script src='http://twitter.github.com/bootstrap/assets/js/bootstrap-tooltip.js'></script>
	<script src='http://twitter.github.com/bootstrap/assets/js/bootstrap-popover.js'></script>
	<script src='http://twitter.github.com/bootstrap/assets/js/bootstrap-button.js'></script>
	<script src='http://twitter.github.com/bootstrap/assets/js/bootstrap-collapse.js'></script>
	<script src='http://twitter.github.com/bootstrap/assets/js/bootstrap-carousel.js'></script>
	<script src='http://twitter.github.com/bootstrap/assets/js/bootstrap-typeahead.js'></script>

</body>
</html>
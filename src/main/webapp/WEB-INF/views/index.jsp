<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
       <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<!--[if IE 7 ]><html class="ie ie7" lang="en"><![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"><![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"><!--<![endif]-->
<head>

<!-- Basic Page Needs
================================================== -->
<meta charset="utf-8">
<title> - Free Theme s& Templates</title>

<!-- Mobile Specific
================================================== -->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- CSS
================================================== -->
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/boxed.css" id="layout">
<link rel="stylesheet" type="text/css" href="css/colors/green.css" id="colors">

<!-- Java Script
================================================== -->
<script src="js/jquery.min.js"></script>
<script src="js/custom.js"></script>
<script src="js/selectnav.js"></script>
<script src="js/flexslider.js"></script>
<script src="js/twitter.js"></script>
<script src="js/tooltip.js"></script>
<script src="js/effects.js"></script>
<script src="js/fancybox.js"></script>
<script src="js/carousel.js"></script>
<script src="js/isotope.js"></script>

<!-- Styles Switcher
================================================== -->
<link rel="stylesheet" type="text/css" href="css/switcher.css">
<script src="js/switcher.js"></script>

</head>
<body>

<!-- Wrapper Start -->
<div id="wrapper">


<!-- Header
================================================== -->

<!-- 960 Container -->
<div class="container ie-dropdown-fix">
<div id="header">
		<!-- Logo -->
		<div class="sixteen columns">
			<div id="logo">
				<a href="#"><img src="images/logo1.jpg" alt="logo" style="height: 114px" /></a>
				<div id="tagline">你的同学都在这里，快加入我们吧！</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<div class="sixteen columns">
		<div id="navigation">
			<ul id="nav">
				<li><a href="index">首页</a></li>
				<li><a href="apply">申请班级</a></li>
				<li style="float: right"><a href="login">登陆</a></li>
			</ul>
		</div> 
		<div class="clear"></div>
	</div>
</div>
<!-- 960 Container / End -->
<div class="container">
<!-- Content
================================================== -->
<div class=" eight  columns">
		<div class="headline no-margin"><h3>最新公告</h3></div>
	
	<c:if test="${tip!=null }">
	<div class="sixteen columns">
		<div class="notification success  closeable" style="margin: 5px 0 25px 0;">
			<p>${tip}</p>
		</div>
	</div>
</c:if>
<div class="">
	<c:forEach items="${newss}" var="bean"> 
	<div class="post">
			<div class="clear"></div>
			<a href="#" class="post-icon video"></a>
			<div class="post-content">
				<div class="post-title"><h2><a href="#">${bean.title }</a></h2></div>
				<div class="post-meta"><span><i class="mini-ico-calendar"></i>${bean.createDate }</span> <span><i class="mini-ico-user"></i>发布者： <a href="#">admin</a></span> <span><i class="mini-ico-comment"></i></span></div>
				<div class="post-description">
					<p>${bean.context }</p>
				</div>
			</div>
		</div>
	</c:forEach>
	<div class="post">
			<div class="clear"></div>
			<a href="#" class="post-icon video"></a>
			<div class="post-content">
				<div class="post-title"><h2><a href="#">${bean.title }</a></h2></div>
				<div class="post-meta"><span><i class="mini-ico-calendar"></i>${bean.createDate }</span> <span><i class="mini-ico-user"></i>发布者： <a href="#">admin</a></span> <span><i class="mini-ico-comment"></i></span></div>
				<div class="post-description">
					<p>${bean.context }</p>
				</div>
			</div>
		</div>
</div>
</div>


	<div class=" eight  columns ">
		<div class="headline no-margin"><h3>最近加入的班级</h3></div>
		<c:forEach items="${gradess }" var="bean">
		<div class="four columns">
			<div class="picture"><a href="#"><img src="images\web\student.png" alt=""></a></div>
			<div class="item-description">
				<h5><a href="#">${bean.name }</a></h5>
				<p>${bean.createDate }加入</p>
			</div>
		</div>
		</c:forEach>
	</div>
 </div>
</div>
<!-- Wrapper / End -->

	<%@include file="./foot.jsp" %>

</body>
</html>
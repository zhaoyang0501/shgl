<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
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
	<!-- Header -->
		<%@include file="./head.jsp" %>
	<!-- Header / End -->
	
</div>
<!-- 960 Container / End -->


<!-- Content
================================================== -->
<div class="container">

	<div class="sixteen columns">
	
		<!-- Page Title -->
		<div id="page-title">
			<h2>班级留言</h2>
			<div id="bolded-line"></div>
		</div>
		<!-- Page Title / End -->

	</div>
</div>

<div class="container">
	
	<div class="comments-sec">
		
		<ol class="commentlist">
		<c:forEach items="${msgBoards }" var="bean">
			 <c:if test="${bean.replyfor==null}">
			 	<li style="width: 100%">
					<div class="comments">
						<div class="avatar"><img style="width: 50px" src="upload/head/${bean.user.headimg }" alt="" border="0"> </div>
						<div class="comment-des">
						<div class="comment-by"><strong>${bean.user.name }</strong><span class="reply"><span style="color:#aaa">/ </span><a href="board?replyfor.id=${bean.id }">回复</a></span><span class="date">${bean.createDate}</span></div>
							<p>${bean.msg}</p>
						</div>
					 </div>
					 <c:if test="${fn:length(bean.subMsg)!=0 }">
					  <ol class="childlist">
					  <c:forEach items="${bean.subMsg }" var="sub">
					  <li style="width: 100%">
						<div class="comments">
							<div class="avatar"><img style="width: 50px" src="upload/head/${sub.user.headimg }" alt="" border="0"> </div>
							<div class="comment-des">
							<div class="comment-by"><strong>${sub.user.name }</strong><span class="reply"><span style="color:#aaa">/  <span class="date">${bean.createDate}</span></div>
							<p>${sub.msg}</p>
							</div>
						</div>
						</li>
					  </c:forEach>
					</ol>
					 </c:if>
				</li>
			 </c:if>
			</c:forEach>
		 </ol>
					 
	</div>
	<div class="clear"></div>
	
	<div class="headline no-margin"><h4>留言</h4></div>
	<div class="form-spacer"></div>
	<div id="contact-form">
			<form method="post"  action="${pageContext.request.contextPath}/saveMsgBoard">
			<input type="hidden" name="replyfor.id" value="${replyfor.id }">
				<div class="field">
					<label>留言内容: </label>
					<textarea name='msg' class="text textarea"></textarea>
				</div>
				
				<div class="field">
					<input type="submit" class='button color medium' value="留言">
				</div>
				
			</form>
		</div>
	<!-- End Portfolio Content -->
		
</div>


</div>
<!-- Wrapper / End -->
	<%@include file="./foot.jsp" %>


</body>
</html>
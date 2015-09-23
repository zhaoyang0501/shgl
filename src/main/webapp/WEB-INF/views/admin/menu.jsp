<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="leftbar leftbar-close clearfix">
	<div class="admin-info clearfix">
		<div class="admin-thumb">
			<i class="icon-user"></i>
		</div>
		<div class="admin-meta">
			<ul>
				<li class="admin-username" style="margin-top: 10px;">欢迎你 ${sessionScope.adminuser.name}</li>
				<li><a href="${pageContext.request.contextPath}/admin/loginout">
				<i class="icon-lock"></i> 退出</a></li>
			</ul>
		</div>
	</div>

	<div class="left-nav clearfix">
		<div class="left-primary-nav">
			<ul id="myTab">
				<li  class="active"><a href="#dailyreport" class="icon-calendar" data-original-title="日报"></a></li>
			</ul>
		</div>
		<div class="responsive-leftbar">
			<i class="icon-list"></i>
		</div>
		<div class="left-secondary-nav tab-content" >
			<div class="tab-pane active dailyreport" id="dailyreport">
				<ul id="nav" class="accordion-nav" >
				<c:if test="${sessionScope.adminuser.username=='admin'}">
						<li><a href="${pageContext.request.contextPath}/admin/user/index"><i class="icon-pencil"></i> 商户管理</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/contract/index"><i class="icon-pencil"></i> 合同管理</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/contract/create"><i class="icon-pencil"></i> 创建合同</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/item/index"><i class="icon-pencil"></i> 商品管理</a></li>
					</c:if>
					<c:if test="${sessionScope.adminuser.username!='admin'}">
						<li><a href="${pageContext.request.contextPath}/admin/mycontract/index"><i class="icon-pencil"></i> 我的合同</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/itemin/index"><i class="icon-pencil"></i>进货</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/itemout/index"><i class="icon-pencil"></i> 销货</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/trade/index"><i class="icon-pencil"></i> 交易记录</a></li>
					</c:if>
				
				</ul>
			</div>
		</div>
	</div>
</div>
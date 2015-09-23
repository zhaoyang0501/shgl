<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ch">
<%@ include file="../common/meta.jsp"%>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/falgun/bootbox.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/falgun/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/falgun/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		if("${tip}" != null && "${tip}" != ""){
			noty({"text":"${tip}","layout":"top","type":"success","timeout":"2000"});
		}
		$(".date").datetimepicker({
			language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
	        format:'yyyy-mm-dd',
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
	    });
	});
</script>
</head>
<body>
	<div class="layout">
		<!-- top -->
		<%@ include file="../top.jsp"%>
		<!-- 导航 -->
		<%@ include file="../menu.jsp"%>
		
		<input type="hidden" id="hf_id" />

		<div class="main-wrapper">
			<div class="container-fluid">
				<div class="row-fluid ">
					<div class="span12">
						<div class="content-widgets ">
							<div class="widget-head  bondi-blue" >
								<h3>我的交易流水</h3>
							</div>
							
							
						<div class="row-fluid">
						<div class="tab-widget">
						<ul class="nav nav-tabs" id="myTab2">
							<li class="active"><a href="#toapprove"><i class="icon-edit"></i>入库记录</a></li>
							<li class=""><a href="#approved"><i class="icon-check"></i>出库记录</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="toapprove">
								<table id='table_toapprove' class="responsive table table-striped table-bordered table-condensed">
									<thead>
										<tr>
											<th>商品</th>
											<th>数量</th>
											<th>单价</th>
											<th>总价</th>
											<th>备注</th>
											<th>入库时间</th>
										</tr>
									</thead>
									<tbody>
									
									<c:forEach items="${in }" var="bean">
									<tr>
										<td>${bean.item.name}</td>
										<td>${bean.count}</td>
										<td>${bean.price}</td>
										<td>${bean.price*bean.count}</td>
										<td>${bean.remark}</td>
										<td>${bean.createDate}</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="tab-pane" id="approved">
							
								<table id='table_approved'class="responsive table table-striped table-bordered table-condensed">
									<thead>
										<tr>
											<th>商品</th>
											<th>数量</th>
											<th>单价</th>
											<th>总价</th>
											<th>备注</th>
											<th>入库时间</th>
										</tr>
									</thead>
									<c:forEach items="${out }" var="bean">
									<tr>
										<td>${bean.item.name}</td>
										<td>${bean.count}</td>
										<td>${bean.price}</td>
										<td>${bean.price*bean.count}</td>
										<td>${bean.remark}</td>
										<td>${bean.createDate}</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../foot.jsp"%>
	</div>

</body>
</html>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<aside class="main-sidebar">
	<section class="sidebar">
		<ul class="sidebar-menu">
			
			<li class="${mDashboardAct}">
				<a href="${pageContext.request.contextPath}/">
					<img src="${pageContext.request.contextPath}/resources/images/Dashboard.png" style="width: 16px;"> &nbsp;&nbsp;<span>Dashboard</span> 
				</a>
			</li>
			
			<li class="treeview ${mAuthoriAct}" >
				<a href="#"><img src="${pageContext.request.contextPath}/resources/images/module/authorization1.png" class="image-responsive"> &nbsp;&nbsp;<span>Authorization Matrix</span><i class="fa fa-angle-left pull-right"></i></a>
				<ul class="treeview-menu ${mAuthorOpen}">
					<li class="${mAuthorGroup}"><a href="${pageContext.request.contextPath}/group"><i class="fa fa-circle-o"></i> Group</a></li>
					<li><a href="${pageContext.request.contextPath}/approval"><i class="fa fa-circle-o"></i> Approval</a></li>
				</ul>
			</li>
			
			<li class="treeview">
				<a href="#"><img src="${pageContext.request.contextPath}/resources/images/module/authorization1.png" class="image-responsive"> &nbsp;&nbsp;<span>Transactions</span><i class="fa fa-angle-left pull-right"></i></a>
				<ul class="treeview-menu">
					<li><a href="${pageContext.request.contextPath}/post-transaction"><i class="fa fa-circle-o"></i> Post Transaction</a></li>					
				</ul>
			</li>
			
			
			<li class="${mReportAct}">
				<a href="${pageContext.request.contextPath}/">
					<img src="${pageContext.request.contextPath}/resources/images/module/Report-16.png" style="width: 16px;"> &nbsp;&nbsp;<span>Report</span> 
				</a>
			</li>
			
			
			
		</ul>
	</section>
</aside>
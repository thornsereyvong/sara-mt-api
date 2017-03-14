<%@page import="balancika.ame.utilities.MenuMatix"%>
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
				<a href="#"><i class="fa fa-users" aria-hidden="true" style="font-size:17px;color:#b9292d"></i> &nbsp;&nbsp;<span>Authorization </span><i class="fa fa-angle-left pull-right"></i></a>
				<ul class="treeview-menu ${mAuthorOpen}">
					<li class="${mAuthor}"><a href="${pageContext.request.contextPath}/authorization"><i class="fa fa-circle-o"></i>Authorization</a></li>
					<li class="${mAuthorGroup}"><a href="${pageContext.request.contextPath}/authorization/group"><i class="fa fa-circle-o"></i>Authorization Group</a></li>
					<li class="${mAuthorEmp}"><a href="${pageContext.request.contextPath}/authorization/employee"><i class="fa fa-circle-o"></i>Employee Authorization</a></li>					
					<%-- <li><a href="${pageContext.request.contextPath}/authorizaton/approval"><i class="fa fa-circle-o"></i>Authorization Approval</a></li> --%>
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
					<img src="${pageContext.request.contextPath}/resources/images/module/Report-16.png" style="width: 16px;"> &nbsp;&nbsp;<span>Report</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">					
					<li>
						<a href="#"><i class="fa fa-circle-o"></i> Account Receivable <i class="fa fa-angle-left pull-right"></i></a>
						<ul class="treeview-menu">
							<li><a href="${pageContext.request.contextPath}/report/account-receivable/invoice-register-summary"><i class="fa fa-circle-o"></i> Invoice Register-[Summary]</a></li>							
							<li><a href="${pageContext.request.contextPath}/report/account-receivable/invoice-register-detail"><i class="fa fa-circle-o"></i> Invoice Register-[Detail]</a></li>
						</ul>
					</li>
					
				</ul>
			</li>
			
		</ul>
	</section>
</aside>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<aside class="main-sidebar">
	<section class="sidebar">
		<ul class="sidebar-menu">
			<li class="${mDashboardAct}">
				<a href="${pageContext.request.contextPath}/">
					<img src="${pageContext.request.contextPath}/resources/images/Dashboard.png" style="width: 16px;"> &nbsp;&nbsp;<span>Dashboard</span> 
				</a>
			</li>
			
			<li class="treeview">
				<a href="#"><i class="fa fa-share"></i><span>Authorization Matrix</span><i class="fa fa-angle-left pull-right"></i></a>
				<ul class="treeview-menu">
					<li><a href="#"><i class="fa fa-circle-o"></i> Group</a></li>
					<li>
						<a href="#"><i class="fa fa-circle-o"></i> Level One <i class="fa fa-angle-left pull-right"></i></a>
						<ul class="treeview-menu">
							<li><a href="#"><i class="fa fa-circle-o"></i> Level Two</a></li>
							<li><a href="#"><i class="fa fa-circle-o"></i> Level Two <i class="fa fa-angle-left pull-right"></i></a>
								<ul class="treeview-menu">
									<li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>
									<li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>
								</ul>
							</li>
						</ul>
					</li>
					<li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.no-js #loader { display: none;  }
.js #loader { display: block; position: absolute; left: 100px; top: 0; }
.se-pre-con {
	position: fixed;
	left: 0px;
	top: 0px;
	width: 100%;
	height: 100%;
	z-index: 9999;
	background: url(${pageContext.request.contextPath}/resources/images/Preloader_1.gif) center no-repeat #fff;
}
</style>
<!-- <div class="se-pre-con">	
</div> -->
<header class="main-header">
	<a href="#" class="logo">
		<span class="logo-mini"><b>AME</b></span>
		<span class="logo-lg" id="ffa"><b>AME</b></span>
	</a>
	<nav class="navbar navbar-static-top" role="navigation">
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a>
		<div class="navbar-custom-menu pull-left" style="font-size: 20px">
			<ul class="nav navbar-nav">
				<li><a><span>${company}</span></a></li>
			</ul>
		</div>
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<li class="dropdown user user-menu">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
						<img src="${pageContext.request.contextPath}/resources/images/user-icon-512.png"class="user-image" alt="User Image"> 
						<span class="hidden-xs">Welcome ${SESSION}!</span>
					</a>
					<ul class="dropdown-menu">
						<li class="user-header">
							<img src="${pageContext.request.contextPath}/resources/images/user-icon-512.png" class="img-circle" alt="User Image">							
						</li>
						<li class="user-footer">
							<div class="pull-right">
								<a href="${pageContext.request.contextPath}/logout" class="btn btn-default btn-flat">Sign out</a>
							</div>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
</header>
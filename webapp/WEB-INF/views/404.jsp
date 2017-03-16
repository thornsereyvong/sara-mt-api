<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>	
		<title>Page Not Found | BALANCIKA</title>	
		<jsp:include page="${request.contextPath}/head"></jsp:include>
		
	</head>
	<body class="sidebar-mini wysihtml5-supported skin-red-light">
    	<div class="wrapper">
    	
			<jsp:include page="${request.contextPath}/header"></jsp:include>
			
						
			<div class="content-wrapper">
				<section class="content-header">
					<h1>Page Not Found</h1>
					<ol class="breadcrumb">
						<li><a href="${pageContext.request.contextPath}"><i class="fa fa-home"></i> Home</a></li>
						<li><a href="#">Page Not Found</a></li>
					</ol>
				</section>
				<section class="content">
					<div class="error-page">
						<h2 class="headline text-yellow">404</h2>
						<div class="error-content">
							<h3>
								<i class="fa fa-warning text-yellow"></i> Oops! Page not found.
							</h3>
							<p>
								We could not find the page you were looking for. Meanwhile, you may
								<a href="${pageContext.request.contextPath}">return to dashboard</a>.
							</p>
						</div>
					</div>
				</section>
			</div>
			<jsp:include page="${request.contextPath}/foot"></jsp:include>
		</div>
		<jsp:include page="${request.contextPath}/footer"></jsp:include>	
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<jsp:include page="${request.contextPath}/head"></jsp:include>
	</head>
	<body class="sidebar-mini wysihtml5-supported skin-red-light" ng-app="agedReceivablesApp">
    	<div class="wrapper">
    	
			<jsp:include page="${request.contextPath}/header"></jsp:include>
			<jsp:include page="${request.contextPath}/menu"></jsp:include>			
			
			<div class="content-wrapper" id="agedReceivables" ng-controller="agedReceivablesCon" data-ng-init="startup()" >
				<section class="content-header">
					<h1>Aged Receivables</h1>
					<ol class="breadcrumb">
						<li><a href="${pageContext.request.contextPath}"><i class="fa fa-home"></i> Home</a></li>
						<li><a href="#">Aged Receivables</a></li>
					</ol>
				</section>
				<section class="content">
				
				
				</section>
				
			</div>
			<jsp:include page="${request.contextPath}/foot"></jsp:include>
		</div>
		<jsp:include page="${request.contextPath}/footer"></jsp:include>
		<script>
			var app = angular.module('agedReceivablesApp', ['angular-loading-bar', 'ngAnimate']).config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
			    cfpLoadingBarProvider.includeSpinner = false;
			}]);			
			var self = this;
			app.controller('agedReceivablesCon',['$scope','$http',function($scope, $http){	
				$scope.startup = function(){
					//$http.get("${pageContext.request.contextPath}/rest/post-transaction/start-up").success(function(response){
						//$scope.ftDate = response.DATA;
					//});
				}
			}]);
		</script>
	</body>
</html>
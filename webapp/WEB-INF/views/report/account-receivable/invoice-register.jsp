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
					<h1>Invoice Register</h1>
					<ol class="breadcrumb">
						<li><a href="${pageContext.request.contextPath}"><i class="fa fa-home"></i> Home</a></li>
						<li><a href="#">Invoice Register</a></li>
					</ol>
				</section>
				<section class="content">
					<div class="col-sm-12" style="padding-left: 0px; margin-top: -15px;">
						<button style="margin-top: 10px;" ng-click="btnPrintData()" class="btn btn-default"><i class="fa fa-print"></i> Print</button>
						<button style="margin-top: 10px;" ng-click="btnEmailData()" class="btn btn-default"><i class="fa fa-envelope-o"></i> Email</button>
						<button style="margin-top: 10px;" ng-click="btnExportData()" class="btn btn-default"><i class="fa fa-external-link"></i> Export</button>
					</div>
					<div class="clearfix"></div>					
					<div class="col-md-12" style="padding-left: 0px;padding-right: 0px; margin-top: 10px;">
						<div class="box box-danger direct-chat direct-chat-danger">
							<div class="box-header with-border">
								<h3 class="box-title">Report Criteria</h3>
								<div class="box-tools pull-right">
									<button class="btn btn-box-tool" data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="box-body">
								
							</div>
							<div class="box-footer">
								<button type="button" class="btn btn-defualt pull-right">Run Report</button>
							</div>
						</div>
					</div>
										
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
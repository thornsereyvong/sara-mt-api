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
					<h1>Invoice Register [Summary]</h1>
					<ol class="breadcrumb">
						<li><a href="${pageContext.request.contextPath}"><i class="fa fa-home"></i> Home</a></li>
						<li><a href="#">Invoice Register [Summary]</a></li>
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
								<div class="col-sm-3 col-md-3 col-xs-12">
									<label class="font-label">Report Type</label>
									<div class="form-group">
										<select style="width:100%"  class="form-control select2-small" name="reportType" id="reportType">
											<option selected value="Report Summary">Report Summary</option>
											<option value="Report Detail">Report Detail</option>
										</select>
									</div>
								</div>
								<div class="col-sm-3 col-md-3 col-xs-12">
									<label class="font-label">Post Status</label>
									<div class="form-group">
										<select style="width:100%"  class="form-control select2-small" name="postStatus" id="postStatus">
											<option selected  value="All">All</option>
											<option value="Open">Open</option>
											<option value="Deleted">Deleted</option>
											<option value="Posted">Posted</option>
											<option value="Voided">Voided</option>
										</select>
									</div>
								</div>
								<div class="col-sm-3 col-md-3 col-xs-12">
									<label class="font-label">Date Filter</label>
									<div class="form-group">
										<select style="width:100%"  class="form-control select2-small" name="datafilter" id="datafilter">
											<option selected  value="All">All</option>
											<option value="range">Range</option>
											<option value="today">Today</option>
											<option value="this period">This Period</option>
											<option value="this year">This Year</option>
										</select>
									</div>
								</div>
								<div class="col-sm-3 col-md-3 col-xs-12">
									<label class="font-label">From Date</label>
									<div class="form-group">
				                  		<div class="input-group">
					                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
					                    	<input type="text" class="form-control pull-right date" disabled readonly="readonly" name="fromdate" id="fromdate">
					                  	</div>
			                		</div>
								</div>
								<div class="col-sm-3 col-md-3 col-xs-12">
									<label class="font-label">To Date</label>
									<div class="form-group">
				                  		<div class="input-group">
					                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
					                    	<input type="text" class="form-control pull-right date" disabled readonly="readonly" name="todate" id="todate">
					                  	</div>
			                		</div>
								</div>
								
							</div>
							<div class="box-footer">
								<button class="btn btn-default pull-right"><i class="fa fa-area-chart"></i> Run Report</button>								
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
			
			
			
			$(function(){				
				//$('#fromdate').val(moment().format('YYYY-MM-DD'));  
			    //$('#todate').val(moment().format('YYYY-MM-DD'));			    
				$("#datafilter").change(function(){
					var action = $("#datafilter").val();
					switch(action) {
					    case 'All':	
					    	$('#fromdate').prop("disabled", true);  
					        $('#todate').prop("disabled", true);
					        $('#fromdate').val(moment().format('YYYY-MM-DD'));  
					        $('#todate').val(moment().format('YYYY-MM-DD'));
					        //angular.element(document.getElementById('postTranCon')).scope().setFromToDate();					        
					        break;
					    case 'range':
					    	$('#fromdate').prop("disabled", false);  
					        $('#todate').prop("disabled", false);
					        $('#fromdate').val("");  
					        $('#todate').val("");
					        break;
					    case 'today':
					    	 $('#fromdate').prop("disabled", true);  
						     $('#todate').prop("disabled", true); 				     				    
						     $('#fromdate').val(moment().format('YYYY-MM-DD'));  
						     $('#todate').val(moment().format('YYYY-MM-DD'));
					        break;
					    case 'this period':
					    	 $('#fromdate').prop("disabled", true);  
						     $('#todate').prop("disabled", true);
						     $('#fromdate').val((new Date()).getFullYear()+"-"+moment().format('MM')+"-"+"01");  
						     $('#todate').val((new Date()).getFullYear()+"-"+moment().format('MM')+"-"+getLastDayOfMonth()); 
					        break;
					    case 'this year':
					    	 $('#fromdate').prop("disabled", true);  
						     $('#todate').prop("disabled", true);
						     $('#fromdate').val((new Date()).getFullYear()+"-01-01");  
						     $('#todate').val((new Date()).getFullYear()+"-12-01"); 
					        break;
					}				
				});				
			});
			
			
		</script>
	</body>
</html>
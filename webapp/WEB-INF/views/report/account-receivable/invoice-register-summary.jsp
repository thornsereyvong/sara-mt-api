<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<jsp:include page="${request.contextPath}/head"></jsp:include>		
		<style>
			.imodal-dialog{width: 100%; height: 100%; margin: 0;padding: 0;}			
			.imodal-content{height: auto; min-height: 100%; border-radius: 0;}			
			.imodal-footer{position: fixed; height: 45px; bottom: 0;width: 100%;}			
			.imodal-button{margin-top: -10px !important;}
			.padding-0{padding: 0px !important;}			
		</style>		
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
					<div class="box box-danger" style="margin-top: 10px;">
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
								<label class="font-label">Post Status</label>
								<div class="form-group">
									<select style="width:100%" class="form-control select2-small" name="postStatus" id="postStatus">
										<option selected value="All">All</option>
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
									<select style="width:100%" class="form-control select2-small" name="datafilter" id="datafilter">
										<option selected value="All">All</option>
										<option value="range">Range</option>
										<option value="today">Today</option>
										<option value="this period">This Period</option>
										<option value="this year">This Year</option>
									</select>
								</div>
							</div>
							<div class="col-sm-3 col-md-3 col-xs-12">
								<label class="font-label">Transaction Date</label>
								<div class="form-group">
			                		<div class="input-group">
					                   	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
					                   	<input type="text" ng-cloak class="form-control pull-right date" date-defualt="{{spValue.saleDate[0].fDate | date:'yyyy-MM-dd'}}" value="{{spValue.saleDate[0].fDate | date:'yyyy-MM-dd'}}" disabled readonly="readonly" name="fromdate" id="fromdate">
				                 	</div>
			             		</div>
							</div>
							<div class="col-sm-3 col-md-3 col-xs-12">
								<label class="font-label">To Date</label>
								<div class="form-group">
					                <div class="input-group">
					                   	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
					                   	<input type="text" ng-cloak class="form-control pull-right date" date-defualt="{{spValue.saleDate[0].tDate | date:'yyyy-MM-dd'}}" value="{{spValue.saleDate[0].tDate | date:'yyyy-MM-dd'}}" disabled readonly="readonly" name="todate" id="todate">
					                 </div>
					             </div>
							</div>
							<div class="col-sm-3 col-md-3 col-xs-12">
								<label class="font-label">From Location</label>
								<div class="form-group">
									<select style="width:100%"  class="form-control select2-small" name="fLoction" id="fLoction">
										<option selected  value="All">All</option>
										<option ng-repeat="loc in spValue.location"  value="{{loc.locationId}}">[{{loc.locationId}}] {{loc.locationName}}</option>											
									</select>
								</div>
							</div>
							<div class="col-sm-3 col-md-3 col-xs-12">
								<label class="font-label">To Location</label>
								<div class="form-group">
									<select style="width:100%"  class="form-control select2-small" name="tLoction" id="tLoction">
										<option selected  value="All">All</option>
										<option ng-repeat="loc in spValue.location"  value="{{loc.locationId}}">[{{loc.locationId}}] {{loc.locationName}}</option>											
									</select>
								</div>
							</div>
							<div class="col-sm-3 col-md-3 col-xs-12">
								<label class="font-label">From Employee</label>
								<div class="form-group">
									<select style="width:100%"  class="form-control select2" name="fEmployee" id="fEmployee">
										<option selected  value="All">All</option>
										<option ng-repeat="emp in spValue.employee"  value="{{emp.empId}}">[{{emp.empId}}] {{emp.empName}}</option>											
									</select>
								</div>
							</div>
							<div class="col-sm-3 col-md-3 col-xs-12">
								<label class="font-label">To Employee</label>
								<div class="form-group">
									<select style="width:100%"  class="form-control select2" name="tEmployee" id="tEmployee">
										<option selected  value="All">All</option>
										<option ng-repeat="emp in spValue.employee"  value="{{emp.empId}}">[{{emp.empId}}] {{emp.empName}}</option>											
									</select>
								</div>
							</div>
							<div class="col-sm-3 col-md-3 col-xs-12">
								<label class="font-label">From Customer</label>
								<div class="form-group">
									<select style="width:100%"  class="form-control select2" name="fCustomer" id="fCustomer">
										<option selected  value="All">All</option>
										<option ng-repeat="cust in spValue.customer"  value="{{cust.custId}}">[{{cust.custId}}] {{cust.custName}}</option>													
									</select>
								</div>
							</div>
							<div class="col-sm-3 col-md-3 col-xs-12">
								<label class="font-label">To Customer</label>
								<div class="form-group">
									<select style="width:100%"  class="form-control select2" name="tCustomer" id="tCustomer">
										<option selected  value="All">All</option>
										<option ng-repeat="cust in spValue.customer"  value="{{cust.custId}}">[{{cust.custId}}] {{cust.custName}}</option>											
									</select>
								</div>
							</div>
							<div class="col-sm-3 col-md-3 col-xs-12">
								<label class="font-label">From Class</label>
								<div class="form-group">
									<select style="width:100%"  class="form-control select2-small" name="fClass" id="fClass">
										<option selected  value="All">All</option>
										<option ng-repeat="class in spValue.class"  value="{{class.classId}}">[{{class.classId}}] {{class.className}}</option>											
									</select>
								</div>
							</div>
							<div class="col-sm-3 col-md-3 col-xs-12">
								<label class="font-label">To Class</label>
								<div class="form-group">
									<select style="width:100%"  class="form-control select2-small" name="tClass" id="tClass">
										<option selected  value="All">All</option>
										<option ng-repeat="class in spValue.class"  value="{{class.classId}}">[{{class.classId}}] {{class.className}}</option>											
									</select>
								</div>
							</div>
							<div class="col-sm-3 col-md-3 col-xs-12">
								<label class="font-label">Short By</label>
								<div class="form-group">
									<select style="width:100%"  class="form-control select2-small" name="fClass" id="fClass">
										<option selected  value="Invoice No">Invoice No</option>
										<option value="Invoice Reference">Invoice Reference</option>
										<option value="Invoice Date">Invoice Date</option>
										<option value="Employee Name">Employee Name</option>
										<option value="Customer Name">Customer Name</option>
										<option value="Amount">Amount</option>
										<option value="Net Amount">Net Amount</option>	
										<option value="Net Amount Due">Net Amount Due</option>										
									</select>
								</div>
							</div>
							<div class="col-sm-3 col-md-3 col-xs-12">
								<label class="font-label">Order</label>
								<div class="form-group">
									<select style="width:100%"  class="form-control select2-small" name="tClass" id="tClass">
										<option selected  value="ASC">Ascending</option>
										<option value="DESC">Descending</option>											
									</select>
								</div>
							</div>
						</div>
						<div class="box-footer">
							<button data-toggle="modal" data-target="#myModal" class="btn btn-default pull-right" ng-click="runReport()"><i class="fa fa-area-chart"></i> Run Report</button>								
						</div>
					</div>		
					
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog imodal-dialog">
							<div class="modal-content imodal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel"><b>Invoice Register [Summary]</b> Period: 12-12-2017 To 12-03-2017</h4>
								</div>
								<div class="modal-body">
									<div class="col-sm-12 table-responsive padding-0">										
										<table class="table table-hover">
											<tr class="active">
												<th>Date</th>
												<th>Invoice No.</th>
												<th>Invoice Ref.</th>
												<th>Customer Name</th>
												<th>Location ID</th>
												<th>Total Amt</th>
												<th>Total Dis.</th>
												<th>Net Total Amt</th>
												<th>Receipt To Date</th>
												<th>Net Amount Due</th>
												<th>Post Status</th>
												<th>Employee Name</th>
												<th>Class ID</th>
												<th>Class Name</th>
											</tr>
											
											<tbody>
												<tr dir-paginate="tr in pageSize.row |itemsPerPage:10" class="ng-cloak">
													<td>{{$index}}</td>
												</tr>
												<tr>
													
													<td colspan="14">
														<dir-pagination-controls max-size="10" direction-links="true" boundary-links="true"></dir-pagination-controls>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<div class="modal-footer imodal-footer">
									<button ng-click="btnPrintData()" class="btn btn-default imodal-button"><i class="fa fa-print"></i> Print</button>
									<button ng-click="btnEmailData()" class="btn btn-default imodal-button"><i class="fa fa-envelope-o"></i> Email</button>
									<button ng-click="btnExportData()" class="btn btn-default imodal-button"><i class="fa fa-external-link"></i> Export</button>
								</div>
							</div>
						</div>
					</div>
					
					
							
					<div id="errors"></div>								
				</section>
			</div>
			<jsp:include page="${request.contextPath}/foot"></jsp:include>
		</div>
		<jsp:include page="${request.contextPath}/footer"></jsp:include>
		<script>
			var app = angular.module('agedReceivablesApp', ['angularUtils.directives.dirPagination','angular-loading-bar', 'ngAnimate']).config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
			    cfpLoadingBarProvider.includeSpinner = false;
			}]);			
			var self = this;
			app.controller('agedReceivablesCon',['$scope','$http',function($scope, $http){	
				$scope.startup = function(){
					$http.get("${pageContext.request.contextPath}/rest/account-receivable/start-up/invoice-register").success(function(response){
						$scope.spValue = response.DATA;
					});
				}
				$scope.sort = function(keyname){
				    $scope.sortKey = keyname;   //set the sortKey to the param passed
				    $scope.reverse = !$scope.reverse; //if true make it false and vice versa
				};

				$scope.pageSize = {};

				$scope.pageSize.rows = [ 
								{ value: "5", label: "5" },
			    				{ value: "10", label: "10" },
			            		{ value: "15", label: "15" },
			            		{ value: "20", label: "20" },
			            		{ value: "25", label: "25" },
			            		{ value: "30", label: "30" },
			            		];
				$scope.pageSize.row = $scope.pageSize.rows[1].value;
				$scope.runReport = function(){
					
					
					
				}
				
				
				
				
			}]);
			
			
			
			$(function(){							   
				$("#datafilter").change(function(){
					var action = $("#datafilter").val();
					switch(action) {
					    case 'All':	
					    	$('#fromdate').prop("disabled", true);  
					        $('#todate').prop("disabled", true);
					        $('#fromdate').val($('#fromdate').attr("date-defualt"));  
					        $('#todate').val($('#todate').attr("date-defualt"));					       
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
															
					<div class="box box-danger">
						<div class="box-header with-border">
							<h3 class="box-title">Report Criteria</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse">
									<i class="fa fa-minus"></i>
								</button>
							</div>
						</div>
						<div class="box-body">								
							<div class="col-sm-6 col-md-3 col-xs-12">
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
							<div class="col-sm-6 col-md-3 col-xs-12">
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
							<div class="col-sm-6 col-md-3 col-xs-12">
								<label class="font-label">Transaction Date</label>
								<div class="form-group">
			                		<div class="input-group">
					                   	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
					                   	<input type="text" ng-cloak class="form-control pull-right date" date-defualt="{{spValue.saleDate[0].fDate | date:'yyyy-MM-dd'}}" value="{{spValue.saleDate[0].fDate | date:'yyyy-MM-dd'}}" disabled readonly="readonly" name="fromdate" id="fromdate">
				                 	</div>
			             		</div>
							</div>
							<div class="col-sm-6 col-md-3 col-xs-12">
								<label class="font-label">To Date</label>
								<div class="form-group">
					                <div class="input-group">
					                   	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
					                   	<input type="text" ng-cloak class="form-control pull-right date" date-defualt="{{spValue.saleDate[0].tDate | date:'yyyy-MM-dd'}}" value="{{spValue.saleDate[0].tDate | date:'yyyy-MM-dd'}}" disabled readonly="readonly" name="todate" id="todate">
					                 </div>
					             </div>
							</div>
							<div class="col-sm-6 col-md-3 col-xs-12">
								<label class="font-label">From Location</label>
								<div class="form-group">
									<select style="width:100%"  class="form-control select2-small" name="fLoction" id="fLocation">
										<option selected  value="All">All</option>
										<option ng-repeat="loc in spValue.location"  value="{{loc.locationId}}">[{{loc.locationId}}] {{loc.locationName}}</option>											
									</select>
								</div>
							</div>
							<div class="col-sm-6 col-md-3 col-xs-12">
								<label class="font-label">To Location</label>
								<div class="form-group">
									<select style="width:100%"  class="form-control select2-small" name="tLoction" id="tLocation">
										<option selected  value="All">All</option>
										<option ng-repeat="loc in spValue.location"  value="{{loc.locationId}}">[{{loc.locationId}}] {{loc.locationName}}</option>											
									</select>
								</div>
							</div>
							<div class="col-sm-6 col-md-3 col-xs-12">
								<label class="font-label">From Employee</label>
								<div class="form-group">
									<select style="width:100%"  class="form-control select2" name="fEmployee" id="fEmployee">
										<option selected  value="All">All</option>
										<option ng-repeat="emp in spValue.employee"  value="{{emp.empId}}">[{{emp.empId}}] {{emp.empName}}</option>											
									</select>
								</div>
							</div>
							<div class="col-sm-6 col-md-3 col-xs-12">
								<label class="font-label">To Employee</label>
								<div class="form-group">
									<select style="width:100%"  class="form-control select2" name="tEmployee" id="tEmployee">
										<option selected  value="All">All</option>
										<option ng-repeat="emp in spValue.employee"  value="{{emp.empId}}">[{{emp.empId}}] {{emp.empName}}</option>											
									</select>
								</div>
							</div>
							<div class="col-sm-6 col-md-3 col-xs-12">
								<label class="font-label">From Customer</label>
								<div class="form-group">
									<select style="width:100%"  class="form-control select2" name="fCustomer" id="fCustomer">
										<option selected  value="All">All</option>
										<option ng-repeat="cust in spValue.customer"  value="{{cust.custId}}">[{{cust.custId}}] {{cust.custName}}</option>													
									</select>
								</div>
							</div>
							<div class="col-sm-6 col-md-3 col-xs-12">
								<label class="font-label">To Customer</label>
								<div class="form-group">
									<select style="width:100%"  class="form-control select2" name="tCustomer" id="tCustomer">
										<option selected  value="All">All</option>
										<option ng-repeat="cust in spValue.customer"  value="{{cust.custId}}">[{{cust.custId}}] {{cust.custName}}</option>											
									</select>
								</div>
							</div>
							<div class="col-sm-6 col-md-3 col-xs-12">
								<label class="font-label">From Class</label>
								<div class="form-group">
									<select style="width:100%"  class="form-control select2-small" name="fClass" id="fClass">
										<option selected  value="All">All</option>
										<option ng-repeat="class in spValue.class"  value="{{class.classId}}">[{{class.classId}}] {{class.className}}</option>											
									</select>
								</div>
							</div>
							<div class="col-sm-6 col-md-3 col-xs-12">
								<label class="font-label">To Class</label>
								<div class="form-group">
									<select style="width:100%"  class="form-control select2-small" name="tClass" id="tClass">
										<option selected  value="All">All</option>
										<option ng-repeat="class in spValue.class"  value="{{class.classId}}">[{{class.classId}}] {{class.className}}</option>											
									</select>
								</div>
							</div>
							<div class="col-sm-6 col-md-3 col-xs-12">
								<label class="font-label">Short By</label>
								<div class="form-group">
									<select style="width:100%"  class="form-control select2-small" name="sr" id="sr">
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
							<div class="col-sm-6 col-md-3 col-xs-12">
								<label class="font-label">Order</label>
								<div class="form-group">
									<select style="width:100%"  class="form-control select2-small" name="orderBy" id="orderBy">
										<option selected  value="ASC">Ascending</option>
										<option value="DESC">Descending</option>											
									</select>
								</div>
							</div>
						</div>
						<div class="box-footer">
							<button class="btn btn-default pull-right" ng-click="runReport()"><i class="fa fa-area-chart"></i> Run Report</button>								
						</div>
					</div>		
					
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog imodal-dialog">
							<div class="modal-content imodal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel"><b>Invoice Register [Summary]</b> <br class="hidden-sm hidden-md hidden-lg hidden-xl"> Period: 12-12-2017 To 12-03-2017</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-xs-12 padding-bottom-5 margin-top-5">
											<form class="form-inline " >
											
											  	<div class="input-group input-group-md pull-left width-110">
										  			<span style="border: 0px; padding-left: 0px;" class="input-group-addon" id="sizing-addon3">Row:</span>
											  		<select style="margin-left: -12px;padding: 0px;"  class="form-control" ng-model="pageSize.row" id ="row" ng-options="obj.value as obj.label for obj in pageSize.rows"></select>
												</div>
												
												<button ng-click="btnPrintData()" class="btn btn-default pull-right margin-left-5"><i class="fa fa-print"></i> Print</button>
												<button ng-click="btnEmailData()" class="btn btn-default pull-right margin-left-5"><i class="fa fa-envelope-o"></i> Email</button>
												<button ng-click="btnExportData()" class="btn btn-default pull-right margin-left-5"><i class="fa fa-external-link"></i> Export</button>
											
											</form>
											
										</div>
										<div class="clearfix"></div>
										<div class="col-sm-12 table-responsive">										
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
												<tbody dir-paginate="tr in record |itemsPerPage:pageSize.row" current-page="currentPage" class="ng-cloak border-top-none">
													<tr>
														<td>{{tr.SalDate}}</td>
														<td>{{tr.SalID}}</td>
														<td>{{tr.SalReference}}</td>
														<td>{{tr.CustName}}</td>
														<td>{{tr.LocationID}}</td>
														<td class="dis-number">{{tr.TotalAmt | number:2}}</td>
														<td class="dis-number">{{tr.DisInvDol | number:2}}</td>
														<td class="dis-number">{{tr.NetTotalAmt | number:2}}</td>
														<td class="dis-number">{{tr.rcpToDate | number:2}}</td>
														<td class="dis-number">{{(tr.NetTotalAmt - tr.rcpToDate) | number:2}}</td>
														<td>{{tr.PostStatus}}</td>
														<td>{{tr.EmpName}}</td>
														<td>{{tr.ClassID}}</td>
														<td>{{tr.ClassName}}</td>
													</tr>
													<tr ng-if="(record[(totalRecord-1)].SalID === tr.SalID)" class="border-top">
														<th colspan="3">Record Count: {{totalRecord}}</th>
														<th colspan="2">Grand Total</th>
														<th class="dis-number">{{totalAmt | number:2}}</th>
														<th class="dis-number">{{totalDis | number:2}}</th>
														<th class="dis-number">{{totalNetAmt | number:2}}</th>
														<th class="dis-number">{{totalRcp | number:2}}</th>
														<th class="dis-number">{{(totalNetAmt-totalRcp) | number:2}}</th>
														<th colspan="4"></th>
													</tr>
												</tbody>
												<tfoot>
													<tr>												
														<td colspan="14" style="padding: 0px;">
															<dir-pagination-controls max-size="pageSize.row" direction-links="true" boundary-links="true"></dir-pagination-controls>
														</td>
													</tr>
												</tfoot>
											</table>
										</div>
									</div>
								</div>
								<!-- <div class="modal-footer imodal-footer">
									<button ng-click="btnPrintData()" class="btn btn-default imodal-button"><i class="fa fa-print"></i> Print</button>
									<button ng-click="btnEmailData()" class="btn btn-default imodal-button"><i class="fa fa-envelope-o"></i> Email</button>
									<button ng-click="btnExportData()" class="btn btn-default imodal-button"><i class="fa fa-external-link"></i> Export</button>
								</div> -->
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
				$scope.currentPage = 1;
				$scope.pageSize = {};
				$scope.pageSize.rows = [{ value: "5", label: "5" },{ value: "10", label: "10" },{ value: "15", label: "15" },{ value: "20", label: "20" },{ value: "25", label: "25" },{ value: "30", label: "30" }];
				$scope.pageSize.row = $scope.pageSize.rows[1].value;
				$scope.runReport = function(){					
					
					dis({"fDate":getValueStringById("fromdate"),"tDate":getValueStringById("todate"), "postStatus":getValueStringById("postStatus"), "fLocation":getValueStringById("fLocation"), "tLocation": getValueStringById("tLocation"), "fEmployee":getValueStringById("fEmployee"), "tEmployee": getValueStringById("tEmployee"), "fClass":getValueStringById("fClass"), "tClass":getValueStringById("tClass"), "sr":getValueStringById("sr"),"orderBy":getValueStringById("orderBy")})
					
					$http({
					    method: 'POST',
					    url: "${pageContext.request.contextPath}/rest/account-receivable/invoice-register/summary",
					    headers: {
					    	'Accept': 'application/json',
					        'Content-Type': 'application/json'
					    },
					    data: {"fDate":getValueStringById("fromdate"),"tDate":getValueStringById("todate"), "postStatus":getValueStringById("postStatus"), "fLocation":getValueStringById("fLocation"), "tLocation": getValueStringById("tLocation"), "fEmployee":getValueStringById("fEmployee"), "tEmployee": getValueStringById("tEmployee"), "fClass":getValueStringById("fClass"), "tClass":getValueStringById("tClass"), "sr":getValueStringById("sr"),"orderBy":getValueStringById("orderBy")}
					}).success(function(response){						
						if(response.MESSAGE == 'SUCCESS'){
							$scope.record = response.DATA;
							$scope.totalRecord = $scope.record.length;
							$scope.totalAmt = 0; $scope.totalDis = 0; $scope.totalNetAmt = 0; $scope.totalRcp = 0; $scope.totalNetAmtDue = 0;
							for(var i=0; i<$scope.totalRecord; i++){
								$scope.totalAmt += Number($scope.record[i].TotalAmt);
								$scope.totalDis += Number($scope.record[i].DisInvDol);
								$scope.totalNetAmt += Number($scope.record[i].NetTotalAmt);
								$scope.totalRcp += Number($scope.record[i].rcpToDate);
								$scope.totalNetAmtDue += $scope.totalNetAmt-$scope.rcpToDate;
							}
						}else{
							$scope.record = [];							
						}
						$("#myModal").modal({backdrop:'static',keyboard:false, show:true});
					});					
				}
				$scope.calTotalPage = function(record, perpage){
					return Math.ceil(record/perpage);
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
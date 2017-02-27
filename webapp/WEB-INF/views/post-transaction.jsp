<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<jsp:include page="${request.contextPath}/head"></jsp:include>
	</head>
	<body class="sidebar-mini wysihtml5-supported skin-red-light" ng-app="postTranApp">
    	<div class="wrapper">
    	
			<jsp:include page="${request.contextPath}/header"></jsp:include>
			<jsp:include page="${request.contextPath}/menu"></jsp:include>
			
			
			<div class="content-wrapper" id="postTranCon" ng-controller="postTranCon" data-ng-init="startup()" >
				<section class="content-header">
					<h1>Post Transaction</h1>
					<ol class="breadcrumb">
						<li><a href="${pageContext.request.contextPath}"><i class="fa fa-home"></i> Home</a></li>
						<li><a href="#">Post Transaction</a></li>
					</ol>
				</section>
				<section class="content" >
					<div class="box box-danger">			
						<div class="box-header">
							<div class="col-sm-12">
								<button style="margin-top: 10px;" ng-click="btnOpenData()" class="btn btn-default"><i class="fa fa-folder-open"></i> Open</button>
								<button style="margin-top: 10px;" ng-click="btnPostData()" class="btn btn-primary"><i class="fa fa-gears"></i> Post</button>
								<button style="margin-top: 10px;" ng-click="btnVoidData()" class="btn btn-danger"><i class="fa fa-trash"></i> Void</button>
								<button style="margin-top: 10px;" ng-click="btnVoidNCloneData()" class="btn btn-success"><i class="fa fa-clone"></i> Void & Clone</button>
								<button style="margin-top: 10px;" ng-click="btnFilterData()" class="btn btn-info"><i class="fa fa-search"></i> Filter</button>
								<button style="margin-top: 10px;" ng-click="btnClose()" class="btn btn-danger"><i class="fa fa-remove"></i> Close</button>
							</div>
						</div>
						<div class="box-body">
							<div class="row">
								<div class="col-sm-12">
									<div class="col-sm-6 col-md-3 col-xs-12">
										<label class="font-label">Transaction Type <span class="requrie">(Required)</span></label>
										<div class="form-group">
											
											<select style="width:100%" ng-model="transType" ng-change="transChange()" class="form-control select2-small" name="tranType" id="tranType">
												<option value="">-- SELECT Transaction Type --</option>
												<option value="AP Invoice">AP Invoice</option>
												<option value="AP Return Invoice">AP Return Invoice</option>
												<option value="AP Debit Note">AP Debit Note</option>
												<option value="AP Payment">AP Payment</option>
												<option value="AR Invoice">AR Invoice</option>
												<option value="AR Return Invoice">AR Return Invoice</option>
												<option value="AR Credit Note">AR Credit Note</option>
												<option value="AR Receipt">AR Receipt</option>
												<option value="IC Transfer">IC Transfer</option>
												<option value="IC Adjustment">IC Adjustment</option>
												<option value="Cash Transfer">Cash Transfer</option>
												<option value="Cash Advance Clearance">Cash Advance Clearance</option>
												<option value="GL Entries">GL Entries</option>
											</select>
										</div>
									</div>
									<div class="col-sm-6 col-md-3 col-xs-12">
			                        	<div class="icheckbox icheckbox-primary"><br><br>
			                        		<input id="ckrShowAll" ng-click="ckrShowAllClick()" class="styled" type="checkbox">
			                        		<label class="cursor-pointer"  for="ckrShowAll">Show posted, voided and deleted transactions</label>
			                        	</div>
									</div>
								</div>
							</div>
							<!-- <div class="row">
								<div class="col-sm-12">
									<div class="col-sm-2"><h4>Filter</h4></div>
									<div class="col-sm-12"><hr style="margin-top: 3px;" /></div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-3 col-xs-12">
										<label class="font-label">Transaction Date</label>
										<div class="form-group">
											<select style="width:100%" class="form-control select2-small" name="tranDate" id="tranDate">
												<option selected value="All">All</option>
												<option value="range">Range</option>
												<option value="today">Today</option>
												<option value="this period">This Period</option>
												<option value="this year">This Year</option>
											</select>
										</div>
									</div>
									<div class="col-sm-6 col-md-3 col-xs-12">
										<label class="font-label">From Date</label>
										<div class="form-group">
					                  		<div class="input-group">
						                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
						                    	<input type="text" class="form-control pull-right date" readonly="readonly" name="fromdate" id="fromdate">
						                  	</div>
				                		</div>
									</div>
									<div class="col-sm-6 col-md-3 col-xs-12">
										<label class="font-label">To Date</label>
										<div class="form-group">
					                  		<div class="input-group">
						                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
						                    	<input type="text" class="form-control pull-right date" readonly="readonly" name="todate" id="todate">
						                  	</div>
				                		</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-3 col-xs-12">
										<label class="font-label">Filter Type</label>
										<div class="form-group">
											<select style="width:100%" class="form-control select2-small" name="filterType" id="filterType">
												<option selected value="All">All</option>
												<option value="Entry No">Entry No</option>
												<option value="Journal Type">Journal Type</option>
												<option value="Reference">Reference</option>
												<option value="Remark">Remark</option>
												<option value="Vendor ID">Vendor ID</option>
												<option value="Vendor Name">Vendor Name</option>
												<option value="Customer ID">Customer ID</option>
												<option value="Customer Name">Customer Name</option>
												<option value="Employee ID">Employee ID</option>
												<option value="Employee Name">Employee Name</option>
												<option value="Total Amount">Total Amount</option>
											</select>
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">&nbsp;</label>
										<div class="form-group">
											<input type="text" class="form-control" name="filterVal" id="filterVal">
										</div>
									</div>
									<div class="col-sm-3 col-md-3 col-xs-12">
										<button type="button" id="btnfSearch" name="btnfSearch" class="btn btn-primary pull-right" >SEARCH</button>
										<br>
									</div>
								</div>
								<div class="col-sm-12"><hr style="margin-top: 3px;" /></div>
								
							</div> -->
							
							<div class="row">
								<div class="col-sm-12">
									<div class="tablecontainer table-responsive"> 
										<table class="table table-hover">
											<tr>
												<th class="width-75 text-center">
													<div class="icheckbox icheckbox-primary"><input id="ckrAll" ng-click="ckrAll()" class="styled" type="checkbox"><label class="cursor-pointer" for="ckrAll"></label></div>
					                        	</th>
												<th>Trans. ID</th>
												<th>Trans. Date</th>
												<th>Trans. Name</th>
												<th>Reference</th>
												<th>Remark</th>
												<th>Total Amount</th>
												<th>Post Status</th>
											</tr>
											<tbody id="data-content-post">
												<tr ng-repeat="tr in trans" id="data-row-{{$index}}" ng-class="{0:'active'}[$index]" ng-click="dataRowClick($index)" >
													<td class="width-75 text-center">
														<div class="icheckbox icheckbox-primary"><input name="ckr" ng-click="ckrDetailClick($index)" id="ckr{{$index}}" class="styled" type="checkbox"><label class="cursor-pointer" for="ckr{{$index}}"></label></div>
													</td>
													<td ng-cloak>{{tr.transId}}</td>
													<td ng-cloak>{{tr.transDate}}</td>
													<td ng-cloak>{{tr.transName}}</td>
													<td ng-cloak>{{tr.transReference}}</td>
													<td ng-cloak>{{tr.transRemark}}</td>
													<td ng-cloak class="dis-number">{{tr.transAmt | number:2}}</td>
													<td ng-cloak>{{tr.transStatus}}</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>		
					
				<section class="content-footer"></section>	
				
				
				<input type="hidden" id="btnFilterM" data-backdrop="static" data-keyboard="false" data-toggle="modal" data-target="#frmFilterPost" />
				<div class="modal fade modal-default" id="frmFilterPost" role="dialog">
					<div class="modal-dialog  modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title"><b>[FILTER] Transactions</b></h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<form id="frmFilterPostTrans">
									<div class="col-md-12">
										<div class="row">
											<div class="col-sm-12">
												<div class="col-sm-6 col-md-4 col-xs-12">
													<label class="font-label">Transaction Date</label>
													<div class="form-group">
														<select style="width:100%"  class="form-control select2-small" name="datafilter" id="datafilter">
															<option value="All">All</option>
															<option value="range">Range</option>
															<option selected value="today">Today</option>
															<option value="this period">This Period</option>
															<option value="this year">This Year</option>
														</select>
													</div>
												</div>
												<div class="col-sm-6 col-md-4 col-xs-12">
													<label class="font-label">From Date</label>
													<div class="form-group">
								                  		<div class="input-group">
									                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
									                    	<input type="text" class="form-control pull-right date" readonly="readonly" name="fromdate" id="fromdate">
									                  	</div>
							                		</div>
												</div>
												<div class="col-sm-6 col-md-4 col-xs-12">
													<label class="font-label">To Date</label>
													<div class="form-group">
								                  		<div class="input-group">
									                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
									                    	<input type="text" class="form-control pull-right date" readonly="readonly" name="todate" id="todate">
									                  	</div>
							                		</div>
												</div>
												<div class="clearfix"></div>
												<div class="col-sm-6 col-md-4 col-xs-12">
													<label class="font-label">Filter Type</label>
													<div class="form-group">
														<select style="width:100%" class="form-control select2-small" name="filterType" id="filterType">
															<option selected value="All">All</option>
															<option value="Entry No">Entry No</option>
															<option value="Journal Type">Journal Type</option>
															<option value="Reference">Reference</option>
															<option value="Remark">Remark</option>
															<option value="Vendor ID">Vendor ID</option>
															<option value="Vendor Name">Vendor Name</option>
															<option value="Customer ID">Customer ID</option>
															<option value="Customer Name">Customer Name</option>
															<option value="Employee ID">Employee ID</option>
															<option value="Employee Name">Employee Name</option>
															<option value="Total Amount">Total Amount</option>
														</select>
													</div>
												</div>
												<div class="col-sm-6 col-md-8 col-xs-12">
													<label class="font-label">&nbsp;</label>
													<div class="form-group">
														<input type="text" class="form-control" name="filterVal" id="filterVal">
													</div>
												</div>
											</div>
										</div>
									</div>
									</form>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" id="btnfCancel" name="btnfCancel" class="btn btn-danger" data-dismiss="modal">CANCEL</button>
								 &nbsp;&nbsp;
								<button type="button" id="btnfSearch" ng-click="btnSearchClick()" name="btnfSearch" class="btn btn-primary pull-right" >SEARCH</button>
							</div>
						</div>
					</div>
				</div>
				
				<div class="modal fade modal-default" id="frmPurchaseInvoice" role="dialog">
					<div class="modal-dialog  modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title"><b>PURCHASE INVOICE</b></h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Entry No</label>
										<div class="form-group">
											<input type="text" value="{{purchaseInvoice.purchaseId}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Post Status</label>
										<div class="form-group">
											<input type="text" value="{{purchaseInvoice.postStatus}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Purchase Type</label>
										<div class="form-group">
											<input type="text" value="{{purchaseInvoice.purchaseType}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Reference</label>
										<div class="form-group">
											<input type="text" value="{{purchaseInvoice.reference}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Purchase Date</label>
										<div class="form-group">
					                  		<div class="input-group">
						                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
						                    	<input type="text" disabled class="form-control pull-right date" value="{{purchaseInvoice.purchaseDate}}" readonly="readonly" name="fromdate">
						                  	</div>
				                		</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Due Date</label>
										<div class="form-group">
					                  		<div class="input-group">
						                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
						                    	<input type="text" disabled class="form-control pull-right date" value="{{purchaseInvoice.dueDate}}" class="form-control pull-right date" readonly="readonly">
						                  	</div>
				                		</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Vendor</label>
										<div class="form-group">
											<input type="text" value="[{{purchaseInvoice.vendor.vendorId}}] {{purchaseInvoice.vendor.vendorName}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Class</label>
										<div class="form-group">
											<input type="text" value="[{{purchaseInvoice.classCode.classId}}] {{purchaseInvoice.classCode.className}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-12">
										<label class="font-label">Remark</label>
										<div class="form-group">
											<input type="text" value="{{purchaseInvoice.remark}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-12 table-responsive">
										<table class="table table-hover">
											<tr>
												<th style="width:200px;">Item ID</th>
												<th class="width-120">Item Name</th>
												<th class="width-120">Location ID</th>
												<th class="width-120">Class ID</th>
												<th class="width-120">UOM</th>
												<th class="width-120">Quantity</th>
												<th class="width-120">Unit Cost</th>
												<th class="width-120">Price Factor</th>
												<th class="width-120">Total Amount</th>
												<th class="width-120">Discount %</th>
												<th class="width-120">Discount $</th>
												<th class="width-120">VAT %</th>
												<th class="width-120">VAT $</th>
												<th class="width-120">ST %</th>
												<th class="width-120">ST $</th>
												<th class="width-120">Net Total Amount</th>
											</tr>
											<tbody>
												<tr ng-repeat="tr in purchaseInvoice.purchaseDetail" >
													<td style="width:200px;">{{tr.item.itemId}}</td>
													<td class="width-120" ng-cloak>{{tr.item.itemName}}</td>
													<td class="width-120" ng-cloak>{{tr.location.locationId}}</td>
													<td class="width-120" ng-cloak>{{tr.classCode.classId}}</td>
													<td class="width-120" ng-cloak>{{tr.uom.uomId}}</td>
													<td class="width-120" ng-cloak>{{tr.purQty | number:4}}</td>
													<td class="width-120" ng-cloak>{{tr.unitCost | number:6}}</td>
													<td class="width-120" ng-cloak>{{tr.factor | number:4}}</td>
													<td class="width-120" ng-cloak>{{tr.totalCost | number:6}}</td>
													<td class="width-120" ng-cloak>{{tr.disPer | number:5}}</td>
													<td class="width-120" ng-cloak>{{tr.disDol | number:2}}</td>
													<td class="width-120" ng-cloak>{{tr.vatPer | number:5}}</td>
													<td class="width-120" ng-cloak>{{tr.vatDol | number:2}}</td>
													<td class="width-120" ng-cloak>{{tr.staxPer | number:5}}</td>
													<td class="width-120" ng-cloak>{{tr.staxDol | number:2}}</td>
													<td class="width-120" ng-cloak>{{tr.netTotalAmt | number:2}}</td>
												</tr>
											</tbody>
										</table>
									</div>
									
									<div class="clearfix"></div>
									<br>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Total Purchase</label>
										<div class="form-group">
											<input type="text" value="{{purchaseInvoice.totalAmt | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Invoice Discount</label>
										<div class="form-group">
											<input type="text" value="{{purchaseInvoice.disInvDol | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Net Invoice</label>
										<div class="form-group">
											<input type="text" value="{{purchaseInvoice.netTotalAmt | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Total Payment to Date</label>
										<div class="form-group">
											<input type="text" value="{{purchaseInvoice.pmtToDate | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Net Amount Due</label>
										<div class="form-group">
											<input type="text" value="{{purchaseInvoice.netTotalAmt | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-danger" data-dismiss="modal">CLOSE</button>
							</div>
						</div>
					</div>
				</div>
				
				<div id="errors"></div>
			</div>
			<jsp:include page="${request.contextPath}/foot"></jsp:include>
		</div>
		<jsp:include page="${request.contextPath}/footer"></jsp:include>
		
		
		<script type="text/javascript">
				
			var app = angular.module('postTranApp', ['angular-loading-bar', 'ngAnimate']).config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
			    cfpLoadingBarProvider.includeSpinner = false;
			}]);
			
			var self = this;
			var LastClickRow = 0;
			app.controller('postTranCon',['$scope','$http',function($scope, $http){	
					
				$scope.btnFilterData = function(){					
					var transType = getValueStringById("tranType");
					var datafilter = getValueStringById("datafilter");
					if(transType != "" && datafilter == 'All'){						
						for(var i=0; i< $scope.ftDate.length;i++){						
							if(transType == $scope.ftDate[i].transType){
								$('#fromdate').prop("disabled", false);  
						        $('#todate').prop("disabled", false);
						        $('#fromdate').val($scope.ftDate[i].fromDate);  
						        $('#todate').val($scope.ftDate[i].toDate);	
						        break;
							}						
						}
					}					
					$("#frmFilterPost").modal("toggle");				
				}
				$scope.transType = "";
				$scope.transChange = function(){
					if($scope.transType != ""){
						$scope.listTransaction(0);						
					}
				}
				
				$scope.startup = function(){
					$http.get("${pageContext.request.contextPath}/rest/post-transaction/start-up").success(function(response){
						$scope.ftDate = response.DATA;
					});
				}
				
				$scope.setFromToDate = function(){					
					var transType = getValueStringById("tranType");
					if(transType != ""){
						for(var i=0; i< $scope.ftDate.length;i++){
							if(transType == $scope.ftDate[i].transType){								
						        $('#fromdate').val($scope.ftDate[i].fromDate);  
						        $('#todate').val($scope.ftDate[i].toDate);	
						        break;
							}						
						}	
					}
				}
				
				$scope.listTransaction = function(searchClick){
					$("#ckrAll").prop('checked', false);
					$scope.totalRow = 0;
					var fromDate = getValueStringById("fromdate");
					var toDate = getValueStringById("todate");
					var filterType = getValueStringById("filterType");
					var transType = getValueStringById("tranType");
					var isVoid = $("#ckrShowAll");
					var isVoidCk = 0;
					if(isVoid.is(':checked')){
						isVoidCk = 1;
					}					
					var search = getValueStringById("filterVal");
					if(transType != ""){
						$http({
				 			method: 'POST',
						    url: "${pageContext.request.contextPath}/rest/post-transaction/list",
						    headers: {
						    	'Accept': 'application/json',
						        'Content-Type': 'application/json'
						    },
						    data : {
							    "fromDate":fromDate,"toDate":toDate, "filterType":filterType, "transType":transType, "search":search, "isVoid": isVoidCk, "searchClick":searchClick
							}
						}).success(function(response) {
							$scope.trans = [];
							if(response.MESSAGE == "SUCCESS"){
								$scope.trans = response.DATA;
							}
							LastClickRow = 0;
						});
					}else{
						$scope.trans = [];
						LastClickRow = 0;
					}
				}
				
				$scope.ckrShowAllClick = function(){					
					$scope.listTransaction(0);					
				}
				$scope.totalRow = 0;
				$scope.ckrAll = function(){					
					var ckrAll = $("#ckrAll");
					if(ckrAll.is(':checked')){
						$scope.totalRow = $("input[name=ckr]").length;
						$("input[name=ckr]").prop('checked', true);
					}else{
						$scope.totalRow = 0;
						$("input[name=ckr]").prop('checked', false);
					}
				}
				
				$scope.ckrDetailClick = function(index){
					var lCkr = $("#ckr"+index);
					if(lCkr.is(':checked')){
						$scope.totalRow++;
					}else{
						$scope.totalRow--;
					}			
					if($scope.totalRow==$("input[name=ckr]").length){
						$("#ckrAll").prop('checked', true);
					}else{
						$("#ckrAll").prop('checked', false);
					}				
				}
				
				$scope.btnSearchClick = function(){
					$scope.listTransaction(1);
				}
				
				
				// void transaction
				$scope.btnVoidData = function(){
					var tr = $("#data-content-post tr");
					var listTrans = [];
					for(var i=0; i<tr.length;i++){
						var ckr = $("#ckr"+i);
						if(ckr.is(':checked')){
							if($scope.trans[i].transStatus == "Posted" && $scope.trans[i].transName != "GL Entries"){
								listTrans.push($scope.trans[i]);
							}
						}
					}
					var transType = getValueStringById("tranType");
					if(listTrans.length>0){
						for(var i=0; i<listTrans.length;i++){
							var last = false;
							if(i==(listTrans.length-1)){
								last = true;
							}
							var status = voidTrans(transType,listTrans[i].transId,listTrans[i].transDate,last);
							if(status){
								continue;
							}else{
								break;
							}
							
						}
					}
					$scope.listTransaction(0);
				}
				
				// void and clone transaction
				$scope.btnVoidNCloneData = function(){
					var tr = $("#data-content-post tr");
					var listTrans = [];
					for(var i=0; i<tr.length;i++){
						var ckr = $("#ckr"+i);
						if(ckr.is(':checked')){
							if($scope.trans[i].transStatus == "Posted" && $scope.trans[i].transName != "GL Entries"){
								listTrans.push($scope.trans[i]);
							}
						}
					}
					var transType = getValueStringById("tranType");
					if(listTrans.length>0){
						for(var i=0; i<listTrans.length;i++){
							var last = false;
							if(i==(listTrans.length-1)){
								last = true;
							}
							var status = voidTransAndClone(transType,listTrans[i].transId,listTrans[i].transDate,last);
							if(status){
								continue;
							}else{
								break;
							}
							
						}
					}
					$scope.listTransaction(0);
				}
				
				//post transaction
				$scope.btnPostData = function(){
					var tr = $("#data-content-post tr");
					var listTrans = [];
					for(var i=0; i<tr.length;i++){
						var ckr = $("#ckr"+i);
						if(ckr.is(':checked')){
							if($scope.trans[i].transStatus == "Open"){
								listTrans.push($scope.trans[i]);
							}
						}
					}
					var transType = getValueStringById("tranType");
					if(listTrans.length>0){
						for(var i=0; i<listTrans.length;i++){
							var last = false;
							if(i==(listTrans.length-1)){
								last = true;
							}
							var status = postTrans(transType,listTrans[i].transId,listTrans[i].transDate,last);
							if(status){
								continue;
							}else{
								break;
							}
							
						}
					}
					$scope.listTransaction(0);
				}
				
				// open transaction
				$scope.btnOpenData = function(){
					var transId = $("#data-row-"+LastClickRow).children().eq(1).text();
					var transType = getValueStringById("tranType");
					if(transId != "" && transType != ""){
						$http({
				 			method: 'POST',
						    url: "${pageContext.request.contextPath}/rest/post-transaction/list-by-id",
						    headers: {
						    	'Accept': 'application/json',
						        'Content-Type': 'application/json'
						    },
						    data : {
						    	"transType" : transType,
							    "transId" : transId
							}
						}).success(function(response) {
							dis(response.DATA)
							$scope.transView = [];
							$scope.purchaseInvoice = [];
							if(response.MESSAGE == "SUCCESS"){
								$scope.transView = response.DATA;				
								switch(transType) {
									case "AP Invoice":
										$scope.purchaseInvoice = response.DATA;
										$("#frmPurchaseInvoice").modal({backdrop: 'static', keyboard: false});				
										break;
									case "AP Return Invoice": 
										
								    	break;
								    case "AP Debit Note":
								    	
								    	break;
								    case "AP Payment":
								    	
								    	break;
							    	case "AR Invoice":
							    		
								    	break;
						    		case "AR Return Invoice":
						    			
								    	break;
						    		case "AR Credit Note":
						    			
								    	break;
									case "AR Receipt":
										
								    	break;
									case "IC Transfer":
										
								    	break;
									case "IC Internal Usage":
										
								    	break;
									case "IC Adjustment":
										
								    	break;
									case "Cash Transfer":
										
								    	break;
									case "Cash Advance":
										
								    	break;
									case "Cash Advance Clearance":
										
								    	break;
									case "GL Entries":
										
								    	break;
								    default:
								}
							}
						});
					}
				}
				
				$scope.dataRowClick = function(index){
					$("#data-row-"+LastClickRow).attr("class","");
					$("#data-row-"+index).attr("class","active");
					LastClickRow = index;
				}
				
			}]);
			
			function voidTrans(transType, transId,transDate,last){
				var content = JSON.parse($.ajax({ 
					url: "${pageContext.request.contextPath}/rest/post-transaction/void",
					method: "POST",
					async: false,
					data : JSON.stringify({
						    "transType" : transType,
						    "transId" : transId,
						    "transDate" : transDate
					}),
					beforeSend: function(xhr) {
					    xhr.setRequestHeader("Accept", "application/json");
					    xhr.setRequestHeader("Content-Type", "application/json");
				    },
				}).responseText);
				if(content.MESSAGE == "FAILED"){
					if(confirm(content.MSG)){
				 		return true;
				 	}else{
				 		return false;
				 	}
				}else{
					if(last)
						confirm("The post transaction was successful voided.");					
					return true;
				}
			}
			function voidTransAndClone(transType, transId,transDate,last){
				var content = JSON.parse($.ajax({ 
					url: "${pageContext.request.contextPath}/rest/post-transaction/void-and-clone",
					method: "POST",
					async: false,
					data : JSON.stringify({
						    "transType" : transType,
						    "transId" : transId,
						    "transDate" : transDate
					}),
					beforeSend: function(xhr) {
					    xhr.setRequestHeader("Accept", "application/json");
					    xhr.setRequestHeader("Content-Type", "application/json");
				    },
				}).responseText);
				if(content.MESSAGE == "FAILED"){
					if(confirm(content.MSG)){
				 		return true;
				 	}else{
				 		return false;
				 	}
				}else{
					if(last)
						confirm("The post transaction was successful voided.");					
					return true;
				}
			}
			function postTrans(transType, transId,transDate,last){
				var content = JSON.parse($.ajax({ 
					url: "${pageContext.request.contextPath}/rest/post-transaction/post",
					method: "POST",
					async: false,
					data : JSON.stringify({
						    "transType" : transType,
						    "transId" : transId,
						    "transDate" : transDate
					}),
					beforeSend: function(xhr) {
					    xhr.setRequestHeader("Accept", "application/json");
					    xhr.setRequestHeader("Content-Type", "application/json");
				    },
				}).responseText);
				if(content.MESSAGE == "FAILED"){
					if(confirm(content.MSG)){
				 		return true;
				 	}else{
				 		return false;
				 	}
				}else{
					if(last)
						confirm("The post transaction was successful posted.");					
					return true;
				}
			}
			$(function(){				
				$('#fromdate').val(moment().format('YYYY-MM-DD'));  
			    $('#todate').val(moment().format('YYYY-MM-DD'));			    
				$("#datafilter").change(function(){
					var action = $("#datafilter").val();
					switch(action) {
					    case 'All':	
					    	$('#fromdate').prop("disabled", false);  
					        $('#todate').prop("disabled", false);
					        $('#fromdate').val(moment().format('YYYY-MM-DD'));  
					        $('#todate').val(moment().format('YYYY-MM-DD'));
					        angular.element(document.getElementById('postTranCon')).scope().setFromToDate();					        
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
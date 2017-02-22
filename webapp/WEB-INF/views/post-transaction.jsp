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
												
												<tr  ng-repeat="tr in trans">
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
					
				<section class="content-footer">
					
				</section>	
				
				
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
						});
					}else{
						$scope.trans = [];
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
							var status = voidTrans(transType,listTrans[i].transId,last);
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
							var status = voidTransAndClone(transType,listTrans[i].transId,last);
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
							var status = voidTransAndClone(transType,listTrans[i].transId,last);
							if(status){
								continue;
							}else{
								break;
							}
							
						}
					}
					$scope.listTransaction(0);
				}
				
			}]);
			
			function voidTrans(transType, transId,last){
				var content = JSON.parse($.ajax({ 
					url: "${pageContext.request.contextPath}/rest/post-transaction/void",
					method: "POST",
					async: false,
					data : JSON.stringify({
						    "transType" : transType,
						    "transId" : transId
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
			function voidTransAndClone(transType, transId,last){
				var content = JSON.parse($.ajax({ 
					url: "${pageContext.request.contextPath}/rest/post-transaction/void-and-clone",
					method: "POST",
					async: false,
					data : JSON.stringify({
						    "transType" : transType,
						    "transId" : transId
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
			function postTrans(transType, transId,last){
				var content = JSON.parse($.ajax({ 
					url: "${pageContext.request.contextPath}/rest/post-transaction/post",
					method: "POST",
					async: false,
					data : JSON.stringify({
						    "transType" : transType,
						    "transId" : transId
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
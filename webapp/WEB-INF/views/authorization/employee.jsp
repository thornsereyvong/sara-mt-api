<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="${request.contextPath}/head"></jsp:include>

</head>
<body class="sidebar-mini wysihtml5-supported skin-red-light" ng-app="authoriGroup">
	<div class="wrapper">

		<jsp:include page="${request.contextPath}/header"></jsp:include>
		<jsp:include page="${request.contextPath}/menu"></jsp:include>
<style>
	.width-75{ width: 75px !important; }
	.cursor-pointer{ cursor: pointer !important; margin-top: -20px !important;}
</style>

		<div class="content-wrapper" ng-controller="authoriCon">
			<section class="content-header">
				<h1>Employee Authorization</h1>
				<ol class="breadcrumb">
					<li><a href="${pageContext.request.contextPath}"><i
							class="fa fa-home"></i> Home</a></li>
					<li><a href="#">Employee Authorization</a></li>
				</ol>
			</section>
			<section class="content ng-scope" >
				<div class="box box-danger">
					<div class="box-header">
						<div class="col-sm-12">
							<button style="margin-top: 10px;" data-toggle="modal" data-target="#myModal" class="btn btn-default">
								<i class="glyphicon glyphicon-plus"></i> Create
							</button>	
						</div>
					</div>
					<div class="box-body">
						<div class="col-md-3 col-sm-3">
							<label>Employee</label>
							<div class="form-group">
								<select class="form-control select2" ng-model="mEmp" ng-change="changeEmp()" >
									<option value="">-- Select Employee --</option>
									<option ng-repeat="op in emps" value="{{op.empID}}" ng-cloak>[{{op.empID}}] {{op.empName}}</option>
								</select>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="tablecontainer table-responsive">
								<table class="table table-hover">
									<thead>
										<tr>
											<th style="cursor: pointer;" ng-click="sort('authProcess')">Authorization Process
												<span class="glyphicon sort-icon" ng-show="sortKey=='authProcess'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
											</th>
											<th style="cursor: pointer;" ng-click="sort('authId')">Authorization Id
												<span class="glyphicon sort-icon" ng-show="sortKey=='authId'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
											</th>
											<th style="cursor: pointer;" ng-click="sort('authName')">Authorization Name
												<span class="glyphicon sort-icon" ng-show="sortKey=='authName'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
											</th>
											
											<th style="width: 175px;"></th>
										</tr>
									</thead>
									<tbody>
										<tbody>
											<tr pagination-id="listAuthEmp" dir-paginate="data in authEmployeeId |orderBy:sortKey:reverse |filter:search |itemsPerPage:10"  >
												<td ng-cloak>{{data.authProcess}}</td>
												<td ng-cloak>{{data.authId}}</td>
												<td ng-cloak>{{data.authName}}</td>
												<td ng-cloak >
													<button class="btn btn-default btn-sm" ng-click="deleteAuthEmployeeById(data.empId,data.authProcess,data.authId)"><i class="glyphicon glyphicon-trash"></i></button>
												</td>							
											</tr> 
									
									</tbody>
								</table>
								<dir-pagination-controls 
									pagination-id="listAuthEmp"
									max-size="10" 
									direction-links="true"
									boundary-links="true"> 
								</dir-pagination-controls>
							</div>
						</div>
					</div>
				</div>
			
			
			
			
			</section>
			<div id="errors"></div>
			<!-- Dialog Create  -->
			
			<div data-ng-init="listEmployee()"  data-backdrop="static" class="modal fade bs-example-modal-lg" id="myModal" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog modal-lg" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" ng-click="closeModal()" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel"><b>[{{btn_save | uppercase}}] Employee Authorization </b></h4>
			      </div>
			     
			     <form id="form_group">
			      <div class="modal-body">
			     
			      <div class="clearfix"></div>
		
			      	 
			      		<div class="col-md-4 col-sm-4">
							<label>Action</label>
							<div class="form-group">
								<select class="form-control" name="action" id="action"  style="width:100%"  >
									<option value="">-- Select Action --</option>
									<option value="1">Override Existing</option>
									<option value="0">Keep Existing</option>
								</select>
							</div>
						</div>
						
						<div class="col-sm-4 col-md-4">
							<label>Process</label>
							<div class="form-group">
								<select class="form-control" name="process" id="process" ng-model="processMod">
									<option value="">-- Select Process --</option>
									<option value="Sale Order">Sale Order</option>
									<option value="Quote">Quote</option>
								</select>
							</div>
						</div>
						
						<div class="col-md-4 col-sm-4">
							<label>Authorization</label>
							<div class="form-group">
								<select class="form-control select2" name="authorization" id="authorization" ng-model="empMod" style="width:100%">
									<option value="">-- Select Authorization --</option>
									<option ng-repeat="ath in authorization" value="{{ath.authId}}" ng-cloak>[{{ath.authId}}] {{ath.authName}}</option>
								</select>
							</div>
						</div>
						 <div class="clearfix"></div>
			       
			      <div class="col-sm-3">
				  	<form class="form-inline">
				        <div class="form-group" style="padding-top: 20px;">
				        	<div class="input-group"> 
				        		<input type="text" pagination-id="listEmployeeCreate" ng-model="searchEmp" class="form-control" placeholder="Search">
				        	</div>
				        </div>
				    </form>
				    <br/>
				</div>
				<div class="col-sm-2">
				  	<form class="form-inline">
				        <div class="form-group" style="padding-top: 20px;">
				        	<label>Row: </label>
				        	<div class="input-group">
				        		<select class="form-control" ng-model="pageSize.row" id ="row" ng-options="obj.value as obj.label for obj in pageSize.rows"></select>
				        	</div>
				        </div>
				    </form>
				    <br/>
				</div>
			       <div class="col-sm-12" >
			 			<div class="tablecontainer table-responsive">
							<table class="table table-hover">
								<tbody>
									<tr>
										<th class="width-75 text-center">
										Count (<span>{{countEmpTrue}}</span>)
										</th> 
										<th style="cursor: pointer;" ng-click="sort('empID')" >Employee ID 
											<span class="glyphicon sort-icon" ng-show="sortKey=='empID'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></th>
										<th style="cursor: pointer;" ng-click="sort('empName')">Employee Name 
											<span class="glyphicon sort-icon" ng-show="sortKey=='empName'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></th>	
									</tr>
								</tbody>
								<tbody id="data-emp">
									<tr pagination-id="listEmployeeCreate" dir-paginate="tr in emps |orderBy:sortKey:reverse |filter:searchEmp |itemsPerPage:pageSize.row" current-page="currentPage" >
												<td class="width-75 text-center">
													<div class="icheckbox icheckbox-primary">
														<input ng-checked="tr.statusCheck" name="ckr" id="ckr{{$index}}" ng-click="ckRowClick(($index + 1) + (currentPage - 1) * pageSize.row)" class="styled" type="checkbox">
														<label class="cursor-pointer" for="ckr{{$index}}"></label>
													</div>
												</td>
												<td ng-cloak>{{tr.empID}}</td>
												<td ng-cloak>{{tr.empName}}</td>							
									</tr> 
								</tbody>
							</table>
							<dir-pagination-controls 
							    pagination-id="listEmployeeCreate"
								max-size="pageSize.row" 
								direction-links="true"
								boundary-links="true"> 
							</dir-pagination-controls> 
						</div>      
			       </div>
			       
			       
			        <div class="clearfix"></div>
			        
			      </div>
			      <div class="modal-footer">
			        <button type="button" id="btn_save" ng-click="getEvent()" value="{{btn_save}}" class="btn btn-default">{{btn_save}}</button>
			        <button type="button" class="btn btn-warning" data-dismiss="modal" ng-click="closeModal()">Cancel</button>
			      </div>
			      
			      </form>
			      
			    </div>
			  </div>
			</div>
			<!-- Close Dialog  -->
			
			
		</div>
		<jsp:include page="${request.contextPath}/foot"></jsp:include>
	</div>
	<jsp:include page="${request.contextPath}/footer"></jsp:include>

	<script type="text/javascript">
				
			var app = angular.module('authoriGroup', ['angularUtils.directives.dirPagination','angular-loading-bar', 'ngAnimate']).config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
			    cfpLoadingBarProvider.includeSpinner = false;
			}]);
			var self = this;
	
			app.controller('authoriCon',['$scope','$http',function($scope, $http){	

				$scope.btn_save = "Create";
				
				$scope.currentPage = 1;
				$scope.countEmpTrue = 0;
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

				
				$scope.sort = function(keyname){
				    $scope.sortKey = keyname;  
				    $scope.reverse = !$scope.reverse;
				};

				$scope.getEvent = function(){
					var getEv = getValueStringById("btn_save");
					if(getEv == "Create"){
						$scope.createAuthGroup();
					}
				
				}	
				
				$scope.ckrAll = function(){					
					var ckrAll = $("#ckrAll");
					if(ckrAll.is(':checked')){
						$("input[name=ckr]").prop('checked', true);
					}else{
						$("input[name=ckr]").prop('checked', false);
					}
				}
				
				$scope.ckRowClick = function(index){
					index--;
					$scope.emps[index].statusCheck = !$scope.emps[index].statusCheck;
					var countObjEmp = Object.keys($scope.emps).length;
					var countStatus = 0;
					for(var i=0;i < countObjEmp ;i++){
						if($scope.emps[i].statusCheck == true){
							countStatus++;
						}	
					}
					$scope.countEmpTrue = countStatus;
				}

				$scope.closeModal = function(){
					$scope.countEmpTrue = 0;
					setValueById("action","");
					setValueById("process","");
					$("#authorization").select2("val","");
					$("#form_group").bootstrapValidator('resetForm', 'true');
					$("input[name=ckr]").prop('checked', false);
					$scope.btn_save = "Create";
					$('#myModal').modal('toggle');
					$scope.currentPage = 1;
					$scope.pageSize.row = $scope.pageSize.rows[1].value;
					$scope.listEmployee();
				}

				$scope.listEmployee = function(){
					$http({
			 			method: 'GET',
					    url: "${pageContext.request.contextPath}/rest/employee/list",
					    headers: {
					    	'Accept': 'application/json',
					        'Content-Type': 'application/json'
					    }	    
					}).success(function(response) {
						$scope.emps = [];
						if(response.MESSAGE == "SUCCESS"){
							$scope.emps = response.DATA;
						}
					});
				}

				$scope.listAuthorization = function(){
					$http({
			 			method: 'GET',
					    url: "${pageContext.request.contextPath}/rest/authorization/list",
					    headers: {
					    	'Accept': 'application/json',
					        'Content-Type': 'application/json'
					    }	    
					}).success(function(response) {
						$scope.authorization = [];
						if(response.MESSAGE == "SUCCESS"){
							$scope.authorization = response.DATA;
						}
					});
				}
				$scope.listAuthorization();
				$scope.listEmployee();

				$scope.changeEmp = function(){
					
					$http({
			 			method: 'GET',
					    url: "${pageContext.request.contextPath}/rest/authorizationemployee/get-employee/"+$scope.mEmp,
					    headers: {
					    	'Accept': 'application/json',
					        'Content-Type': 'application/json'
					    } 
					}).success(function(response) {	
					
						if(response.MESSAGE == "sucess"){
							$scope.authEmployeeId = response.DATA;		
						}else{
							
						}
						
					});
					
				}
				
				$scope.createAuthGroup = function(){

					$.confirm({
					    title: '<h3 class="text-center">Are you sure you want to create this employee authorization ?</h3>',
					    content: '<hr>',
					    type: 'green',
					    buttons: {
					        createAuthorization: {
					            text: 'Yes',
					            action: function () {

					            	var listEmpDetail = [];
									
									for(var i=0; i< Object.keys($scope.emps).length ;i++){		
										if($scope.emps[i].statusCheck == true){	
											listEmpDetail.push({"empID":$scope.emps[i].empID});
										}	
									}
								

										$('#form_group').data('bootstrapValidator').validate();
										var addAuthGroup = $("#form_group").data('bootstrapValidator').validate().isValid();
										if(addAuthGroup){
											var process = getValueStringById("process");
											var authorization = getValueStringById("authorization");
											var action = getValueStringById("action");
											var stringValue = {
												    			"authProcess":process,"authId":authorization, "action": action,"empDetail":listEmpDetail
															  };
											$http({
									 			method: 'POST',
											    url: "${pageContext.request.contextPath}/rest/authorizationemployee/create",
											    headers: {
											    	'Accept': 'application/json',
											        'Content-Type': 'application/json'
											    }	,
											    data : stringValue    
											}).success(function(response) {	


												if(response.MESSAGE == "success"){
													$scope.closeModal();
													$.alert({
							                            title: '<h3 class="text-center">Success</h3>',
							                            type: 'green',
							                            content: response.DESCRIPTION+"<hr>",
							                     	});
													
												}else {
													$.alert({
							                            title: '<h3 class="text-center">Fail</h3>',
							                            type: 'red',
							                            content: "<hr>",
							                       });
												}
												
											});
										}

					            	
								 }
					        },
					        cancelAction:{
								text: 'Cancel',
								action: function(){
									
								}
						    }
					    }
					});
					
			            
			            
					
					
				}


			
				$scope.deleteAuthEmployeeById = function(empId,process,authID){
					$.confirm({
					    title: '<h3 class="text-center">Are you sure you want to delete  this process '+process+' ?</h3>',
					    type: 'orange',
					    content: 'This dialog will automatically trigger \'cancel\' in 6 seconds if you don\'t respond.'+"<hr>",
					    autoClose: 'cancelAction|8000',
					    buttons: {
					    	confirm: {
					            text: 'Delete',
					            action: function () {
					            	$http({   
					            		method:"POST",  
									    url: "${pageContext.request.contextPath}/rest/authorizationemployee/deleteById/"+empId+"/"+process+"/"+authID,
									    headers: {
									    	'Accept': 'application/json',
									        'Content-Type': 'application/json'
									    }	    
									}).success(function(response) {
							
										if(response.MESSAGE == "success"){
											 $.alert({
						                            title: '<h3 class="text-center">Success</h3>',
						                            type: 'green',
						                            content: response.DESCRIPTION+"<hr>",
						                     });
											$scope.changeEmp();
										}else{
											$.alert({
					                            title: '<h3 class="text-center">Fail</h3>',
					                            type: 'red',
					                            content: response.DESCRIPTION+"<hr>",
					                       });
										}
									});
					            }
					    	},
					    	cancelAction:{
								text:"Cancel"
						    }
						    	
					    }
					});
					
					
				}





				
				
				
			}]);
			
			
			
		</script>	
		
		<script type="text/javascript">

		
			$(document).ready(function(){

				$('#form_group').bootstrapValidator({
					message: 'This value is not valid',
					feedbackIcons: {
						valid: 'glyphicon glyphicon-ok',
						invalid: 'glyphicon glyphicon-remove',
						validating: 'glyphicon glyphicon-refresh'
					},
					fields: {
						action: {
							validators: {
								notEmpty: {
									message: 'The authorization action is required and can not be empty!'
								}
							}
						},
						process: {
							validators: {
								notEmpty: {
									message: 'The authorization process must is required and can not be empty!'
								}
							}
						},
						authorization: {
							validators: {
								notEmpty: {
									message: 'The authorization  is required and can not be empty!'
								}
							}
						}
						
					}
				});

			  
			});
		
		</script>		
	
</body>
</html>
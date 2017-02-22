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
				<h1>Authorization</h1>
				<ol class="breadcrumb">
					<li><a href="${pageContext.request.contextPath}"><i
							class="fa fa-home"></i> Home</a></li>
					<li><a href="#">Authorization</a></li>
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
						<div class="col-sm-12">
							<div class="tablecontainer table-responsive" data-ng-init="listAuthorization()">
								<table class="table table-hover">
									<thead>
										<tr>
											<th style="cursor: pointer;" ng-click="sort('authId')">Authorization ID
												<span class="glyphicon sort-icon" ng-show="sortKey=='authId'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
											</th>
											<th style="cursor: pointer;" ng-click="sort('authName')">Authorization Name
												<span class="glyphicon sort-icon" ng-show="sortKey=='authName'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
											</th>
											<th style="cursor: pointer;" ng-click="sort('authType')">Authorization Type
												<span class="glyphicon sort-icon" ng-show="sortKey=='authType'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
											</th>
											<!-- <th style="cursor: pointer;" ng-click="sort('authCount')">Count Employee
												<span class="glyphicon sort-icon" ng-show="sortKey=='authCount'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
											</th> -->
											<th style="width: 175px;"></th>
										</tr>
									</thead>
									<tbody>
										<tbody>
											
											<tr pagination-id="listauth" dir-paginate="data in auth |orderBy:sortKey:reverse |filter:search |itemsPerPage:10"  >
												<td ng-cloak>{{data.authId}}</td>
												<td ng-cloak>{{data.authName}}</td>
												<td ng-cloak>{{data.authType}}</td>
												<!-- <td ng-cloak>{{data.authCount}}</td> -->
												<td ng-cloak >
													<button class="btn btn-danger" ng-click="deleteauth(data.authId)"><i class="glyphicon glyphicon-trash"></i> Delete</button>
													<button class="btn btn-info"  data-toggle="modal" data-target="#myModal" ng-click="getauthByID(data.authId)"  data-toggle="modal" data-target="#myModalEdit"><i class="glyphicon glyphicon-pencil"></i> Edit</button>
												</td>							
											</tr> 
									
									</tbody>
								</table>
								<dir-pagination-controls 
									pagination-id="listauth"
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
			
			<div data-ng-init="listEmployee()"  data-backdrop="static" class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog modal-lg" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" ng-click="closeModal()" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel"><b>[{{btn_save | uppercase}}] Group</b></h4>
			      </div>
			       <form id="form_group">
			      <div class="modal-body">
			     
			      <div class="clearfix"></div>
			       
			       <div class="col-sm-12">
			       		<label>Authorization Name</label>
			       		<div class="form-group">
			       			<input type="text" name="authori_name" id="authori_name" ng-model="authName" class="form-control"> 
			       		</div>
			       </div>
			        <div class="clearfix"></div>
			       <div class="col-sm-12">
			       		<label>Authorization Description</label>
			       		<div class="form-group">
			       			<input type="text" name="authori_desc" id="authori_desc" ng-model="authDesc" class="form-control"> 
			       		</div>
			       </div>
			       
			      <div class="col-sm-3">
				  	<form class="form-inline">
				        <div class="form-group" style="padding-top: 20px;">
				        	<div class="input-group"> 
				        		<input type="text" pagination-id="listEmployeeCreate" ng-model="search" class="form-control" placeholder="Search">
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
										</th> 
										<th style="cursor: pointer;" ng-click="sort('empID')" >Employee ID 
											<span class="glyphicon sort-icon" ng-show="sortKey=='empID'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></th>
										<th style="cursor: pointer;" ng-click="sort('empName')">Employee Name 
											<span class="glyphicon sort-icon" ng-show="sortKey=='empName'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></th>	
									</tr>
								</tbody>
								<tbody id="data-emp">
									<tr pagination-id="listEmployeeCreate" dir-paginate="tr in emps |orderBy:sortKey:reverse |filter:search |itemsPerPage:pageSize.row" current-page="currentPage" >
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
						$scope.createauth();
					}else{
						$scope.updateauth();
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
				}

				

				
				$scope.closeModal = function(){
					setValueById("authori_name","");
					setValueById("authori_desc","");
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
						$scope.auth = [];
						if(response.MESSAGE == "SUCCESS"){
							$scope.auth = response.DATA;
						}
					});
				}
				
				$scope.createauth = function(){
					var listEmpDetail = [];
					
					for(var i=0; i< Object.keys($scope.emps).length ;i++){		
						
						if($scope.emps[i].statusCheck == true){	
							listEmpDetail.push({"authEmpId":$scope.emps[i].empID});
						}
						
					}
				

						$('#form_group').data('bootstrapValidator').validate();
						var addauth = $("#form_group").data('bootstrapValidator').validate().isValid();
						if(addauth){
							var groupName = getValueStringById("authori_name");
							var groupDesc = getValueStringById("authori_desc");
							var stringValue = {
								    "authName":groupName,"authDesc":groupDesc, "authDetail":listEmpDetail
							};
							$http({
					 			method: 'POST',
							    url: "${pageContext.request.contextPath}/rest/authorization/create",
							    headers: {
							    	'Accept': 'application/json',
							        'Content-Type': 'application/json'
							    }	,
							    data : stringValue    
							}).success(function(response) {	
						
								if(response.MESSAGE == "SUCCESS"){
									$scope.listAuthorization();
									$scope.closeModal();
								}else if(response.MESSAGE == "EXIST"){
									alert("EXIST");
								}
								
							});
						}
					
				}


				$scope.updateauth = function(){
					var tr = $("#data-emp tr");
					var listEmpDetail = [];
					for(var i=0; i< Object.keys($scope.emps).length ;i++){		
						
						if($scope.emps[i].statusCheck == true){	
							listEmpDetail.push({"authEmpId":$scope.emps[i].empID});
						}
						
					}
				

						$('#form_group').data('bootstrapValidator').validate();
						var addauth = $("#form_group").data('bootstrapValidator').validate().isValid();
						if(addauth){
							var groupName = getValueStringById("authori_name");
							var groupDesc = getValueStringById("authori_desc");
							var stringValue = {
								    "authId": $scope.authoriID ,"authName":groupName,"authDesc":groupDesc, "authDetail":listEmpDetail
							};
							
							$http({
					 			method: 'POST',
							    url: "${pageContext.request.contextPath}/rest/authorization/edit",
							    headers: {
							    	'Accept': 'application/json',
							        'Content-Type': 'application/json'
							    }	,
							    data : stringValue    
							}).success(function(response) {	
								
								if(response.MESSAGE == "SUCCESS"){
									$scope.listAuthorization();
									$scope.closeModal();
								}else if(response.MESSAGE == "EXIST"){
									alert("EXIST");
								}
								
							});
						}
					
				}

				$scope.deleteauth = function(authID){
					$http({
			 			method: 'DELETE',
					    url: "${pageContext.request.contextPath}/rest/authorization/delete/"+authID,
					    headers: {
					    	'Accept': 'application/json',
					        'Content-Type': 'application/json'
					    }	    
					}).success(function(response) {
						if(response.MESSAGE == "SUCCESS"){
							
							$scope.listAuthorization();
						}else{
					
						}
					});
				}

				$scope.getauthByID = function(authID){
					$scope.btn_save = "Edit";
					$http({
			 			method: 'GET',
					    url: "${pageContext.request.contextPath}/rest/authorization/get/"+authID,
					    headers: {
					    	'Accept': 'application/json',
					        'Content-Type': 'application/json'
					    }	    
					}).success(function(response) {
						$scope.authByID = [];
						if(response.MESSAGE == "SUCCESS"){
							$scope.authByID = response.authorization;
							$scope.authorizationDetail = response.authorizationDetail;
							$scope.authoriID =  "";
							 angular.forEach($scope.authByID, function(value, key){
									$("#authori_name").val(value.authName);
									$("#authori_desc").val(value.authDesc);
									$scope.authoriID = value.authId;
							 });

							$scope.emps = response.Employees;

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
						authori_name: {
							validators: {
								notEmpty: {
									message: 'The authorization group name is required and can not be empty!'
								},
								stringLength: {
									max: 255,
									message: 'The authorization group name must be less than 255 characters long.'
								}
							}
						},
						authori_desc: {
							validators: {
								stringLength: {
									max: 255,
									message: 'The authorization group description must be less than 255 characters long.'
								}
							}
						}
						
					}
				});


				
			  
			});
		
		</script>		
	
</body>
</html>
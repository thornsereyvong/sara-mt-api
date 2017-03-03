<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="${request.contextPath}/head"></jsp:include>

</head>
<body class="sidebar-mini wysihtml5-supported skin-red-light" ng-app="authorization" >
	<div class="wrapper">

		<jsp:include page="${request.contextPath}/header"></jsp:include>
		<jsp:include page="${request.contextPath}/menu"></jsp:include>
	<style>
			.width-75{ width: 75px !important; }
			.cursor-pointer{ cursor: pointer !important; margin-top: -20px !important;}
		
		</style>

		<div class="content-wrapper" ng-controller="authoriCon" id="authorizationApp">
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
							<button style="margin-top: 10px;" ng-click="btn_createEv()" class="btn btn-default">
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
													<button class="btn btn-danger btn-sm" ng-click="deleteAuthorizationId(data.authId)"><i class="glyphicon glyphicon-trash"></i> Delete</button>
													<button class="btn btn-info btn-sm"  data-toggle="modal" data-target="#myModal" ng-click="getauthByID(data.authId)"  data-toggle="modal" data-target="#myModalEdit"><i class="glyphicon glyphicon-pencil"></i> Edit</button>
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
			
			<div   data-backdrop="static" class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog modal-lg" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" ng-click="closeModal()" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel"><b>[{{btn_save | uppercase}}] Authorization</b></h4>
			      </div>
			       <form id="form_authori">
			      <div class="modal-body">
			     
			      <div class="clearfix"></div>
			       
			       <div class="col-sm-6">
			       		<label>Authorization Name</label>
			       		<div class="form-group">
			       			<input type="text" name="authName" ng-model="authName" id="authName" class="form-control"> 
			       		</div>
			       </div>
			       <div class="col-sm-6">
			       		<label>Authorization Type</label>
			       		<div class="form-group">
			       			<select class="form-control " id="authType" name="authType" ng-change="changeType()" ng-model="sAuthType" >
			       				<option value="">-- Select Type</option>
			       				<option value="Individual">Individual</option>
			       				<option value="Group">Group</option>
			       			</select>
			       		</div>
			       </div>
			       <div class="clearfix"></div>
			        <div class="col-sm-6" id="div_andOr">
			       		<label>Authorization And / Or</label>
			       		<div class="form-group">
			       			<select class="form-control"  id="authAndOr" name="authAndOr" ng-change="changeAndOr()" ng-model="sAuthAndOr">
			       				<option value="">-- Select And / Or --</option>
			       				<option value="And">And</option>
			       				<option value="Or">Or</option>
			       			</select>
			       		</div>
			       </div>
			       
			        <div class="col-sm-6" id="div_amount">
			       		<label>Authorization Amount</label>
			       		<div class="form-group" id="divAthAmountIndividual">
			       			<input type="text" class="form-control" ng-model="authAmount" ng-change="changeAuthAmountIndi()"  id="authAmount" name="authAmount"> 
			       		</div>
			       </div>
			       
			       
			       
			     <div class="clearfix"></div>
			     <div id="div_group" style="display: none">
			     <div class="col-sm-3">
				  	<form class="form-inline">
				        <div class="form-group" style="padding-top: 20px;">
				        	<div class="input-group"> 
				        		<input type="text" ng-model="searchAuthGroup" class="form-control" placeholder="Search">
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
			     	<div class="col-sm-12">
			     		<table class="table">
			     			<thead>
			     				<tr>
			     					<th></th>
			     					<th style="width:120px">Authorization Id</th>
			     					<th style="width:150px">Authorization Name</th>
			     					<th style="width:180px">Authorization Employee</th>	
			     					<th style="wdith:30px;">Authorization And/Or</th>
			     					<th style="width:160px;">Authorization Amount</th>
			     				</tr>
			     			</thead>
			     			<tbody id="table_group">
			     				<tr pagination-id="listAuthorizationGroups" dir-paginate="trs in authorizationGroup |orderBy:sortKey:reverse |filter:searchAuthGroup |itemsPerPage:pageSize.row" current-page="currentPage" >
										<td class="width-75 text-center">
											<div class="icheckbox icheckbox-primary">
												<input ng-checked="trs.statusCheck" name="ckrAuth" id="ckrAuth{{$index}}" ng-click="ckRowClickAuth(($index + 1) + (currentPage - 1) * pageSize.row)" class="styled" type="checkbox">
												<label class="cursor-pointer" for="ckrAuth{{$index}}"></label>
											</div>
										</td>
										<td ng-cloak>{{trs.authGroupId}}</td>
										<td ng-cloak>{{trs.authGroupName}}</td>	
										<td ng-cloak>{{trs.authGroupCount}}</td>	
										<td ng-cloak>
											<div class="form-group" style="margin-bottom: 0;" id="divAuthAndOr{{trs.itemNumber}}">
												<select class="form-control" style='padding-right: 18.5px;' ng-change="andOrCheckAg(trs.itemNumber)"; ng-model="trs.andOrCheck" id="chrAuthAndOr{{trs.itemNumber}}" data-index="{{$index}}" name="nameAuthAndOr{{trs.itemNumber}}" >
													<option value="">-- And / Or --</option>
													<option value="And">And</option>
													<option value="Or">Or</option>
												</select>
											</div>
										</td>	
										<td ng-cloak>
											<div class="form-group" style="margin-bottom: 0;" id="divAuthAmount{{trs.itemNumber}}">
												<input type="text" ng-disabled="((trs.statusCheck == true) && trs.andOrCheck == 'Or')" ng-change="amountCheckAg(trs.itemNumber)" ng-model="trs.amountCheck" id="chrAuthAmount{{trs.itemNumber}}" data-index="{{$index}}" name="nameAuthAmount{{trs.itemNumber}}" class="form-control">
											</div>
										</td>					
									</tr> 
			     			</tbody>
			     			
			     		</table>
			     		<dir-pagination-controls 
							    pagination-id="listAuthorizationGroups"
								max-size="pageSize.row" 
								direction-links="true"
								boundary-links="true"> 
							</dir-pagination-controls> 
			     	</div>
			     </div>
			     <div id="div_emp" style="display: none" >
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
				
			var app = angular.module('authorization', ['angularUtils.directives.dirPagination','angular-loading-bar', 'ngAnimate']).config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
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

				$scope.setErrorField = function(id,message){
					
					var i = '<i class="form-control-feedback bv-no-label glyphicon glyphicon-remove" data-bv-icon-for="'+id+'" style="display: block;"></i>';
					var small = '<small class="help-block" data-bv-validator="notEmpty" data-bv-for="'+id+'" data-bv-result="INVALID" style="">'+message+'</small>';
					$("#"+id).find("i").remove();
					$("#"+id).find("small").remove();
					$("#"+id).removeClass("form-group has-feedback has-success").addClass("form-group has-feedback has-error");
					$("#"+id).append(i+ small);
				}

				$scope.setSuccessField = function(id){
					var i = '<i class="form-control-feedback bv-no-label glyphicon glyphicon-ok" data-bv-icon-for="'+id+'" style="display: block;"></i>';
					//var small = '<small class="help-block" data-bv-validator="notEmpty" data-bv-for="salStartDate" data-bv-result="INVALID" style="">The start date greater this month ! </small>';
					$("#"+id).find("i").remove();
					$("#"+id).find("small").remove();
					$("#"+id).removeClass("form-group has-feedback has-error").addClass("form-group has-feedback has-success");
					$("#"+id).append(i);
				}

				$scope.setNomallField = function(id){
					$("#"+id).removeClass("has-error");
					$("#"+id).removeClass("has-success");
					$("#"+id).find("i").remove();
					$("#"+id).find("small").remove();
				}

				$scope.getEvent = function(){
					var getEv = getValueStringById("btn_save");
					if(getEv == "Create"){
						$scope.createauth();
					}else{
						$scope.updateauth();
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
					if(isNaN($scope.authAmount)){
						$scope.setErrorField("divAthAmountIndividual","The authorization amount can not input string !");
					}else{
						
						if(parseInt(countStatus) < parseInt($scope.authAmount)){
							$scope.setErrorField("divAthAmountIndividual","Please check employee equal or greater than authorization amount  !");
						}else{
							$scope.setSuccessField("divAthAmountIndividual");
						}
					}
					
				}

				$scope.ckRowClickAuth = function(index){
					index--;
					$scope.authorizationGroup[index].statusCheck = !$scope.authorizationGroup[index].statusCheck;
					if($scope.authorizationGroup[index].statusCheck == true){
						$scope.authorizationGroup[index].andOrCheck = "";
						$scope.authorizationGroup[index].amountCheck = "";
					}else{
						$scope.authorizationGroup[index].andOrCheck = "";
						$scope.authorizationGroup[index].amountCheck = "";
						$scope.setNomallField("divAuthAndOr"+$scope.authorizationGroup[index].itemNumber);
						$scope.setNomallField("divAuthAmount"+$scope.authorizationGroup[index].itemNumber);
					}
				}
				
				/* $scope.ckrAll = function(){					
					var ckrAll = $("#ckrAll");
					if(ckrAll.is(':checked')){
						$("input[name=ckr]").prop('checked', true);
					}else{
						$("input[name=ckr]").prop('checked', false);
					}
				} */

				$scope.btn_createEv = function(){
					$('#myModal').modal('toggle');
					$scope.sAuthType = "";
					$scope.listAuthorizationGroup();
				}
				
				$scope.clearValidate = function(str){
					
					$(str+" .form-group").removeClass("has-error").addClass("");
					$(str+" .form-group").find("i").attr("style","display:none");
					$(str+" .form-group").find("i").removeClass("glyphicon" ,"glyphicon-remove").addClass("");
					$(str+" .form-group").find("small").attr("style","display:none");
					$(str+" .form-group").find("small").attr("data-bv-result","NOT_VALIDATED");
					
				}

			
				

				
				
	
				$scope.changeType = function(){
					$scope.sAuthAndOr = "";
					$(".select2").select2();
					$scope.listAuthorizationGroup();
					if($scope.sAuthType == "Individual"){
						$("#div_group").css("display","none");
						$("#authAndOr").removeAttr("disabled","");
						$("#div_andOr").css("display","");
						$("#div_emp").css("display","");
						$scope.listEmployee();
						$scope.currentPage = 1;
						$scope.pageSize.row = $scope.pageSize.rows[1].value;	
								
					}else if($scope.sAuthType == "Group"){

						$scope.listAuthorizationGroup();
						
						$("#div_group").css("display","");
						$("#div_emp").css("display","none");
						$("#div_andOr").css("display","none");
						$("#div_amount").css("display","none");
						$("#authAmount").attr("disabled","disabled");
						$("#authAndOr").attr("disabled","disabled");
						$("#authAndOr").val("");
						//$('#form_authori').bootstrapValidator('revalidateField', 'authAndOr');
						setValueById("authAmount","");
						//$('#form_authori').bootstrapValidator('revalidateField', 'authAmount');
					
					}else{

						$("#div_emp").css("display","none");
						$("#div_andOr").css("display","none");
						$("#div_amount").css("display","none");
						$("#authAmount").attr("disabled","disabled");
						$("#authAndOr").attr("disabled","disabled");
						$("#authAndOr").val("");
						$('#form_authori').bootstrapValidator('revalidateField', 'authAndOr');
						setValueById("authAmount","");
						$('#form_authori').bootstrapValidator('revalidateField', 'authAmount');
						
					}
				}

				$scope.changeAndOr = function(){
					var andOr = getValueStringById("authAndOr");
					if(andOr == "And"){
						$("#div_amount").css("display","");
						$("#authAmount").removeAttr("disabled","");
					}else{
						$("#div_amount").css("display","none");
						$("#authAmount").attr("disabled","disabled");
					}
				}
				
				

				$scope.closeModal = function(){
					setValueById("authType","");
					setValueById("authAmount","");
					$('#form_authori').bootstrapValidator("resetForm",true);
					$("#div_andOr").css("display","none");
					$("#div_amount").css("display","none");
					$("#authAmount").attr("disabled","disabled");
					$("#authAndOr").attr("disabled","disabled");
					$("#div_emp").css("display","none");
					$("#form_authori").bootstrapValidator('resetForm', 'true');
					$("input[name=ckr]").prop('checked', false);
					$scope.btn_save = "Create";
					$('#myModal').modal('toggle');
					$scope.currentPage = 1;
					$scope.pageSize.row = $scope.pageSize.rows[1].value;
					$scope.listEmployee();
					$("#div_emp").css("display","none");
					$("#div_group").css("display","none");
					$scope.setNomallField("divAthAmountIndividual");
				}

				
				
				$scope.createauth = function(){

					$('#form_authori').data('bootstrapValidator').validate();
					var addauth = $("#form_authori").data('bootstrapValidator').validate().isValid();
					if(addauth){
						
						if($scope.sAuthType == "Individual"){

							var countObjEmp = Object.keys($scope.emps).length;
							var countStatus = 0;
							var listEmpDetail = [];
							for(var i=0;i < countObjEmp ;i++){
								if($scope.emps[i].statusCheck == true){
									countStatus++;
									listEmpDetail.push({"authEmpId":$scope.emps[i].empID,"authGroupId":"","authGroupAndOr":"","authGroupAmount":""});
								}	
							}

							if($("#authAndOr").val() == "And"){
								if(isNaN($scope.authAmount)){
									$scope.setErrorField("divAthAmountIndividual","The authorization amount can not input string !");
								}else{
									
									if(parseInt(countStatus) < parseInt($scope.authAmount)){
										$scope.setErrorField("divAthAmountIndividual","Please check employee equal or greater than authorization amount  !");
									}else{
										$scope.setSuccessField("divAthAmountIndividual");
										
										var stringValue = {
											"authName":getValueStringById("authName"),
											"authType":getValueStringById("authType"), 
											"authAndOr":"And", 
											"authAmount":getValueStringById("authAmount"),
											"authorizationDetail": listEmpDetail
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
							}else if($("#authAndOr").val() == "Or"){
							
								var stringValue = {
										"authName":getValueStringById("authName"),
										"authType":getValueStringById("authType"), 
										"authAndOr":"Or", 
										"authAmount":0,
										"authorizationDetail": listEmpDetail
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
							

						}else if($scope.sAuthType == "Group"){
							var countObj = Object.keys($scope.authorizationGroup).length;
							var countTrueFalse = 0;
							var countOjbTrue = 0;
							var listGroupDetail = [];
							for(var i = 0; i < countObj; i++){	
								if($scope.authorizationGroup[i].statusCheck == true){
									countOjbTrue++;
									if($scope.authorizationGroup[i].andOrCheck == "And"){
										$scope.setSuccessField("divAuthAndOr"+$scope.authorizationGroup[i].itemNumber);
										if($scope.authorizationGroup[i].amountCheck != ""){
											if (isNaN($scope.authorizationGroup[i].amountCheck)) {
												$scope.setErrorField("divAuthAmount"+$scope.authorizationGroup[i].itemNumber,"The authorization amount can not input string !");
											}else{
												if(parseInt($scope.authorizationGroup[i].amountCheck) > parseInt($scope.authorizationGroup[i].authGroupCount)){
													$scope.setErrorField("divAuthAmount"+$scope.authorizationGroup[item-1].itemNumber,"Amount can not big than employee in group "+ $scope.authorizationGroup[item-1].authGroupName+" !");
												}else{
													$scope.setSuccessField("divAuthAmount"+$scope.authorizationGroup[i].itemNumber);
													countTrueFalse++;
													listGroupDetail.push({"authEmpId":"","authGroupId":$scope.authorizationGroup[i].authGroupId,"authGroupAndOr":"And","authGroupAmount":$scope.authorizationGroup[i].amountCheck});
												}
											}	
										}else{
											$scope.setErrorField("divAuthAmount"+$scope.authorizationGroup[i].itemNumber,"The authorization amount is required and can not be empty!");
										}
									}else if($scope.authorizationGroup[i].andOrCheck == "Or"){
										countTrueFalse++;
										listGroupDetail.push({"authEmpId":"","authGroupId":$scope.authorizationGroup[i].authGroupId,"authGroupAndOr":"Or","authGroupAmount":1});
										$scope.setSuccessField("divAuthAndOr"+$scope.authorizationGroup[i].itemNumber);
									}else{
										$scope.setErrorField("divAuthAndOr"+$scope.authorizationGroup[i].itemNumber,"The authorization and \ or is required and can not be empty!");
									}
								}
							}//close for loop object 	

							if(countTrueFalse == countOjbTrue){
								
								var stringValue = {
										"authName":getValueStringById("authName"),
										"authType":getValueStringById("authType"), 
										"authAndOr":"", 
										"authAmount":0,
										"authorizationDetail": listGroupDetail
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
							}else{
								alert("Error Group");
							}
							
						}else{
						
						}
						
					}else{

					}

				}

				

				$scope.changeAuthAmountIndi = function(){
					if($scope.authAmount == ""){
						$scope.setErrorField("divAthAmountIndividual","The authorization amount is required and can not be empty!");
					}else if($scope.authAmount != ""){
						
						var countObjEmp = Object.keys($scope.emps).length;
						var countStatus = 0;
						for(var i=0;i < countObjEmp ;i++){
							if($scope.emps[i].statusCheck == true){
								countStatus++;
							}	
						}
					
						if(isNaN($scope.authAmount)){
							$scope.setErrorField("divAthAmountIndividual","The authorization amount can not input string !");
						}else{
							
							if(parseInt(countStatus) >= parseInt($scope.authAmount)){	
								$scope.setSuccessField("divAthAmountIndividual");
							}else{
								$scope.setErrorField("divAthAmountIndividual","Please check employee equal or greater than authorization amount  !");				
							}
						}
						
						
					}else{

					}
				}
				
				$scope.andOrCheckAg = function(item){
					var countObj = Object.keys($scope.authorizationGroup).length;
					var itemID = $("#chrAuthAndOr"+item).val();
						if($scope.authorizationGroup[item-1].statusCheck == true){
							if($scope.authorizationGroup[item-1].andOrCheck == "And"){
								$scope.authorizationGroup[item-1].amountCheck = "";
								$scope.setSuccessField("divAuthAndOr"+$scope.authorizationGroup[item-1].itemNumber);
							}else if($scope.authorizationGroup[item-1].andOrCheck == "Or"){
								$scope.setSuccessField("divAuthAndOr"+$scope.authorizationGroup[item-1].itemNumber);
								$scope.setNomallField("divAuthAmount"+$scope.authorizationGroup[item-1].itemNumber);
								$scope.authorizationGroup[item-1].amountCheck = "";
							}else {
								$scope.setErrorField("divAuthAndOr"+$scope.authorizationGroup[item-1].itemNumber,"The authorization and \ or is required and can not be empty!");
							}
						}else{
							if($scope.authorizationGroup[item-1].andOrCheck == ""){
								$scope.setNomallField("divAuthAndOr"+$scope.authorizationGroup[item-1].itemNumber);
							}
						}
					
				}

			

				$scope.amountCheckAg = function(item){
					var itemID = "";
					var itemID = $("#chrAuthAmount"+item).val();
					if($scope.authorizationGroup[item-1].statusCheck == true){
						if($scope.authorizationGroup[item-1].andOrCheck == "And"){
							if($scope.authorizationGroup[item-1].amountCheck != ""){
								if (isNaN($scope.authorizationGroup[item-1].amountCheck)) {
									$scope.setErrorField("divAuthAmount"+$scope.authorizationGroup[item-1].itemNumber,"The authorization amount can not input string !");
								}else{
									
									if(parseInt($scope.authorizationGroup[item-1].amountCheck) > parseInt($scope.authorizationGroup[item-1].authGroupCount)){
										$scope.setErrorField("divAuthAmount"+$scope.authorizationGroup[item-1].itemNumber,"Amount can not big than employee in group "+ $scope.authorizationGroup[item-1].authGroupName+" !");
									}else{
										$scope.setSuccessField("divAuthAmount"+$scope.authorizationGroup[item-1].itemNumber);
									}
								}	
							}else {
								$scope.setErrorField("divAuthAmount"+$scope.authorizationGroup[item-1].itemNumber,"The authorization amount is required and can not be empty!");
							}
						}
					}else{
						if($scope.authorizationGroup[item-1].amountCheck == ""){
							$scope.setNomallField("divAuthAmount"+$scope.authorizationGroup[item-1].itemNumber);
						}
					}
					
				}

				$scope.updateauth = function(){
				
					$('#form_authori').data('bootstrapValidator').validate();
					var addauth = $("#form_authori").data('bootstrapValidator').validate().isValid();
					if(addauth){
						
						if($scope.sAuthType == "Individual"){

							var countObjEmp = Object.keys($scope.emps).length;
							var countStatus = 0;
							var listEmpDetail = [];
							for(var i=0;i < countObjEmp ;i++){
								if($scope.emps[i].statusCheck == true){
									countStatus++;
									listEmpDetail.push({"authEmpId":$scope.emps[i].empID,"authGroupId":"","authGroupAndOr":"","authGroupAmount":""});
								}	
							}

							if($("#authAndOr").val() == "And"){
								if(isNaN($scope.authAmount)){
									$scope.setErrorField("divAthAmountIndividual","The authorization amount can not input string !");
								}else{
									
									if(parseInt(countStatus) < parseInt($scope.authAmount)){
										$scope.setErrorField("divAthAmountIndividual","Please check employee equal or greater than authorization amount  !");
									}else{
										$scope.setSuccessField("divAthAmountIndividual");
										alert("And");
										var stringValue = {
											"authId":$scope.authoriID,
											"authName":getValueStringById("authName"),
											"authType":getValueStringById("authType"), 
											"authAndOr":"And", 
											"authAmount":getValueStringById("authAmount"),
											"authorizationDetail": listEmpDetail
										};

										$http({
								 			method: 'POST',
										    url: "${pageContext.request.contextPath}/rest/authorization/update",
										    headers: {
										    	'Accept': 'application/json',
										        'Content-Type': 'application/json'
										    }	,
										    data : stringValue    
										}).success(function(response) {	
											if(response.MESSAGE == "success"){
												$scope.listAuthorization();
												$scope.closeModal();
											}else if(response.MESSAGE == "exist"){
												alert("EXIST");
											}else if(response.MESSAGE == "used"){

											}else if(response.MESSAGE == "not allowed"){

											}
										});
										
									}
								}
							}else if($("#authAndOr").val() == "Or"){
							alert("Or");
								var stringValue = {
										"authId":$scope.authoriID,
										"authName":getValueStringById("authName"),
										"authType":getValueStringById("authType"), 
										"authAndOr":"Or", 
										"authAmount":0,
										"authorizationDetail": listEmpDetail
									};

									$http({
							 			method: 'POST',
									    url: "${pageContext.request.contextPath}/rest/authorization/update",
									    headers: {
									    	'Accept': 'application/json',
									        'Content-Type': 'application/json'
									    }	,
									    data : stringValue    
									}).success(function(response) {	
										if(response.MESSAGE == "success"){
											$scope.listAuthorization();
											$scope.closeModal();
										}else if(response.MESSAGE == "exist"){
											alert("EXIST");
										}else if(response.MESSAGE == "used"){

										}else if(response.MESSAGE == "not allowed"){

										}
										
									});
							}
							

						}else if($scope.sAuthType == "Group"){
							
							var countObj = Object.keys($scope.authorizationGroup).length;
							var countTrueFalse = 0;
							var countOjbTrue = 0;
							var listGroupDetail = [];
							for(var i = 0; i < countObj; i++){	
								if($scope.authorizationGroup[i].statusCheck == true){
									countOjbTrue++;
									if($scope.authorizationGroup[i].andOrCheck == "And"){
										$scope.setSuccessField("divAuthAndOr"+$scope.authorizationGroup[i].itemNumber);
										if($scope.authorizationGroup[i].amountCheck != ""){
											if (isNaN($scope.authorizationGroup[i].amountCheck)) {
												$scope.setErrorField("divAuthAmount"+$scope.authorizationGroup[i].itemNumber,"The authorization amount can not input string !");
											}else{
												if(parseInt($scope.authorizationGroup[i].amountCheck) > parseInt($scope.authorizationGroup[i].authGroupCount)){
													$scope.setErrorField("divAuthAmount"+$scope.authorizationGroup[item-1].itemNumber,"Amount can not big than employee in group "+ $scope.authorizationGroup[item-1].authGroupName+" !");
												}else{
													$scope.setSuccessField("divAuthAmount"+$scope.authorizationGroup[i].itemNumber);
													countTrueFalse++;
													listGroupDetail.push({"authEmpId":"","authGroupId":$scope.authorizationGroup[i].authGroupId,"authGroupAndOr":"And","authGroupAmount":$scope.authorizationGroup[i].amountCheck});
												}
											}	
										}else{
											$scope.setErrorField("divAuthAmount"+$scope.authorizationGroup[i].itemNumber,"The authorization amount is required and can not be empty!");
										}
									}else if($scope.authorizationGroup[i].andOrCheck == "Or"){
										countTrueFalse++;
										listGroupDetail.push({"authEmpId":"","authGroupId":$scope.authorizationGroup[i].authGroupId,"authGroupAndOr":"Or","authGroupAmount":1});
										$scope.setSuccessField("divAuthAndOr"+$scope.authorizationGroup[i].itemNumber);
									}else{
										$scope.setErrorField("divAuthAndOr"+$scope.authorizationGroup[i].itemNumber,"The authorization and \ or is required and can not be empty!");
									}
								}
							}//close for loop object 	

						
							if(countTrueFalse == countOjbTrue){
								
								var stringValue = {
										"authId":$scope.authoriID,
										"authName":getValueStringById("authName"),
										"authType":getValueStringById("authType"), 
										"authAndOr":"", 
										"authAmount":0,
										"authorizationDetail": listGroupDetail
									};
								$http({
						 			method: 'POST',
								    url: "${pageContext.request.contextPath}/rest/authorization/update",
								    headers: {
								    	'Accept': 'application/json',
								        'Content-Type': 'application/json'
								    }	,
								    data : stringValue    
								}).success(function(response) {	
									
									if(response.MESSAGE == "success"){
										$scope.listAuthorization();
										$scope.closeModal();
									}else if(response.MESSAGE == "exist"){
										alert("EXIST");
									}else if(response.MESSAGE == "used"){

									}else if(response.MESSAGE == "not allowed"){

									}
									
								});
							}else{
								alert("Error Group");
							}
							
						}else{
						
						}
						
					}else{

					}
				}

				$scope.deleteAuthorizationId = function(authID){
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
						$scope.emps = [];
						if(response.MESSAGE == "SUCCESS"){
							$scope.authoriID =  "";
							
							$scope.authByID = response.authorization;
							$scope.authorizationDetail = response.authorizationDetail;
							$scope.authorizationGroup = response.authorizationGroup;
							
					
							//$scope.sAuthAndOr = "";
							 angular.forEach($scope.authByID, function(value, key){
									$("#authName").val(value.authName);
									$("#authType").val(value.authType);
									$scope.authoriID = value.authId;
									$scope.sAuthAndOr = value.authType;
									$scope.sAuthType = value.authType;
									$(".select2").select2();
								
									if(value.authType == "Individual"){	
										$scope.emps = response.Employees;
										
										
										$scope.sAuthAndOr = value.authAndOr;
										setValueById("authAndOr",value.authAndOr);
										$("#div_andOr").css("display","");
										
										if(value.authAndOr == "And"){
											$("#authAmount").removeAttr("disabled","");
											$("#authAndOr").removeAttr("disabled","");
											$("#div_amount").css("display","");
											$("#authAmount").val(value.authAmount);
											setValueById("authAmount",value.authAmount);
										}else{
											$("#authAndOr").removeAttr("disabled","");
											$("#div_amount").css("display","none");
										}
										
										setValueById("authAmount",value.authAmount);
										$("#div_group").css("display","none");
										
										
										$("#div_emp").css("display","");
										$("#authType").val(value.authType);
										
	
									}else if(value.authType == "Group"){

										$scope.authorizationGroup = response.authorizationGroup;
										
										$("#div_group").css("display","");
										$("#div_emp").css("display","none");
										$("#div_andOr").css("display","none");
										$("#div_amount").css("display","none");
										$("#authAmount").attr("disabled","disabled");
										$("#authAndOr").attr("disabled","disabled");
										$("#authAndOr").val("");
										setValueById("authAmount",""); 
									
									}
							 });
							
						}
					});
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

				$scope.listAuthorizationGroup = function(){
					$http({
			 			method: 'GET',
					    url: "${pageContext.request.contextPath}/rest/authorizationgroup/list",
					    headers: {
					    	'Accept': 'application/json',
					        'Content-Type': 'application/json'
					    }	    
					}).success(function(response) {
						$scope.authorizationGroup = [];
						if(response.MESSAGE == "SUCCESS"){
							$scope.authorizationGroup = response.DATA;	
						}
					});
				}


				
				
				
			}]);
			
			
			
		</script>	
		
		<script type="text/javascript">
	
			$(document).ready(function(){

				$("#authType").change(function(){		
				});
				
				$(".select2").select2();
				$("#div_andOr").css("display","none");
				$("#div_amount").css("display","none");
				$("#authAmount").attr("disabled","disabled");
				$("#authAndOr").attr("disabled","disabled");
				
			
				$('#form_authori').bootstrapValidator({
					message: 'This value is not valid',
					feedbackIcons: {
						valid: 'glyphicon glyphicon-ok',
						invalid: 'glyphicon glyphicon-remove',
						validating: 'glyphicon glyphicon-refresh'
					},
					fields: {
						authName: {
							validators: {
								notEmpty: {
									message: 'The authorization name is required and can not be empty!'
								},
								stringLength: {
									max: 255,
									message: 'The authorization name must be less than 255 characters long.'
								}
							}
						},
						/* authAmount: {
							validators: {
								notEmpty: {
									message: 'The authorization amount is required and can not be empty!'
								},
								stringLength: {
									max: 255,
									message: 'The authorization amount must be less than 255 characters long.'
								},
								integer: {
				                    message: 'The value is not an integer',
				                    // The default separators
				                    thousandsSeparator: '',
				                    decimalSeparator: '.'
				                }
							}
						}, */
						authType: {
							validators: {
								notEmpty: {
									message: 'The authorization type is required and can not be empty!'
								}
							}
						},
						authAndOr: {
							validators: {
								notEmpty: {
									message: 'The authorization and \ or is required and can not be empty!'
								}
							}
						}
						
					}
				});
				
				
			});
		
		</script>		
	
</body>
</html>
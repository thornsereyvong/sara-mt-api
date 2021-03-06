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
									<div class="col-lg-3 col-sm-6 col-md-6 col-xs-12">
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
												<option value="Cash Advance">Cash Advance</option>
												<option value="Cash Advance Clearance">Cash Advance Clearance</option>
												<option value="GL Entries">GL Entries</option>
											</select>
										</div>
									</div>
									<div class="col-lg-3 col-sm-6 col-md-6 col-xs-12">
			                        	<div class="icheckbox icheckbox-primary"><br class="hidden-xs"><br>
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
				
				<div class="modal fade modal-default" id="frmPurchaseReturnInvoice" role="dialog">
					<div class="modal-dialog  modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title"><b>PURCHASE RETURN INVOICE</b></h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Entry No</label>
										<div class="form-group">
											<input type="text" value="{{purchaseReturnInvoice.purchaseReturnId}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Post Status</label>
										<div class="form-group">
											<input type="text" value="{{purchaseReturnInvoice.postStatus}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Purchase Invoice No.</label>
										<div class="form-group">
											<input type="text" value="{{purchaseReturnInvoice.purchaseId}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Reference</label>
										<div class="form-group">
											<input type="text" value="{{purchaseReturnInvoice.reference}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Return Date</label>
										<div class="form-group">
					                  		<div class="input-group">
						                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
						                    	<input type="text" disabled class="form-control pull-right date" value="{{purchaseReturnInvoice.purchaseReturnDate}}" readonly="readonly" name="fromdate">
						                  	</div>
				                		</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6">
										<label class="font-label">Vendor</label>
										<div class="form-group">
											<input type="text" value="[{{purchaseReturnInvoice.vendor.vendorId}}] {{purchaseReturnInvoice.vendor.vendorName}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Class</label>
										<div class="form-group">
											<input type="text" value="[{{purchaseReturnInvoice.classCode.classId}}] {{purchaseReturnInvoice.classCode.className}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-12">
										<label class="font-label">Remark</label>
										<div class="form-group">
											<input type="text" value="{{purchaseReturnInvoice.remark}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-12 table-responsive">
										<table class="table table-hover">
											<tr>
												<th>Item ID</th>
												<th>Item Name</th>
												<th>Location ID</th>
												<th>Class ID</th>
												<th>UOM</th>
												<th>Quantity</th>
												<th>Unit Cost</th>
												<th>Price Factor</th>
												<th>Total Amount</th>
												<th>Discount %</th>
												<th>Discount $</th>
												<th>VAT %</th>
												<th>VAT $</th>
												<th>ST %</th>
												<th>ST $</th>
												<th>Net Total Amount</th>
											</tr>
											<tbody>
												<tr ng-repeat="tr in purchaseReturnInvoice.purchaseReturnDetail" >
													<td ng-cloak>{{tr.item.itemId}}</td>
													<td ng-cloak>{{tr.item.itemName}}</td>
													<td ng-cloak>{{tr.location.locationId}}</td>
													<td ng-cloak>{{tr.classCode.classId}}</td>
													<td ng-cloak>{{tr.uom.uomId}}</td>
													<td ng-cloak>{{tr.retQty | number:4}}</td>
													<td ng-cloak>{{tr.unitCost | number:6}}</td>
													<td ng-cloak>{{tr.factor | number:4}}</td>
													<td ng-cloak>{{tr.totalCost | number:6}}</td>
													<td ng-cloak>{{tr.disPer | number:5}}</td>
													<td ng-cloak>{{tr.disDol | number:2}}</td>
													<td ng-cloak>{{tr.vatPer | number:5}}</td>
													<td ng-cloak>{{tr.vatDol | number:2}}</td>
													<td ng-cloak>{{tr.staxPer | number:5}}</td>
													<td ng-cloak>{{tr.staxDol | number:2}}</td>
													<td ng-cloak>{{tr.netTotalAmt | number:2}}</td>
												</tr>
											</tbody>
										</table>
									</div>
									
									<div class="clearfix"></div>
									<br>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Total Return Purchase</label>
										<div class="form-group">
											<input type="text" value="{{purchaseReturnInvoice.totalAmt | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Invoice Discount</label>
										<div class="form-group">
											<input type="text" value="{{purchaseReturnInvoice.disInvDol | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Net Invoice</label>
										<div class="form-group">
											<input type="text" value="{{purchaseReturnInvoice.netTotalInvoice | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Total Applied Payment</label>
										<div class="form-group">
											<input type="text" value="{{purchaseReturnInvoice.totalAppPay | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Net Amount Due</label>
										<div class="form-group">
											<input type="text" value="{{purchaseReturnInvoice.netTotalAmt | number:2}}" disabled class="form-control text-right" >
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
				
				<div class="modal fade modal-default" id="frmAPDebitNote" role="dialog">
					<div class="modal-dialog  modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title"><b>AP DEBIT NOTE</b></h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Entry No</label>
										<div class="form-group">
											<input type="text" value="{{apDebitNote.entryId}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Post Status</label>
										<div class="form-group">
											<input type="text" value="{{apDebitNote.postStatus}}" disabled class="form-control" >
										</div>
									</div>
									
									<div class="col-sm-6">
										<label class="font-label">Reference</label>
										<div class="form-group">
											<input type="text" value="{{apDebitNote.reference}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Debit Note Date</label>
										<div class="form-group">
					                  		<div class="input-group">
						                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
						                    	<input type="text" disabled class="form-control pull-right date" value="{{apDebitNote.debitNoteDate}}" readonly="readonly" name="fromdate">
						                  	</div>
				                		</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6">
										<label class="font-label">Vendor</label>
										<div class="form-group">
											<input type="text" value="[{{apDebitNote.vendor.vendorId}}] {{apDebitNote.vendor.vendorName}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Class</label>
										<div class="form-group">
											<input type="text" value="[{{apDebitNote.classCode.classId}}] {{apDebitNote.classCode.className}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-12">
										<label class="font-label">Remark</label>
										<div class="form-group">
											<input type="text" value="{{apDebitNote.remark}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-12 table-responsive">
										<table class="table table-hover">
											<tr>
												<th>Item ID</th>
												<th>Item Name</th>
												<th>Location ID</th>
												<th>Class ID</th>
												<th>UOM</th>
												<th>Quantity</th>
												<th>Unit Cost</th>
												<th>Price Factor</th>
												<th>Total Amount</th>
												<th>Discount %</th>
												<th>Discount $</th>
												<th>VAT %</th>
												<th>VAT $</th>
												<th>ST %</th>
												<th>ST $</th>
												<th>Net Total Amount</th>
											</tr>
											<tbody>
												<tr ng-repeat="tr in apDebitNote.debitNoteDetail" >
													<td ng-cloak>{{tr.item.itemId}}</td>
													<td ng-cloak>{{tr.item.itemName}}</td>
													<td ng-cloak>{{tr.location.locationId}}</td>
													<td ng-cloak>{{tr.classCode.classId}}</td>
													<td ng-cloak>{{tr.uom.uomId}}</td>
													<td ng-cloak>{{tr.qty | number:4}}</td>
													<td ng-cloak>{{tr.unitCost | number:6}}</td>
													<td ng-cloak>{{tr.factor | number:4}}</td>
													<td ng-cloak>{{tr.totalCost | number:6}}</td>
													<td ng-cloak>{{tr.disPer | number:5}}</td>
													<td ng-cloak>{{tr.disDol | number:2}}</td>
													<td ng-cloak>{{tr.vatPer | number:5}}</td>
													<td ng-cloak>{{tr.vatDol | number:2}}</td>
													<td ng-cloak>{{tr.staxPer | number:5}}</td>
													<td ng-cloak>{{tr.staxDol | number:2}}</td>
													<td ng-cloak>{{tr.netTotalAmt | number:2}}</td>
												</tr>
											</tbody>
										</table>
									</div>
									
									<div class="clearfix"></div>
									<br>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Total Return Purchase</label>
										<div class="form-group">
											<input type="text" value="{{apDebitNote.totalAmt | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Invoice Discount</label>
										<div class="form-group">
											<input type="text" value="{{apDebitNote.disInvDol | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Net Invoice</label>
										<div class="form-group">
											<input type="text" value="{{apDebitNote.netTotalInvoice | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Total Applied Payment</label>
										<div class="form-group">
											<input type="text" value="{{apDebitNote.totalAppPay | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Net Amount Due</label>
										<div class="form-group">
											<input type="text" value="{{apDebitNote.netTotalAmt | number:2}}" disabled class="form-control text-right" >
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
				
				<div class="modal fade modal-default" id="frmAPPayment" role="dialog">
					<div class="modal-dialog  modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title"><b>AP Payment</b></h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Entry No</label>
										<div class="form-group">
											<input type="text" value="{{apPayment.pmtId}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Post Status</label>
										<div class="form-group">
											<input type="text" value="{{apPayment.postStatus}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Payment Type</label>
										<div class="form-group">
											<input type="text" value="{{apPayment.pmtTypepmtType}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Reference</label>
										<div class="form-group">
											<input type="text" value="{{apPayment.reference}}" disabled class="form-control" >
										</div>
									</div>								
									<div class="col-sm-6">
										<label class="font-label">Payment Date</label>
										<div class="form-group">
					                  		<div class="input-group">
						                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
						                    	<input type="text" disabled class="form-control pull-right date" value="{{apPayment.pmtDate}}" readonly="readonly" name="fromdate">
						                  	</div>
				                		</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Cash Account ID</label>
										<div class="form-group">
											<input type="text" value="[{{apPayment.account.accountId}}] {{apPayment.account.accountName}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Vendor</label>
										<div class="form-group">
											<input type="text" value="[{{apPayment.vendor.vendorId}}] {{apPayment.vendor.vendorName}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Class</label>
										<div class="form-group">
											<input type="text" value="[{{apPayment.classCode.classId}}] {{apPayment.classCode.className}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-12">
										<label class="font-label">Remark</label>
										<div class="form-group">
											<input type="text" value="{{apPayment.remark}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-12 table-responsive">
										<table class="table table-hover">
											<tr>
												<th>Line No</th>
												<th>Purchase ID</th>
												<th>Reference</th>
												<th>Purchase Date</th>
												<th>Original Amount</th>
												<th>Payment To Date</th>
												<th>Amount Due</th>
												<th>Net Amount Due</th>
												<th>Description</th>
												<th>Discount</th>
												<th>Payment To Cash Account</th>
												<th>Applied Payments</th>
											</tr>
											<tbody>
												<tr ng-repeat="tr in apPayment.apPaymentDetail" >
													<td ng-cloak>{{$index+1}}</td>
													<td ng-cloak>{{tr.purchase.purchaseId}}</td>
													<td ng-cloak>{{tr.purchase.reference}}</td>
													<td ng-cloak>{{tr.purchase.purchaseDate}}</td>
													<td ng-cloak>{{tr.purchase.netTotalAmt | number:2}}</td>
													<td ng-cloak>{{tr.purchase.pmtToDate | number:2}}</td>
													<td ng-cloak>{{tr.amountDue | number:2}}</td>
													<td ng-cloak>{{tr.netAmountDue | number:2}}</td>
													<td ng-cloak>{{tr.transDescription}}</td>
													<td ng-cloak>{{tr.discount | number:2}}</td>
													<td ng-cloak>{{tr.pmtAmount | number:2}}</td>
													<td ng-cloak>{{tr.appliedAmt | number:2}}</td>
												</tr>
											</tbody>
										</table>
									</div>
									
									<div class="clearfix"></div>
									<br>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Pay Invoice</label>
										<div class="form-group">
											<input type="text" value="{{apDebitNote.totalAmt | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Total Discount</label>
										<div class="form-group">
											<input type="text" value="{{apPayment.totalDiscount | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Total Cash</label>
										<div class="form-group">
											<input type="text" value="{{getTotalCash() | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Total Payment</label>
										<div class="form-group">
											<input type="text" value="{{apDebitNote.totalAppPay | number:2}}" disabled class="form-control text-right" >
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
								
				<div class="modal fade modal-default" id="frmAPInvoice" role="dialog">
					<div class="modal-dialog  modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title"><b>AP INVOICE</b></h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Entry No</label>
										<div class="form-group">
											<input type="text" value="{{apInvoice.saleId}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Post Status</label>
										<div class="form-group">
											<input type="text" value="{{apInvoice.postStatus}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Sale Type</label>
										<div class="form-group">
											<input type="text" value="{{apInvoice.saleType}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Reference</label>
										<div class="form-group">
											<input type="text" value="{{apInvoice.reference}}" disabled class="form-control" >
										</div>
									</div>								
									<div class="col-sm-6">
										<label class="font-label">Sale Date</label>
										<div class="form-group">
					                  		<div class="input-group">
						                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
						                    	<input type="text" disabled class="form-control pull-right date" value="{{apInvoice.saleDate}}" readonly="readonly" name="fromdate">
						                  	</div>
				                		</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Due Date</label>
										<div class="form-group">
					                  		<div class="input-group">
						                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
						                    	<input type="text" disabled class="form-control pull-right date" value="{{apInvoice.dueDate}}" readonly="readonly" name="fromdate">
						                  	</div>
				                		</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Customer</label>
										<div class="form-group">
											<input type="text" value="[{{apInvoice.customer.custId}}] {{apInvoice.customer.custName}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Price Code</label>
										<div class="form-group">
											<input type="text" value="[{{apInvoice.priceCode.priceCode}}] {{apInvoice.priceCode.des}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Ship To</label>
										<div class="form-group">
											<input type="text" value="{{apInvoice.shipTo}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Sale Rep. ID</label>
										<div class="form-group">
											<input type="text" value="[{{apInvoice.employee.empID}}] {{apInvoice.employee.empName}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Class</label>
										<div class="form-group">
											<input type="text" value="[{{apInvoice.classCode.classId}}] {{apInvoice.classCode.className}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Remark</label>
										<div class="form-group">
											<input type="text" value="{{apInvoice.remark}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-12 table-responsive">
										<table class="table table-hover">
											<tr>
												<th>Item ID</th>
												<th>Item Name</th>
												<th>Location ID</th>
												<th>Class ID</th>
												<th>UOM</th>
												<th>Quantity</th>
												<th>Unit Cost</th>
												<th>Price Factor</th>
												<th>Total Amount</th>
												<th>Discount %</th>
												<th>Discount $</th>
												<th>VAT %</th>
												<th>VAT $</th>
												<th>ST %</th>
												<th>ST $</th>
												<th>Net Total Amount</th>
											</tr>
											<tbody>
												<tr ng-repeat="tr in apInvoice.saleDetails" >
													<td ng-cloak>{{tr.item.itemId}}</td>
													<td ng-cloak>{{tr.item.itemName}}</td>
													<td ng-cloak>{{tr.location.locationId}}</td>
													<td ng-cloak>{{tr.classCode.classId}}</td>
													<td ng-cloak>{{tr.uom.uomId}}</td>
													<td ng-cloak>{{tr.qty | number:4}}</td>
													<td ng-cloak>{{tr.unitPrice | number:6}}</td>
													<td ng-cloak>{{tr.factor | number:4}}</td>
													<td ng-cloak>{{tr.totalAmt | number:6}}</td>
													<td ng-cloak>{{tr.disPer | number:5}}</td>
													<td ng-cloak>{{tr.disDol | number:2}}</td>
													<td ng-cloak>{{tr.vtaxPer | number:5}}</td>
													<td ng-cloak>{{tr.vtaxDol | number:2}}</td>
													<td ng-cloak>{{tr.staxPer | number:5}}</td>
													<td ng-cloak>{{tr.staxDol | number:2}}</td>
													<td ng-cloak>{{tr.netTotalAmt | number:2}}</td>
												</tr>
											</tbody>
										</table>
									</div>
									
									<div class="clearfix"></div>
									<br>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Discount</label>
										<div class="form-group">
											<input type="text" value="{{apInvoice.totalDis | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Net Total Amount</label>
										<div class="form-group">
											<input type="text" value="{{apInvoice.netTotalAmt | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Specific Tax</label>
										<div class="form-group">
											<input type="text" value="{{apInvoice.totalSTax | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Payment To Date</label>
										<div class="form-group">
											<input type="text" value="{{apInvoice.pmtToDate | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">VAT</label>
										<div class="form-group">
											<input type="text" value="{{apInvoice.totalVTax | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Net Amount Due</label>
										<div class="form-group">
											<input type="text" value="{{apInvoice.netTotalAmt-apInvoice.pmtToDate | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-danger" data-dismiss="modal">CLOSE</button>
							</div>
						</div>
					</div>
				</div>
				
				<div class="modal fade modal-default" id="frmAPInvoiceReturn" role="dialog">
					<div class="modal-dialog  modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title"><b>AP INVOICE RETURN</b></h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Entry No</label>
										<div class="form-group">
											<input type="text" value="{{apInvoiceReturn.saleReturnId}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Post Status</label>
										<div class="form-group">
											<input type="text" value="{{apInvoiceReturn.postStatus}}" disabled class="form-control" >
										</div>
									</div>									
									<div class="col-sm-6">
										<label class="font-label">Reference</label>
										<div class="form-group">
											<input type="text" value="{{apInvoiceReturn.reference}}" disabled class="form-control" >
										</div>
									</div>								
									<div class="col-sm-6">
										<label class="font-label">Sale Date</label>
										<div class="form-group">
					                  		<div class="input-group">
						                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
						                    	<input type="text" disabled class="form-control pull-right date" value="{{apInvoiceReturn.saleDate}}" readonly="readonly" name="fromdate">
						                  	</div>
				                		</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Due Date</label>
										<div class="form-group">
					                  		<div class="input-group">
						                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
						                    	<input type="text" disabled class="form-control pull-right date" value="{{apInvoiceReturn.dueDate}}" readonly="readonly" name="fromdate">
						                  	</div>
				                		</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Customer</label>
										<div class="form-group">
											<input type="text" value="[{{apInvoiceReturn.customer.custId}}] {{apInvoiceReturn.customer.custName}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Price Code</label>
										<div class="form-group">
											<input type="text" value="[{{apInvoiceReturn.priceCode.priceCode}}] {{apInvoiceReturn.priceCode.des}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Ship To</label>
										<div class="form-group">
											<input type="text" value="{{apInvoiceReturn.shipTo}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Sale Rep. ID</label>
										<div class="form-group">
											<input type="text" value="[{{apInvoiceReturn.employee.empID}}] {{apInvoiceReturn.employee.empName}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Class</label>
										<div class="form-group">
											<input type="text" value="[{{apInvoiceReturn.classCode.classId}}] {{apInvoiceReturn.classCode.className}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Sale Invoice No.</label>
										<div class="form-group">
											<input type="text" value="{{apInvoiceReturn.saleId}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Remark</label>
										<div class="form-group">
											<input type="text" value="{{apInvoiceReturn.remark}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-12 table-responsive">
										<table class="table table-hover">
											<tr>
												<th>Item ID</th>
												<th>Item Name</th>
												<th>Location ID</th>
												<th>Class ID</th>
												<th>UOM</th>
												<th>Sale Qty</th>
												<th>Return To Date</th>
												<th>Return Qty</th>
												<th>Unit Cost</th>
												<th>Price Factor</th>
												<th>Total Amount</th>
												<th>Discount %</th>
												<th>Discount $</th>
												<th>VAT %</th>
												<th>VAT $</th>
												<th>ST %</th>
												<th>ST $</th>
												<th>Net Total Amount</th>
											</tr>
											<tbody>
												<tr ng-repeat="tr in apInvoiceReturn.saleReturnDetail" >
													<td ng-cloak>{{tr.item.itemId}}</td>
													<td ng-cloak>{{tr.item.itemName}}</td>
													<td ng-cloak>{{tr.location.locationId}}</td>
													<td ng-cloak>{{tr.classCode.classId}}</td>
													<td ng-cloak>{{tr.uom.uomId}}</td>
													<td ng-cloak>{{tr.salQty | number:4}}</td>
													<td ng-cloak>{{(tr.salQty-tr.qty) | number:4}}</td>
													<td ng-cloak>{{tr.qty | number:4}}</td>
													<td ng-cloak>{{tr.unitPrice | number:6}}</td>
													<td ng-cloak>{{tr.factor | number:4}}</td>
													<td ng-cloak>{{tr.totalAmt | number:6}}</td>
													<td ng-cloak>{{tr.disPer | number:5}}</td>
													<td ng-cloak>{{tr.disDol | number:2}}</td>
													<td ng-cloak>{{tr.vtaxPer | number:5}}</td>
													<td ng-cloak>{{tr.vtaxDol | number:2}}</td>
													<td ng-cloak>{{tr.staxPer | number:5}}</td>
													<td ng-cloak>{{tr.staxDol | number:2}}</td>
													<td ng-cloak>{{tr.netTotalAmt | number:2}}</td>
												</tr>
											</tbody>
										</table>
									</div>
									
									<div class="clearfix"></div>
									<br>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Discount</label>
										<div class="form-group">
											<input type="text" value="{{apInvoice.totalDis | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Net Total Amount</label>
										<div class="form-group">
											<input type="text" value="{{apInvoice.netTotalAmt | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Specific Tax</label>
										<div class="form-group">
											<input type="text" value="{{apInvoice.totalSTax | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Payment To Date</label>
										<div class="form-group">
											<input type="text" value="{{apInvoice.pmtToDate | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">VAT</label>
										<div class="form-group">
											<input type="text" value="{{apInvoice.totalVTax | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Net Amount Due</label>
										<div class="form-group">
											<input type="text" value="{{apInvoice.netTotalAmt-apInvoice.pmtToDate | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-danger" data-dismiss="modal">CLOSE</button>
							</div>
						</div>
					</div>
				</div>
								
				<div class="modal fade modal-default" id="frmARCdn" role="dialog">
					<div class="modal-dialog  modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title"><b>AR CREDIT NOTE</b></h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Entry No</label>
										<div class="form-group">
											<input type="text" value="{{arCdn.entryId}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Post Status</label>
										<div class="form-group">
											<input type="text" value="{{arCdn.postStatus}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Reference</label>
										<div class="form-group">
											<input type="text" value="{{arCdn.reference}}" disabled class="form-control" >
										</div>
									</div>								
									<div class="col-sm-6">
										<label class="font-label">Credit Note Date</label>
										<div class="form-group">
					                  		<div class="input-group">
						                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
						                    	<input type="text" disabled class="form-control pull-right date" value="{{arCdn.creditNoteDate}}" readonly="readonly" name="fromdate">
						                  	</div>
				                		</div>
									</div>
									
									<div class="col-sm-6">
										<label class="font-label">Customer</label>
										<div class="form-group">
											<input type="text" value="[{{arCdn.customer.custId}}] {{apInvoice.customer.custName}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Price Code</label>
										<div class="form-group">
											<input type="text" value="[{{arCdn.priceCode.priceCode}}] {{apInvoice.priceCode.des}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Ship To</label>
										<div class="form-group">
											<input type="text" value="{{arCdn.shipTo}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Sale Rep. ID</label>
										<div class="form-group">
											<input type="text" value="[{{arCdn.employee.empID}}] {{arCdn.employee.empName}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Class</label>
										<div class="form-group">
											<input type="text" value="[{{arCdn.classCode.classId}}] {{arCdn.classCode.className}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Remark</label>
										<div class="form-group">
											<input type="text" value="{{arCdn.remark}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-12 table-responsive">
										<table class="table table-hover">
											<tr>
												<th>Item ID</th>
												<th>Item Name</th>
												<th>Location ID</th>
												<th>Class ID</th>
												<th>UOM</th>
												<th>Quantity</th>
												<th>Unit Cost</th>
												<th>Price Factor</th>
												<th>Total Amount</th>
												<th>Discount %</th>
												<th>Discount $</th>
												<th>VAT %</th>
												<th>VAT $</th>
												<th>ST %</th>
												<th>ST $</th>
												<th>Net Total Amount</th>
											</tr>
											<tbody>
												<tr ng-repeat="tr in arCdn.creditNoteDetail" >
													<td ng-cloak>{{tr.item.itemId}}</td>
													<td ng-cloak>{{tr.item.itemName}}</td>
													<td ng-cloak>{{tr.location.locationId}}</td>
													<td ng-cloak>{{tr.classCode.classId}}</td>
													<td ng-cloak>{{tr.uom.uomId}}</td>
													<td ng-cloak>{{tr.qty | number:4}}</td>
													<td ng-cloak>{{tr.unitPrice | number:6}}</td>
													<td ng-cloak>{{tr.factor | number:4}}</td>
													<td ng-cloak>{{tr.totalAmt | number:6}}</td>
													<td ng-cloak>{{tr.disPer | number:5}}</td>
													<td ng-cloak>{{tr.disDol | number:2}}</td>
													<td ng-cloak>{{tr.vtaxPer | number:5}}</td>
													<td ng-cloak>{{tr.vtaxDol | number:2}}</td>
													<td ng-cloak>{{tr.staxPer | number:5}}</td>
													<td ng-cloak>{{tr.staxDol | number:2}}</td>
													<td ng-cloak>{{tr.netTotalAmt | number:2}}</td>
												</tr>
											</tbody>
										</table>
									</div>
									
									<div class="clearfix"></div>
									<br>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Discount</label>
										<div class="form-group">
											<input type="text" value="{{arCdn.totalDis | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Net Total Amount</label>
										<div class="form-group">
											<input type="text" value="{{arCdn.netTotalAmt | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Specific Tax</label>
										<div class="form-group">
											<input type="text" value="{{arCdn.totalSTax | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Payment To Date</label>
										<div class="form-group">
											<input type="text" value="{{arCdn.pmtToDate | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">VAT</label>
										<div class="form-group">
											<input type="text" value="{{arCdn.totalVTax | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Net Amount Due</label>
										<div class="form-group">
											<input type="text" value="{{arCdn.netTotalAmt-apInvoice.pmtToDate | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-danger" data-dismiss="modal">CLOSE</button>
							</div>
						</div>
					</div>
				</div>
				
				<div class="modal fade modal-default" id="frmARReceipt" role="dialog">
					<div class="modal-dialog  modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title"><b>AR Receipt</b></h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Entry No</label>
										<div class="form-group">
											<input type="text" value="{{arReceipt.rcpId}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Post Status</label>
										<div class="form-group">
											<input type="text" value="{{arReceipt.postStatus}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Payment Type</label>
										<div class="form-group">
											<input type="text" value="{{arReceipt.rcpType}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Reference</label>
										<div class="form-group">
											<input type="text" value="{{arReceipt.reference}}" disabled class="form-control" >
										</div>
									</div>								
									<div class="col-sm-6">
										<label class="font-label">Payment Date</label>
										<div class="form-group">
					                  		<div class="input-group">
						                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
						                    	<input type="text" disabled class="form-control pull-right date" value="{{arReceipt.rcpDate}}" readonly="readonly" name="fromdate">
						                  	</div>
				                		</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Cash Account ID</label>
										<div class="form-group">
											<input type="text" value="[{{arReceipt.account.accountId}}] {{arReceipt.account.accountName}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Customer</label>
										<div class="form-group">
											<input type="text" value="[{{arReceipt.customer.custId}}] {{arReceipt.customer.custName}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Class</label>
										<div class="form-group">
											<input type="text" value="[{{arReceipt.classCode.classId}}] {{arReceipt.classCode.className}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-12">
										<label class="font-label">Remark</label>
										<div class="form-group">
											<input type="text" value="{{arReceipt.remark}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-12 table-responsive">
										<table class="table table-hover">
											<tr>
												<th>Line No</th>
												<th>Sale ID</th>
												<th>Reference</th>
												<th>Sale Date</th>
												<th>Original Amount</th>
												<th>Receipt To Date</th>
												<th>Amount Due</th>
												<th>Net Amount Due</th>
												<th>Description</th>
												<th>Discount</th>
												<th>Payment To Cash Account</th>
												<th>Applied Payments</th>
											</tr>
											<tbody>
												<tr ng-repeat="tr in arReceipt.receiptDetail" >
													<td ng-cloak>{{$index+1}}</td>
													<td ng-cloak>{{tr.sale.saleId}}</td>
													<td ng-cloak>{{tr.sale.reference}}</td>
													<td ng-cloak>{{tr.sale.saleDate}}</td>
													<td ng-cloak>{{tr.sale.netTotalAmt | number:2}}</td>
													<td ng-cloak>{{-tr.sale.pmtToDate | number:2}}</td>
													<td ng-cloak>{{tr.amountDue | number:2}}</td>
													<td ng-cloak>{{tr.netAmountDue | number:2}}</td>
													<td ng-cloak>{{tr.transDescription}}</td>
													<td ng-cloak>{{tr.discount | number:2}}</td>
													<td ng-cloak>{{tr.pmtAmount | number:2}}</td>
													<td ng-cloak>{{tr.appliedAmt | number:2}}</td>
												</tr>
											</tbody>
										</table>
									</div>
									
									<div class="clearfix"></div>
									<br>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Pay Invoice</label>
										<div class="form-group">
											<input type="text" value="{{arReceipt.TotalDiscount + getTotalCashReceipt() | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Total Discount</label>
										<div class="form-group">
											<input type="text" value="{{arReceipt.totalDiscount | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Total Cash</label>
										<div class="form-group">
											<input type="text" value="{{getTotalCashReceipt() | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Total Payment</label>
										<div class="form-group">
											<input type="text" value="{{(getTotalCashReceipt()+getTotalReceiptApplied()) | number:2}}" disabled class="form-control text-right" >
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
				
				<div class="modal fade modal-default" id="frmICTransfer" role="dialog">
					<div class="modal-dialog  modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title"><b>IC TRANSFER</b></h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Entry No</label>
										<div class="form-group">
											<input type="text" value="{{icTransfer.trfId}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Post Status</label>
										<div class="form-group">
											<input type="text" value="{{icTransfer.postStatus}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Transfer To Consignment</label>
										<div class="form-group">
											<input type="text" value="{{icTransfer.consignment ==1 ? '[YES]':'[NO]'}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Reference</label>
										<div class="form-group">
											<input type="text" value="{{icTransfer.reference}}" disabled class="form-control" >
										</div>
									</div>								
									<div class="col-sm-6">
										<label class="font-label">Transfer Date</label>
										<div class="form-group">
					                  		<div class="input-group">
						                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
						                    	<input type="text" disabled class="form-control pull-right date" value="{{icTransfer.trfDate}}" readonly="readonly" name="fromdate">
						                  	</div>
				                		</div>
									</div>
									
									<div class="col-sm-6">
										<label class="font-label">Customer</label>
										<div class="form-group">
											<input type="text" value="[{{icTransfer.customer.custId}}] {{arReceipt.customer.custName}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Location</label>
										<div class="form-group">
											<input type="text" value="[{{icTransfer.location.locationId}}] {{icTransfer.location.locationName}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Remark</label>
										<div class="form-group">
											<input type="text" value="{{icTransfer.trfDescription}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Retail Price Code</label>
										<div class="form-group">
											<input type="text" value="{{icTransfer.pRetail}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Wholesale Price Code </label>
										<div class="form-group">
											<input type="text" value="{{icTransfer.pWholeSale}}" disabled class="form-control" >
										</div>
									</div>
									
									<div class="col-sm-12 table-responsive">
										<table class="table table-hover">
											<tr>
												<th>Item ID</th>
												<th>Item Name</th>
												<th>From Location ID</th>
												<th>To Location ID</th>
												<th>Uom ID</th>
												<th>Quantity</th>
												<th>Retail Price</th>
												<th>Wholesale Prcie</th>
											</tr>
											<tbody>
												<tr ng-repeat="tr in icTransfer.trfDetail" >
													<td ng-cloak>{{tr.item.itemId}}</td>
													<td ng-cloak>{{tr.item.itemName}}</td>
													<td ng-cloak>{{tr.fLocation.locationId}}</td>
													<td ng-cloak>{{tr.tLocation.locationId}}</td>
													<td ng-cloak>{{tr.uom.uomId}}</td>
													<td ng-cloak>{{tr.trfQty | number:2}}</td>
													<td ng-cloak>{{tr.retailPrice | number:2}}</td>
													<td ng-cloak>{{tr.wholeSalePrice | number:2}}</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="clearfix"></div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-danger" data-dismiss="modal">CLOSE</button>
							</div>
						</div>
					</div>
				</div>
				
				<div class="modal fade modal-default" id="frmICAdjustment" role="dialog">
					<div class="modal-dialog  modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title"><b>IC ADJUSTMENT</b></h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Entry No</label>
										<div class="form-group">
											<input type="text" value="{{icAdjustment.adjId}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Post Status</label>
										<div class="form-group">
											<input type="text" value="{{icAdjustment.postStatus}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Adjustment Date</label>
										<div class="form-group">
					                  		<div class="input-group">
						                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
						                    	<input type="text" disabled class="form-control pull-right date" value="{{icAdjustment.adjDate}}" readonly="readonly" name="fromdate">
						                  	</div>
				                		</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Reference</label>
										<div class="form-group">
											<input type="text" value="{{icAdjustment.adjReference}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-12">
										<label class="font-label">Remark</label>
										<div class="form-group">
											<input type="text" value="{{icAdjustment.adjDesc}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-12 table-responsive">
										<table class="table table-hover">
											<tr>
												<th>Item ID</th>
												<th>Item Name</th>
												<th>Location ID</th>
												<th>Class ID</th>
												<th>Batch No</th>
												<th>Uom ID</th>
												<th>Qty Adjustment</th>
											</tr>
											<tbody>
												<tr ng-repeat="tr in icAdjustment.adjDetail" >
													<td ng-cloak>{{tr.item.itemId}}</td>
													<td ng-cloak>{{tr.item.itemName}}</td>
													<td ng-cloak>{{tr.location.locationId}}</td>
													<td ng-cloak>{{tr.classCode.classId}}</td>
													<td ng-cloak>{{tr.batchNo}}</td>
													<td ng-cloak>{{tr.uom.uomId}}</td>
													<td ng-cloak>{{tr.adjQty | number:2}}</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="clearfix"></div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-danger" data-dismiss="modal">CLOSE</button>
							</div>
						</div>
					</div>
				</div>
				
				<div class="modal fade modal-default" id="frmCashAdvance" role="dialog">
					<div class="modal-dialog  modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title"><b>CASH ADVANCE</b></h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Entry No</label>
										<div class="form-group">
											<input type="text" value="{{cashAdvance.caId}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Post Status</label>
										<div class="form-group">
											<input type="text" value="{{cashAdvance.postStatus}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Cash Advance Date</label>
										<div class="form-group">
					                  		<div class="input-group">
						                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
						                    	<input type="text" disabled class="form-control pull-right date" value="{{cashAdvance.caDate}}" readonly="readonly" name="fromdate">
						                  	</div>
				                		</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Expected Clear Date</label>
										<div class="form-group">
					                  		<div class="input-group">
						                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
						                    	<input type="text" disabled class="form-control pull-right date" value="{{cashAdvance.expectedClearDate}}" readonly="readonly" name="fromdate">
						                  	</div>
				                		</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Reference</label>
										<div class="form-group">
											<input type="text" value="{{cashAdvance.caReference}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Employee</label>
										<div class="form-group">
											<input type="text" value="[{{cashAdvance.employee.empID}}] cashAdvance.employee.empName" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Cash Account</label>
										<div class="form-group">
											<input type="text" value="[{{cashAdvance.cashAccount.accountId}}] {{cashAdvance.cashAccount.accountName}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Cash Advance Account</label>
										<div class="form-group">
											<input type="text" value="[{{cashAdvance.cashAdvanceAccount.accountId}}] {{cashAdvance.cashAdvanceAccount.accountName}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Class</label>
										<div class="form-group">
											<input type="text" value="[{{cashAdvance.classCode.classId}}] cashAdvance.classCode.className" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Account</label>
										<div class="form-group">
											<input type="text" value="{{cashAdvance.clearAmount | number:2}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-12">
										<label class="font-label">Remark</label>
										<div class="form-group">
											
										</div>
									</div>
									<div class="col-sm-12">
										<div class="form-group">
											<textarea rows="5" style="width:100%">{{cashAdvance.remark}}</textarea>
										</div>
									</div>
									<div class="clearfix"></div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-danger" data-dismiss="modal">CLOSE</button>
							</div>
						</div>
					</div>
				</div>
				
				<div class="modal fade modal-default" id="frmCashAdvanceClearance" role="dialog">
					<div class="modal-dialog  modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title"><b>CASH ADVANCE CLEARANCE</b></h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Entry No</label>
										<div class="form-group">
											<input type="text" value="{{cashAdvanceClearance.clId}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Post Status</label>
										<div class="form-group">
											<input type="text" value="{{cashAdvanceClearance.postStatus}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Clearance Date</label>
										<div class="form-group">
					                  		<div class="input-group">
						                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
						                    	<input type="text" disabled class="form-control pull-right date" value="{{cashAdvanceClearance.clearDate}}" readonly="readonly" name="fromdate">
						                  	</div>
				                		</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Reference</label>
										<div class="form-group">
											<input type="text" value="{{cashAdvanceClearance.reference}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Cash Account</label>
										<div class="form-group">
											<input type="text" value="[{{cashAdvanceClearance.account.accountId}}] {{cashAdvanceClearance.account.accountName}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Employee</label>
										<div class="form-group">
											<input type="text" value="[{{cashAdvanceClearance.employee.empID}}] {{cashAdvanceClearance.employee.empName}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Class</label>
										<div class="form-group">
											<input type="text" value="[{{cashAdvanceClearance.classCode.classId}}] {{cashAdvanceClearance.classCode.className}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Remark</label>
										<div class="form-group">
											<input type="text" value="{{cashAdvanceClearance.remark}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-12 table-responsive">
										<table class="table table-hover">
											<tr>
												<th>Cash Advance ID</th>
												<th>Reference</th>
												<th>Cash Advance Date</th>
												<th>Original Amount</th>
												<th>Payment To Date</th>
												<th>Amount Due</th>
												<th>Net Amount Due</th>
												<th>Description</th>
											</tr>
											<tbody>
												<tr  ng-repeat="tr in cashAdvanceClearance.cashAdvanceClearances" ng-if="tr.cashAdvance.caId != null" >
													<td ng-cloak>{{tr.cashAdvance.caId}}</td>
													<td ng-cloak>{{tr.cashAdvance.caReference}}</td>
													<td ng-cloak>{{tr.cashAdvance.caDate}}</td>
													<td ng-cloak>{{tr.cashAdvance.amount | number:2}}</td>
													<td ng-cloak>{{tr.cashAdvance.clearAmount | number:2}}</td>
													<td ng-cloak>{{(tr.cashAdvance.amount-tr.cashAdvance.clearAmount) | number:2}}</td>
													<td ng-cloak>{{tr.remark}}</td>
													<td ng-cloak>{{((tr.cashAdvance.amount-tr.cashAdvance.clearAmount)-tr.clearAmount) | number:2}}</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="clearfix"></div>
									<br>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Total Amount</label>
										<div class="form-group">
											<input type="text" value="{{getTotalAmountClearance() | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-danger" data-dismiss="modal">CLOSE</button>
							</div>
						</div>
					</div>
				</div>   
							
				<div class="modal fade modal-default" id="frmGLEntries" role="dialog">
					<div class="modal-dialog  modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title"><b>GENERAL JOURNAL ENTRY</b></h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Entry No</label>
										<div class="form-group">
											<input type="text" value="{{glEntries.jId}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-xs-12">
										<label class="font-label">Post Status</label>
										<div class="form-group">
											<input type="text" value="{{glEntries.postStatus}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Journal Date</label>
										<div class="form-group">
					                  		<div class="input-group">
						                    	<div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
						                    	<input type="text" disabled class="form-control pull-right date" value="{{glEntries.jDate}}" readonly="readonly" name="fromdate">
						                  	</div>
				                		</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Account Peroid</label>
										<div class="form-group">
											<input type="text" value="[{{glEntries.periodM}}] [{{glEntries.peroidY}}]" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Source Code</label>
										<div class="form-group">
											<input type="text" value="{{glEntries.type}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-6">
										<label class="font-label">Journal Reference</label>
										<div class="form-group">
											<input type="text" value="{{glEntries.reference}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-12">
										<label class="font-label">Remark</label>
										<div class="form-group">
											<input type="text" value="{{glEntries.remark}}" disabled class="form-control" >
										</div>
									</div>
									<div class="col-sm-12 table-responsive">
										<table class="table table-hover">
											<tr>
												<th>Account ID</th>
												<th>Account Name</th>
												<th>Class ID</th>
												<th>Trans. Date</th>
												<th>Description</th>
												<th>Debit</th>
												<th>Credit</th>
											</tr>
											<tbody>
												<tr  ng-repeat="tr in glEntries.journalDetail">
													<td ng-cloak>{{tr.account.accountId}}</td>
													<td ng-cloak>{{tr.account.accountName}}</td>
													<td ng-cloak>{{tr.classCode.classId}}</td>
													<td ng-cloak>{{tr.jDate}}</td>
													<td ng-cloak>{{tr.reference}}</td>
													<td ng-cloak>{{(tr.drCr == 'Dr' ? tr.amount:0) | number:2}}</td>
													<td ng-cloak>{{(tr.drCr == 'Cr' ? -tr.amount:0) | number:2}}</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="clearfix"></div>
									<br>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Total Debit</label>
										<div class="form-group">
											<input type="text" value="{{getTotalDrAmount() | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Total Credit</label>
										<div class="form-group">
											<input type="text" value="{{getTotalCrAmount() | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-sm-6 col-md-6 col-xs-12 pull-right">
										<label class="font-label">Total Balance</label>
										<div class="form-group">
											<input type="text" value="{{(getTotalDrAmount()-getTotalCrAmount()) | number:2}}" disabled class="form-control text-right" >
										</div>
									</div>
									<div class="clearfix"></div>
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
							if(response.MESSAGE == "SUCCESS"){			
								switch(transType) {
									case "AP Invoice":
										$scope.purchaseInvoice = response.DATA;
										$("#frmPurchaseInvoice").modal({backdrop: 'static', keyboard: false});				
										break;
									case "AP Return Invoice": 
										$scope.purchaseReturnInvoice = response.DATA;
										$("#frmPurchaseReturnInvoice").modal({backdrop: 'static', keyboard: false});
								    	break;
								    case "AP Debit Note":
								    	$scope.apDebitNote = response.DATA;
										$("#frmAPDebitNote").modal({backdrop: 'static', keyboard: false});
								    	break;
								    case "AP Payment":
								    	$scope.apPayment = response.DATA;
										$("#frmAPPayment").modal({backdrop: 'static', keyboard: false});
								    	break;
							    	case "AR Invoice":
							    		$scope.apInvoice = response.DATA;
										$("#frmAPInvoice").modal({backdrop: 'static', keyboard: false});
								    	break;
						    		case "AR Return Invoice":
						    			$scope.apInvoiceReturn = response.DATA;
										$("#frmAPInvoiceReturn").modal({backdrop: 'static', keyboard: false});
								    	break;
						    		case "AR Credit Note":
						    			$scope.arCdn = response.DATA;
										$("#frmARCdn").modal({backdrop: 'static', keyboard: false});
								    	break;
									case "AR Receipt":
										$scope.arReceipt = response.DATA;
										$("#frmARReceipt").modal({backdrop: 'static', keyboard: false});										
								    	break;
									case "IC Transfer":
										$scope.icTransfer = response.DATA;
										$("#frmICTransfer").modal({backdrop: 'static', keyboard: false});
								    	break;
									case "IC Internal Usage":
										
								    	break;
									case "IC Adjustment":
										$scope.icAdjustment = response.DATA;
										$("#frmICAdjustment").modal({backdrop: 'static', keyboard: false});
								    	break;
									case "Cash Transfer":
										$scope.cashTransfer = response.DATA;
										$("#frmCashTransfer").modal({backdrop: 'static', keyboard: false});
								    	break;
									case "Cash Advance":
										$scope.cashAdvance = response.DATA;
										$("#frmCashAdvance").modal({backdrop: 'static', keyboard: false});
								    	break;
									case "Cash Advance Clearance":
										$scope.cashAdvanceClearance = response.DATA;
										$("#frmCashAdvanceClearance").modal({backdrop: 'static', keyboard: false});
								    	break;
									case "GL Entries":
										$scope.glEntries = response.DATA;
										$("#frmGLEntries").modal({backdrop: 'static', keyboard: false});
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
				
				$scope.getTotalCash = function(){
					if (typeof ($scope.apPayment) === 'undefined') {
			            return 0;
			        }
					var x = 0;	
					if($scope.apPayment.apPaymentDetail != null){
						for(var i=0;i<$scope.apPayment.apPaymentDetail.length;i++){
							x += Number($scope.apPayment.apPaymentDetail[i].pmtAmount);
						}
					}
					return x;
				}
				$scope.getTotalCashReceipt = function(){
					if (typeof ($scope.arReceipt) === 'undefined') {
			            return 0;
			        }
					var x = 0;	
					if($scope.arReceipt.receiptDetail != null){
						for(var i=0;i<$scope.arReceipt.receiptDetail.length;i++){
							x += Number($scope.arReceipt.receiptDetail[i].pmtAmount);
						}
					}
					return x;
				}
				$scope.getTotalReceiptApplied = function(){
					if (typeof ($scope.arReceipt) === 'undefined') {
			            return 0;
			        }
					var x = 0;
					if($scope.arReceipt.receiptDetail != null){
						for(var i=0;i<$scope.arReceipt.receiptDetail.length;i++){
							x += Number($scope.arReceipt.receiptDetail[i].appliedAmt);
						}
					}
					return x;
				}
				$scope.getTotalAmountClearance = function(){
					if (typeof ($scope.cashAdvanceClearance) === 'undefined') {
			            return 0;
			        }
					var x = 0;
					if($scope.cashAdvanceClearance.cashAdvanceClearances != null){
						for(var i=0;i<$scope.cashAdvanceClearance.cashAdvanceClearances.length;i++){
							x += Number($scope.cashAdvanceClearance.cashAdvanceClearances[i].cashAdvance.amount)-Number($scope.cashAdvanceClearance.cashAdvanceClearances[i].cashAdvance.clearAmount)-Number($scope.cashAdvanceClearance.cashAdvanceClearances[i].clearAmount);
						}
					}
					return x;
				}
				$scope.getTotalDrAmount = function(){
					if (typeof ($scope.glEntries) === 'undefined') {
			            return 0;
			        }
					var x = 0;
					if($scope.glEntries.journalDetail != null){
						for(var i=0;i<$scope.glEntries.journalDetail.length;i++){
							if($scope.glEntries.journalDetail[i].drCr == 'Dr')
								x += Number($scope.glEntries.journalDetail[i].amount);
						}
					}
					return x;
				}
				$scope.getTotalCrAmount = function(){
					if (typeof ($scope.glEntries) === 'undefined') {
			            return 0;
			        }
					var x = 0;
					if($scope.glEntries.journalDetail != null){
						for(var i=0;i<$scope.glEntries.journalDetail.length;i++){
							if($scope.glEntries.journalDetail[i].drCr == 'Cr')
								x += Number($scope.glEntries.journalDetail[i].amount);
						}
					}
					return -x;
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
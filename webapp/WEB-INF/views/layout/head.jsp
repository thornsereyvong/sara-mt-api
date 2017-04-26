<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>${title} | BALANCIKA</title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link type="image/png" href="${pageContext.request.contextPath}/resources/images/favicon.png" rel="shortcut icon" />
<link type="text/css"  href="${pageContext.request.contextPath}/resources/plugins/select2/select2.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrapValidator.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/ionicons.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/daterangepicker/daterangepicker-bs3.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/timepicker/bootstrap-timepicker.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/datatables/dataTables.bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/fileinput.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/editor/summernote.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/editor/font-awesome.min.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/editor/summernote.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/sweetalert/sweetalert.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/angular/css/angular-material.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/angular/css/loading-bar.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/toggle/bootstrap-toggle.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/build.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jquery_confirm/jquery-confirm.css">
<style type="text/css">
	.cursor_move{ cursor: move; }
	.width-120{ width: 300px !important; }
	.width-110{ width: 110px !important; }
	.width-100{ width: 100px !important; }
	.width-80{ width: 80px !important; }
	.width-90{ width: 90px !important; }
	.width-94{ width: 94px !important; }
	.requrie{color: #b9292d;}
	.select2{ width: 100%; }
	.iText-right{ text-align:right !important; }
	.dis-number{ text-align:right !important; margin-right: 10px !important; width:120px !important;}
	.iPanel{ margin-top: -25px; }
	.color_msg{ color:#F8BB86 !important; }
	.min-height-300{ height: 300px !important;  }
	.has-error .select2-selection {  border: 1px solid #a94442; border-radius: 2px;}
	.has-success .select2-selection{ border: 1px solid #00a65a; border-radius: 2px;}
	.color_menu { color: #b9292d; }
	.width-75{ width: 75px !important; }
	.cursor-pointer{ cursor: pointer !important; margin-top: -20px !important;}
	.has-error .select2-selection {border: 1px solid #a94442;border-radius: 2px;}
	.has-success .select2-selection{border: 1px solid #00a65a;border-radius: 2px;}

	.imodal-dialog{width: 100%; height: 100%; margin: 0; padding: 0;}
	.imodal-content{height: auto; min-height: 100%; border-radius: 0;}
	.imodal-footer{position: fixed; height: 45px; bottom: 0;width: 100%;}
	.imodal-button{margin-top: -10px !important;}
	.padding-0{padding: 0px !important;}
	.pagination{margin: 5px 0;}			
	@media screen and (max-width:1283px){
		.table-responsive {width: 100%;margin-bottom: 15px;overflow-y: hidden;-ms-overflow-style: -ms-autohiding-scrollbar;border: 1px solid #ddd;}				
		.table-responsive>.table {margin-bottom: 0;}
		.table-responsive>.table>tbody>tr>td, .table-responsive>.table>tbody>tr>th, .table-responsive>.table>tfoot>tr>td, .table-responsive>.table>tfoot>tr>th, .table-responsive>.table>thead>tr>td, .table-responsive>.table>thead>tr>th {white-space: nowrap;}
		.table-responsive>.table-bordered {border: 0;}
		.table-responsive>.table-bordered>tbody>tr>td:first-child, .table-responsive>.table-bordered>tbody>tr>th:first-child, .table-responsive>.table-bordered>tfoot>tr>td:first-child, .table-responsive>.table-bordered>tfoot>tr>th:first-child, .table-responsive>.table-bordered>thead>tr>td:first-child, .table-responsive>.table-bordered>thead>tr>th:first-child {border-left: 0;}
		.table-responsive>.table-bordered>tbody>tr>td:last-child, .table-responsive>.table-bordered>tbody>tr>th:last-child, .table-responsive>.table-bordered>tfoot>tr>td:last-child, .table-responsive>.table-bordered>tfoot>tr>th:last-child, .table-responsive>.table-bordered>thead>tr>td:last-child, .table-responsive>.table-bordered>thead>tr>th:last-child {border-right: 0;}
		.table-responsive>.table-bordered>tbody>tr:last-child>td, .table-responsive>.table-bordered>tbody>tr:last-child>th, .table-responsive>.table-bordered>tfoot>tr:last-child>td, .table-responsive>.table-bordered>tfoot>tr:last-child>th { border-bottom: 0;}
	}
	body.modal-open {
	    overflow: hidden;
	}
	.border-top-none{border-top: 0px !important;}
	.border-top{border-top: 2px solid #ddd !important;}
	.padding-bottom-5{ padding-bottom: 5px !important; }
	.margin-top-5{ margin-top: -5px !important;}
   	.margin-left-5{margin-left: 5px !important;}
	
</style>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.min.js"></script>	
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquerysession.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/angular/angular.js"></script>
	<script src="${pageContext.request.contextPath}/resources/angular/angular-material.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/angular/angular-animate.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/angular/loading-bar.js"></script>
	<script src="${pageContext.request.contextPath}/resources/angular/angular-aria.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/angular/angular-messages.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/angular/FileSaver.js"></script>
	<script src="${pageContext.request.contextPath}/resources/angular/svg-assets-cache.js"></script>
	<script src="${pageContext.request.contextPath}/resources/angular/dirPagination.js"></script>
	
	
	<script src="${pageContext.request.contextPath}/resources/plugins/toggle/bootstrap-toggle.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrapValidator.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/moment.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery-ui.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script src="${pageContext.request.contextPath}/resources/plugins/timepicker/bootstrap-timepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/plugins/daterangepicker/daterangepicker.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/js/jPages.js"></script>
	<script src="${pageContext.request.contextPath}/resources/plugins/select2/select2.full.js"></script>
	<script src="${pageContext.request.contextPath}/resources/dist/sweetalert/sweetalert-dev.js"></script>
	
	
	
    <script src="${pageContext.request.contextPath}/resources/plugins/sparkline/jquery.sparkline.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/plugins/knob/jquery.knob.js"></script>
    
    
    <script src="${pageContext.request.contextPath}/resources/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/plugins/fastclick/fastclick.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/dist/js/app.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/dist/js/demo.js"></script>
    
    <script src="${pageContext.request.contextPath}/resources/js.mine/function.mine.js"></script>
  	<script>
    	$(function () {    	 
   	  		$(".select2").select2();
   	  		$(".select2-small").select2({ minimumResultsForSearch: Infinity}); //{ minimumResultsForSearch: Infinity}    	  
    	  	$(".date").daterangepicker();
    	  	
    	  	$('.table-responsive').on('show.bs.dropdown', function () {
	     		$('.table-responsive').css( "overflow", "inherit" );
   			});
   	
   			$('.table-responsive').on('hide.bs.dropdown', function () {
	     		$('.table-responsive').css( "overflow", "auto" );
   			}); 

      	}); 
       String.prototype.trunc = String.prototype.trunc ||  function(n){
           return this.length>n ? this.substr(0,n-1)+'...' : this.toString();
       };
    </script>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>       

<html>
  <head>
    <title>
      Service Data
    </title>
   <link type="text/css" rel="stylesheet" href="stylesheet.css">
   <link type="text/css" rel="stylesheet" href="bootstrap-2.3.2/css/bootstrap.min.css">
   <link type="text/css" rel="stylesheet" href="jasny-bootstrap/css/bootstrap.min.css">

	<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.min.js" defer></script>
	<script type="text/javascript" src="bootstrap-2.3.2/js/bootstrap.min.js" defer></script>
	<script type="text/javascript" src="jasny-bootstrap/js/bootstrap.min.js" defer></script>
   
   </head>
  <body onload="init()">
  
  <div class="navbar navbar-inverse navbar-static-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="brand" href="http://172.31.104.12/serviceData"><i class="iconic-document-alt-stroke"></i>&nbsp;Service Data</a>
			
				<ul class="nav pull-right">
					<!-- Apps -->
					<a href="#" class="btn btn-inverse" data-toggle="dropdown"><i class="icon-th icon-white"></i></a>
					<ul class="dropdown-menu">
						<li>
							<a href="http://172.31.104.12/sts2"><i class="iconic-beaker"></i>&nbsp;STS2</a>
						</li>
						<li>
							<a href="http://172.31.104.12/pts"><i class="iconic-layers"></i>&nbsp;PTS</a>
						</li>
						<li>
							<a href="http://172.31.104.12/seqDB"><i class="iconic-box"></i>&nbsp;SeqDB</a>
						</li>
						<li class="disabled">
							<a href="http://172.31.104.12/serviceData"><i class="iconic-document-alt-stroke"></i>&nbsp;Service Data</a>
						</li>
						<li>
							<a href="http://172.31.104.12/ap"><i class="iconic-aperture"></i>&nbsp;Analysis Portal</a>
						</li>
					</ul>
				</ul>
			</div>
		</div>
		
		<%@ include file="/WEB-INF/jsp/includes/success.jsp"%>
		
	</div>
   
   <div class="container">
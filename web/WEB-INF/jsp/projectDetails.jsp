<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>

<div class="row">
<div class="span12">

<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>

<h2>Project Details</h2>

<div class="form-horizontal">

	<div class="control-group">
		<label class="control-label">Project Name:</label>
			<div class="controls">
				<input class="input-xlarge" type="text" name="name" value="<c:out value="${command.name}" />" readonly=""/>
			</div>
	</div>

	<div class="control-group">
		<label class="control-label">Note:</label>
			<div class="controls">
				<input class="input-xlarge" type="text" name="note" value="<c:out value="${command.note}" />" readonly="" />
			</div>
	</div>

	<div class="control-group">
		<div class="controls">

		<a class="btn" href="<c:url value="/index.htm">
	<c:param name="projectId" value="${command.projectId}"/></c:url>">Edit</a>

<a class="btn" href="<c:url value="/archiveProject.htm">
	<c:param name="projectId" value="${command.projectId}"/></c:url>" >
	Archive</a>

<a class="btn" href="<c:url value="/deleteProject.htm">
	<c:param name="projectId" value="${command.projectId}"/></c:url>" 
	onclick="return (confirm('Are you sure you want to delete this project?
	 Read the warning carefully before you confirm!')) ">Delete</a> 

		</div>
	</div>
	   
 </div>
	   	 
	 <font color="red">Warning:</font> Delete this project will also delete
	  all the results related to this project. 
	  Please archive it before deletion. 
	  
</div>
</div>

<div class="row">
<div class="span6">	  
<a class="btn" href="<c:url value="/loadResults.htm">
		<c:param name="projectId" value="${command.projectId}"/></c:url>">
		<b>Load Results</b></a>
		
<a class="btn" href="<c:url value="/exportResults.htm">
		<c:param name="projectId" value="${command.projectId}"/></c:url>">
		<b>Export Results</b></a> 

</div>
</div>

<%@ include file="/WEB-INF/jsp/includes/projectRunDateList.jsp" %>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>
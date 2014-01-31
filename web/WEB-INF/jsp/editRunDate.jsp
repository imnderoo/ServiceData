<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>

<div class="row">
<div class="span6">
<h2>Edit Note</h2>

<form class="form-horizontal"  method="post" enctype="multipart/form-data">

	<div class="control-group">
		<label class="control-label">Project:</label>
			<div class="controls">
				<input class="input-xlarge" type="text" name="projectName" value="<c:out value="${SelectedProject.name}" />" readonly=""/>
				 <input type="hidden" name="projectId" value="<c:out value="${SelectedProject.projectId}"/>" />
			</div>
	</div>
	
	<div class="control-group">
		<label class="control-label">Load Result Time:</label>
			<div class="controls">
				<spring:bind path="command.date">
					<input class="input-xlarge" type="text" name="date" value="<c:out value="${status.value}" />" readonly=""/>
				</spring:bind> 
			</div>
	</div>

	<div class="control-group">
		<label class="control-label">Note:</label>
			<div class="controls">
				<spring:bind path="command.note">
					<input class="input-xlarge" type="text" name="date" value="<c:out value="${status.value}" />" />
				</spring:bind> 
			</div>
	</div>

	<div class="control-group">
		<div class="controls">
    		<input class="btn" type="submit" name="action" value="Save">
		</div>
	</div>
</form>

</div>
</div>

<div class="row">
<div class="span6">
  
  <h2>Assay List</h2>
  
  <form class="form-horizontal" action="deleteAssay.htm" method="post" 
  	onsubmit="return validateAndSubmit(); ">
  	
  	<SCRIPT LANGUAGE="javascript">
function validateAndSubmit(){	
 return confirm("Are you sure you want to delete this assay's results of this RUN?");
 }
</SCRIPT>

  	<input type="hidden" name="runId"
           	value="<c:out value="${runId}"/>" />
           	      
	<div class="control-group">
	<div class="controls">
			           	          	
  	<table class="details">
  		<tr><th>Assay Name</th><th></th></tr>
	  	<c:forEach items="${uniAssayList}" var="assay">
    		<tr> 	<td>
    			<c:out value="${assay}"/>    		 
    		</td>   <td>
    	 		<input type="checkbox" name="assay" 
    	 			value="<c:out value="${assay}"/>"/>
    		</td>   </tr>
    	</c:forEach>
    	<tr>       <td colspan="2">
          <input class="btn" type="submit" name="delete" value="Delete Assay Result">
        </td>      </tr>
  	</table>
  	
  	</div>
  	</div>
  </form>
  
</div>
</div>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>
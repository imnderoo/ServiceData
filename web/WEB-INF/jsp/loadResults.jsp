<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>

<div class="row">
<div class="span8">
<h2>Load Results</h2>

<form class="form-horizontal"  method="post" enctype="multipart/form-data">

	<div class="control-group">
		<label class="control-label">Project:</label>
			<div class="controls">
				<input class="input-xlarge" type="text" name="projectName" value="<c:out value="${SelectedProject.name}" />" readonly=""/>
				<input type="hidden" name="projectId" value="<c:out value="${SelectedProject.projectId}"/>" />
			</div>
	</div>
	
	<h3>Result File Format</h3>
	
	<div class="control-group">
		<label class="control-label">Sample ID Column #:</label>
			<div class="controls">
				<select class="input-mini" name="sampleIdColumnNo" >
			    	<c:forEach items="${allColumnNumbers}" var="colNo">
	           	 		<option value="<c:out value="${colNo}"/>"><c:out value="${colNo}"/></option>
	           		</c:forEach>
	       		</select> 
			</div>
	</div>
	
	<div class="control-group">
		<label class="control-label">Assay Name Column #:</label>
			<div class="controls">
				<select  class="input-mini" name="assayNameColumnNo" >
			    	<c:forEach items="${allColumnNumbers}" var="colNo">
	              		<option value="<c:out value="${colNo}"/>"><c:out value="${colNo}"/></option>
	            	</c:forEach>
	       		</select> 
			</div>
	</div>
	
	<div class="control-group">
		<label class="control-label">Result Column #:</label>
			<div class="controls">
			<select class="input-mini" name="resultColumnNo" >
			    <c:forEach items="${allColumnNumbers}" var="colNo">
	              <option value="<c:out value="${colNo}"/>"><c:out value="${colNo}"/></option>
	            </c:forEach>
	       </select>
			</div>
	</div>
	
	<div class="control-group">
			<div class="controls">
				<label class="checkbox">
					<input type="checkbox" name="MutiAssay" value="yes" />
					Multiple Assays</label>
			</div>
	</div>
	
	<div class="control-group">
		<label class="control-label">Upload Result File:</label>
			<div class="controls">
				<div class="fileupload fileupload-new" data-provides="fileupload">
			<div class="input-append">
				<span class="uneditable-input fileupload-preview input-medium"></span> <span class="btn btn-file"> <span class="fileupload-new">Select
						file</span> <span class="fileupload-exists">Change</span> <input type="file" name="file" /></span><a href="#"
					class="btn fileupload-exists" data-dismiss="fileupload">Remove</a>
			</div>
		</div>
				<!-- <input type="file" name="file"/> -->
			</div>
	</div>
	
	<div class="control-group">
		<label class="control-label">Note:</label>
			<div class="controls">
          		<input type="text" name="note" />
			</div>
	</div>

	<div class="control-group">
		<div class="controls">
		   	<input class="btn" type="submit" name="action" value="Load">
    	    <input class="btn" type="reset" name="Submit2" value="Reset">
		</div>
	</div>
    
</form>

<br>

<h3>Mutiple assays file sample</h3>

<table>
<tr><td>Sample ID</td><td>rs2241715</td><td>rs1982072</td><td>rs12602</td><td>rs4803462</td><td>rs2316973</td></tr>
<tr><td>JS1018</td><td>GG</td><td>TT</td><td>GG</td><td>AA</td><td>CC</td></tr>
<tr><td>JS1020</td><td>GG</td><td>0</td><td>GG</td><td>0</td><td>CC</td></tr>
<tr><td>JS1025</td><td>GG</td><td>TT</td><td>GG</td><td>AA</td><td>CC</td></tr>
<tr><td>JS1031</td><td>GG</td><td>TT</td><td>GG</td><td>AA</td><td>CC</td></tr>
</table>

</div>
</div>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>
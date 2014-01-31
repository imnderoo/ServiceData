<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>

<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp" %>

<h2>Export Result</h2>

<form class="form-horizontal"  method="post" enctype="multipart/form-data">

	<div class="control-group">
		<label class="control-label">Project:</label>
			<div class="controls">
				<input class="input-xlarge" type="text" name="projectName" value="<c:out value="${SelectedProject.name}" />" readonly=""/>
				<input type="hidden" name="projectId" value="<c:out value="${SelectedProject.projectId}"/>" />
			</div>
	</div>


	<div class="control-group">
		<div class="controls">
			<label class="checkbox">
				<input type="checkbox" name="linkageFormat" value="yes"> Linkage format
			</label>
		</div>
	</div>
	
	<h3>Select Run To Export</h3>


<div class="control-group">
		<div class="controls">
	
	<table class="span6 table table-striped table-condensed">
	<tr>
		<th>Result Load Time</th>
		<th>Note</th>
		<th>Edit</th>
	</tr>
    <c:forEach items="${uniRunDates}" var="date">
    <tr> 
    	<td>
			<label class="checkbox">
    			<input type="checkbox" name="SelectedRD" value="<c:out value="${date.date}"/>" />
    			<a href="<c:url value="/runDetails.htm">
    			<c:param name="projectId" value="${SelectedProject.projectId}"/>
    			<c:param name="runDate" value="${date.date}"/></c:url>">
    			<c:out value="${date.date}"/></a>
    		</label>
    	</td>
    	
    	<td>
	    	<c:out value="${date.note}"/>
    	</td>
    	
    	<td>
    		<a href="<c:url value="/editRunDateNote.htm">
    		<c:param name="runId" value="${date.runId}"/></c:url>">
    		<i class="icon-edit"></i></a>    		 
    	</td>
    </tr>
    </c:forEach>
   
  </table>

</div>
</div>

	<div class="control-group">
		<div class="controls">
          <input class="btn" type="submit" name="action" value="Export">
          <input class="btn" type="reset" name="Submit2" value="Reset">
      </div>
      </div> 
      
     	<!-- Just export everything. Filter using Excel instead. Keeping this so I don't have to change the code. -->
    <input class="hidden" type="file" name="file"/>
            
</form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>
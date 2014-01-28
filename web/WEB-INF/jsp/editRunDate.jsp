<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>

<tr><td>

<p><h2>Edit Note</h2></p>

<form method="post" enctype="multipart/form-data">
  <table border="0" class="details">
    
	<tr> 
          <td>Project: </td>
          <td> <c:out value="${SelectedProject.name}"/>
            <input type="hidden" name="projectId"
           	value="<c:out value="${SelectedProject.projectId}"/>" />
       	  </td>
    </tr>

	<tr> 
          <td>Load Result Time: </td>
          <td> 
          <spring:bind path="command.date">
             <INPUT type="text" READONLY maxlength="255" size="30" name="date" 
                 value="<c:out value="${status.value}"/>">
		  </spring:bind> 
       </td>
    </tr>
 
	<tr> 
          <td>Note: </td>
          <td> 
          <spring:bind path="command.note">
             <INPUT type="text" maxlength="255" size="30" name="note" 
                 value="<c:out value="${status.value}"/>">
		  </spring:bind> 
       </td>
    </tr>
    
    <tr> 
      <td colspan="2">
          <input type="submit" name="action" value="Save">
        </td>
    </tr>
  </table>
  </form>
  <br><br>
  <h3>Assay List</h3>
  <form action="deleteAssay.htm" method="post" 
  	onsubmit="return validateAndSubmit(); ">
  	
  	<SCRIPT LANGUAGE="javascript">
function validateAndSubmit(){	
 return confirm("Are you sure you want to delete this assay's results of this RUN?");
 }
</SCRIPT>

  	<input type="hidden" name="runId"
           	value="<c:out value="${runId}"/>" />
  	<table width="70%" border="0" class="details">
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
          <input type="submit" name="delete" value="Delete Assay Result">
        </td>      </tr>
  	</table>
  </form>
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>
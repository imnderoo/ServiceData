<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<br>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>
<%@ include file="/WEB-INF/jsp/includes/errorMessage.jsp" %>

<h2>Export Result (List file should be tab delimited text only)</h2>

<form method="post" enctype="multipart/form-data">
  <table width="70%" class="details">
   
	<tr> 
          <td>Project: </td>
          <td> 
          <c:out value="${SelectedProject.name}"/>
          <input type="hidden" name="projectId"
           	value="<c:out value="${SelectedProject.projectId}"/>" />
<!--             	
          <select name="projectId" size="1">
          
    	 <c:forEach items="${projectList}" var="project">
    	     <option 
			 
			 value="<c:out value="${project.projectId}"/>"><c:out value="${project.name}"/></option>
    	  </c:forEach>
          </select>
-->       
       </td>
    </tr>

	<tr> 
	 <td>
		Upload a sampleId list file:</td><td>
           <input type="file" name="file"/>
      </td>
    </tr>

	  <tr> 
          <td colspan="2">
			 <input type="checkbox" name="linkageFormat" value="yes"> Linkage format <br>
          </td>
      </tr>
      
    <tr><td>
    </td></tr>
    <c:forEach items="${uniRunDates}" var="date">
    <tr> 
    	<td>
    	<a href="<c:url value="/runDetails.htm">
    		<c:param name="projectId" value="${SelectedProject.projectId}"/>
    		<c:param name="runDate" value="${date.date}"/></c:url>">
    		<c:out value="${date.date}"/></a>
    		<input type="checkbox" name="SelectedRD" value="<c:out value="${date.date}"/>" />
    	</td>
    	<td>
    	<a href="<c:url value="/editRunDateNote.htm">
    		<c:param name="runId" value="${date.runId}"/></c:url>">
    		<c:out value="${date.note}"/>&nbsp;&nbsp;&nbsp; </a>    		 
    	</td>
    </tr>
    </c:forEach>
   
  </table>

	<tr> 
      <td colspan="2">
        <p>
          <input type="submit" name="action" value="Export">
          <input type="reset" name="Submit2" value="Reset">
        </p>
        </td>
    </tr>
    
  

    </table>
</form>

</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>
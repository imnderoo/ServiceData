<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>

<p><h2>Delete Results</h2></p>

<form method="post" >
  <table border="0" class="details">
    
	<tr> 
          <td>Project: </td>
          <td> 
          
          <select name="projectId" size="1">
          
    	 <c:forEach items="${projectList}" var="project">
    	     <option 
			 
			 value="<c:out value="${project.projectId}"/>"><c:out value="${project.name}"/></option>
    	  </c:forEach>
          </select>
       
       </td>
    </tr>

	<tr><td>
	GeneChip:
	</td>
	<td>
	 <select name="geneChipId" size="1">
          
    	 <c:forEach items="${geneChipList}" var="geneChip">
    	     <option 
			 
			 value="<c:out value="${geneChip.geneChipId}"/>"><c:out value="${geneChip.name}"/></option>
    	  </c:forEach>
          </select>
	</td></tr>

	<tr> 
	  <td>SampleId:</td>
      <td> 
    
	  <INPUT type="text" maxlength="255" size="30" name="sampleUserId" > 
	 
	</td>
	</tr>

	<tr> 
      <td colspan="2">
        <p>
          <input type="submit" name="action" value="Delete">
          <input type="reset" name="Submit2" value="Reset">
        </p>
        </td>
    </tr>
  </table>
  </form>

</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>
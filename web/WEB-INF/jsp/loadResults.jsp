<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>

<p><h2>Load Results</h2></p>

<form method="post" enctype="multipart/form-data">
  <table border="0" class="details">
    
	<tr> 
          <td>Project: </td>
          <td> <c:out value="${SelectedProject.name}"/>
          <input type="hidden" name="projectId"
           	value="<c:out value="${SelectedProject.projectId}"/>" />
		 <!--<select name="projectId" size="1">
    	 <c:forEach items="${projectList}" var="project">
    	     <option value="<c:out value="${project.projectId}"/>"><c:out value="${project.name}"/></option>
    	  </c:forEach> </select>-->       
       </td>
    </tr>

	<tr> 
          <td>Result file format</td>
          <td> 
          		<table> <tr> <td>SampleId column number:</td><td>
			<select name="sampleIdColumnNo" >
			    <c:forEach items="${allColumnNumbers}" var="colNo">
	              <option value="<c:out value="${colNo}"/>"><c:out value="${colNo}"/></option>
	            </c:forEach>
	       </select>  </td></tr>
	       <tr><td>
		   Assay Name column number:</td><td>
			<select name="assayNameColumnNo" >
			    <c:forEach items="${allColumnNumbers}" var="colNo">
	              <option value="<c:out value="${colNo}"/>"><c:out value="${colNo}"/></option>
	            </c:forEach>
	       </select>  </td></tr>
	       <tr><td>
		   Result column number:</td><td>
			<select name="resultColumnNo" >
			    <c:forEach items="${allColumnNumbers}" var="colNo">
	              <option value="<c:out value="${colNo}"/>"><c:out value="${colNo}"/></option>
	            </c:forEach>
	       </select></td></tr>
	       <tr><td>*Mutiple assays</td>
	       		<td><input type="checkbox" name="MutiAssay" value="yes" /></td></tr>
	       
			</table>
		  </td>
    </tr>

	 <tr> 
          <td>Upload Result file:</td>
          <td>
             <input type="file" name="file"/>
          </td>
    </tr>
 
	<tr> 
          <td>Note: </td>
          <td>  
          <input type="text" name="note"	 />
       </td>
    </tr>
    
    <tr> 
      <td colspan="2">
        <p>
          <input type="submit" name="action" value="Load">
          <input type="reset" name="Submit2" value="Reset">
        </p>
        </td>
    </tr>
  </table>
  </form>
<br>
*Mutiple assays file sample:<br>
<table>
<tr><td>Sample ID</td><td>rs2241715</td><td>rs1982072</td><td>rs12602</td><td>rs4803462</td><td>rs2316973</td></tr>
<tr><td>JS1018</td><td>GG</td><td>TT</td><td>GG</td><td>AA</td><td>CC</td></tr>
<tr><td>JS1020</td><td>GG</td><td>0</td><td>GG</td><td>0</td><td>CC</td></tr>
<tr><td>JS1025</td><td>GG</td><td>TT</td><td>GG</td><td>AA</td><td>CC</td></tr>
<tr><td>JS1031</td><td>GG</td><td>TT</td><td>GG</td><td>AA</td><td>CC</td></tr>
</table>
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>
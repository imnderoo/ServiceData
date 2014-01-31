<div class="row">
<div class="span8">
<h2> Project List </h2>
  <table class="table table-condensed table-striped">
    <tr>
    	<th>Project Name</th>
    	<th>Note</th>
    </tr>
    
    <c:forEach items="${allProjects}" var="project">
    <tr> 
    	<td>
    	<a href="<c:url value="/projectDetails.htm"><c:param name="projectId" value="${project.projectId}"/></c:url>"><c:out value="${project.name}"/></a>
    	</td>
    	<td><c:out value="${project.note}"/>&nbsp;</td>
        
    </tr>
    </c:forEach>
   
  </table>
  </div>
</div
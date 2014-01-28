<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>
<%@ include file="/WEB-INF/jsp/includes/success.jsp" %>
<p><h2>Project Details</h2>
<a href="<c:url value="/index.htm">
	<c:param name="projectId" value="${command.projectId}"/></c:url>">Edit it</a>
	&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
	&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
	&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
	&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
	<a href="<c:url value="/loadResults.htm">
		<c:param name="projectId" value="${command.projectId}"/></c:url>">
		<b>Load Results</b></a>
	<a href="<c:url value="/exportResults.htm">
		<c:param name="projectId" value="${command.projectId}"/></c:url>">
		<b>Export Results</b></a> 
<br>
<a href="<c:url value="/archiveProject.htm">
	<c:param name="projectId" value="${command.projectId}"/></c:url>" >
	Archive this project</a> <br>
<a href="<c:url value="/deleteProject.htm">
	<c:param name="projectId" value="${command.projectId}"/></c:url>" 
	onclick="return (confirm('Are you sure you want to delete this project?
	 Read the warning carefully before you confirm!')) ">Delete it</a> &nbsp;
	 <font color="red">Warning:</font> Delete this project will also delete
	  all the results related to this project. 
	  Please archive it before deletion. <br>

<p>

  <table width="60%" border="0" class="details">
    <tr> 
      <td>Project Name:</td>
      <td> 
      <c:out value="${command.name}"/>&nbsp;
      </td>
    </tr>
    <tr> 
      <td>Note:</td>
      <td> 
      <c:out value="${command.note}"/>&nbsp;
      </td>
    </tr>
  </table>

<%@ include file="/WEB-INF/jsp/includes/projectRunDateList.jsp" %>

</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>
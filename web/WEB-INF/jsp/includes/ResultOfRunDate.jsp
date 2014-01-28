<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>

<a href="<c:url value="/deleteResults.htm"><c:param name="projectId" 
	value="${projectId}"/><c:param name="date" value="${date}"/></c:url>" 
	onclick="return (confirm('Are you sure you want to delete these results?' )) " >
	Delete These Results</a>


<h3>Results:</h3>
  <table width="70%" border="0" class="details">
    <c:out value="${tables}" escapeXml="false" />
  </table>

</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>
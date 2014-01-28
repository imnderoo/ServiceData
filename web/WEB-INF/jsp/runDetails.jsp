<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>

<tr><td>

<a href="<c:url value="/deleteResults.htm">
	<c:param name="projectId" value="${projectId}"/>
	<c:param name="date" value="${date}"/></c:url>
	" onclick="return (confirm('Are you sure you want to delete these results?' )) " >
	Delete These Results</a>

<h3>Results:</h3>
  <table width="70%" border="0" class="details">
     <tr><th></th>
     	<c:forEach items="${assays}" var="assays">
     		<th><c:out value="${assays}"/></th>
     	</c:forEach>
     </tr>
     
     <c:set var="itemNumber" value="0" />
     <c:forEach items="${sampleIds}" var="sampleId">
     	<tr> <td><c:out value="${sampleId}"/></td>
     		<c:forEach items="${results[itemNumber]}" var="result">
     			<td><a href="editResult.htm?resultId=<c:out value="${result.resultId}"/>">
     				<c:out value="${result.result}"/></a>
     			</td>
     		</c:forEach>
     		<c:set var="itemNumber" value="${itemNumber+1}" />
     	</tr>
     </c:forEach>     
  </table>

</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>
<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>
<div class="row">
<div class="span10">

<h3>Results:</h3>

<a class="btn" href="<c:url value="/deleteResults.htm">
	<c:param name="projectId" value="${projectId}"/>
	<c:param name="date" value="${date}"/></c:url>
	" onclick="return (confirm('Are you sure you want to delete these results?' )) " >
	Delete These Results</a>

	<p>

  <table class="details">
     <tr><th></th>
     	<c:forEach items="${assays}" var="assays">
     		<th class="nano-text"><c:out value="${assays}"/></th>
     	</c:forEach>
     </tr>
     
     <c:set var="itemNumber" value="0" />
     <c:forEach items="${sampleIds}" var="sampleId">
     	<tr> <td class="small-text"><c:out value="${sampleId}"/></td>
     		<c:forEach items="${results[itemNumber]}" var="result">
     			<td><a href="editResult.htm?resultId=<c:out value="${result.resultId}"/>">
     				<c:out value="${result.result}"/></a>
     			</td>
     		</c:forEach>
     		<c:set var="itemNumber" value="${itemNumber+1}" />
     	</tr>
     </c:forEach>     
  </table>

</div>
</div>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>
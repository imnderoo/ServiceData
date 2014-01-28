<h3> Result List </h3>
 
 
  <table width="70%" border="0" class="details">
    <tr>
    	<th>Result Load Time</th>
    	<th>Note</th>
    	
    </tr>
    
    <c:forEach items="${uniRunDates}" var="date">
    <tr> 
    	<td>
    	<a href="<c:url value="/runDetails.htm">
    		<c:param name="projectId" value="${command.projectId}"/>
    		<c:param name="runDate" value="${date.date}"/></c:url>">
    		<c:out value="${date.date}"/></a>    		 
    	</td>
    	
    	<td>
    	<a href="<c:url value="/editRunDateNote.htm">
    		<c:param name="runId" value="${date.runId}"/></c:url>">
    		<c:out value="${date.note}"/>&nbsp;&nbsp;&nbsp; </a>    		 
    	</td>
    </tr>
    </c:forEach>
   
  </table>

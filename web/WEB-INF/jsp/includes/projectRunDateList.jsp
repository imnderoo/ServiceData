<div class="row">
<div class="span6">
<h2> Result List </h2>

  <table class="table table-condensed table-striped">
    <tr>
    	<th>Result Load Time</th>
    	<th>Note</th>
    	<th>Edit</th>
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
	    	<c:out value="${date.note}"/>
    	</td>
    	<td>
    		<a href="<c:url value="/editRunDateNote.htm">
    		<c:param name="runId" value="${date.runId}"/></c:url>">
    		<i class="icon-edit"></i></a>    		 
    	</td>
    </tr>
    </c:forEach>
  </table>
</div>
</div>
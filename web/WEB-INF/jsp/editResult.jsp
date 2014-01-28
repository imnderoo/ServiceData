<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>

<p><h2>Edit /New project</h2></p>
<form method="post">
  <table border="0" class="details">
    <tr> 
      <td>SampleId : </td>
      <td> <c:out value="${command.sampleId}"/>
      </td>
    </tr>

	<tr> 
      <td>Assay : </td>
      <td> <c:out value="${command.assay}"/>
      </td>
    </tr>

	 <tr> 
      <td>Result: </td>
      <td> 
        <spring:bind path="command.result">
           <INPUT type="text" maxlength="255" size="30" name="result" 
           value="<c:out value="${status.value}"/>">
	</spring:bind>
      </td>
    </tr>
   
    <tr> 
      <td colspan="2">
        <p>
          <input type="submit" name="Submit" value="Save">
          <input type="reset" name="Submit2" value="Reset">
        </p>
        </td>
    </tr>
  </table>
  </form>
</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>
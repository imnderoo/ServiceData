<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>

<p><h2>Edit /New project</h2></p>
<form method="post">
  <table border="0" class="details">
    <tr> 
      <td>Project Name:</td>
      <td> 
        <spring:bind path="command.name">
	  <INPUT type="text" maxlength="255" size="30" name="name" 
	  	value="<c:out value="${status.value}"/>" > 
	  <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		</FONT>
	</spring:bind>
	
      </td>
    </tr>
  
    <tr> 
      <td>Note: </td>
      <td> 
        <spring:bind path="command.note">
           <INPUT type="text" maxlength="255" size="30" name="note" value="<c:out value="${status.value}"/>">
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

<%@ include file="/WEB-INF/jsp/includes/projectList.jsp" %>
  

</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>
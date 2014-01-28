<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>


<tr><td>

<p><h2>Edit /New genechip</h2></p>

<form method="post" enctype="multipart/form-data">
  <table border="0" class="details">
    <tr> 
      <td>Genechip Name:</td>
      <td> 
        <spring:bind path="command.name">
	  <INPUT type="text" maxlength="255" size="30" name="name" value="<c:out value="${status.value}"/>" > 
	  <FONT color="red">
		    <B><c:out value="${status.errorMessage}"/></B>
		</FONT>
	</spring:bind>
	</td>
	</tr>

	<tr> 
          <td>Upload SNPs file:</td>
          <td>
             <input type="file" name="file"/>
          </td>
    </tr>
	
  
  
   
   
   
    <tr> 
      <td colspan="2">
        <p>
          <input type="submit" name="action" value="Save">
          <input type="reset" name="Submit2" value="Reset">
        </p>
        </td>
    </tr>
  </table>
  </form>

 <%@ include file="/WEB-INF/jsp/includes/geneChipListBody.jsp" %>
  

</td></tr>
<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>
<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>
<div class="row">
<div class="span8">

<h2>Edit / New project</h2>

<form class="form-horizontal" method="post">

	<div class="control-group">
		<label class="control-label">Project Name:</label>
			<div class="controls">
		        <spring:bind path="command.name">
				<input class="input-xlarge" type="text" name="name" value="<c:out value="${status.value}" />" />
			  	<font color="red"><b><c:out value="${status.errorMessage}"/></b></font>
		  		</spring:bind>
			</div>
	</div>

	<div class="control-group">
		<label class="control-label">Note:</label>
			<div class="controls">
		        <spring:bind path="command.note">
				<input class="input-xlarge" type="text" name="note" value="<c:out value="${status.value}" />" />
		  		</spring:bind>
			</div>
	</div>
	
	<div class="control-group">
		<div class="controls">
			
          <input class="btn" type="submit" name="Submit" value="Save">
          <input class="btn" type="reset" name="Submit2" value="Reset">

		</div>
	</div>
  </form>

<%@ include file="/WEB-INF/jsp/includes/projectList.jsp" %>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>

<%@ include file="/WEB-INF/jsp/includes/head.jsp" %>
<div class="row">
<div class="span6">
<h2>Edit /New project</h2>
<form class="form-horizontal" method="post">

	<div class="control-group">
		<label class="control-label">SampleId:</label>
			<div class="controls">

				<input class="input-medium" type="text" name="name" value="<c:out value="${command.sampleId}" />" readonly=""/>


			</div>
	</div>
	
		<div class="control-group">
		<label class="control-label">Assay:</label>
			<div class="controls">
				<input class="input-medium" type="text" name="name" value="<c:out value="${command.assay}" />" readonly=""/>
			</div>
	</div>
	
		<div class="control-group">
		<label class="control-label">Result:</label>
			<div class="controls">
		        <spring:bind path="command.result">
				<input class="input-medium" type="text" name="name" value="<c:out value="${status.value}" />" />
		  		</spring:bind>
			</div>
		</div>
	
		<div class="control-group">
			<div class="controls">
	
          <input class="btn" type="submit" name="Submit" value="Save">
          <input class="btn" type="reset" name="Submit2" value="Reset">

			<div>
		</div>
  </form>

<%@ include file="/WEB-INF/jsp/includes/foot.jsp" %>
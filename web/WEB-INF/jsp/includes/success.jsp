<c:if test="${not empty message}">
	<div class="page-alert">
			<div class="alert alert-info" role="status">
				<c:out value="${message}" />
			</div>
		</div>
</c:if>
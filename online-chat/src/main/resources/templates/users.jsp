<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="layout/header.jsp">
	<jsp:param value="Users" name="title" />
	<jsp:param value="users" name="page" />
</jsp:include>

<h2>Users:</h2>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-bordered table-striped table-hover">
	<thead>
		<tr>
			<th>name</th>
			<th>enabled</th>
			<th>roles</th>
			<th>remove</th>
			<th>edit</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="user">
			<tr>
				<td>${user.name}</td>
				<td>${user.enabled}</td>
				<td>
					<c:forEach items="${user.userRoles}" var="ur">
						${ur.role.name}
						<br/>
					</c:forEach>
				</td>
				<td>
					<a href="users/delete.html?id=${user.id}" 
					   class="btn btn-primary btn-danger btn-remove">remove</a>
				</td>
				<td>
					<a href="users/edit.html?id=${user.id}" 
					   class="btn btn-primary">edit</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<script type="text/javascript">

$(document).ready(function() {
	$(".btn-remove").click(function(e) {
		// do not remove just yet :-)
		e.preventDefault();
		// pass URL to dialog
		$("#myModal .btn-danger").attr("href", $(this).attr("href"));
		// show dialog
		$('#myModal').modal();
	});
});
	
</script>


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Really remove?</h4>
      </div>
      <div class="modal-body">
        Do you really want to remove this user? 
        There's no going back!
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <a type="button" class="btn btn-danger" href="">remove</a>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<jsp:include page="layout/footer.jsp" />

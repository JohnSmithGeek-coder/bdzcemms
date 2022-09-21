<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table id="user-table" class="table table-striped">
    <thead>
        <tr>
            <th>使用人编号</th>
            <th>使用人</th>
        </tr>
	</thead>
	<tbody>
		<c:forEach var="user" items="${listUser}">
			<tr class="user-tr">
				<td class="number" ><c:out value="${user.number}"/></td>
				<td class="name"><c:out value="${user.name}"/></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<script src="javascripts/user-table.js"></script>
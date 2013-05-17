<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<tr>
	<td></td>
	<td></td>
	<td>${good.producer}</td>
	<td>${good.model}</td>
	<td>${good.dateOfIssue}</td>
	<td>${good.color}</td>
	<td>
	    <c:choose>
			<c:when test="${good.notInStock}">
			    not in stock
			</c:when>
			<c:otherwise>
			    ${good.price}
			</c:otherwise>
		</c:choose>
	</td>
</tr>


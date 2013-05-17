<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<tr>
	<td></td>
	<td>${subcategory.name}</td>
</tr>
<c:forEach var="good" items="${subcategory.goods}">
	<%@ include file="good.jsp"%>
</c:forEach>

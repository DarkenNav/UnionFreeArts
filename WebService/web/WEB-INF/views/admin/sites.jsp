<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>Sites</title>
</head>

<body>
<c:if test="${not empty sites}">
<table border = "1">
	<tr>
		<td>Сайт</td>
		<td>Действия</td>
	</tr>
	<c:forEach var="listValue" items="${sites}">
	<tr valign = "top">
		<td>${listValue.name}</td>
		<td>Remove Edit</td>
	</tr>
	</c:forEach>
</table>
</c:if>
<form method="post" modelAttribute="newSite" action="/newSite">

	Name: <input  type = "text" name = "name" value = "${site.name}"/><br/>
	<button>Submit</button>

</form>
<form method="delete" modelAttribute="removeSite" action="/removeSite">

	Name: <input  type = "text" name = "name" value = "${site.name}"/><br/>
	<button>Submit</button>
</form>

</body>

</html>
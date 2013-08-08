<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ include file="/WEB-INF/views/include/header/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Success</title>
</head>
<body>
  <c:set var="key" value="${key}" />
  <c:set var="value" value="${value}" />
  <h2 style="margin-bottom: 10px; padding-bottom: 0px;">${key} is updated successfully!</h2> 
  <c:if test="${key != 'password'}">
     <h3 style="margin-bottom: 10px; padding-bottom: 0px;">New ${key} : ${value}</h3>   
  </c:if>    
  <a href="<spring:url value="/truconnect/index"/>">Toolkit Menu</a>
</body>
</html>
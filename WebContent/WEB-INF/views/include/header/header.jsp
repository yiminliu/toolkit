
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <head>
    <title>TruConnect and Web on the Go Admin Toolkit</title>
       <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
       <meta http-equiv="Content-Style-Type" content="text/css" />
       <meta http-equiv="Content-Language" content="en-us" />
       <!--[if !IE 7]>
          <style type="text/css">
             #wrapper {display:table;height:100%}
          </style>
       <![endif]-->
  </head>
  <body>
    <c:url var = "logoutUrl" value="j_spring_security_logout" />
    <c:url var="loginUrl" value="/j_spring_security_check" />
    <!--<c:url var = "logoutUrl" value="/logout" />-->
    <div style="text-align: right;"">
      <!-- <security:authorize access="isAnonymous()">
         <form action="${loginUrl}" method="post">
            Username: <input type="text" name="j_username" /> &nbsp;
            Password: <input type="password" name="j_password" /> &nbsp;
            <input type="submit" value="Log in" />
         </form>
      </security:authorize>
      -->
      <sec:authorize access="isAuthenticated()">
        Welcome: 
        <sec:authentication property = "principal.username" />
        </br>
        <a href="${logoutUrl}"> Logout</a>
      </sec:authorize>   
    </div>    
      <!-- 
         <c:if test="${not empty CONTROLLING_USER}">
            <sec:authorize ifAnyGranted="ROLE_ADMIN, ROLE_MANAGER, ROLE_AGENT, ROLE_SU"> </sec:authorize>
         </c:if>
         <div id="header">
              <div id="loginMenu">
                 <c:if test="${USER.userId > 0 && CONTROLLING_USER.userId <= 0}">
                   <div>Welcome ${USER.contactInfo.firstName} ${USER.contactInfo.lastName} | <a href="<spring:url value='/logout' />"><strong>LOGOUT</strong></a></div>
                 </c:if>
              </div>
         </div>
      -->
    </div>
  </body>
</html>
    
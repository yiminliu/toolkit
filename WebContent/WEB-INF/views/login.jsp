<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<spring:url var="loginUrl" value="/j_spring_security_check" />
<div id="login" class="span8 colborder">
     <form id="login" class="login" action="${loginUrl}" method="POST">
        <h3>Login</h3>
        <p>You need to log in as a admin user to use the toolkit</p>
        <div class="span5 offset1">
            <!-- <c:if test="${not empty param.login_error}">
                    <div style="color:red">
                       <li>Username or Password was incorrect.</li>
                    </div>
                  </c:if>
            -->
            <c:if test="${not empty param.login_error}">
               <div style="color:red;"> 
                  Your login attempt was not successful, try again.<br />
                  Caused :  ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
               </div>
            </c:if>
            <input class="span5" id="j_username" type="text" name="j_username" placeholder="Username" /> 
            <input class="span5" id="j_password" type="password" name="j_password" placeholder="Password" /> 
            <input type="submit" value="Login"/>
       </div>
    </form>
</div>

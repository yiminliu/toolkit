<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ include file="/WEB-INF/views/include/header/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset Password</title>
<style>
.error {
color: #ff0000;
font-weight: bold;
}
</style>
</head>
<body>
   <div>
       <h2 style="margin-bottom: 10px; padding-bottom: 0px;">Reset Password</h2>
       <form:form commandName="user" method="post" class="form-horizontal">
       <form:errors path="*" cssClass="error" />
         <!-- Error Alert -->
        <!-- <c:if test="${not empty requestScope['org.springframework.validation.BindingResult.user'].allErrors}">
            <div class="row">
               <h3>Please correct the following problems</h3>
                <form:errors path="email" />
                <form:errors path="password" />
                <spring:bind path="user">
                   <c:forEach items="${status.errorMessages}" var="error" varStatus="status">
                      <span id="global.${status.index}.errors"><c:out value="${error}" /> </span>
                   </c:forEach>
                </spring:bind>
           </div>
         </c:if>
         -->
         <br/>
         <fieldset>        
           <table cellspacing = "0">
             <tr>
               <th><form:label path="email" cssClass="required">Email Address</form:label></th>
               <td><form:input path="email" cssClass="span-8" cssErrorClass="span-8 validationFailed" /></td>
             </tr> 
           <tr>
              <th><form:label path="password" cssClass="required">Password to set</form:label></th>
              <td><form:input path="password" cssClass="span-8" cssErrorClass="span-8 validationFailed" /></td>
           </tr> 
           <tr>
              <th></th>
              <!-- <a id="updatePasswordCouponButton" href="#" class="button action-m centerFloats"><span>Submit</span></a>--> 
              <td><input id="resetPasswordSubmit" type="submit" name="_eventId_submit" value="Submit" class="hidden" /></td>
          </tr>     
          <form:hidden path="action" value="resetPassword" />     
          </table>
        </fieldset> 
     </form:form>
   </div>     
</body>
</html>
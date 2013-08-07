<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ include file="/WEB-INF/views/include/header/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Username and Email</title>
</head>
<body>
   <div>
       <h1 style="margin-bottom: 10px; padding-bottom: 0px;">Enter User Account Information</h1>
       <form:form commandName="user" method="post" class="form-horizontal">
       <form:errors path="*" cssClass="error" />
         <!-- Error Alert -->
         <!--<c:if test="${not empty requestScope['org.springframework.validation.BindingResult.user'].allErrors}">
            <div class="row">
              <div class="alert error">
                <h3>Please correct the following problems</h3>
                <form:errors path="email" />
                <form:errors path="password" />
                <form:errors path="newPassword" />
                <spring:bind path="user">
                   <c:forEach items="${status.errorMessages}" var="error" varStatus="status">
                      <span id="global.${status.index}.errors"><c:out value="${error}" /> </span>
                   </c:forEach>
                </spring:bind>
              </div>
            </div>
         </c:if>-->
         <fieldset>        
           <table cellspacing = "0">
             <tr>
               <th><form:label path="email" cssClass="required">Current Email Address</form:label></th>
               <td><form:input path="email" cssClass="span-8" cssErrorClass="span-8 validationFailed" /></td>
           </tr> 
           <tr>
              <th><form:label path="newEmail" cssClass="required">New Email Address</form:label></th>
              <td><form:input path="newEmail" cssClass="span-8" cssErrorClass="span-8 validationFailed" /></td>
           </tr> 
           <tr>
              <th></th>
              <!-- <a id="updatePasswordCouponButton" href="#" class="button action-m centerFloats"><span>Submit</span></a>--> 
              <td><input id="updateUsernameAndEmailSubmit" type="submit" name="_eventId_submit" value="Submit" class="hidden" /></td>
          </tr>     
          <form:hidden path="action" value="updateUsernameAndEmail" />     
          </table>
        </fieldset> 
     </form:form>   
</body>
</html>
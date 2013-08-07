<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/header/header.jsp"%>

<h3>TruConnect Admin Toolkit</h3>

<div class="row">
  <div class="span8">
    <ul>
     <sec:authorize ifAllGranted="ROLE_ADMIN">    
        <li><a href="<spring:url value="/truconnect/user/resetPassword"/>">Reset Password</a></li>
        <li><a href="<spring:url value="/truconnect/user/updatePassword"/>">Update Password</a></li>
        <li><a href="<spring:url value="/truconnect/user/updateUsernameAndEmail"/>">Update Email (also update username)</a></li>
     </sec:authorize>
     <li><a href="<spring:url value="/truconnect/balance/audit"/>">Audit Account Balance</a></li>
  </ul>
  </div>
</div>

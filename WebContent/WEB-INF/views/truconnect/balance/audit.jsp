<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/header/header.jsp"%>
<html>
   <head>
      <title>Balance Audit</title>
   </head>
   
   <div>
      <form:form method="POST">
        <table align="center">
          <tr>
             <td>Account Number: <input type="text" name="accountNo"/></td>
          </tr>
          <tr>
             <td><input type="submit" value="Submit"/></td>
          </tr>
        </table>
      </form:form>
    </div>
 </html>         
           
               
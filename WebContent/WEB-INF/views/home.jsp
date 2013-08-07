<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ include file="/WEB-INF/views/include/header/header.jsp"%>

<div class="span8 colborder">
  <h3>TruConnect Admin Toolkit</h3>
 
   <div class="container">
      <div class="span-18 colborder" style="min-height: 200px;">
        <table border="1" cellspacing="10">
          <tr style="float: middle;">
            <td><a id="truconnectToolkit" href="<spring:url value="/truconnect/index" />" class="button action-m"><span>TruConnect Tooolkit</span></a></td>
            <td><a id="webonthgoToolkit" href="<spring:url value="/webonthego/index" />" class="button action-m"><span>WebontheGo Toolkit</span></a></td>
          </tr>
        </table>
      </div>
    </div>   
</div>
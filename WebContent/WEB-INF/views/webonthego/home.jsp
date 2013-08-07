<!-- <%@ include file="/WEB-INF/views/include/header/header.jsp"%>-->

<!-- <div class="span8 colborder">-->
  <h3>TruConnect and WebontheGo Toolkit</h3>
 
   <!-- <div class="container">
    <div id="main-content">
      <div class="span-18 colborder" style="min-height: 200px;">-->
        <table border="1" cellspacing="10">
          <tr style="float: middle;">
            <td><a id="createTicket" href="<spring:url value="/support/ticket/createTicket" />" class="button action-m"><span>Create Ticket</span></a></td>
            <!-- <td>
                 <a id="showYourTickets" href="<spring:url value="/support/ticket/showLoggedinUserTickets/1" />" class="button action-m"><span>Show Your Tickets</span></a>
              </td>
              -->
            <td>
              <!-- <a id="showOpenTickets" href="<spring:url value="/ticket/showOpenTickets/1" />" class="button action-m"><span>Show Open Tickets</span></a>-->
              <a id="showOpenTickets" href="<spring:url value="/support/ticket/showOpenTickets" />" class="button action-m"><span>Show Open Tickets</span></a>
            </td>
            <td><a id="showOpenTickets" href="<spring:url value="/support/ticket/showAllTickets" />" class="button action-m"><span>Show All Tickets</span></a></td>
            <td><a id="searchTickets" href="<spring:url value="/support/ticket/searchTickets" />" class="button action-m"><span>Search Tickets</span></a></td>
          </tr>
        </table>
       <!-- </div>
      </div>
    </div>
</div>-->       
<!--<%@ include file="/WEB-INF/views/include/footer/footer.jsp"%>-->
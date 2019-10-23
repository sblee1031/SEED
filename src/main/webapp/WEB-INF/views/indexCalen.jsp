<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>


     <div class="col-lg-12">
         <h1 class="page-header">
         <a href="javascript: fn_moveDate('<c:out value="${preWeek}"/>')"><i class="fa fa-angle-left fa-fw"></i></a>
         
         <c:out value="${month}"/>월 <c:out value="${week}"/>째주
         <a href="javascript: fn_moveDate('<c:out value="${nextWeek}"/>')"><i class="fa fa-angle-right fa-fw"></i></a>
         </h1>
     </div>
 
     <div class="col-lg-12" id="weekDiv">
     	<c:forEach var="calenList" items="${calenList}" varStatus="status">    
             <div class="calendarColumn <c:if test="${calenList.ISTODAY}">today</c:if>">
                 <div class="panel <c:if test="${calenList.ISTODAY}">panel-red</c:if> <c:if test="${!calenList.ISTODAY}">panel-default</c:if> height100">
                     <div class="panel-heading" style="text-align:center">
                     	<c:out value="${calenList.MONTH}"/>월 <c:out value="${calenList.DAY}"/>일 (<c:out value="${calenList.WEEK}"/>)
                     </div> 
                     <div class="panel-body">
							<c:forEach var="items" items="${calenList.LIST}" varStatus="status"> 
				             	<div class="calendarDay" onmouseover="calendarDayMouseover(event, '<c:out value="${items.SSNO}"/>', '<c:out value="${calenList.DATE}"/>')" onmouseout="calendarDayMouseout()">
					             	<c:if test='${items.PK_SAWON_CODE==sessionScope.PK_SAWON_CODE}'>
					             		<a href="schForm?ssno=<c:out value="${items.SSNO}"/>&sdseq=<c:out value="${items.SDSEQ}"/>"><c:out value="${items.SSTITLE}"/></a>
				             		</c:if>
					             	<c:if test='${items.SSNO!=null and items.PK_SAWON_CODE!=sessionScope.PK_SAWON_CODE}'> 
					              		<a href="schRead?ssno=<c:out value="${items.SSNO}"/>&sdseq=<c:out value="${items.SDSEQ}"/>"><c:out value="${items.SSTITLE}"/></a>
				             		</c:if>
					             	<c:if test='${items.SSNO==null}'> 
					             		<span style="color:<c:out value="${items.FONTCOLOR}"/>"><c:out value="${items.SSTITLE}"/></span>
				             		</c:if>				             		
				             	</div>
				             </c:forEach>                     
                     </div> 
                 </div>
             </div>
        </c:forEach>
		<div class="calenSlideButton calenSlideButton_left" onclick="ev_prevSlide()">&#10094;</div>
		<div class="calenSlideButton calenSlideButton_right" onclick="ev_nextSlide()">&#10095;</div>
     </div>

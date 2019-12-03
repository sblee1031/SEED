<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title><s:message code="common.pageTitle"/></title>
    <link href="${pageContext.request.contextPath}/resources/css/sb-admin/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/sb-admin/metisMenu.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/sb-admin/sb-admin-2.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/sb-admin/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/resources/js/dynatree/ui.dynatree.css" rel="stylesheet" id="skinSheet"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/daterangepicker/daterangepicker.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="${pageContext.request.contextPath}/resources/js/jquery-2.2.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/sb-admin/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/sb-admin/metisMenu.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/sb-admin/sb-admin-2.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/project9.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/datepicker/bootstrap-datepicker.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/daterangepicker/moment.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/daterangepicker/daterangepicker.js"></script>
	    
<script>
window.onload = function() {
	$(function() {
		  $("#dateRangePicker").daterangepicker({
		  	autoUpdateInput:false, // 초기값 오늘날짜로 지정안되게하기 -> placeholder 보이게됨.
		    opens: 'center' // 달력선택시 중앙에서 팝업
		  }, function(start, end, label) {
			$('#dateRangePicker').val(start.format('YYYY-MM-DD') + '   ~   ' + end.format('YYYY-MM-DD')); // autoUpdateInput:false 이기 때문에 지정해줘야함. 
		    $('#startday').val(start.format('YYYY-MM-DD'));
		    $('#endday').val(end.format('YYYY-MM-DD'));
		  });
		});
}

function fn_formSubmit(){
	document.form1.submit();	
}
</script>
    
</head>

<body>

    <div id="wrapper">

		<form role="form" id="form1" name="form1"  method="post">
        <div id="page-wrapper" style="margin: 0px;">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><i class="fa fa-edit fa-fw"></i> 개인문서함</h1>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
				 	<label><input name="searchExt1" id="searchExt1" type="radio" value="" onclick="fn_formSubmit()" <c:if test='${searchVO.searchExt1==""}'>checked</c:if>> 전체</label>
				 	<label><input name="searchExt1" id="searchExt1" type="radio" value="0" onclick="fn_formSubmit()" <c:if test='${searchVO.searchExt1=="0"}'>checked</c:if>> 임시저장</label>
				 	<label><input name="searchExt1" id="searchExt1" type="radio" value="2" onclick="fn_formSubmit()" <c:if test='${searchVO.searchExt1=="2"}'>checked</c:if>> 진행중</label>
				 	<label><input name="searchExt1" id="searchExt1" type="radio" value="4" onclick="fn_formSubmit()" <c:if test='${searchVO.searchExt1=="4"}'>checked</c:if>> 완료</label>
				 	<label><input name="searchExt1" id="searchExt1" type="radio" value="3" onclick="fn_formSubmit()" <c:if test='${searchVO.searchExt1=="3"}'>checked</c:if>> 반려</label>
                </div>
            </div>
            
            <div class="row">
                <div class="col-lg-12">
		            <button type="button" class="btn btn-default pull-right" onclick="fn_moveToURL('signDocTypeList')">
		            <i class="fa fa-edit fa-fw"></i> 기안하기</button>      
				</div>
            </div>
            
            <!-- /.row -->
            <div class="panel panel-default"> 
            	<div class="panel-body">
					<div class="listHead">
						<div class="listHiddenField pull-left field60"><s:message code="board.no"/></div>
						<div class="listHiddenField pull-right field100">종류</div>
						<div class="listHiddenField pull-right field60"><s:message code="board.attach"/></div>
						<div class="listHiddenField pull-right field100"><s:message code="crud.crdate"/></div>
						<div class="listHiddenField pull-right field100"><s:message code="crud.usernm"/></div>
						<div class="listHiddenField pull-right field100">상태</div>
						<div class="listTitle"><s:message code="crud.crtitle"/></div>
					</div>
					
					<c:if test="${listview.size()==0}">
						<div class="listBody height200">
						</div>
					</c:if>
					
					<c:forEach var="listview" items="${listview}" varStatus="status">
						<c:url var="link" value="signDocRead">
							<c:param name="PK_AD_NUM" value="${listview.PK_AD_NUM}" />
						</c:url>
					
						<div class="listBody">
							<div class="listHiddenField pull-left field60 textCenter"><c:out value="${searchVO.totRow-((searchVO.page-1)*searchVO.displayRowCount + status.index)}"/></div>
							<div class="listHiddenField pull-right field100 textCenter"><c:out value="${listview.DOCTYPE_DTTITLE}"/></div>
							<div class="listHiddenField pull-right field60">
								<c:if test="${listview.filecnt>0}">
									<i class="fa fa-download fa-fw" title="<c:out value="${listview.filecnt}"/>"></i>
								</c:if>	
							</div>
							<div class="listHiddenField pull-right field100 textCenter" style="font-size:11px;"><c:out value="${listview.AD_MOD_DATE}"/></div>
							<div class="listHiddenField pull-right field100 textCenter"><c:out value="${listview.SAWON_NAME}"/></div>
							<div class="listHiddenField pull-right field100 textCenter"><c:out value="${listview.AD_DOCSTATUS}"/></div>
							<div class="listTitle" title="<c:out value="${listview.AD_TITLE}"/>">
								<a href="${link}"><c:out value="${listview.AD_TITLE}"/></a>
							</div>
						</div>
					</c:forEach>	
					
					<br/>
					    <jsp:include page="../../common/pagingforSubmit.jsp" />
				    
						<div class="form-group">
							<div class="row">
								<div class="checkbox col-lg-3 pull-left">
			                        <label class="pull-right">
	                                	<input type="checkbox" name="searchType" value="day" <c:if test="${fn:indexOf(searchVO.searchType, 'day')!=-1}">checked="checked"</c:if>/>
				                      	기간
				                    </label>
			                   </div>
			                   <div class="input-group custom-search-form col-lg-3">
											<div class="input-group">
											<span class="input-group-addon">
											<i class="fa fa-calendar bigger-110"></i>
											</span>
											<input class="form-control" type="text" name="date-range-picker" id="dateRangePicker" placeholder="날짜를 선택하세요" value="" style="width:313px;" readonly>
											<input class="form-control" type="hidden" name="startday" id="startday" value="<c:out value="${searchVO.startday}"/>">
											<input class="form-control" type="hidden" name="endday" id="endday" value="<c:out value="${searchVO.endday}"/>">
											</div>
			                                <span class="input-group-btn">
				                                <button class="btn btn-default" onclick="fn_formSubmit()">
				                                    <i class="fa fa-search"></i>
				                                </button>
				                            </span>
		                       </div>
		                   </div>
							<div class="row">
								<div class="col-lg-3 pull-left">
									<label class="pull-right">
										<select name="searchType" id="selectbox" class="form-control">
									        <option selected="selected">검색조건</option>
									        <option value="AD_TITLE, AD_CONTENT" <c:if test="${fn:indexOf(searchVO.searchType, 'AD_TITLE, AD_CONTENT')!=-1}">selected="selected"</c:if>>제목+내용</option>
									        <option value="AD_TITLE" <c:if test="${fn:indexOf(searchVO.searchType, 'AD_TITLE')!=-1}">selected="selected"</c:if>>제목</option>
									        <option value="AD_CONTENT" <c:if test="${fn:indexOf(searchVO.searchType, 'AD_CONTENT')!=-1}">selected="selected"</c:if>>내용</option>
									    </select>
								    </label>
			                   </div>
			                   <div class="input-group custom-search-form col-lg-3">
		                                <input class="form-control" placeholder="검색어를 입력하세요" type="text" name="searchKeyword" 
		                                	   value='<c:out value="${searchVO.searchKeyword}"/>' >
		                                <span class="input-group-btn">
		                                <button class="btn btn-default" onclick="fn_formSubmit()">
		                                    <i class="fa fa-search"></i>
		                                </button>
		                            </span>
		                       </div>
	                       </div>
						</div>
            	</div>    
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->
		</form>	

    </div>
    <!-- /#wrapper -->
</body>

</html>

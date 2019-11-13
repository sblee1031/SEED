<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일일 근태 등록</title>
<!----------------------------------------------------------------------------------------->
<link rel="stylesheet" href="${contextPath}/resources/css/style.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="${contextPath}/resources/ibsheet/ibsheetinfo.js"></script>
<script src="${contextPath}/resources/ibsheet/ibsheet.js"></script>
<script src="${contextPath}/resources/ibsheet/ibleaders.js"></script>
<script language="javascript">
	//시트 높이 계산용
	var pageheightoffset = 200;

	/*Sheet 기본 설정 */
	function LoadPage() {
		mySheet.RemoveAll();
		//아이비시트 초기화
		var initSheet = {};
		initSheet.Cfg = {
			SearchMode : smLazyLoad,
			ToolTip : 1
		};
		initSheet.HeaderMode = {
			Sort : 1,
			ColMove : 1,
			ColResize : 1,
			HeaderCheck : 1
		};

		initSheet.Cols = [ // 상태, 삭제는 건들면 안됨. SaveName은 VO속성과 동일하게
		// MinWidth는 최소 길이값.(줄여도 최소길이값 이하로 안줄여짐.)
		// Align : data 정렬값.
		// keyfield : keyfield값 필수값 체크시 필요(미입력시 추가안됨.)
		// MultiLineText:1  : 하나의 셀에 여러값을 넣을수 있음(shift+enter)
		// Wrap:1 : 데이터
		{
			Header : "상태",
			Type : "Status",
			SaveName : "STATUS",
			MinWidth : 50
		}, {
			Header : "근무일자",
			Type : "Text",
			SaveName : "pk_DAILY_TA_WORKING_DATE",
			MinWidth : 100,
		}, {
			Header : "성명",
			Type : "Text",
			SaveName : "sawon_NAME",
			MinWidth : 80,
		}, {
			Header : "부서",
			Type : "Text",
			SaveName : "fk_RANK_NAME",
			MinWidth : 80
		}, {
			Header : "직급",
			Type : "Text",
			SaveName : "rank_NAME",
			MinWidth : 80,
			Wrap : 1
		}, {
			Header : "출근시각",
			Type : "Text",
			SaveName : "daily_TA_GO_TIME",
			MinWidth : 60
		}, {
			Header : "퇴근시각",
			Type : "Text",
			SaveName : "daily_TA_OFF_TIME",
			MinWidth : 60
		}, {
			Header : "근무시간",
			Type : "Text",
			SaveName : "daily_TA_WORK_TIME",
			MinWidth : 60
		}, {
			Header : "연장근로",
			Type : "Text",
			SaveName : "daily_TA_EXTEN_WORK",
			MinWidth : 60
		}, {
			Header : "지각시간",
			Type : "Text",
			SaveName : "daily_TA_LATE_TIME",
			MinWidth : 60
		}, {
			Header : "조퇴시간",
			Type : "Text",
			SaveName : "daily_TA_LEAVE_TIME",
			MinWidth : 60
		} ];
		IBS_InitSheet(mySheet, initSheet);
		mySheet.SetEditable(false);
		mySheet.SetEditableColorDiff(1); // 편집불가능할 셀 표시구분  (1: 수정가능  빈칸 : 수정불가)
		//mySheet.ShowSubSum([{StdCol:"Release",SumCols:"price",Sort:"asc"}]);
		//doAction('search');
	}
	/*Sheet 각종 처리*/
	function doAction(sAction) {
		switch (sAction) {
		case "search": //조회
			var val = "PK_DAILY_TA_WORKING_DATE=" + document.getElementById("PK_DAILY_TA_WORKING_DATE").value
					+ "&pk_SAWON_CODE=" + '<%=session.getAttribute("PK_SAWON_CODE")%>';
				
			mySheet.DoSearch("${contextPath}/attendance/p0001/searchList.do",val);
			break;

		//------------------------------------------------------------------------------------------------------------------------------	
		//출근
		case "gowork":
			var select_row = mySheet.GetSelectRow();
			
			var Now = new Date();
			NowTime1 = Now.getHours();
			if (NowTime1 < 10) {
				NowTime1 = "0" + NowTime1;
			}
			NowTime2 = +Now.getMinutes();
			if (NowTime2 < 10) {
				NowTime2 = "0" + NowTime2;
			}
			var Time1 = NowTime1 + ":" + NowTime2; // ex) 
			var Time = NowTime1 - 9 + " :" + NowTime2;
			console.log("time" + Time1)

	
			mySheet.SetCellValue(select_row, 5, Time1);

			//======================================================================
			//지각시간
			console.log(NowTime1);
			if (NowTime1 >= 8 && NowTime1 <= 17) {
				console.log(NowTime1);
				mySheet.SetCellValue(select_row, 9, Time);//지각시간
				if (NowTime2 < 10) {
					MowTime2 = "0" + NowTime2;
				}
			}
			break;
			
			
		case "save":
			mySheet.DoSave("${contextPath}/attendance/p0001/insertData.do");
			break;
		//----------------------------------------------------------------------------------------------------------------------------------

		case "gohome": //퇴근
			var select_row = mySheet.GetSelectRow();
			var Now = new Date();
			var NowTime3 = Now.getHours();
			if (NowTime3 < 10) {
				NowTime3 = "0" + NowTime3;
			}
			var NowTime4 = +Now.getMinutes();
			if (NowTime4 < 10) {
				NowTime4 = "0" + NowTime4;
			}
			var Time2 = NowTime3 + ":" + NowTime4;
			mySheet.SetCellValue(select_row, 6, Time2);

			//========================================================================
			//조퇴시간

			var Time2 = NowTime3 + ":" + NowTime4;
			var HTime = 18 - NowTime3;
			var MTime = 60 - NowTime4;
			if (NowTime4 > 0) {
				HTime = 17 - NowTime3;
				MTime = 60 - NowTime4;
				if (MTime < 10) {
					MTime = "0" + MTime;
				}
			}

			var Time = HTime + " : " + MTime;
			if (NowTime3 < 18) {
				mySheet.SetCellValue(select_row, 10, Time); //조퇴시간
			}
			//==========================================================================
			//연장근무
			var ExTime = NowTime3 - 18 + " : " + NowTime4;
			if (NowTime3 > 17) {
				mySheet.SetCellValue(select_row, 8, ExTime);
			}
			//============================================================================
			var WorkTime1 = NowTime3 - NowTime1;//시간
			var WorkTime2 = NowTime4 - NowTime2;//분
			if (NowTime2 > NowTime4) {
				NowTime2 - NowTime1 - 1 + " : " + NowTime4 + 60 - NowTime2;
				alert("if문");
			}
			var WorkTime = WorkTime1 + " : " + WorkTime2
			mySheet.SetCellValue(select_row, 7, WorkTime);//근무시간
			mySheet.SetCellValue(select_row, 6, Time2);//퇴근시간
			break;
		}

	}
	
	// mySheet 조회 끝나기 직전 이벤트 
	function mySheet_OnSearchEnd() {
		console.log(mySheet.GetCellValue(1,1));
		if(mySheet.GetCellValue(1,1) == -1 ){
			val1 = "PK_DAILY_TA_WORKING_DATE=" + document.getElementById("PK_DAILY_TA_WORKING_DATE").value
			+ "&pk_SAWON_CODE=" + '<%=session.getAttribute("PK_SAWON_CODE")%>';
			//날짜부분 들어가는 코딩 
			mySheet.DoSearch("${contextPath}/attendance/p0001/searchList_sawon.do",val1);// 사원관련정보 조회 = 이름 부서 등
		}else if(mySheet.GetCellValue(1,1) == "" || mySheet.GetCellValue(1,1) == null){
			var PK_DAILY_TA_WORKING_DATE = document.getElementById("PK_DAILY_TA_WORKING_DATE").value;
			mySheet.SetCellValue(1, 1, PK_DAILY_TA_WORKING_DATE);
			//첫번째 열에서 두번째에다가 변수이름:PK_DAILY_TA_WORKING_DATE를 추가한다.
		}
	}
</script>
</head>
<body onload="LoadPage()">
	<div class="page_title">
		<span><a class="closeDepth" href="#">closeDepth</a></span> <span
			class="title">근태/연차관리 > <b>일일 근태 확인</b></span>
	</div>
	<div class="main_content">
		<br> <label for="fromDate">근무일자</label> <input type='date'
			id="PK_DAILY_TA_WORKING_DATE" name="PK_DAILY_TA_WORKING_DATE" /> <input
			type='hidden' id="PK_DAILY_TA_WORKING_DATE"
			name="PK_DAILY_TA_WORKING_DATE" />
		<!-------------------- 달력 -------------------------------->

		<br> <a href="javascript:doAction('search')"
			class="f1_btn_gray lightgray" style="float: right;">조회</a>
		<div class="page_title"></div>

		<!-- 구분 선 -->
		<br> <br>

		<h4>일일근태 확인</h4>
		<div class="ib_function float">
			<a href="javascript:doAction('gohome')" class="f1_btn_gray lightgray"
				style="float: right;">퇴근</a> <a href="javascript:doAction('gowork')"
				class="f1_btn_gray lightgray" type="HH:MM" style="float: right;">출근</a>
			<a href="javascript:doAction('save')" class="f1_btn_gray lightgray"
				type="HH:MM" style="float: right;">저장</a> <br> <br>

			<div class="exp_product"></div>
			<!-- 공간을 넓게 -->
			<!--<div class="ib_product">  없으면 크기가 작아짐(3줄정도로 바뀜 -->
			<script>
				createIBSheet("mySheet", "100%", "100%");
			</script>
</body>
</html>

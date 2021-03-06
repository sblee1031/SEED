package hr.attendance.p0002.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hr.attendance.p0002.vo.business_VO;
import hr.attendance.p0002.vo.holiday_VO;
import hr.attendance.p0002.service.holiday_Service;
import project.common.DateVO;
import project.common.Field3VO;

@Controller("holiday_Controller")
public class holiday_ControllerImpl implements holiday_Controller {
	private static final Logger logger = LoggerFactory.getLogger(holiday_ControllerImpl.class);
	@Autowired
	holiday_Service holiday_Service;
	@Autowired
	holiday_VO holiday_VO;
	business_VO business_VO;


	@Override
	@RequestMapping(value = "attendance/p0002/holiday.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView holiday(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		viewName = "attendance/p0002/holiday";
		request.setCharacterEncoding("utf-8");
		// ModelAndView main = new ModelAndView("hr/p0001_init");
		ModelAndView main = new ModelAndView(viewName);
		return main;
	}
	
	@Override
	@RequestMapping(value = "Abusiness_M", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView business_M(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		viewName = "attendance/p0002/business_M";
		request.setCharacterEncoding("utf-8");
		// ModelAndView main = new ModelAndView("hr/p0001_init");
		ModelAndView main = new ModelAndView(viewName);
		return main;
	}
	
	@Override
	@RequestMapping(value = "Aholiday_M", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView holiday_M(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		viewName = "attendance/p0002/holiday_M";
		request.setCharacterEncoding("utf-8");
		// ModelAndView main = new ModelAndView("hr/p0001_init");
		ModelAndView main = new ModelAndView(viewName);
		return main;
	}

	@Override
	@RequestMapping(value = "Aholiday_da", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView holiday_da(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		viewName = "attendance/p0002/holiday_da";
		request.setCharacterEncoding("utf-8");
		// ModelAndView main = new ModelAndView("hr/p0001_init");
		ModelAndView main = new ModelAndView(viewName);
		return main;
	}

	@Override
	@RequestMapping(value = "attendance/p0002/business.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView business(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		viewName = "attendance/p0002/business";
		request.setCharacterEncoding("utf-8");
		ModelAndView main = new ModelAndView(viewName);
		return main;
	}

	@Override
	@RequestMapping(value = "Abusiness_da", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView business_da(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		viewName = "attendance/p0002/business_da";
		request.setCharacterEncoding("utf-8");
		// ModelAndView main = new ModelAndView("hr/p0001_init");
		ModelAndView main = new ModelAndView(viewName);
		return main;
	}

	@Override
	@RequestMapping(value = "Along_time", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView outside(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		viewName = "attendance/p0002/long_time";
		request.setCharacterEncoding("utf-8");
		ModelAndView main = new ModelAndView(viewName);
		return main;
	}
	
	@Override
	@RequestMapping(value = "sawon_pop.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView sawon_pop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		viewName = "attendance/p0002/sawon_pop";
		request.setCharacterEncoding("utf-8");
		ModelAndView main = new ModelAndView(viewName);
		return main;
	}

	@Override
	@RequestMapping(value = "Aholiday_calc", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView holiday_calc(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		viewName = "/attendance/p0002/holiday_calc";
		request.setCharacterEncoding("utf-8");
		ModelAndView main = new ModelAndView(viewName);
		return main;
	}

	@ResponseBody
	@RequestMapping(value = "attendance/p0002/select.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/text;charset=utf-8")
	public String select(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		List<HashMap<String, String>> result = holiday_Service.select();
		ObjectMapper mapper = new ObjectMapper();
		String resultJson = mapper.writeValueAsString(result);
		return resultJson;
	}
	
	

	// ============================================================================================================

	// 출장 조회화면
	@Override
	@RequestMapping(value = "attendance/p0002/searchList_busin.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map searchList_busin(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws Exception {
		String userno = request.getSession().getAttribute("PK_SAWON_CODE").toString();

		request.setCharacterEncoding("utf-8");
		Map<String, Object> searchMap = new HashMap<String, Object>(); // �??��조건
		Map<String, Object> resultMap = new HashMap<String, Object>(); // 조회결과
		searchMap.put("PK_SAWON_CODE", request.getParameter("pk_SAWON_CODE"));
		searchMap.put("HOLIDAY_PAY", request.getParameter("HOLIDAY_PAY"));
		searchMap.put("fd_year", request.getParameter("fd_year")+"%");

		List<business_VO> data = holiday_Service.searchList_busin(searchMap);
		List<DateVO> calenList = new ArrayList<DateVO>();
		Field3VO fld = new Field3VO();
		fld.setField1(userno);
		resultMap.put("Data", data);
		resultMap.put("calenList", calenList);

		return resultMap;

	}

	// 출장 관리자 조회화면
	@Override
	@RequestMapping(value = "attendance/p0002/searchList_busin_da.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public Map searchList_busin_da(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws Exception {

		request.setCharacterEncoding("utf-8");
		Map<String, Object> searchMap = new HashMap<String, Object>(); // �??��조건
		Map<String, Object> resultMap = new HashMap<String, Object>(); // 조회결과
		searchMap.put("PK_SAWON_CODE", request.getParameter("pk_SAWON_CODE"));
		searchMap.put("fd_year", request.getParameter("fd_year")+request.getParameter("fd_month")+"%");
		searchMap.put("sawon_num", request.getParameter("sawon_num"));
		searchMap.put("option", request.getParameter("option"));
		searchMap.put("HOLIDAY_PAY", request.getParameter("HOLIDAY_PAY"));
		// ?��?��?�� 조회
		List<business_VO> data = holiday_Service.searchList_busin_da(searchMap);
		List<DateVO> calenList = new ArrayList<DateVO>();
		Field3VO fld = new Field3VO();
		resultMap.put("Data", data);
		resultMap.put("calenList", calenList);
		return resultMap;

	}
	
	//휴가계산화면 조회
		@Override
		@RequestMapping(value = "attendance/p0002/searchList_calc.do", method = { RequestMethod.GET,
				RequestMethod.POST })
		@ResponseBody
		public Map searchList_calc(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
				throws Exception {

			request.setCharacterEncoding("utf-8");
			Map<String, Object> searchMap = new HashMap<String, Object>(); // �??��조건
			Map<String, Object> resultMap = new HashMap<String, Object>(); // 조회결과
			searchMap.put("fd_year", request.getParameter("fd_year"));
			searchMap.put("sawon_num", request.getParameter("sawon_num"));
			searchMap.put("option", request.getParameter("option"));
			
			// ?��?��?�� 조회
			List<holiday_VO> data = holiday_Service.searchList_calc(searchMap);
			List<DateVO> calenList = new ArrayList<DateVO>();
			Field3VO fld = new Field3VO();
			resultMap.put("Data", data);
			resultMap.put("calenList", calenList);
			return resultMap;

		}
	
//휴가 사원화면 조회
	@Override
	@RequestMapping(value = "attendance/p0002/searchList_holi.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map searchList_holi(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws Exception {
		String userno = request.getSession().getAttribute("PK_SAWON_CODE").toString();

		request.setCharacterEncoding("utf-8");
		Map<String, Object> searchMap = new HashMap<String, Object>(); // �??��조건
		Map<String, Object> resultMap = new HashMap<String, Object>(); // 조회결과

		searchMap.put("PK_SAWON_CODE", request.getParameter("pk_SAWON_CODE"));
		searchMap.put("HOLIDAY_PAY", request.getParameter("HOLIDAY_PAY"));
		searchMap.put("fd_year", request.getParameter("fd_year")+"%");
		searchMap.put("fd_year1", request.getParameter("fd_year"));
		
		// ?��?��?�� 조회
		List<holiday_VO> data = holiday_Service.searchList_holi(searchMap);
		List<DateVO> calenList = new ArrayList<DateVO>();
		Field3VO fld = new Field3VO();
		fld.setField1(userno);
		resultMap.put("Data", data);
		return resultMap;

	}
	
	//휴가 관리자화면 조회
	@Override
	@RequestMapping(value = "attendance/p0002/searchList_holi_da.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public Map searchList_holi_da(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, Object> searchMap = new HashMap<String, Object>(); // �??��조건
		Map<String, Object> resultMap = new HashMap<String, Object>(); // 조회결과
		searchMap.put("fd_year", request.getParameter("fd_year")+request.getParameter("fd_month")+"%");
		searchMap.put("sawon_num", request.getParameter("sawon_num"));
		searchMap.put("option", request.getParameter("option"));
		searchMap.put("HOLIDAY_PAY", request.getParameter("HOLIDAY_PAY"));
		searchMap.put("PK_SAWON_CODE", request.getParameter("pk_SAWON_CODE"));
		searchMap.put("fd_year1", request.getParameter("fd_year"));
		
		// ?��?��?�� 조회
		List<holiday_VO> data = holiday_Service.searchList_holi_da(searchMap);
		List<DateVO> calenList = new ArrayList<DateVO>();
		Field3VO fld = new Field3VO();
		resultMap.put("Data", data);
		resultMap.put("calenList", calenList);
		return resultMap;

	}
	
	//휴가 관리자화면 조회
		@Override
		@RequestMapping(value = "attendance/p0002/searchList_holi_da2.do", method = { RequestMethod.GET,
				RequestMethod.POST })
		@ResponseBody
		public Map searchList_holi_da2(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
				throws Exception {
			request.setCharacterEncoding("utf-8");
			Map<String, Object> searchMap = new HashMap<String, Object>(); // �??��조건
			Map<String, Object> resultMap = new HashMap<String, Object>(); // 조회결과
			searchMap.put("sawon_NAME", request.getParameter("sawon_name"));
			searchMap.put("sawon_CODE", request.getParameter("sawon_code"));
			searchMap.put("fk_DEPT_NAME", request.getParameter("dept"));
			searchMap.put("rank_NAME", request.getParameter("rank"));
			searchMap.put("fd_year1", request.getParameter("fd_year"));
			System.out.println("name"+ searchMap.put("sawon_NAME", request.getParameter("sawon_name")));
			System.out.println("code"+searchMap.put("sawon_CODE", request.getParameter("sawon_code")));
			System.out.println("DEPT"+searchMap.put("fk_DEPT_NAME", request.getParameter("dept")));
			System.out.println("RANK"+searchMap.put("rank_NAME", request.getParameter("rank")));
			System.out.println("YEAR"+	searchMap.put("fd_year1", request.getParameter("fd_year")));
			
			// ?��?��?�� 조회
			List<holiday_VO> data = holiday_Service.searchList_holi_da2(searchMap);
			List<DateVO> calenList = new ArrayList<DateVO>();
			Field3VO fld = new Field3VO();
			resultMap.put("Data", data);
			resultMap.put("calenList", calenList);
			return resultMap;

		}


	//사원휴가화면 추가
	@Override
	@RequestMapping(value = "attendance/p0002/insertData_holi.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map saveData_holi(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		Map<String, String[]> dataMap = new HashMap<String, String[]>(); // ???��?��Daa
		Map<String, Object> resultMap = new HashMap<String, Object>(); // 처리결과

		// ???�� Data 추출?���?
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String[] values = request.getParameterValues(name);
			dataMap.put(name, values);
		}

		Map<String, String> result = new HashMap<String, String>();
		try {
			holiday_Service.saveData_holi(dataMap);
			result.put("Code", "0");
			result.put("Message", "???��?��?��?��?��?��");
		} catch (Exception e) {
			result.put("Code", "-1");
			result.put("Message", "???��?�� ?��?��?��???��?��?��");
			e.printStackTrace();
		}

		resultMap.put("Result", result);
		return resultMap;
	}

	//휴가 관리자화면 추가
	@Override
	@RequestMapping(value = "attendance/p0002/insertData_holi_da.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public Map saveData_holi_da(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, String[]> dataMap = new HashMap<String, String[]>(); // ???��?��Daa
		Map<String, Object> resultMap = new HashMap<String, Object>(); // 처리결과

		// ???�� Data 추출?���?
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String[] values = request.getParameterValues(name);
			dataMap.put(name, values);
		}

		Map<String, String> result = new HashMap<String, String>();
		System.out.println("1. " + dataMap);
		try {
			holiday_Service.saveData_holi_da(dataMap);
			result.put("Code", "0");
			result.put("Message", "???��?��?��?��?��?��");
		} catch (Exception e) {
			result.put("Code", "-1");
			result.put("Message", "???��?�� ?��?��?��???��?��?��");
			e.printStackTrace();
		}

		resultMap.put("Result", result);
		return resultMap;
	}

	//출장화면 추가
	@Override
	@RequestMapping(value = "attendance/p0002/insertData_busin.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map saveData_busin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, String[]> dataMap = new HashMap<String, String[]>(); // ???��?��Daa
		Map<String, Object> resultMap = new HashMap<String, Object>(); // 처리결과

		// ???�� Data 추출?���?
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String[] values = request.getParameterValues(name);
			System.out.println("controller_name :" + name);
			System.out.println("controller_values :" + values);
			dataMap.put(name, values);
		}

		Map<String, String> result = new HashMap<String, String>();
		System.out.println("1. " + dataMap);
		try {
			holiday_Service.saveData_busin(dataMap);
			result.put("Code", "0");
			result.put("Message", "???��?��?��?��?��?��");
		} catch (Exception e) {
			result.put("Code", "-1");
			result.put("Message", "???��?�� ?��?��?��???��?��?��");
			e.printStackTrace();
		}

		resultMap.put("Result", result);
		return resultMap;
	}

	//휴가 관리자화면 추가
	@Override
	@RequestMapping(value = "attendance/p0002/insertData_busin_da.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public Map saveData_busin_da(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, String[]> dataMap = new HashMap<String, String[]>(); // ???��?��Daa
		Map<String, Object> resultMap = new HashMap<String, Object>(); // 처리결과

		// ???�� Data 추출?���?
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String[] values = request.getParameterValues(name);
			dataMap.put(name, values);
		}

		Map<String, String> result = new HashMap<String, String>();
		System.out.println("1. " + dataMap);
		try {
			holiday_Service.saveData_busin_da(dataMap);
			result.put("Code", "0");
			result.put("Message", "???��?��?��?��?��?��");
		} catch (Exception e) {
			result.put("Code", "-1");
			result.put("Message", "???��?�� ?��?��?��???��?��?��");
			e.printStackTrace();
		}

		resultMap.put("Result", result);
		return resultMap;
	}
	
	
	//휴가계산 화면 추가
		@Override
		@RequestMapping(value = "attendance/p0002/insertData_calc.do", method = { RequestMethod.GET,
				RequestMethod.POST })
		@ResponseBody
		public Map saveData_calc(HttpServletRequest request, HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("utf-8");
			Map<String, String[]> dataMap = new HashMap<String, String[]>(); // ???��?��Daa
			Map<String, Object> resultMap = new HashMap<String, Object>(); // 처리결과

			// ???�� Data 추출?���?
			Enumeration enu = request.getParameterNames();
			while (enu.hasMoreElements()) {
				String name = (String) enu.nextElement();
				String[] values = request.getParameterValues(name);
				dataMap.put(name, values);
			}

			Map<String, String> result = new HashMap<String, String>();
			System.out.println("1. " + dataMap);
			try {
				holiday_Service.saveData_calc(dataMap);
				result.put("Code", "0");
				result.put("Message", "???��?��?��?��?��?��");
			} catch (Exception e) {
				result.put("Code", "-1");
				result.put("Message", "???��?�� ?��?��?��???��?��?��");
				e.printStackTrace();
			}

			resultMap.put("Result", result);
			return resultMap;
		}
	

	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String viewName = uri.substring(begin, end);
		if (viewName.indexOf(".") != -1) {
			viewName = viewName.substring(0, viewName.lastIndexOf("."));
		}
		if (viewName.lastIndexOf("/") != -1) {
			viewName = viewName.substring(viewName.lastIndexOf("/", 1), viewName.length());
		}
		return viewName;
	}

}

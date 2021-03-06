package hr.human.p0007.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hr.human.p0002.vo.BalVO;
import hr.human.p0007.service.education_manageService;
import hr.human.p0007.vo.education_manageVO;
import hr.pay.p0001.vo.TA_inputVO;


@Controller("education_manageController")
public class education_manageControllerImpl implements education_manageController {
   private static final Logger logger = LoggerFactory.getLogger(education_manageControllerImpl.class);
   @Autowired
   education_manageService education_manageService;
   @Autowired
   education_manageVO education_manageVO;
   
   @Override
   @RequestMapping(value = "/human/p0007/init.do", method = { RequestMethod.GET, RequestMethod.POST })
   public ModelAndView searchInit(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String viewName = getViewName(request);
      viewName = "/human/s0002/init";
      request.setCharacterEncoding("utf-8");
      //ModelAndView main = new ModelAndView("hr/p0001_init");
      ModelAndView main = new ModelAndView(viewName);
      return main;
   }
   
   @Override
   @RequestMapping(value = "/human/p0007/tab_education_manage.do", method = { RequestMethod.GET, RequestMethod.POST })
   public ModelAndView tab_education_manage(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String viewName = getViewName(request);
      viewName = "human/p0007/tab_education_manage";
      request.setCharacterEncoding("utf-8");
      //ModelAndView main = new ModelAndView("hr/p0001_init");
      ModelAndView main = new ModelAndView(viewName);
      return main;
   }
   
   @Override
   @RequestMapping(value = "/human/p0007/education_manage.do", method = { RequestMethod.GET, RequestMethod.POST })
   public ModelAndView education_manage(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String viewName = getViewName(request);
      viewName = "human/p0007/education_manage";
      request.setCharacterEncoding("utf-8");
      //ModelAndView main = new ModelAndView("hr/p0001_init");
      ModelAndView main = new ModelAndView(viewName);
      return main;
   }
   
   
   @Override
   @RequestMapping(value = "/human/p0007/education_manage2.do", method = { RequestMethod.GET, RequestMethod.POST })
   public ModelAndView education_manage2(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String viewName = getViewName(request);
      viewName = "human/p0007/education_manage2";
      request.setCharacterEncoding("utf-8");
      //ModelAndView main = new ModelAndView("hr/p0001_init");
      ModelAndView main = new ModelAndView(viewName);
      return main;
   }
   
   @Override
	@RequestMapping(value = "/human/p0007/edu_add_sawon_target.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView edu_add_sawon_target(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		viewName = "/human/p0007/edu_add_sawon_target";
		request.setCharacterEncoding("utf-8");
		
		ModelAndView main = new ModelAndView(viewName);
		return main;
	}
   
	//조회를 누를시 돌아가는 공간
	@Override
	@RequestMapping(value = "/human/p0007/sawon_search.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map sawon_search(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, Object> searchMap = new HashMap<String, Object>(); 
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
		searchMap.put("pk_DEPT_CODE", request.getParameter("pk_DEPT_CODE")); // 부서코드
		searchMap.put("rank_NAME", request.getParameter("rank_NAME")); // 직책
		
		List<education_manageVO> data = education_manageService.sawon_search(searchMap);
		resultMap.put("Data", data);
		        
		return resultMap;
	}
   
   
	//부서 선택시 나오는 화면
	@Override
	@RequestMapping(value = "/human/p0007/DeptList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map DeptList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, Object> searchMap = new HashMap<String, Object>(); 
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
		
		List<education_manageVO> data = education_manageService.DeptList(searchMap);
        resultMap.put("Data", data);
        
        return resultMap;
	}
	
	

   @Override
   @RequestMapping(value = "/human/p0007/ibsheet.do", method = { RequestMethod.GET, RequestMethod.POST })
   public ModelAndView ibSheet(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String viewName = getViewName(request);
      viewName = "/human/s0002/ibsheet_basic";
      request.setCharacterEncoding("utf-8");
      //ModelAndView main = new ModelAndView("hr/p0001_init");
      ModelAndView main = new ModelAndView(viewName);
      return main;
   }
   
   @Override
   @RequestMapping(value = "/human/p0007/searchList.do", method = { RequestMethod.GET, RequestMethod.POST })
   @ResponseBody
   public Map searchList(HttpServletRequest request, HttpServletResponse response) throws Exception {
      request.setCharacterEncoding("utf-8");
      Map<String, Object> searchMap = new HashMap<String, Object>(); // 검색조건
      Map<String, Object> resultMap = new HashMap<String, Object>(); // 조회결과
      
      // 검색조건설정
      searchMap.put("PK_EDUCATION_MANA_CODE", request.getParameter("PK_EDUCATION_MANA_CODE"));
      searchMap.put("education_MANA_END", request.getParameter("education_MANA_END"));

      
      //데이터 조회
      List<education_manageVO> data = education_manageService.searchList(searchMap);
        resultMap.put("Data", data);
        
        return resultMap;
   }
   
   
   
   
   @Override
   @RequestMapping(value = "/human/p0007/searchList2.do", method = { RequestMethod.GET, RequestMethod.POST })
   @ResponseBody
   public Map searchList2(HttpServletRequest request, HttpServletResponse response) throws Exception {
      request.setCharacterEncoding("utf-8");
      Map<String, Object> searchMap = new HashMap<String, Object>(); // 검색조건
      Map<String, Object> resultMap = new HashMap<String, Object>(); // 조회결과
      
      // 검색조건설정
      searchMap.put("PK_EDUCATION_MANA_CODE", request.getParameter("PK_EDUCATION_MANA_CODE"));
      searchMap.put("education_MANA_END", request.getParameter("education_MANA_END"));

      
      //데이터 조회
      List<education_manageVO> data = education_manageService.searchList2(searchMap);
        resultMap.put("Data", data);
        
        return resultMap;
   }
   

   @Override
   @RequestMapping(value = "/human/p0007/searchList3.do", method = { RequestMethod.GET, RequestMethod.POST })
   @ResponseBody
   public Map searchList3(HttpServletRequest request, HttpServletResponse response) throws Exception {
      request.setCharacterEncoding("utf-8");
      Map<String, Object> searchMap = new HashMap<String, Object>(); // 검색조건
      Map<String, Object> resultMap = new HashMap<String, Object>(); // 조회결과
      
      // 검색조건설정
      searchMap.put("fk_EDUCATION_ADD_CODE", request.getParameter("FK_EDUCATION_ADD_CODE"));
      searchMap.put("PK_EDUCATION_MANA_CODE", request.getParameter("PK_EDUCATION_MANA_CODE"));
System.out.println(request.getParameter("PK_EDUCATION_MANA_CODE"));
      
      //데이터 조회
      List<education_manageVO> data = education_manageService.searchList3(searchMap);
        resultMap.put("Data", data);
        
        return resultMap;
   }
   
   @Override
   @RequestMapping(value = "/human/p0007/insertData.do", method = { RequestMethod.GET, RequestMethod.POST })
   @ResponseBody
   public Map saveData(HttpServletRequest request, HttpServletResponse response) throws Exception {
      request.setCharacterEncoding("utf-8");
      Map<String, String[]> dataMap = new HashMap<String, String[]>(); // 저장할Data
      Map<String, Object> resultMap = new HashMap<String, Object>(); // 처리결과
      
      // 저장 Data 추출하기
      Enumeration enu = request.getParameterNames();
      while (enu.hasMoreElements()) {
         String name = (String) enu.nextElement();
         String[] values = request.getParameterValues(name);
         dataMap.put(name, values);
      }
      
      Map<String, String> result = new HashMap<String, String>();
      try {
    	  education_manageService.saveData(dataMap);   
         result.put("Code","0");
         result.put("Message","저장되었습니다");
      }catch(Exception e) {
         result.put("Code","-1");
         result.put("Message","저장에 실패하였습니다");
         e.printStackTrace();
      }
      
      resultMap.put("Result", result);         
        return resultMap;
   }
   
   
   
   
   @Override
   @RequestMapping(value = "/human/p0007/insertData2.do", method = { RequestMethod.GET, RequestMethod.POST })
   @ResponseBody
   public Map saveData2(HttpServletRequest request, HttpServletResponse response) throws Exception {
      request.setCharacterEncoding("utf-8");
      Map<String, String[]> dataMap = new HashMap<String, String[]>(); // 저장할Data
      Map<String, Object> resultMap = new HashMap<String, Object>(); // 처리결과
      
      // 저장 Data 추출하기
      Enumeration enu = request.getParameterNames();
      while (enu.hasMoreElements()) {
         String name = (String) enu.nextElement();
         String[] values = request.getParameterValues(name);
         dataMap.put(name, values);
      }
      
      Map<String, String> result = new HashMap<String, String>();
      try {
    	  education_manageService.saveData2(dataMap);   
         result.put("Code","0");
         result.put("Message","저장되었습니다");
      }catch(Exception e) {
         result.put("Code","-1");
         result.put("Message","저장에 실패하였습니다");
         e.printStackTrace();
      }
      
      resultMap.put("Result", result);         
        return resultMap;
   }
   
   
   @Override
   @RequestMapping(value = "/human/p0007/insertData3.do", method = { RequestMethod.GET, RequestMethod.POST })
   @ResponseBody
   public Map saveData3(HttpServletRequest request, HttpServletResponse response) throws Exception {
      request.setCharacterEncoding("utf-8");
      Map<String, String[]> dataMap = new HashMap<String, String[]>(); // 저장할Data
      Map<String, Object> resultMap = new HashMap<String, Object>(); // 처리결과
      
      // 저장 Data 추출하기
      Enumeration enu = request.getParameterNames();
      while (enu.hasMoreElements()) {
         String name = (String) enu.nextElement();
         String[] values = request.getParameterValues(name);
         dataMap.put(name, values);
      }
      
      Map<String, String> result = new HashMap<String, String>();
      try {
    	  education_manageService.saveData3(dataMap);   
         result.put("Code","0");
         result.put("Message","저장되었습니다");
      }catch(Exception e) {
         result.put("Code","-1");
         result.put("Message","저장에 실패하였습니다");
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
         viewName = viewName.substring(viewName.lastIndexOf("/",1), viewName.length());
      }
      return viewName;
   }

}
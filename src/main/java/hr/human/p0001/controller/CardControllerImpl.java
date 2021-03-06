package hr.human.p0001.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hr.human.p0001.service.CardService;
import hr.human.p0001.vo.CardVO;
import hr.human.p0001.vo.Com_codeVO;
import hr.human.p0001.vo.CardFamVO;
import hr.human.p0001.dao.CardDAO;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.ui.ModelMap;

import hr.elect.p0002.vo.SignImageVO;

import project.common.FileUtil;
import project.common.FileVO;

@Controller("h_insaCardController")
public class CardControllerImpl implements CardController{
	private static final Logger logger = LoggerFactory.getLogger(CardControllerImpl.class);
	
	@Autowired
	private CardDAO CardDAO;
	
	@Autowired
	CardService p0001Service;
	@Autowired
	CardVO p0001VO;
	@Autowired
	CardFamVO CardFamVO;
	
	//test페이지
	@Override
	@RequestMapping(value = "/human/p0001/sample.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView sample(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		viewName = "/human/p0001/sample";
		request.setCharacterEncoding("utf-8");
		ModelAndView main = new ModelAndView(viewName);
		return main;
	}
	
	
	//인사기록카드_페이지
	@Override
	@RequestMapping(value = "/human/p0001/insa_card.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView insaCard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		viewName = "/human/p0001/insa_card";
		request.setCharacterEncoding("utf-8");
		ModelAndView main = new ModelAndView(viewName);
		
		return main;
	}
	
	@Override
	@RequestMapping(value = "/human/p0001/searchList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map searchList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, Object> searchMap = new HashMap<String, Object>(); // 검색조건
		Map<String, Object> resultMap = new HashMap<String, Object>(); // 조회결과
		// 검색조건설정
		searchMap.put("person_BC_OUTPUT", request.getParameter("person_BC_OUTPUT"));
		
		//데이터 조회
		List<CardVO> data = p0001Service.searchList(searchMap);
        resultMap.put("Data", data);
        
        return resultMap;
	}
	
	//인사기록카드_조회
		@Override
		@RequestMapping(value = "/human/p0001/ISA.do", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public Map ISA(HttpServletRequest request, HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("utf-8");
			Map<String, Object> searchMap = new HashMap<String, Object>(); // 검색조건
			Map<String, Object> resultMap = new HashMap<String, Object>(); // 조회결과
			// 검색조건설정
			
			//데이터 조회
			List<CardVO> data = p0001Service.ISA(searchMap);
	        resultMap.put("Data", data);
	        
	        return resultMap;
		}
		
	// 인사기록카드_select 조회 _ json
		@Override
		@RequestMapping(value = "/human/p0001/ISA_c.do", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public Map ISA_c(HttpServletRequest request, HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("utf-8");
			Map<String, Object> searchMap = new HashMap<String, Object>(); // 검색조건
			Map<String, Object> resultMap = new HashMap<String, Object>(); // 조회결과
			// 검색조건설정
			
			//데이터 조회
			List<CardVO> data = p0001Service.ISA_c(searchMap);
	        resultMap.put("Data", data);
	        return resultMap;
		}
		
	// 인사기록카드_select 조회 _ json
		@Override
		@RequestMapping(value = "/human/p0001/COM_CODE.do", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public Map COM_CODE(HttpServletRequest request, HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("utf-8");
			Map<String, Object> searchMap = new HashMap<String, Object>(); // 검색조건
			Map<String, Object> resultMap = new HashMap<String, Object>(); // 조회결과
			// 검색조건설정

			// 데이터 조회
			List<Com_codeVO> data = p0001Service.COM_CODE(searchMap);
			resultMap.put("Data", data);

			return resultMap;
		}
		
		// 인사기록카드_채용/거주_ json
				@Override
				@RequestMapping(value = "/human/p0001/ISA_cha.do", method = { RequestMethod.GET, RequestMethod.POST })
				@ResponseBody
				public Map ISA_cha(HttpServletRequest request, HttpServletResponse response) throws Exception {
					request.setCharacterEncoding("utf-8");
					Map<String, Object> searchMap = new HashMap<String, Object>(); // 검색조건
					Map<String, Object> resultMap = new HashMap<String, Object>(); // 조회결과
					// 검색조건설정
					searchMap.put("fk_ISA_SAWON_CODE", request.getParameter("fk_ISA_SAWON_CODE"));
					searchMap.put("pk_SAWON_CODE", request.getParameter("pk_SAWON_CODE"));

					// 데이터 조회
					if("".equals(request.getParameter("fk_ISA_SAWON_CODE"))) {
						List<CardVO> data = p0001Service.ISA_cha_N(searchMap);
						resultMap.put("Data", data);
					}else {
						List<CardVO> data = p0001Service.ISA_cha(searchMap);
						resultMap.put("Data", data);
					}
					
					

					return resultMap;
				}
				
			
	@Override
	@RequestMapping(value = "/human/p0001/insertData.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map saveData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, String[]> dataMap = new HashMap<String, String[]>(); // 저장할Daa
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
			p0001Service.saveData(dataMap);
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
	
    /**
     * 결재이미지 저장, 업데이트.
     */
	@RequestMapping(value = "/human/p0001/imageSave")
	@ResponseBody
    public String imageSave(HttpServletRequest request,  HttpServletResponse response , SignImageVO signImageInfo)  throws IOException  {
		response.setContentType("text/html;charset=utf-8");
		
  		String userno = request.getParameter("PK_SAWON_CODE");
  	    
  		signImageInfo.setPK_SAWON_CODE(userno);
  	        
  		FileUtil fs = new FileUtil();
  	       
  		FileVO fileInfo = fs.saveImage(signImageInfo.getPhotofile());
  		if (fileInfo != null) {
  			signImageInfo.setPhoto(fileInfo.getRealname());
  	        CardDAO.updateSignImage(signImageInfo);
  		}
  		
  		String photo = signImageInfo.getPhoto();
        
  		return photo;
    }

	
	@RequestMapping(value = "/human/p0001/imageDelete")
	@ResponseBody
    public void imageDelete(HttpServletRequest request,  HttpServletResponse response , SignImageVO signImageInfo)  throws IOException  {
		response.setContentType("text/html;charset=utf-8");
		
  		String userno = request.getParameter("PK_SAWON_CODE");
        CardDAO.deleteSignImage(signImageInfo);
        
    }
	
	
}

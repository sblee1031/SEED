package hr.human.p0009.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface education_statusController {
	public ModelAndView searchInit(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView ibSheet(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public Map searchList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public Map searchList2(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public Map searchList3(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public Map searchList4(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView tab_education_status(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView education_status(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView education_status2(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public Map saveData(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public Map saveData2(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public Map saveData3(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	
}
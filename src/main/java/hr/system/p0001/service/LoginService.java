package hr.system.p0001.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import hr.system.p0001.vo.SawonVO;
import hr.system.p0001.vo.LoginVO;



public interface LoginService {
	
	public SawonVO selectMember4Login(LoginVO param) throws DataAccessException;
    
    public void insertLogIn(String param) throws DataAccessException;

    public void insertLogOut(String param) throws DataAccessException;

	public void send_mail(SawonVO member, String div) throws Exception;

	public void find_pw(HttpServletResponse response, SawonVO sawonVO) throws Exception;
	
	public SawonVO find_by_Email(SawonVO sawonVO);

}

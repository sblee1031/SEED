package hr.system.p0002.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import hr.system.p0001.vo.SawonVO;
import hr.system.p0002.vo.Insa_personEnroll_VO;//인적 정보
import hr.system.p0002.vo.Insa_emEnroll_VO;//재직 정보
import hr.system.p0002.vo.Insa_salEnroll_VO;//급여 정보
import hr.system.p0002.vo.Insa_popup_VO;//공통 팝업

public interface Insa_infoEnroll_Service {
	public List<SawonVO> searchList(Map<String, Object> searchMap) throws DataAccessException; // 인사정보등록
	public List<Insa_personEnroll_VO> searchList2(Map<String, Object> searchMap) throws DataAccessException; // 인사정보등록-인적 정보
	public List<Insa_emEnroll_VO> searchList3(Map<String, Object> searchMap) throws DataAccessException; // 인사정보등록-재직 정보
	public List<Insa_salEnroll_VO> searchList4(Map<String, Object> searchMap) throws DataAccessException; // 인사정보등록-급여 정보
	
	public List<Insa_popup_VO> common_pop(Map<String, Object> searchMap) throws DataAccessException; // 인사정보등록-공통 팝업
	public List<Insa_popup_VO> dept_pop(Map<String, Object> searchMap) throws DataAccessException; // 인사정보등록-부서 팝업
	public List<Insa_popup_VO> hobong_pop(Map<String, Object> searchMap) throws DataAccessException; // 인사정보등록-호봉 팝업
	
	public void saveData(Map<String, String[]> dataMap)  throws DataAccessException ; // 위와 순서 동일
	public void saveData2(Map<String, String[]> dataMap)  throws DataAccessException ;
	public void saveData3(Map<String, String[]> dataMap)  throws DataAccessException ;
	public void saveData4(Map<String, String[]> dataMap)  throws DataAccessException ;

	public void updateMember(Map<String, Object> dataMap);

	public void insertMember(Map<String, Object> dataMap);
}

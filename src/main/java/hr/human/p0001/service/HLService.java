package hr.human.p0001.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import hr.human.p0001.vo.CarVO;
import hr.human.p0001.vo.CardFamVO;
import hr.human.p0001.vo.CardVO;
import hr.human.p0001.vo.CertVO;
import hr.human.p0001.vo.Com_codeVO;
import hr.human.p0001.vo.HlVO;

public interface HLService {
	public List<HlVO> ISA_hl(Map<String, Object> searchMap) throws DataAccessException;

	public void saveDataHL(Map<String, String[]> dataMap, String fk_HL_SAWON_CODE) throws DataAccessException;
}

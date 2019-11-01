package hr.attendance.p0001.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import hr.attendance.p0001.vo.day_regist_VO;
import hr.system.p0001.vo.SawonVO;




@Repository("day_regist_DAO")
public class day_regist_DAOImpl implements day_regist_DAO {
	@Autowired
	private SqlSession sqlSession;

	//����� ȭ�� ��ȸ
	@Override
	public List<day_regist_VO> searchList(Map<String, Object> searchMap) throws DataAccessException {
		List<day_regist_VO> list = sqlSession.selectList("hr.attendance.p0001.searchList", searchMap);
		return list;
	}

	
	//����� ȭ�� ��ȸ(������)
	@Override
	public List<day_regist_VO> da_searchList(Map<String, Object> searchMap) throws DataAccessException {
		List<day_regist_VO> list = sqlSession.selectList("hr.attendance.p0001.searchList", searchMap);
		return list;
	}

	

	//�߰�, ���
	@Override
	public void insertData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("hr.attendance.p0001.insertData", row);
	}
	


	//����
	@Override
	public void updateData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("hr.attendance.p0001.updateData", row);
	}

	//����
	@Override
	public void deleteData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("hr.attendance.p0001.deleteData", row);
	}

	
	//�� �߰��� ���ֱ�
		@Override
		public void GetData(Map<String, String> row) throws DataAccessException {
			sqlSession.update("hr.attendance.p0001.getData", row);
		}
}
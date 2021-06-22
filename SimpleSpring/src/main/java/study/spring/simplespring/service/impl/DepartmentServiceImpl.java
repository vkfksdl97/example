package study.spring.simplespring.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import study.spring.simplespring.model.Department;
import study.spring.simplespring.service.DepartmentService;

/** 학과 데이터 관리 기능을 제공하기 위한 Service 계층에 대한 구현체 */
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService{
	/** MyBatis 세션 객체 주입 설정 */
	@Autowired
	SqlSession sqlSession;
	
	/**
	 * 학과 데이터 상세 조회
	 * @param Department 조회할 학과의 일련번호를 담고 있는 Beans
	 * @return 조회된 데이터가 저장된 Beans
	 * @throw Exception
	 */
	@Override
	public Department getDepartmentItem(Department input) throws Exception {
		Department result = null;
		
		try {
			result = sqlSession.selectOne("DepartmentMapper.selectItem", input);
			
			if (result == null) {
				throw new NullPointerException("result=null");
			}
		} catch (NullPointerException e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		
		return result;
	}

	@Override
	public List<Department> getDepartmentList(Department input) throws Exception {
		List<Department> result = null;
		
		try {
			result = sqlSession.selectList("DepartmentMapper.selectList", input);
			
			if (result == null) {
				throw new NullPointerException("result=null");
			}
		} catch (NullPointerException e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		
		return result;
	}
	
	/** 전체 데이터 수 조회 */
	@Override
	public int getDepartmentCount(Department input) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.selectOne("DepartmentMapper.selectCountAll", input);
			
			if (result == 0) {
				throw new NullPointerException("result=null");
			}
		} catch (NullPointerException e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		
		return result;
	}

	@Override
	public int addDepartment(Department input) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.insert("DepartmentMapper.insertItem", input);
			
			if (result == 0) {
				throw new NullPointerException("result=null");
			}
		} catch (NullPointerException e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("저장된 데이터가 없습니다.");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 저장에 실패했습니다.");
		}
		
		return result;
	}

	@Override
	public int editDepartment(Department input) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.update("DepartmentMapper.updateItem", input);
			
			if (result == 0) {
				throw new NullPointerException("result=null");
			}
		} catch (NullPointerException e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("수정된 데이터가 없습니다.");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 수정에 실패했습니다.");
		}
		
		return result;
	}

	@Override
	public int deleteDepartment(Department input) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.delete("DepartmentMapper.deleteItem", input);
			
			if (result == 0) {
				throw new NullPointerException("result=null");
			}
		} catch (NullPointerException e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("삭제된 데이터가 없습니다.");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 삭제에 실패했습니다.");
		}
		
		return result;
	}

}

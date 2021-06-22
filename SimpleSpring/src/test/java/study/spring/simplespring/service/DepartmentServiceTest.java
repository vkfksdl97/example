package study.spring.simplespring.service;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;
import study.spring.simplespring.model.Department;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*-context.xml" })
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentServiceTest {
	@Autowired
	private DepartmentService DS;
	
	/** 단일행 조회 테스트 */
	@Test
	public void testA() {
		Department input = new Department();
		input.setDeptno(101);
		
		Department output = null;
		
		try {
			output = DS.getDepartmentItem(input);
			log.debug(output.toString());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}	// end testA
	
	/** 다중행 조회 테스트 */
	@Test
	public void testB() {
		Department input = new Department();
		input.setDname("공학");
		
		List<Department> output = null;
		
		try {
			output = DS.getDepartmentList(input);
			
			for (Department item : output) {
				log.debug(item.toString());
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}	// end testB
	
	/** 전체 데이터 수 조회 */
	@Test
	public void testC() {
		int count = 0;
		
		try {
			count = DS.getDepartmentCount(null);
			log.debug("전체 데이터 수 : " + count);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}	// end testC
	
	/** 조건에 따른 데이터 수 조회 */
	@Test
	public void testD() {
		int count = 0;
		
		Department input = new Department();
		input.setDname("공학");
		
		try {
			count = DS.getDepartmentCount(input);
			log.debug("공학을 포함하는 학과이름을 갖는 데이터 수 : " + count);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}	// end testD
	
	/** 데이터 저장 테스트 */
	@Test
	public void testE() {
		Department input = new Department();
		input.setDname("신규학과");
		
		int output = 0;
		
		try {
			output = DS.addDepartment(input);
			log.debug("저장된 데이터 수 : " + output);
			// [중요!!] 생성된 PK값은 MyBatis에 의해 입력 파라미터의 해당 멤버변수에 셋팅된다.
			log.debug("생성된 PK값 : " + input.getDeptno());
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}	// end testE
	
	/** 데이터 수정 테스트 */
	@Test
	public void testF() {
		Department input = new Department();
		input.setDeptno(204);
		input.setDname("수정학과");
		input.setLoc("5호관");
		
		int output = 0;
		
		try {
			output = DS.editDepartment(input);
			log.debug("수정된 데이터 수 : " + output);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}	// end testF
	
	/** 데이터 삭제 테스트 */
	@Test
	public void testG() {
		Department input = new Department();
		input.setDeptno(204);
		
		int output = 0;
		
		try {
			output = DS.deleteDepartment(input);
			log.debug("삭제된 데이터 수 : " +  output);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}	// end testG
}

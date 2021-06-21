package study.spring.simplespring.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
import uap_clj.java.api.Browser;
import uap_clj.java.api.Device;
import uap_clj.java.api.OS;

/**
 * Handles requests for the application home page.
 */
@Slf4j
// 모든 컨트롤러 클래스가 정의해야 하는 어노테이션
@Controller
public class HomeController {
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * 웹 페이지의 URL과 접근 방식 정의
	 */
	@RequestMapping(value = {"/", "/simple/home.do"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String home(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {	// 메서드 파라미터 필요에 따라 다양한 패턴이 존재함
		// model 객체를 통해서 View에 데이터 전달
		log.debug("Locale: " + locale.getLanguage());						
		log.debug("Locale: " + locale.getCountry());
		log.debug("Locale: " + locale.getDisplayLanguage());						
		log.debug("Locale: " + locale.getDisplayCountry());						
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		// View에 key와 value의 쌍으로 데이터 전달
		// view에서는 JSTL을 통해 Model2와 같은 방식으로 데이터 접근
		model.addAttribute("serverTime", formattedDate );
		
		// 이 컨트롤러가 호출할 View의 경로 void형인 경우 메서드 이름을 View 이름으로 인식한다.
		return "home";
	}	// end home
	
	/**
	 * UserAgent 정보를 확인하기 위한 페이지
	 * 
	 * @param model
	 * @param request
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/user_agent.do", method = RequestMethod.GET)
	public String userAgent(Model model, HttpServletRequest request) {
		
		/** 1) 접근한 클라이언트의 HTTP 헤더 정보 가져오기 */
		String ua = request.getHeader("User-Agent");
		
		/** 2) "uap" 라이브러리의 기능을 통해 UserAgent 정보 파싱 */
		// 웹 프라우저 정보
		Map<String, String> browser = Browser.lookup(ua);
		
		// 운영체제 정보
		Map<String, String> os = (Map<String, String>) OS.lookup(ua);
		
		// 디바이스 정보
		Map<String, String> device = (Map<String, String>) Device.lookup(ua);
		
		/** 3) 추출된 정보들을 출력하기 위해 문자열로 묶기 */
		String browserStr = String.format("- Browser: {family=%s, patch=%s, major=%s, minor=%s}",
				browser.get("family"), browser.get("patch"), browser.get("major"), browser.get("minor"));
		
		String osStr = String.format("- OS: {family=%s, patch=%s, patch_minor=%s, major=%s, minor=%s}",
				os.get("family"), os.get("patch"), os.get("patch_minor"), os.get("major"), os.get("minor"));
		
		String deviceStr = String.format("- Device: {family=%s, model=%s, brand=%s}",
				device.get("family"), device.get("model"), device.get("brand"));
		
		/** 4) 화면에 출력하기 위해 View에 전달 */
		model.addAttribute("user_agent", ua);
		model.addAttribute("browser", browserStr);
		model.addAttribute("os", osStr);
		model.addAttribute("device", deviceStr);
		
		/** 5) View 이름 리턴 */
		return "user_agent";
	}	// end userAgent
	
}

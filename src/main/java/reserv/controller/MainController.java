package reserv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	//회사 소개 화면으로 연결
	@RequestMapping("/intro.rj")
	public String intro() {
		return "main/intro";
	}
	
	//이용가이드 화면으로 연결
	@RequestMapping("/guide.rj")
	public String guide() {
		return "main/guide";
	}
	
	//서비스 소개 화면으로 연결
	@RequestMapping("/intro_serv.rj")
	public String intro_serv() {
		return "main/intro_serv";
	}
}

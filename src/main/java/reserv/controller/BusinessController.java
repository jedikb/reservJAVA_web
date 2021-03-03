package reserv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BusinessController {

	//매장 정보 화면으로 연결
	@RequestMapping("/list.bu")
	public String list() {
		return "business/list";
	}
}

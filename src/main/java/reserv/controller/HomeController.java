package reserv.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String home(HttpSession session) {
		//session.setAttribute("category", session); // 아래와 동일
		session.removeAttribute("category");
		return "main";
	}
	
}

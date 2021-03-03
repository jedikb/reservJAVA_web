package reserv.controller;

import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

	//상품 정보 화면으로 연결
	@RequestMapping("/list.pr")
	public String list() {
		return "product/list";
	}
	
	public String insert(Model model) {
		//from 에서 넘어온 List 를 담는다
		
		
		
		return "redirect:product/list";
	}
}

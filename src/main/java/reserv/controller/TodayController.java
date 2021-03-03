package reserv.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import business.BusinessVO;
import today.TodayServiceImpl;
import today.TodayVO;

@Controller
public class TodayController {
	@Autowired private TodayServiceImpl service;
	
	@RequestMapping("/list.today")
	public String list(HttpSession session, Model model, int member_code) {
		//로그인 제한 걸기
		model.addAttribute("loginInfo", session.getAttribute("loginInfo"));
		
		//business_code 불러오기 위한 빌드업
		BusinessVO vo =  service.getBusinessVO(member_code);
		int business_code = vo.getBusiness_code();
		
		//todayList 불러오기
		model.addAttribute("list", service.list(business_code));
		//List<TodayVO> list = service.list(business_code);
		
		//System.out.println(list.get(0).getProduct_name());
		return "today/list";
	}
}

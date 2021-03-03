package reserv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReservationController {
	
	//예약 현황 화면으로 연결
	@RequestMapping("/list_res.re")
	public String list_res() {
		return "reservation/list_res";
	}
	
	//결재 내역 화면으로 연결
	@RequestMapping("/list_pay.re")
	public String list_pay() {
		return "reservation/list_pay";
	}

}

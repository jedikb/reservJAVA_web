package reserv.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import member.MemberServiceImpl;
import member.MemberVO;

@Controller
public class MemberController {
	@Autowired private MemberServiceImpl service;

	// 로그인 화면으로 연결
	@RequestMapping("/login")
	public String login() {
		return "member/login";
	}

	// 회원가입 화면으로 연결
	@RequestMapping("/join")
	public String join() {
		return "member/join";
	}

	//회원가입처리 요청
		@ResponseBody @RequestMapping(value="/member"
							, produces="text/html; charset=utf-8")
		public String join(MemberVO vo, HttpSession session, HttpServletRequest request) {
			//화면에서 입력한 회원정보를 DB에 저장한 후 홈으로 연결
			StringBuffer msg = new StringBuffer("<script>");
			if( service.member_join(vo) ) {
				//common.sendEmail(session, vo.getEmail(), vo.getName());
				msg.append("alert('회원가입을 축하합니다 ^^'); location='"+
										 request.getContextPath() + "'; ");
//				msg.append("alert('회원가입을 축하합니다 ^^'); location='index'; ");
			}else {
				msg.append("alert('회원가입 실패 ㅠㅠ'); history.go(-1)");
			}
			msg.append("</script>");
			return msg.toString();
		}
	

	// reserv 자체 로그인처리 요청
	@ResponseBody
	@RequestMapping("/reservlogin")
	public boolean login(String id, String pw, HttpSession session) {
		// 화면에서 입력한 아이디/비번이 일치하는 회원정보를 조회해온다
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", id);
		map.put("member_pw", pw);
		MemberVO vo = service.member_login(map);
		// 로그인한 회원정보를 세션에 담아둔다
		session.setAttribute("loginInfo", vo);
		return vo == null ? false : true;
	}

	// 로그아웃처리 요청
	@RequestMapping("/logout")
	public String logout(HttpSession session) {

		String social = ((MemberVO) session.getAttribute("loginInfo")).getSocial_type();
		session.removeAttribute("loginInfo");

//			GET /oauth/logout
		// ?client_id=?
		// &logout_redirect_uri=?
		// &state=? HTTP/1.1
//					Host: kauth.kakao.com
		// 카카오로그인인 경우 카카오계정도 함께 로그아웃되게 하자
		if (social != null && social.equals("kakao")) {
			StringBuffer url = new StringBuffer("https://kauth.kakao.com/oauth/logout");
			// url.append("?client_id=").append(kakao_client_id);
			url.append("&logout_redirect_uri=").append("http://localhost/iot");
			return "redirect:" + url.toString();
		} else
			return "redirect:/";
	}
}

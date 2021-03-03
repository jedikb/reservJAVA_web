package reserv.controller;

import java.io.File;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import common.CommonService;
import member.MemberVO;
import question.QuestionCommentVO;
import question.QuestionPage;
import question.QuestionServiceImpl;
import question.QuestionVO;

@Controller
public class QuestionController {
	@Autowired private QuestionServiceImpl service;
	@Autowired private QuestionPage page;
	@Autowired private CommonService common;
	
	//문의글 댓글 삭제 요청
	@ResponseBody @RequestMapping("/question/comment/delete/{reply_code}")
	public void comment_delete(@PathVariable int reply_code) {
		service.question_comment_delete(reply_code);
	}
	
	//문의글 댓글 목록 조회
	@RequestMapping("/question/comment/{reply_sub_code}")
	public String comment_list(@PathVariable int reply_sub_code, Model model) {
		model.addAttribute("list", service.question_comment_list(reply_sub_code) );
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("lf", "\n");
		return "question/comment/comment_list";
	}
	
	//문의글 댓글 변경 저장 처리 요청
	@ResponseBody @RequestMapping(value="/question/comment/update"
								, produces="application/text; charset=utf-8")
	public String comment_update( @RequestBody QuestionCommentVO vo) { //json형태의 데이터를 담기 위해서는 @RequestBody를 써주면 된다
		//화면에서 변경 입력한 정보를 DB에 저장한 후 호출한 곳으로 간다
		return service.question_comment_update(vo) > 0 ? "성공" : "실패";
	}
	
	//문의글 댓글 저장처리 요청
	@ResponseBody @RequestMapping("/question/comment/insert")
	public boolean comment_insert(QuestionCommentVO vo, HttpSession session) {
		//화면에서 입력한 댓글정보를 DB에 저장한 후 
		MemberVO user = (MemberVO)session.getAttribute("loginInfo");
		vo.setReply_member_code( user.getMember_code() );
		return service.question_comment_insert(vo) > 0 ? true : false;
	}
	
	//------------------------------------------------------------
	//문의글 수정 저장 처리 요청
	@RequestMapping("/update.qu")
	public String update(QuestionVO vo, String attach
						, HttpSession session
						, MultipartFile file, Model model) {
		QuestionVO board = service.question_view( vo.getBoard_code() );
		String uuid =  "D:\\Dropbox\\TeamProject\\files"
						+ "/" + board.getBoard_filepath();
		//첨부파일 관련처리
		if(!file.isEmpty()) {
			vo.setBoard_file(file.getOriginalFilename());
			vo.setBoard_filepath(common.fileUpload(session, file, "question"));
			
			//기존에 첨부된 파일이 있었다면 서버에서 삭제
			if( board.getBoard_file() != null) {
				File f = new File( uuid );
				if( f.exists() ) f.delete();
			}
		} else {
			//기존에 첨부된 파일을 삭제/ 기존에 첨부파일이 없는 경우
			if( attach.isEmpty() ) {
				if( board.getBoard_file() != null ) {
					File f = new File( uuid ); 
					if( f.exists() ) f.delete();
				}
			} else {
				// 기존에 첨부된 파일을 그대로 사용하는 경우
				vo.setBoard_file(board.getBoard_file());
				vo.setBoard_filepath(board.getBoard_filepath());
			}
		}
		
		//화면에서 입력한 정보를 DB에 변경 저장한 후 보기화면으로 연결
		service.question_update(vo);
		model.addAttribute("url", "view.qu");
		model.addAttribute("board_code", vo.getBoard_code());
		return "question/redirect";
	}
	
	//문의글 수정 화면 요청
	@RequestMapping("/modify.qu")
	public String modify(int board_code, Model model) {
		//해당 글의 정보를 DB에서 조회하여 수정 화면에 출력
		model.addAttribute("vo", service.question_view(board_code));
		return "question/modify";
	}
	
	//문의글 삭제 처리 요청
	@RequestMapping("/delete.qu")
	public String delete(int board_code, HttpSession session, Model model) {
		//첨부 파일이 있는 경우 물리적 서버의 영역에서 파일을 삭제
		QuestionVO vo = service.question_view(board_code);
		if(vo.getBoard_file() !=null) {
			File file = new File( "D:\\Dropbox\\TeamProject\\files"
								+ "/" + vo.getBoard_filepath());
			if(file.exists()) file.delete();
		}
		//선택한 글을 DB에서 삭제한 후 목록화면으로 연결
		service.question_delete(board_code);
		
		model.addAttribute("page", page);
		model.addAttribute("url", "list.qu");
		//return "redirect:list.bo";
		return "question/redirect";
	}
	
	
	//첨부파일 다운로드 요청
	@ResponseBody @RequestMapping("/download.qu")
	public void download(int board_code, HttpSession session, HttpServletResponse response) {
		QuestionVO vo = service.question_view(board_code);
		common.fileDownLoad(vo.getBoard_file(), vo.getBoard_filepath(), session, response);
	}
	
	//문의글 글 상세보기 화면 요청
	@RequestMapping("/view.qu")
	public String view(int board_code, Model model) {
		service.question_read(board_code);
		//선택한 글의 정보를 DB에서 조회하여 보기화면에 출력
		model.addAttribute("vo", service.question_view(board_code));
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("crlf", "\n");
		model.addAttribute("page", page);
		return "question/view";
	}
	
	
	//문의글 저장처리 요청
	@RequestMapping("/insert.qu")
	public String insert(QuestionVO vo, MultipartFile file, 
					HttpSession session) {
		//화면에서 입력한 정보를 DB에 저장한 후 목록화면으로 연결
		if(!file.isEmpty()) {
			vo.setBoard_file(file.getOriginalFilename());
			vo.setBoard_filepath(common.fileUpload(session, file, "question"));
		}
		
		MemberVO user = (MemberVO) session.getAttribute("loginInfo");
		vo.setBoard_member_code(user.getMember_code());
		service.question_insert(vo);
		return "redirect:list.qu";
	}
	
	//문의글 쓰기 화면 요청
	@RequestMapping("/new.qu")
	public String question() {
		return "question/new";
	}
	
	//문의글 목록 조회
	@RequestMapping("/list.qu")
	public String list(HttpSession session, Model model
			, String search, String keyword
			, @RequestParam(defaultValue = "10") int pageList 
			, @RequestParam(defaultValue = "list")String viewType
			, @RequestParam(defaultValue = "1") int curPage) {
		session.setAttribute("category", "qu");
		//DB에서 문의글 목록을 조회하여 화면에 출력
		if (search == null) {
			search = "all";
		}
		if (keyword == null) {
			keyword = "";
		}
		page.setCurPage(curPage);
		page.setSearch(search);
		page.setKeyword(keyword);
		page.setPageList(pageList);
		page.setViewType(viewType);
		model.addAttribute("page", service.question_list(page));		
		return "question/list";
	}
}

package reserv.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import common.CommonService;
import member.MemberVO;
import notice.NoticeCommentVO;
import notice.NoticePage;
import notice.NoticeServiceImpl;
import notice.NoticeVO;

@Controller
public class NoticeController {
	@Autowired private NoticeServiceImpl service;
	@Autowired private NoticePage page;
	@Autowired private CommonService common;
	
	//댓글 삭제 요청
	@ResponseBody @RequestMapping("/notice/comment/delete/{reply_code}")
	public void comment_delete(@PathVariable int reply_code) {
		service.notice_comment_delete(reply_code);
	}
	
	//댓글 목록 조회
	@RequestMapping("/notice/comment/{reply_sub_code}")
	public String comment_list(@PathVariable int reply_sub_code, Model model) {
		model.addAttribute("list", service.notice_comment_list(reply_sub_code) );
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("lf", "\n");
		return "notice/comment/comment_list";
	}
	
	//댓글 변경 저장 처리 요청
	@ResponseBody @RequestMapping(value="/notice/comment/update"
								, produces="application/text; charset=utf-8")
	public String comment_update( @RequestBody NoticeCommentVO vo) { //json형태의 데이터를 담기 위해서는 @RequestBody를 써주면 된다
		//화면에서 변경 입력한 정보를 DB에 저장한 후 호출한 곳으로 간다
		return service.notice_comment_update(vo) > 0 ? "성공" : "실패";
	}
	
	//댓글 저장처리 요청
	@ResponseBody @RequestMapping("/notice/comment/insert")
	public boolean comment_insert(NoticeCommentVO vo, HttpSession session) {
		//화면에서 입력한 댓글정보를 DB에 저장한 후 
		MemberVO user = (MemberVO)session.getAttribute("loginInfo");
		vo.setReply_member_code( user.getMember_code() );
		return service.notice_comment_insert(vo) > 0 ? true : false;
	}
	
	//------------------------------------------------------------
	//글 수정 저장 처리 요청
	@RequestMapping("/update.no")
	public String update(NoticeVO vo, String attach
						, HttpSession session
						, MultipartFile file, Model model) {
		NoticeVO board = service.board_view( vo.getBoard_code() );
		String uuid =  "D:\\Dropbox\\TeamProject\\files"
						+ "/" + board.getBoard_filepath();
		//첨부파일 관련처리
		if(!file.isEmpty()) {
			vo.setBoard_file(file.getOriginalFilename());
			vo.setBoard_filepath(common.fileUpload(session, file, "notice"));
			
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
		service.board_update(vo);
		model.addAttribute("url", "view.no");
		model.addAttribute("board_code", vo.getBoard_code());
		return "notice/redirect";
	}
	
	//글 수정 화면 요청
	@RequestMapping("/modify.no")
	public String modify(int board_code, Model model) {
		//해당 글의 정보를 DB에서 조회하여 수정 화면에 출력
		model.addAttribute("vo", service.board_view(board_code));
		return "notice/modify";
	}
	
	//글 삭제 처리 요청
	@RequestMapping("/delete.no")
	public String delete(int board_code, HttpSession session, Model model) {
		//첨부 파일이 있는 경우 물리적 서버의 영역에서 파일을 삭제
		NoticeVO vo = service.board_view(board_code);
		if(vo.getBoard_file() !=null) {
			File file = new File( "D:\\Dropbox\\TeamProject\\files"
								+ "/" + vo.getBoard_filepath());
			if(file.exists()) file.delete();
		}
		//선택한 글을 DB에서 삭제한 후 목록화면으로 연결
		service.board_delete(board_code);
		
		model.addAttribute("page", page);
		model.addAttribute("url", "list.no");
		return "notice/redirect";
	}
	
	//첨부파일 다운로드 요청
	@ResponseBody @RequestMapping("/download.no")
	public void download(int board_code, HttpSession session, HttpServletResponse response) {
		NoticeVO vo = service.board_view(board_code);
		common.fileDownLoad(vo.getBoard_file(), vo.getBoard_filepath(), session, response);
	}
	
	//글 상세보기 화면 요청
	@RequestMapping("/view.no")
	public String view(int board_code, Model model) {
		service.board_read(board_code);
		//선택한 글의 정보를 DB에서 조회하여 보기화면에 출력
		model.addAttribute("vo", service.board_view(board_code));
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("crlf", "\n");
		model.addAttribute("page", page);
		return "notice/view";
	}
	
	//글 저장처리 요청
	@RequestMapping("/insert.no")
	public String insert(NoticeVO vo, MultipartFile file, 
					HttpSession session) {
		//화면에서 입력한 정보를 DB에 저장한 후 목록화면으로 연결
		if(!file.isEmpty()) {
			vo.setBoard_file(file.getOriginalFilename());
			vo.setBoard_filepath(common.fileUpload(session, file, "notice"));
		}
		
		MemberVO user = (MemberVO) session.getAttribute("loginInfo");
		vo.setBoard_member_code(user.getMember_code());
		service.board_insert(vo);
		return "redirect:list.no";
	}
	
	//글 쓰기 화면 요청
	@RequestMapping("/new.no")
	public String notice() {
		return "notice/new";
	}
	
	//글 목록 조회
	@RequestMapping("/list.no")
	public String list(HttpSession session, Model model
			, String search, String keyword
			, @RequestParam(defaultValue = "10") int pageList 
			, @RequestParam(defaultValue = "list")String viewType
			, @RequestParam(defaultValue = "1") int curPage) {
		session.setAttribute("category", "no");
		//DB에서 notice 목록을 조회하여 화면에 출력
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
		model.addAttribute("page", service.board_list(page));		
		return "notice/list";
	}
}
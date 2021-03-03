package notice;

import java.util.List;

public interface NoticeService {
	//CRUD
	int board_insert(NoticeVO vo);				//신규 저장
	NoticePage board_list(NoticePage page);		//페이지 단위 목록 조회
	NoticeVO board_view(int board_code);					//선택한 방명록 보기 조회
	void board_read(int board_code);					//선택한 글의 조회 수 증가 처리
	int board_update(NoticeVO vo);				//선택한 글의 변경 저장
	int board_delete(int board_code);					//선택한 공지글 삭제
	
	// ---- 댓글을 채팅 방처럼 보이게 해보자
	int notice_comment_insert(NoticeCommentVO vo);// 댓글 저장 처리
	List<NoticeVO> notice_comment_list(int reply_sub_code);// 댓글 목록 조회
	int notice_comment_update(NoticeCommentVO vo);// 댓글 변경 저장 처리
	int notice_comment_delete(int reply_code);// 댓글 삭제 처리
}

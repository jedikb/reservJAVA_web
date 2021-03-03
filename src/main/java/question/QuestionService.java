package question;

import java.util.List;

public interface QuestionService {
	int question_insert(QuestionVO vo); //문의글 신규 저장
	QuestionPage question_list(QuestionPage page);// 페이지 단위 목록 조회
	QuestionVO question_view(int board_code);// 선택한 문의 글 보기
	void question_read(int board_code);// 선택한 글의 조회 수 증가 처리
	int question_update(QuestionVO vo);// 선택한 글의 변경 저장
	int question_delete(int board_code);// 선택한 글 삭제
	
	// ---- 댓글을 채팅 방처럼 보이게 해보자
	int question_comment_insert(QuestionCommentVO vo);// 댓글 저장 처리
	List<QuestionVO> question_comment_list(int reply_sub_code);// 댓글 목록 조회
	int question_comment_update(QuestionCommentVO vo);// 댓글 변경 저장 처리
	int question_comment_delete(int reply_code);// 댓글 삭제 처리
}

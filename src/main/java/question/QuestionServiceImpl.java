package question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired private QuestionDAO dao;

	@Override
	public int question_insert(QuestionVO vo) {
		return dao.question_insert(vo);
	}

	@Override
	public QuestionPage question_list(QuestionPage page) {
		return dao.question_list(page);
	}

	@Override
	public QuestionVO question_view(int board_code) {
		return dao.question_view(board_code);
	}

	@Override
	public void question_read(int board_code) {
		dao.question_read(board_code);
	}

	@Override
	public int question_update(QuestionVO vo) {
		return dao.question_update(vo);
	}

	@Override
	public int question_delete(int board_code) {
		return dao.question_delete(board_code);
	}

	@Override
	public int question_comment_insert(QuestionCommentVO vo) {
		return dao.question_comment_insert(vo);
	}

	@Override
	public List<QuestionVO> question_comment_list(int reply_sub_code) {
		return dao.question_comment_list(reply_sub_code);
	}

	@Override
	public int question_comment_update(QuestionCommentVO vo) {
		return dao.question_comment_update(vo);
	}

	@Override
	public int question_comment_delete(int reply_code) {
		return dao.question_comment_delete(reply_code);
	}
}

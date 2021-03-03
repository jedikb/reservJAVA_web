package question;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDAO implements QuestionService {
	@Autowired @Qualifier("bteam") private SqlSession sql;
	
	@Override
	public int question_insert(QuestionVO vo) {
		return sql.insert("question.mapper.insert", vo);
	}

	@Override
	public QuestionPage question_list(QuestionPage page) {
		page.setTotalList( sql.selectOne("question.mapper.totalList", page) );
		page.setList( sql.selectList("question.mapper.list", page) );
		return page;
	}

	@Override
	public QuestionVO question_view(int board_code) {
		return sql.selectOne("question.mapper.view", board_code);
	}

	@Override
	public void question_read(int board_code) {
		sql.update("question.mapper.read", board_code);
	}

	@Override
	public int question_update(QuestionVO vo) {
		return sql.update("question.mapper.update", vo);
	}

	@Override
	public int question_delete(int board_code) {
		return sql.delete("question.mapper.delete", board_code);
	}

	@Override
	public int question_comment_insert(QuestionCommentVO vo) {
		return sql.insert("question.mapper.comment_insert", vo);
	}

	@Override
	public List<QuestionVO> question_comment_list(int reply_sub_code) {
		return sql.selectList("question.mapper.comment_list", reply_sub_code);
	}

	@Override
	public int question_comment_update(QuestionCommentVO vo) {
		return sql.update("question.mapper.comment_update", vo);
	}

	@Override
	public int question_comment_delete(int reply_code) {
		return sql.update("question.mapper.comment_delete", reply_code);
	}

}

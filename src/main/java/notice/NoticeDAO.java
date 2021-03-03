package notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDAO implements NoticeService {
	@Autowired @Qualifier("bteam") private SqlSession sql;

	@Override
	public int board_insert(NoticeVO vo) {
		return sql.insert("notice.mapper.insert", vo);
	}

	@Override
	public NoticePage board_list(NoticePage page) {
		page.setTotalList( sql.selectOne("notice.mapper.totalList", page) );
		page.setList( sql.selectList("notice.mapper.list", page) );
		return page;
	}

	@Override
	public NoticeVO board_view(int board_code) {
		return sql.selectOne("notice.mapper.view", board_code);
	}

	@Override
	public void board_read(int board_code) {
		sql.update("notice.mapper.read", board_code);
	}

	@Override
	public int board_update(NoticeVO vo) {
		return sql.update("notice.mapper.update", vo);
	}

	@Override
	public int board_delete(int board_code) {
		return sql.delete("notice.mapper.delete", board_code);
	}

	@Override
	public int notice_comment_insert(NoticeCommentVO vo) {
		return sql.insert("notice.mapper.comment_insert", vo);
	}

	@Override
	public List<NoticeVO> notice_comment_list(int reply_sub_code) {
		return sql.selectList("notice.mapper.comment_list", reply_sub_code);
	}

	@Override
	public int notice_comment_update(NoticeCommentVO vo) {
		return sql.update("notice.mapper.comment_update", vo);
	}

	@Override
	public int notice_comment_delete(int reply_code) {
		return sql.delete("notice.mapper.comment_delete", reply_code);
	}

	
}

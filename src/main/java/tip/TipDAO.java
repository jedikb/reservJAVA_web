package tip;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class TipDAO implements TipService{
	@Autowired @Qualifier("bteam") private SqlSession sql;

	@Override
	public int board_insert(TipVO vo) {
		return sql.insert("tip.mapper.insert", vo);
	}

	@Override
	public TipPage board_list(TipPage page) {
		page.setTotalList( sql.selectOne("tip.mapper.totalList", page) );
		page.setList( sql.selectList("tip.mapper.list", page) );
		return page;
	}

	@Override
	public TipVO board_view(int board_code) {
		return sql.selectOne("tip.mapper.view", board_code);
	}

	@Override
	public void board_read(int board_code) {
		sql.update("tip.mapper.read", board_code);
	}

	@Override
	public int board_update(TipVO vo) {
		return sql.update("tip.mapper.update", vo);
	}

	@Override
	public int board_delete(int board_code) {
		return sql.delete("tip.mapper.delete", board_code);
	}

	@Override
	public int tip_comment_insert(TipCommentVO vo) {
		return sql.insert("tip.mapper.comment_insert", vo);
	}

	@Override
	public List<TipVO> tip_comment_list(int reply_sub_code) {
		return sql.selectList("tip.mapper.comment_list", reply_sub_code);
	}

	@Override
	public int tip_comment_update(TipCommentVO vo) {
		return sql.update("tip.mapper.comment_update", vo);
	}

	@Override
	public int tip_comment_delete(int reply_code) {
		return sql.update("tip.mapper.comment_delete", reply_code);
	}

}

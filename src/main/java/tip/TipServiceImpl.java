package tip;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipServiceImpl implements TipService {
	@Autowired private TipDAO dao;
	
	@Override
	public int board_insert(TipVO vo) {
		return dao.board_insert(vo);
	}

	@Override
	public TipPage board_list(TipPage page) {
		return dao.board_list(page);
	}

	@Override
	public TipVO board_view(int board_code) {
		return dao.board_view(board_code);
	}

	@Override
	public void board_read(int board_code) {
		dao.board_read(board_code);
	}

	@Override
	public int board_update(TipVO vo) {
		return dao.board_update(vo);
	}

	@Override
	public int board_delete(int board_code) {
		return dao.board_delete(board_code);
	}

	@Override
	public int tip_comment_insert(TipCommentVO vo) {
		return dao.tip_comment_insert(vo);
	}

	@Override
	public List<TipVO> tip_comment_list(int reply_parent_code) {
		return dao.tip_comment_list(reply_parent_code);
	}

	@Override
	public int tip_comment_update(TipCommentVO vo) {
		return dao.tip_comment_update(vo);
	}

	@Override
	public int tip_comment_delete(int reply_code) {
		return dao.tip_comment_delete(reply_code);
	}

}

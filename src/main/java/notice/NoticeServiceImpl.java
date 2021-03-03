package notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired private NoticeDAO dao;
	

	@Override
	public int board_insert(NoticeVO vo) {
		return dao.board_insert(vo);
	}

	@Override
	public NoticePage board_list(NoticePage page) {
		return dao.board_list(page);
	}

	@Override
	public NoticeVO board_view(int board_code) {
		return dao.board_view(board_code);
	}

	@Override
	public void board_read(int board_code) {
		dao.board_read(board_code);
	}

	@Override
	public int board_update(NoticeVO vo) {
		return dao.board_update(vo);
	}

	@Override
	public int board_delete(int board_code) {
		return dao.board_delete(board_code);
	}

	@Override
	public int notice_comment_insert(NoticeCommentVO vo) {
		return dao.notice_comment_insert(vo);
	}

	@Override
	public List<NoticeVO> notice_comment_list(int reply_sub_code) {
		return dao.notice_comment_list(reply_sub_code);
	}

	@Override
	public int notice_comment_update(NoticeCommentVO vo) {
		return dao.notice_comment_update(vo);
	}

	@Override
	public int notice_comment_delete(int reply_code) {
		return dao.notice_comment_delete(reply_code);
	}

}

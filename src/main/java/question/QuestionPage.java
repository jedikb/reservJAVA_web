package question;

import java.util.List;

import org.springframework.stereotype.Component;

import common.PageVO;

@Component
public class QuestionPage extends PageVO{
	private List<QuestionVO> list;

	public List<QuestionVO> getList() {
		return list;
	}
	public void setList(List<QuestionVO> list) {
		this.list = list;
	}
}

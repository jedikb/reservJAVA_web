package tip;

import java.util.Date;

public class TipVO {
	//writer = board_member_code
	//id = board_code
	//no는 no대로 있어야 한다
	//----------- 일단은 그대로page.jsp 를 위해 board_code를 no로 변경한다
	//공통 코드를 위해 다른 것들도 그렇게 해야 할 듯
	private int board_code, no, board_status, board_group_code, board_member_code, board_readcount;
	private String board_subject, board_content, member_name, board_file, board_filepath;
	private Date board_write_date, board_update_date;
	//만들어진 테이블은 써보자!
	//보통은 board_update_date를 보여주고 
	//커서를 가져가면 최초 작성일을 보여준다.
	//이때 다른 정보도 띄울게 있는지 보자.
	
	public String getBoard_file() {
		return board_file;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getBoard_code() {
		return board_code;
	}
	public void setBoard_code(int board_code) {
		this.board_code = board_code;
	}
	public void setBoard_file(String board_file) {
		this.board_file = board_file;
	}
	public String getBoard_filepath() {
		return board_filepath;
	}
	public void setBoard_filepath(String board_filepath) {
		this.board_filepath = board_filepath;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public int getBoard_status() {
		return board_status;
	}
	public void setBoard_status(int board_status) {
		this.board_status = board_status;
	}
	public int getBoard_group_code() {
		return board_group_code;
	}
	public void setBoard_group_code(int board_group_code) {
		this.board_group_code = board_group_code;
	}
	public int getBoard_member_code() {
		return board_member_code;
	}
	public void setBoard_member_code(int board_member_code) {
		this.board_member_code = board_member_code;
	}
	public int getBoard_readcount() {
		return board_readcount;
	}
	public void setBoard_readcount(int board_readcount) {
		this.board_readcount = board_readcount;
	}
	public String getBoard_subject() {
		return board_subject;
	}
	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public Date getBoard_write_date() {
		return board_write_date;
	}
	public void setBoard_write_date(Date board_write_date) {
		this.board_write_date = board_write_date;
	}
	public Date getBoard_update_date() {
		return board_update_date;
	}
	public void setBoard_update_date(Date board_update_date) {
		this.board_update_date = board_update_date;
	}
}

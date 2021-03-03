package notice;

public class NoticeCommentVO {
	// id = reply_code, pid = reply_sub_code, 
	//wirter = member_code, name = member_name
	private int reply_code, reply_status, reply_sub_code, reply_parent_code, reply_member_code;
	private String reply_content, member_name;
	private String reply_write_date, reply_update_date;
	
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public int getReply_code() {
		return reply_code;
	}
	public void setReply_code(int reply_code) {
		this.reply_code = reply_code;
	}
	public int getReply_status() {
		return reply_status;
	}
	public void setReply_status(int reply_status) {
		this.reply_status = reply_status;
	}
	public int getReply_sub_code() {
		return reply_sub_code;
	}
	public void setReply_sub_code(int reply_sub_code) {
		this.reply_sub_code = reply_sub_code;
	}
	public int getReply_parent_code() {
		return reply_parent_code;
	}
	public void setReply_parent_code(int reply_parent_code) {
		this.reply_parent_code = reply_parent_code;
	}
	public int getReply_member_code() {
		return reply_member_code;
	}
	public void setReply_member_code(int reply_member_code) {
		this.reply_member_code = reply_member_code;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public String getReply_write_date() {
		return reply_write_date;
	}
	public void setReply_write_date(String reply_write_date) {
		this.reply_write_date = reply_write_date;
	}
	public String getReply_update_date() {
		return reply_update_date;
	}
	public void setReply_update_date(String reply_update_date) {
		this.reply_update_date = reply_update_date;
	}

}

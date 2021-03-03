package today;

import java.sql.Date;

public class TodayVO {
	//예약한 손님 실명을 넣을 수 없으니 닉으로 표시하자
	int booking_code, booking_kind, booking_num;
	String booking_date_reservation, member_nick, product_name;
	
	public int getBooking_code() {
		return booking_code;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public void setBooking_code(int booking_code) {
		this.booking_code = booking_code;
	}
	public int getBooking_kind() {
		return booking_kind;
	}
	public void setBooking_kind(int booking_kind) {
		this.booking_kind = booking_kind;
	}
	public String getMember_nick() {
		return member_nick;
	}
	public void setMember_nick(String member_nick) {
		this.member_nick = member_nick;
	}
	public int getBooking_num() {
		return booking_num;
	}
	public void setBooking_num(int booking_num) {
		this.booking_num = booking_num;
	}
	public String getBooking_date_reservation() {
		return booking_date_reservation;
	}
	public void setBooking_date_reservation(String booking_date_reservation) {
		this.booking_date_reservation = booking_date_reservation;
	}

}

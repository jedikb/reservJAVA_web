package business;

public class BusinessVO {
/*
business_code                 not null number         
business_name                 not null varchar2(100)  
business_member_code          not null number         
business_category_parent_code not null number         
business_category_code        not null number         
business_addr                 not null varchar2(1000) 
business_tel                           varchar2(100)  
business_image                         varchar2(1024) 
business_info                          varchar2(2000) 
business_star_avg                      number         
business_hashtag                       varchar2(1024) 
business_lng                           number         
business_lat                           number         	
business_date                          date           
*/
	private int    business_code;
	private String business_name;
	private int    business_member_code;
	private int    business_category_parent_code;
	private int    business_category_code;
	private String business_addr;
	private String business_tel;
	private String business_image = null;
	private String business_info;
	private int    business_star_avg;
	private String business_hashtag;
	private double business_lng = 0.0;
	private double business_lat = 0.0;
	private String business_date;
	
	//조회(select) 쿼리를 위한 맴버변수
	//-----------------------------------------------------------------------------------//
	private int    no;
	private String business_member_name;
	private String business_category_parent_name;
	private String business_category_name;
	private String business_address[];

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getBusiness_member_name() {
		return business_member_name;
	}
	public void setBusiness_member_name(String business_member_name) {
		this.business_member_name = business_member_name;
	}
	public String getBusiness_category_parent_name() {
		return business_category_parent_name;
	}
	public void setBusiness_category_parent_name(String business_category_parent_name) {
		this.business_category_parent_name = business_category_parent_name;
	}
	public String getBusiness_category_name() {
		return business_category_name;
	}
	public void setBusiness_category_name(String business_category_name) {
		this.business_category_name = business_category_name;
	}
	public String[] getBusiness_address() {
		return business_address;
	}
	public void setBusiness_address(String[] business_address) {
		this.business_address = business_address;
		// business_address[]가 저장될때, business_addr에도 구분자('|')로 합쳐서 주소정보를 저장한다.
		this.business_addr = String.join("|", business_address);
	}
	//DB테이블 맴버변수
	//-----------------------------------------------------------------------------------//
	public String getBusiness_addr() {
		return business_addr;
	}
	public void setBusiness_addr(String business_addr) {
		this.business_addr = business_addr;
		// business_addr이 저장될때, business_address[] 배열에도 '|'로 분리해서 주소정보를 저장한다.
		this.business_address = business_addr.split("\\|");
	}

	public int getBusiness_code() {
		return business_code;
	}
	public void setBusiness_code(int business_code) {
		this.business_code = business_code;
	}
	public String getBusiness_name() {
		return business_name;
	}
	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}
	public int getBusiness_member_code() {
		return business_member_code;
	}
	public void setBusiness_member_code(int business_member_code) {
		this.business_member_code = business_member_code;
	}
	public int getBusiness_category_parent_code() {
		return business_category_parent_code;
	}
	public void setBusiness_category_parent_code(int business_category_parent_code) {
		this.business_category_parent_code = business_category_parent_code;
	}
	public int getBusiness_category_code() {
		return business_category_code;
	}
	public void setBusiness_category_code(int business_category_code) {
		this.business_category_code = business_category_code;
	}
	public String getBusiness_tel() {
		return business_tel;
	}
	public void setBusiness_tel(String business_tel) {
		this.business_tel = business_tel;
	}
	public String getBusiness_image() {
		return business_image;
	}
	public void setBusiness_image(String business_image) {
		this.business_image = business_image;
	}
	public String getBusiness_info() {
		return business_info;
	}
	public void setBusiness_info(String business_info) {
		this.business_info = business_info;
	}
	public int getBusiness_star_avg() {
		return business_star_avg;
	}
	public void setBusiness_star_avg(int business_star_avg) {
		this.business_star_avg = business_star_avg;
	}
	public String getBusiness_hashtag() {
		return business_hashtag;
	}
	public void setBusiness_hashtag(String business_hashtag) {
		this.business_hashtag = business_hashtag;
	}
	public double getBusiness_lng() {
		return business_lng;
	}
	public void setBusiness_lng(double business_lng) {
		this.business_lng = business_lng;
	}
	public double getBusiness_lat() {
		return business_lat;
	}
	public void setBusiness_lat(double business_lat) {
		this.business_lat = business_lat;
	}
	public String getBusiness_date() {
		return business_date;
	}
	public void setBusiness_date(String business_date) {
		this.business_date = business_date;
	}
}

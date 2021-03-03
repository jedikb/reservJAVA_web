package today;

import java.util.List;

import business.BusinessVO;

public interface TodayService {

	List<TodayVO> list(int business_code);// 오늘의 예약 목록이 나와야 함
	// 클릭 시 예약에 대한 자세한 항목이 나오게도 하고 싶은데
	// 너무 일이 커질 수도.. 그리고 테블릿 망가지면 어캄
	
	public BusinessVO getBusinessVO(int member_code);
}

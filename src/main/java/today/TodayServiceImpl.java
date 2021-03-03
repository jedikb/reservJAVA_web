package today;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import business.BusinessVO;

@Service
public class TodayServiceImpl implements TodayService{
	@Autowired private TodayDAO dao;

	@Override
	public List<TodayVO> list(int business_code) {
		return dao.list(business_code);
	}

	public BusinessVO getBusinessVO(int member_code) {
		return dao.getBusinessVO(member_code);
	}

}

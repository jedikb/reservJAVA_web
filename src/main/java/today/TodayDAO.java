package today;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import business.BusinessVO;

@Repository
public class TodayDAO implements TodayService{
	@Autowired @Qualifier("bteam") private SqlSession sql;

	@Override
	public List<TodayVO> list(int business_code) {
		return sql.selectList("today.mapper.list", business_code);
	}

	@Override
	public BusinessVO getBusinessVO(int member_code) {
		return sql.selectOne("today.mapper.busicode", member_code);
	}
	
}

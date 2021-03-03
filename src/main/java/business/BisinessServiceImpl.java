package business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BisinessServiceImpl implements BisinessService {
	@Autowired private BisinessDAO dao;
	
}

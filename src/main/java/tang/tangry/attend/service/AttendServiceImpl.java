package tang.tangry.attend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tang.tangry.attend.dao.AttendMapper;
import tang.tangry.attend.entity.Attend;

/**
 * Created by tryu on 2017/7/2.
 */
@Service("attendServiceImpl")
public class AttendServiceImpl implements AttendService {
    @Autowired
    private AttendMapper attendMapper;

    @Override
    public int ClockIn(Attend attend) {
        return attendMapper.insertSelective(attend);
    }
}

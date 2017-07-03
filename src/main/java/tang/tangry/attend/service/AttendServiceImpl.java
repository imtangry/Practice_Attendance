package tang.tangry.attend.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tang.tangry.attend.dao.AttendMapper;
import tang.tangry.attend.entity.Attend;
import tang.tangry.common.constant.Constant;
import tang.tangry.common.utils.GetDate;

import java.util.Date;

/**
 * Created by tryu on 2017/7/2.
 */
@Service("attendServiceImpl")
public class AttendServiceImpl implements AttendService {
    @Autowired
    private AttendMapper attendMapper;

    /**
     * Create by tryu 2017/7/2 16:17
     * 使用log4j,上班打卡，9:30之后打卡算异常,返回打卡操作的情况。
     * 逻辑写在service里？
     */
    Log log = LogFactory.getLog(AttendServiceImpl.class);

    @Override
    public String clockIn(int userId) {
        Attend attend = new Attend();
        Date currentDate = new Date();
        int week = GetDate.currentDayOfWeek(currentDate);
        attend.setUserId(userId);
        attend.setAttendDate(currentDate);
        attend.setAttendWeek((byte) week);
        //打卡状态默认为正常，出现某一个异常就视为异常
        attend.setAttendStatus((byte) 1);
        //不存在记录，此次打卡就记为上班打卡，并判断其打卡时间
        if (getAttend(userId) == null) {
            if (currentDate.compareTo(GetDate.beSetDate(Constant.CLOCK_IN_HOUR, Constant.CLOCK_IN_MINUTE)) < 0) {
                attend.setOnDuty(currentDate);
                int status = attendMapper.insertSelective(attend);
                if (status == 0) {
                    return "上班打卡失败，系统错误，请联系管理员";
                } else {
                    return "上班打卡成功，开始修仙";
                }
            } else {
                attend.setAttendStatus((byte) 0);
                attend.setOnDuty(currentDate);
                int status = attendMapper.insertSelective(attend);
                if (status == 0) {
                    return "上班打卡失败，系统错误，请联系管理员";
                } else {
                    return "上班打卡成功，开始修仙";
                }
            }
        }
        //下面是更新下班打卡时间，下班打卡可多次发生，以最后一次为准，超过午夜12点记为异常，且不加入记录
        else {
            if (currentDate.compareTo(GetDate.beSetDate(Constant.CLOCK_OUT_HOUR, Constant.CLOCK_OUT_MINUTE)) < 0) {
                attend.setOffDuty(currentDate);
                int status = clockOut(attend);
                if (status == 0) {
                    return "下班打卡失败，系统错误，请联系管理员";
                } else {
                    return "下班打卡成功";
                }
            } else {
                attend.setAttendStatus((byte) 0);
                return "下班打卡失败，已经是新的一天了";
            }
        }
    }

    //下班打卡
    @Override
    public int clockOut(Attend attend) {
        return attendMapper.updateByPrimaryKeyOffDuty(attend);
    }

    @Override
    public Attend getAttend(int userId) {
        Attend attend = attendMapper.selectByDate(userId);
        return attend;
    }
}

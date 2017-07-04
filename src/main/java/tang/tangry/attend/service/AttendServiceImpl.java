package tang.tangry.attend.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tang.tangry.attend.dao.AttendMapper;
import tang.tangry.attend.entity.Attend;
import tang.tangry.attend.vo.QueryCondition;
import tang.tangry.common.constant.Constant;
import tang.tangry.common.page.Pages;
import tang.tangry.common.utils.GetDate;

import java.util.Date;
import java.util.List;

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


    /**
     * Create by tryu 2017/7/3 20:25
     * 根据条件和数据库查询数据库是否有数据，有数据进行分页处理
     */
    @Override
    public Pages getPages(QueryCondition queryCondition) {
        queryCondition.initDate();
        int record = attendMapper.getRecord(queryCondition);
        if (record == 0) {
            return null;
        } else {
            /**
             *Create by tryu 2017/7/4 11:19
             *还可以使用limit获得分页，重新写一个初始化方法。效率高，数据库兼容差
             */
//            List<Attend> listAttend = attendMapper.getRecordAttend(queryCondition);
//            Pages pages = queryCondition;
//            pages.initPagesSublist(record, queryCondition.getPageNum(), listAttend);
            queryCondition.initPagesLimit(record);
            List<Attend> listAttend = attendMapper.getRecordAttendLimit(queryCondition);
            queryCondition.setDataList(listAttend);
            return queryCondition;
        }
    }

    /**
     * Create by tryu 2017/7/4 17:45
     * 获得用户的userId，返回一个int数组
     */
    @Override
    public List<Integer> getUserIds() {
        return attendMapper.selectUserIds();
    }

    /**
     * Create by tryu 2017/7/4 18:06
     * 周六周日的情况需要判断
     * 检查打卡记录，没有记录创建一条，状态为异常,需要加上打卡星期。
     * 有记录:
     * 1.检查下班打卡情况，无记录为异常。
     * 2.计算工作时间，时间不够attend_status置为异常
     */
    @Override
    public int checkAttendance(Attend attend) {
        Attend result = getAttend(attend.getUserId());
        if (result == null) {
            attend.setAttendWeek((byte) GetDate.currentDayOfWeek(new Date()));
            attend.setAttendStatus((byte) 2);
            return attendMapper.insertSelective(attend);
        } else {
            if (result.getOffDuty() == null) {
                result.setAttendStatus((byte) 2);
                //result已经有id，不会报错
                return attendMapper.updateByPrimaryKeySelective(result);
            } else {
                int absence = GetDate.absenceTimes(result.getOnDuty(), result.getOffDuty());
                //状态初始默认正常，不需要判断
                if (absence > 0) {
                    result.setAbsence(absence);
                    result.setAttendStatus((byte) 2);
                    return attendMapper.updateByPrimaryKeySelective(result);
                }
            }
        }
        return 0;
    }
}

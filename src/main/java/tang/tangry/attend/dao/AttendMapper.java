package tang.tangry.attend.dao;

import tang.tangry.attend.entity.Attend;
import tang.tangry.attend.vo.QueryCondition;

import java.util.List;

public interface AttendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Attend record);

    int insertSelective(Attend record);

    Attend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Attend record);

    int updateByPrimaryKeyOffDuty(Attend record);

    int updateByPrimaryKey(Attend record);

    Attend selectByDate(int userId);

    int getRecord(QueryCondition queryCondition);

    List<Attend> getRecordAttend(QueryCondition queryCondition);

    List<Attend> getRecordAttendLimit(QueryCondition queryCondition);

    List<Integer> selectUserIds();

    int updateStatus(Attend record);
}
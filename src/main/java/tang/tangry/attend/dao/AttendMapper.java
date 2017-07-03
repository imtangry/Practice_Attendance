package tang.tangry.attend.dao;

import tang.tangry.attend.entity.Attend;

public interface AttendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Attend record);

    int insertSelective(Attend record);

    Attend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Attend record);

    int updateByPrimaryKeyOffDuty(Attend record);

    int updateByPrimaryKey(Attend record);

    Attend selectByDate(int userId);
}
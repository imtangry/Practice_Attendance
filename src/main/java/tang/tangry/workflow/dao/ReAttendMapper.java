package tang.tangry.workflow.dao;

import tang.tangry.workflow.entity.ReAttend;

import java.util.List;

public interface ReAttendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReAttend record);

    int insertSelective(ReAttend record);

    ReAttend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReAttend record);

    int updateByPrimaryKey(ReAttend record);

    List<ReAttend> selectReAttendRecord(String userName);
}
package tang.tangry.user.dao;

import tang.tangry.user.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByUname(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
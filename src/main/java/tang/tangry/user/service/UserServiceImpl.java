package tang.tangry.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tang.tangry.user.dao.UserMapper;
import tang.tangry.user.entity.User;

/**
 * Created by tryu on 2017/6/30.
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUname(String uname) {
        User user=userMapper.selectByUname(uname);
        return user;
    }

    @Override
    public int addUser(User user) {
        int status=userMapper.insertSelective(user);
        return status;
    }
}

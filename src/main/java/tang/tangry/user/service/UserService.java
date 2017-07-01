package tang.tangry.user.service;

import org.springframework.stereotype.Service;
import tang.tangry.user.entity.User;

/**
 * Created by tryu on 2017/6/30.
 */

public interface UserService {
    User findUserByUname(String uname);

    int addUser(User user);
}

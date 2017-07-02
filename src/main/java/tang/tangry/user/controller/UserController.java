package tang.tangry.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tang.tangry.user.entity.User;
import tang.tangry.user.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tryu on 2017/6/29.
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping
    public String add() {
        return "add";
    }

    /**
     * Create by tryu 2017/7/1 9:58
     * 解决再次进入登录页面的情况,并防止拦截器死循环
     */
    @RequestMapping("/already_logged_in")
    public String logined() {
        return "logined";
    }


    /**
     * Create by tryu 2017/7/1 10:00
     * 退出登陆,并防止拦截器死循环
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest req) {
        req.getSession().invalidate();
        return "login";
    }
}

/**
 * Create by tryu 2017/7/1 14:45
 * 操作考勤系统
 */
@Controller
@RequestMapping
class AttendanceManager {
    
    @RequestMapping
    public String home() {
        System.out.println("接收到进入主页请求");
        return "index";
    }
}
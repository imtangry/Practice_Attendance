package tang.tangry.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tang.tangry.user.entity.SimpleUserInfo;
import tang.tangry.user.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by tryu on 2017/6/29.
 */
@Controller
@RequestMapping("user")
public class UserController {

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

    /**
    *Create by tryu 2017/7/2 11:39
    *Ajax方式返回用户部分信息
    */
    @RequestMapping("/info")
    @ResponseBody
    public SimpleUserInfo info(HttpSession session){
        System.out.println("接收到用户信息请求");
        User user =(User)session.getAttribute("userInfo");
        SimpleUserInfo simpleUserInfo =new SimpleUserInfo();
        simpleUserInfo.setName(user.getRealName());
        simpleUserInfo.setHead_img(user.getHeadImage());
        return simpleUserInfo;
    }
}

/**
 * Create by tryu 2017/7/1 14:45
 * 操作考勤系统
 */
@Controller
@RequestMapping
class AttendanceManager {
    /**
     * Create by tryu 2017/7/2 11:39
     * 进入系统主页
     */
    @RequestMapping
    public String home() {
        System.out.println("接收到进入主页请求");
        return "index";
    }
}
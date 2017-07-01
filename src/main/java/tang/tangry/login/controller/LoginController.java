package tang.tangry.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tang.tangry.common.utils.CodeUtils;
import tang.tangry.user.dao.UserMapper;
import tang.tangry.user.entity.User;
import tang.tangry.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by tryu on 2017/6/30.
 */
@Controller
@RequestMapping("login")
public class LoginController {
    /**
     * Create by tryu 2017/7/1 9:57
     * 登陆页面
     */
    @RequestMapping
    public String index() {
        System.out.println("接收到了请求");
        return "login";
    }

    @Autowired
    private UserService userService;

    /**
     * Create by tryu 2017/7/1 9:57
     * 校验用户
     */
    @RequestMapping(value = "/check")
    @ResponseBody
    public String check(HttpServletRequest req) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        String dbpwd = CodeUtils.encrypt(pwd);
        User user = userService.findUserByUname(uname);
        if (user != null && dbpwd.equals(user.getPassword())) {
            req.getSession().setAttribute("userInfo", user);
            return "success";
        } else {
            return "failed";
        }
    }

    /**
     * 为了方便增加用户，模拟一个注册功能
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String pwd = user.getPassword();
        System.out.println(user.getRealName());
        user.setPassword(CodeUtils.encrypt(pwd));
        int status = userService.addUser(user);
        if (status != 0) {
            return "succ";
        } else {
            return "failed";
        }
    }
}

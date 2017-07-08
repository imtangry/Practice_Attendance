package tang.tangry.attend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tang.tangry.attend.entity.Attend;
import tang.tangry.attend.service.AttendService;
import tang.tangry.attend.vo.QueryCondition;
import tang.tangry.common.page.Pages;
import tang.tangry.user.entity.User;

import javax.servlet.http.HttpSession;

/**
 * Created by tryu on 2017/7/2.
 */
@Controller
@RequestMapping("attend")
public class AttendController {
    /**
     * Create by tryu 2017/7/2 13:34
     * 返回考勤信息页面
     */
    @RequestMapping
    public String attendInfo() {
        return "attend";
    }


    /**
     * Create by tryu 2017/7/2 14:45
     * 接收打卡请求，缺勤功能暂时不包含，
     */
    @Autowired
    private AttendService attendService;

    @RequestMapping("clockin")
    @ResponseBody
    public String clockIn(HttpSession session) {
        int userId = ((User) session.getAttribute("userInfo")).getId();
        return attendService.clockIn(userId);
    }

    /**
     * Create by tryu 2017/7/3 18:56
     * 解决分页请求,前端请求日期，根据日期返回分页？
     * 路径暂时clockin，postman测试
     */
    @RequestMapping("/clockinpages")
    @ResponseBody
    public Pages pages(HttpSession session, QueryCondition queryCondition) {
        queryCondition.setUserId(((User) session.getAttribute("userInfo")).getId());
        Pages pages = attendService.getPages(queryCondition);
        return pages;
    }

}

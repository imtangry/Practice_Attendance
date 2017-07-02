package tang.tangry.attend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tang.tangry.attend.entity.Attend;
import tang.tangry.attend.service.AttendService;

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
     * 模拟打卡情况
     */
    @Autowired
    private AttendService attendService;

    @RequestMapping("clockin")
    public String clockIn(@RequestBody Attend attend) {
        int status = attendService.ClockIn(attend);
        if (status == 0) {
            return "failed";
        } else {
            return "success";
        }
    }


}

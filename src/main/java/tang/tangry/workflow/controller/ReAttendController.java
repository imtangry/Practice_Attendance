package tang.tangry.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tang.tangry.user.entity.User;
import tang.tangry.workflow.entity.ReAttend;
import tang.tangry.workflow.service.ReAttendService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by tryu on 2017/7/5.
 */
@Controller
@RequestMapping("reattend")
public class ReAttendController {
    @Autowired
    private ReAttendService reAttendService;


    @RequestMapping
    public String myReattend(Model model,HttpSession session){
        String userRealName = ((User)session.getAttribute("userInfo")).getRealName();
        List<ReAttend> reAttendList = reAttendService.listReAttend(userRealName);
        model.addAttribute("reAttendList",reAttendList);
        return "reattend";
    }


    /**
     * Create by tryu 2017/7/7 19:56
     * 接收页面的补签请求，插入补签数据到数据库，并开始工作流
     */
    @RequestMapping("/start")
    @ResponseBody
    public String startReAttendFlow(@RequestBody ReAttend reAttend) {
        boolean reAttendStatus = reAttendService.startReAttendFlow(reAttend);
        if(reAttendStatus){
            return "补签成功";
        }else{
            return "补签失败";
        }
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<ReAttend> listTasks() {
        String userName = "老李";
        List<ReAttend> tasks = reAttendService.listTasks(userName);
        return tasks;
    }

    @RequestMapping("/approve")
    public void approveReAttendFlow(@RequestBody ReAttend reAttend) {
        reAttendService.approve(reAttend);
    }

    @RequestMapping("/dblist")
    @ResponseBody
    public List<ReAttend> listReAttend(String userName) {
        List<ReAttend> lists = reAttendService.listReAttend(userName);
        return lists;
    }
}

package tang.tangry.common.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import tang.tangry.attend.entity.Attend;
import tang.tangry.attend.service.AttendService;

import java.util.Date;
import java.util.List;

/**
 * Created by tryu on 2017/7/4.
 * 每天检查一遍每个人的打卡记录
 */
public class AttendStatusCheck {

    @Autowired
    private AttendService attendService;

    public void check() {
        //执行调度，逻辑都写在service层了
        Attend attend = new Attend();
        attend.setAttendDate(new Date());
        List<Integer> userIds = attendService.getUserIds();
        for (int i:userIds) {
            attend.setUserId(i);
            attendService.checkAttendance(attend);
        }
    }
}

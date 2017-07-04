package tang.tangry.attend.service;

import tang.tangry.attend.entity.Attend;
import tang.tangry.attend.vo.QueryCondition;
import tang.tangry.common.page.Pages;

import java.util.List;

/**
 * Created by tryu on 2017/7/2.
 */
public interface AttendService {
    String clockIn(int userId);
    Attend getAttend(int userId);
    int clockOut(Attend attend);
    Pages getPages(QueryCondition queryCondition);
    List<Integer> getUserIds();
    int checkAttendance(Attend attend);
}

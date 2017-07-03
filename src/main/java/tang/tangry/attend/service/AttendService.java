package tang.tangry.attend.service;

import tang.tangry.attend.entity.Attend;

/**
 * Created by tryu on 2017/7/2.
 */
public interface AttendService {
    String clockIn(int userId);
    Attend getAttend(int userId);
    int clockOut(Attend attend);
}

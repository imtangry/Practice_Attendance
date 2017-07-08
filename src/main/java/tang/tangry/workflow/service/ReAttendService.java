package tang.tangry.workflow.service;

import tang.tangry.workflow.entity.ReAttend;
import java.util.List;

/**
 * Created by tryu on 2017/7/5.
 * 补签工作流的服务
 */
public interface ReAttendService {

    boolean startReAttendFlow(ReAttend reAttend);

    List<ReAttend> listTasks(String userName);

    void approve(ReAttend reAttend);

    List<ReAttend> listReAttend(String userName);
}

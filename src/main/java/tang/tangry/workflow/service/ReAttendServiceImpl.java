package tang.tangry.workflow.service;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tang.tangry.attend.dao.AttendMapper;
import tang.tangry.attend.entity.Attend;
import tang.tangry.workflow.dao.ReAttendMapper;
import tang.tangry.workflow.entity.ReAttend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tryu on 2017/7/5.
 * 补签工作流的服务实现
 */
@Service("reAttendServiceImpl")
public class ReAttendServiceImpl implements ReAttendService {
    private static final String RE_ATTEND_FLOW_ID = "re_attend";
    private static final Byte RE_ATTEND_UNDER_RROCESSING = 1;
    private static final Byte RE_ATTEND_PASS = 2;
    private static final Byte RE_ATTEND_REFUSE = 3;
    //流程下一步处理人
    private static final String RE_ATTEND_NEXT_HANDLER = "next_handler";
    //补签记录
    private static final String RE_ATTEND_RECORD = "re_attend";

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ReAttendMapper reAttendMapper;
    @Autowired
    private AttendMapper attendMapper;

    /**
     * Create by tryu 2017/7/6 11:18
     * 启动补签工作流，
     */
    @Override
    @Transactional
    public boolean startReAttendFlow(ReAttend reAttend) {
        reAttend.setLeaderHeandle("老李");
        reAttend.setReStatus(RE_ATTEND_UNDER_RROCESSING);
        //插入记录到数据库补签表,要设置dao方法插入成功返回id
        int status = reAttendMapper.insertSelective(reAttend);
        if (status > 0) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(RE_ATTEND_RECORD, reAttend);
            map.put(RE_ATTEND_NEXT_HANDLER, reAttend.getLeaderHeandle());
            //启动流程实例
            ProcessInstance instance = runtimeService.startProcessInstanceByKey(RE_ATTEND_FLOW_ID, map);
            //提交一个补签业务，它有一个id
            Task task = taskService.createTaskQuery().processInstanceId(instance.getId()).singleResult();
            taskService.complete(task.getId(), map);
            return true;
        }else{
            return  false;
        }
    }

    /**
     * Create by tryu 2017/7/6 11:20
     * 查询自己需要处理的流程
     */
    @Override
    public List<ReAttend> listTasks(String userName) {
        List<ReAttend> reAttendList = new ArrayList<ReAttend>();
        List<Task> taskList = taskService.createTaskQuery().processVariableValueEquals(userName).list();
        if (CollectionUtils.isNotEmpty(taskList)) {
            for (Task task : taskList) {
                //拿到task里面的map
                Map<String, Object> variable = taskService.getVariables(task.getId());
                //在map里拿到之前添加的补签记录
                ReAttend reAttend = (ReAttend) variable.get(RE_ATTEND_RECORD);
                //将这个map里的补签记录和task关联起来，建立前端后端数据库的联系
                reAttend.setTaskId(task.getId());
                //返回自己要处理的所有补签请求
                reAttendList.add(reAttend);
            }
        }
        return reAttendList;
    }

    /**
     * Create by tryu 2017/7/6 11:21
     * 审批自己需要处理的流程,根据流程的id。
     */
    @Override
    @Transactional
    public void approve(ReAttend reAttend) {
        //获得特定id的task，并进行审批。
        Task task = taskService.createTaskQuery().taskId(reAttend.getTaskId()).singleResult();
        //判断审批返回状态
        if (("" + RE_ATTEND_PASS).equals(reAttend.getApproveFlag())) {
            //审批通过，需要修改对应attend表记录和reattend表记录
            Attend attend = new Attend();
            attend.setId(reAttend.getAttendId());
            attend.setOnDuty(reAttend.getReDutyOn());
            attend.setOffDuty(reAttend.getReDutyOff());
            reAttend.setReStatus(RE_ATTEND_PASS);
            attendMapper.updateByPrimaryKeySelective(attend);
            reAttendMapper.updateByPrimaryKeySelective(reAttend);

        } else if (("" + RE_ATTEND_REFUSE).equals(reAttend.getApproveFlag())) {
            //审批不通过，修改reattend表记录
            reAttend.setReStatus(RE_ATTEND_REFUSE);
            reAttendMapper.updateByPrimaryKeySelective(reAttend);
        }
        taskService.complete(reAttend.getTaskId());
    }

    /**
     * Create by tryu 2017/7/6 11:21
     * 查询补签申请状态
     */
    @Override
    public List<ReAttend> listReAttend(String userName) {
        List<ReAttend> reAttendAStatusList = reAttendMapper.selectReAttendRecord(userName);
        return reAttendAStatusList;
    }
}

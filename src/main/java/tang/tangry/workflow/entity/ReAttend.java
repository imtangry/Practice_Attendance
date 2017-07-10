package tang.tangry.workflow.entity;

import java.io.Serializable;
import java.util.Date;

public class ReAttend implements Serializable {
    private static final long serialVersionUID = 3387576934587843365L;

    private Integer id;

    private Integer attendId;

    private String reStarter;

    private Date reDutyOn;

    private Date reDutyOff;

    private String leaderHeandle;

    private Byte reStatus;

    private String comment;

    private String taskId;
    private String approveFlag;
    private Date reAttendDate;

    public Date getReAttendDate() {
        return reAttendDate;
    }

    public void setReAttendDate(Date reAttendDate) {
        this.reAttendDate = reAttendDate;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(String approveFlag) {
        this.approveFlag = approveFlag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAttendId() {
        return attendId;
    }

    public void setAttendId(Integer attendId) {
        this.attendId = attendId;
    }

    public String getReStarter() {
        return reStarter;
    }

    public void setReStarter(String reStarter) {
        this.reStarter = reStarter == null ? null : reStarter.trim();
    }

    public Date getReDutyOn() {
        return reDutyOn;
    }

    public void setReDutyOn(Date reDutyOn) {
        this.reDutyOn = reDutyOn;
    }

    public Date getReDutyOff() {
        return reDutyOff;
    }

    public void setReDutyOff(Date reDutyOff) {
        this.reDutyOff = reDutyOff;
    }

    public String getLeaderHeandle() {
        return leaderHeandle;
    }

    public void setLeaderHeandle(String leaderHeandle) {
        this.leaderHeandle = leaderHeandle == null ? null : leaderHeandle.trim();
    }

    public Byte getReStatus() {
        return reStatus;
    }

    public void setReStatus(Byte reStatus) {
        this.reStatus = reStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}
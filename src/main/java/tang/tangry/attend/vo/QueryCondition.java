package tang.tangry.attend.vo;

import tang.tangry.common.page.Pages;

/**
 * Created by tryu on 2017/7/3.
 */
public class QueryCondition extends Pages {
    private int pageNum;
    private int UserId;
    private int attendStatus;
    private String rangeDate;
    private String afterDate;
    private String preDate;

    public void initDate() {
        this.preDate = this.rangeDate.substring(0, this.rangeDate.indexOf("/"));
        this.afterDate = this.rangeDate.substring(this.rangeDate.indexOf("/") + 1);
    }

    public int getAttendStatus() {
        return attendStatus;
    }

    public void setAttendStatus(int attendStatus) {
        this.attendStatus = attendStatus;
    }

    public String getRangeDate() {
        return rangeDate;
    }

    public void setRangeDate(String rangeDate) {
        this.rangeDate = rangeDate;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getPreDate() {
        return preDate;
    }

    public void setPreDate(String preDate) {
        this.preDate = preDate;
    }

    public String getAfterDate() {
        return afterDate;
    }

    public void setAfterDate(String afterDate) {
        this.afterDate = afterDate;
    }
}

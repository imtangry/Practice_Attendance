package tang.tangry.common.page;

import tang.tangry.common.constant.Constant;

import java.util.List;

/**
 * Created by tryu on 2017/7/3.
 * 页面数据?每一页都是一个对象？
 * 死套路，粘贴复制
 */
public class Pages {
    private int totalPages;// 总页数，公共的
    private int pageSize;// 一页显示几条数据，公共的？
    private int currentPage;// 当前页，私有的，以后通过页面传进来？
    private int totalData;// 总数据，公共的
    private List<?> dataList;// 数据，公共的
    private int startRow;//数据库分页起始值

    private int queryRow;//数据库查询数据

    public Pages(int totalPages, int pageSize, int currentPage, int totalData, List<?> dataList) {
        super();
        this.totalPages = totalPages;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalData = totalData;
        this.dataList = dataList;
    }

    // 写一个方法，通过数据库分页。
    public void initPagesLimit(int totalData) {
        // 确定总数据
        this.totalData = totalData;
        // 确定总页数
        this.totalPages = this.totalData % this.pageSize == 0 ? this.totalData / this.pageSize : this.totalData / this.pageSize + 1;
        //确定limit的起始值，和取出数量。
        this.queryRow = this.pageSize;


    }

    //写一个初始化的方法(使用sublist，效率低)
    public void initPagesSublist(int totalData, int pageNum, List<?> srcList) {
        this.totalData = totalData;
        pageNum = pageNum <= 0 ? 1 : pageNum;
        // 确定每页显示的数据，以后根据页面传参确定每页显示的数据？
        this.pageSize = Constant.MAX_PAGESIZE;
        // 确定总页数
        this.totalPages = this.totalData % Constant.MAX_PAGESIZE == 0 ? this.totalData / Constant.MAX_PAGESIZE : this.totalData / Constant.MAX_PAGESIZE + 1;
        // 确定当前页码
        this.currentPage = pageNum > this.totalPages ? this.totalPages : pageNum;
        //确定当前页需要显示的数据，JDBC中index从1算，一般index从0开始算。
        if (this.totalData != 0) {
            int fromIndex = this.pageSize * (this.currentPage - 1);
            this.dataList = this.pageSize * this.currentPage > this.totalData ? srcList.subList(fromIndex, this.totalData) : srcList.subList(fromIndex, this.pageSize * this.currentPage);
        }
    }

    public Pages() {
        super();
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalData() {
        return totalData;
    }

    public void setTotalData(int totalData) {
        this.totalData = totalData;
    }

    public List<?> getDataList() {
        return dataList;
    }

    public void setDataList(List<?> dataList) {
        this.dataList = dataList;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getQueryRow() {
        return queryRow;
    }

    public void setQueryRow(int queryRow) {
        this.queryRow = queryRow;
    }

}

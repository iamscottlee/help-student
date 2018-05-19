package com.project.help.pojo;

import java.util.List;

public class PoorStuGrid {
    private int current;//当前页面号
    private int rowCount;//每页行数
    private int total;//总行数
    private List<PoorStudent> rows;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<PoorStudent> getRows() {
        return rows;
    }

    public void setRows(List<PoorStudent> rows) {
        this.rows = rows;
    }
}

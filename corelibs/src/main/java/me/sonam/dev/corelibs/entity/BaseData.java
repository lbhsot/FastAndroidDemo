package me.sonam.dev.corelibs.entity;

import java.util.List;

/**
 * 服务器返回数据
 * Created by SonamLee on 2017/3/7.
 */
public class BaseData<T extends BaseRows> {
    private boolean isSucceed;
    private String message;
    private String footer;
    private int total;
    private NewPrimaryKeys newPrimaryKeys;
    private List<T> rows;

    public boolean isSucceed() {
        return isSucceed;
    }

    public void setSucceed(boolean succeed) {
        isSucceed = succeed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public NewPrimaryKeys getNewPrimaryKeys() {
        return newPrimaryKeys;
    }

    public void setNewPrimaryKeys(NewPrimaryKeys newPrimaryKeys) {
        this.newPrimaryKeys = newPrimaryKeys;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}

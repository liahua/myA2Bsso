package com.chenahua.jta.vo;

public class ResultJson {
    /*200成功，404失败*/
    private int data;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public ResultJson(int i) {
        this.data=i;
    }

    @Override
    public String toString() {
        return "ResultJson{" +
                "data=" + data +
                '}';
    }
}

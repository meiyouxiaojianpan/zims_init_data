package com.helios.gao.domain;

/**
 * @author : gaozhiwen
 * @date : 2019/7/5
 */
public class A {
    private String aaa;
    private String bbb;
    private ReceiptDetail detail;

    public A(String aaa, ReceiptDetail detail) {
        this.aaa = aaa;
        this.detail = detail;
    }

    public A(String aaa, String bbb) {
        this.aaa = aaa;
        this.bbb = bbb;
    }

    public String getAaa() {
        return aaa;
    }

    public void setAaa(String aaa) {
        this.aaa = aaa;
    }

    public ReceiptDetail getDetail() {
        return detail;
    }

    public void setDetail(ReceiptDetail detail) {
        this.detail = detail;
    }

    public String getBbb() {
        return bbb;
    }

    public void setBbb(String bbb) {
        this.bbb = bbb;
    }
}

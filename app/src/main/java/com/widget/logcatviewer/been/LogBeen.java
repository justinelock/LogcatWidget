package com.widget.logcatviewer.been;

public class LogBeen {

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String isOpenLogcatViewer;
        private String accountId;

        public String getIsOpenLogcatViewer() {
            return isOpenLogcatViewer;
        }

        public void setIsOpenLogcatViewer(String isOpenLogcatViewer) {
            this.isOpenLogcatViewer = isOpenLogcatViewer;
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }
    }
}

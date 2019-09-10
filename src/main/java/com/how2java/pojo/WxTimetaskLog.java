package com.how2java.pojo;

import java.util.Date;

public class WxTimetaskLog {
    private String id;

    private Date createDate;

    private String jobId;

    private String reason;

    private String state;

    public WxTimetaskLog(String id, Date createDate, String jobId, String reason, String state) {
        this.id = id;
        this.createDate = createDate;
        this.jobId = jobId;
        this.reason = reason;
        this.state = state;
    }

    public WxTimetaskLog() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}
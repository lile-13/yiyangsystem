package com.yiyang.elderlycare.entity;

import java.util.Date;

public class Outward {
    private Integer id;
    private Integer bedId;
    private String customerName;
    private Date outwardDate;
    private Date returnDate;
    private Integer status;
    private Date applyTime;
    private Date auditTime;
    private Integer auditorId;
    private String remark;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getBedId() { return bedId; }
    public void setBedId(Integer bedId) { this.bedId = bedId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public Date getOutwardDate() { return outwardDate; }
    public void setOutwardDate(Date outwardDate) { this.outwardDate = outwardDate; }

    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public Date getApplyTime() { return applyTime; }
    public void setApplyTime(Date applyTime) { this.applyTime = applyTime; }

    public Date getAuditTime() { return auditTime; }
    public void setAuditTime(Date auditTime) { this.auditTime = auditTime; }

    public Integer getAuditorId() { return auditorId; }
    public void setAuditorId(Integer auditorId) { this.auditorId = auditorId; }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }

    @Override
    public String toString() {
        String statusText = status == 0 ? "已提交" : status == 1 ? "同意" : "拒绝";
        return "外出ID:" + id + " | 床位:" + bedId + " | 老人:" + customerName
                + " | 外出日期:" + outwardDate + " | 预计回院:" + returnDate
                + " | 状态:" + statusText + " | 备注:" + remark;
    }
}

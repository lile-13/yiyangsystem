package entity;

import java.util.Date;

public class Backdown {
    private Integer id;
    private Integer bedId;
    private String customerName;
    private Date backdownDate;
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

    public Date getBackdownDate() { return backdownDate; }
    public void setBackdownDate(Date backdownDate) { this.backdownDate = backdownDate; }

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
        return "退住ID:" + id + " | 床位:" + bedId + " | 老人:" + customerName
                + " | 退住日期:" + backdownDate + " | 状态:" + statusText
                + " | 备注:" + remark;
    }
}

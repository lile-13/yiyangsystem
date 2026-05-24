package entity;

import java.util.Date;

public class Beddetails {
    private Integer id;
    private Date startDate;
    private Date endDate;
    private String bedDetails;
    private Integer customerId;
    private Integer bedId;
    private Integer isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getBedDetails() {
        return bedDetails;
    }

    public void setBedDetails(String bedDetails) {
        this.bedDetails = bedDetails;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getBedId() {
        return bedId;
    }

    public void setBedId(Integer bedId) {
        this.bedId = bedId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "记录ID:" + id + " | 床位ID:" + bedId + " | 老人ID:" + customerId
                + " | 入住日期:" + startDate + " | 结束日期:" + endDate
                + " | 详情:" + (bedDetails != null ? bedDetails : "无");
    }
}

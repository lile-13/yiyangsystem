package entity;

import java.time.LocalDate;

public class CustomerNurseItem {
    private Integer id;
    private Integer itemId;
    private Integer customerId;
    private Integer levelId;
    private Integer nurseNumber;
    private LocalDate buyTime;
    private LocalDate maturityTime;
    private Integer isDeleted;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getItemId() { return itemId; }
    public void setItemId(Integer itemId) { this.itemId = itemId; }

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

    public Integer getLevelId() { return levelId; }
    public void setLevelId(Integer levelId) { this.levelId = levelId; }

    public Integer getNurseNumber() { return nurseNumber; }
    public void setNurseNumber(Integer nurseNumber) { this.nurseNumber = nurseNumber; }

    public LocalDate getBuyTime() { return buyTime; }
    public void setBuyTime(LocalDate buyTime) { this.buyTime = buyTime; }

    public LocalDate getMaturityTime() { return maturityTime; }
    public void setMaturityTime(LocalDate maturityTime) { this.maturityTime = maturityTime; }

    public Integer getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Integer isDeleted) { this.isDeleted = isDeleted; }

    @Override
    public String toString() {
        return "客户护理ID:" + id + " | 客户ID:" + customerId + " | 项目ID:" + itemId
                + " | 级别ID:" + levelId + " | 次数:" + nurseNumber
                + " | 购买:" + buyTime + " | 到期:" + maturityTime;
    }
}

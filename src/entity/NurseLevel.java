package entity;

import java.math.BigDecimal;

public class NurseLevel {
    private Integer id;
    private String levelName;
    private String levelDesc;
    private BigDecimal monthlyFee;
    private Integer isDeleted;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getLevelName() { return levelName; }
    public void setLevelName(String levelName) { this.levelName = levelName; }

    public String getLevelDesc() { return levelDesc; }
    public void setLevelDesc(String levelDesc) { this.levelDesc = levelDesc; }

    public BigDecimal getMonthlyFee() { return monthlyFee; }
    public void setMonthlyFee(BigDecimal monthlyFee) { this.monthlyFee = monthlyFee; }

    public Integer getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Integer isDeleted) { this.isDeleted = isDeleted; }

    @Override
    public String toString() {
        return "护理级别ID:" + id + " | 名称:" + levelName + " | 描述:" + levelDesc
                + " | 月费:" + monthlyFee;
    }
}

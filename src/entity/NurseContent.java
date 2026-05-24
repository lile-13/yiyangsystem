package entity;

public class NurseContent {
    private Integer id;
    private String serialNumber;
    private String nursingName;
    private String servicePrice;
    private String executionCycle;
    private Integer status;
    private Integer isDeleted;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public String getNursingName() { return nursingName; }
    public void setNursingName(String nursingName) { this.nursingName = nursingName; }

    public String getServicePrice() { return servicePrice; }
    public void setServicePrice(String servicePrice) { this.servicePrice = servicePrice; }

    public String getExecutionCycle() { return executionCycle; }
    public void setExecutionCycle(String executionCycle) { this.executionCycle = executionCycle; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public Integer getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Integer isDeleted) { this.isDeleted = isDeleted; }

    @Override
    public String toString() {
        String s = status == 1 ? "启用" : "停用";
        return "护理项目ID:" + id + " | 编码:" + serialNumber + " | 名称:" + nursingName
                + " | 价格:" + servicePrice + " | 周期:" + executionCycle + " | 状态:" + s;
    }
}

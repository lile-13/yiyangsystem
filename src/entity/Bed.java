package entity;

public class Bed {
    private Integer bedId;
    private String bedNo;
    private Integer bedStatus;

    public Integer getBedId() {
        return bedId;
    }

    public void setBedId(Integer bedId) {
        this.bedId = bedId;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public Integer getBedStatus() {
        return bedStatus;
    }

    public void setBedStatus(Integer bedStatus) {
        this.bedStatus = bedStatus;
    }

    @Override
    public String toString() {
        String statusText = "";
        if (bedStatus == 1) {
            statusText = "空闲";
        } else if (bedStatus == 2) {
            statusText = "已入住";
        } else {
            statusText = "其他状态";
        }
        return "床位ID：" + bedId + " | 床位号：" + bedNo + " | 状态：" + statusText;
    }
}
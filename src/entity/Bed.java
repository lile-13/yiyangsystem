package entity;

public class Bed {
    private Integer id;
    private Integer roomNo;
    private Integer bedStatus;
    private String remarks;
    private String bedNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public Integer getBedStatus() {
        return bedStatus;
    }

    public void setBedStatus(Integer bedStatus) {
        this.bedStatus = bedStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    @Override
    public String toString() {
        String statusText;
        if (bedStatus == 0) {
            statusText = "空闲";
        } else if (bedStatus == 1) {
            statusText = "已入住";
        } else if (bedStatus == 2) {
            statusText = "外出";
        } else {
            statusText = "其他";
        }
        return "床位ID:" + id + " | 房间号:" + roomNo + " | 床位号:" + bedNo
                + " | 状态:" + statusText + " | 备注:" + (remarks != null ? remarks : "无");
    }
}

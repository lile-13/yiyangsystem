package entity;

public class Room {
    private Integer id;
    private String roomFloor;
    private Integer roomNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomFloor() {
        return roomFloor;
    }

    public void setRoomFloor(String roomFloor) {
        this.roomFloor = roomFloor;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    @Override
    public String toString() {
        return "房间ID:" + id + " | 楼层:" + roomFloor + " | 房间号:" + roomNo;
    }
}

package com.yiyang.elderlycare.dao;

import com.yiyang.elderlycare.entity.Room;
import java.util.List;

public interface RoomDAO {
    List<Room> findAll();
    List<Room> findByFloor(String floor);
    Room findByRoomNo(Integer roomNo);
    int add(Room room);
    int update(Room room);
    int delete(Integer roomNo);
}

package dao;

import entity.Room;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {

    @Override
    public List<Room> findAll() {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT * FROM room";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Room r = new Room();
                r.setId(rs.getInt("id"));
                r.setRoomFloor(rs.getString("room_floor"));
                r.setRoomNo(rs.getInt("room_no"));
                list.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Room> findByFloor(String floor) {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT * FROM room WHERE room_floor = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, floor);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Room r = new Room();
                    r.setId(rs.getInt("id"));
                    r.setRoomFloor(rs.getString("room_floor"));
                    r.setRoomNo(rs.getInt("room_no"));
                    list.add(r);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Room findByRoomNo(Integer roomNo) {
        String sql = "SELECT * FROM room WHERE room_no = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, roomNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Room r = new Room();
                    r.setId(rs.getInt("id"));
                    r.setRoomFloor(rs.getString("room_floor"));
                    r.setRoomNo(rs.getInt("room_no"));
                    return r;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int add(Room room) {
        String sql = "INSERT INTO room(room_floor, room_no) VALUES(?,?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, room.getRoomFloor());
            pstmt.setInt(2, room.getRoomNo());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Room room) {
        String sql = "UPDATE room SET room_floor=?, room_no=? WHERE room_no=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, room.getRoomFloor());
            pstmt.setInt(2, room.getRoomNo());
            pstmt.setInt(3, room.getRoomNo());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Integer roomNo) {
        String sql = "DELETE FROM room WHERE room_no = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, roomNo);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

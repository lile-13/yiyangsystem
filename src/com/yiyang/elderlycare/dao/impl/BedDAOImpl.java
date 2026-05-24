package com.yiyang.elderlycare.dao.impl;
import com.yiyang.elderlycare.dao.BedDAO;

import com.yiyang.elderlycare.entity.Bed;
import com.yiyang.elderlycare.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BedDAOImpl implements BedDAO {

    @Override
    public List<Bed> findAll() {
        List<Bed> list = new ArrayList<>();
        String sql = "SELECT * FROM bed";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Bed bed = new Bed();
                bed.setId(rs.getInt("id"));
                bed.setRoomNo(rs.getInt("room_no"));
                bed.setBedStatus(rs.getInt("bed_status"));
                bed.setRemarks(rs.getString("remarks"));
                bed.setBedNo(rs.getString("bed_no"));
                list.add(bed);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Bed> findByStatus(Integer status) {
        List<Bed> list = new ArrayList<>();
        String sql = "SELECT * FROM bed WHERE bed_status = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, status);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Bed bed = new Bed();
                    bed.setId(rs.getInt("id"));
                    bed.setRoomNo(rs.getInt("room_no"));
                    bed.setBedStatus(rs.getInt("bed_status"));
                    bed.setRemarks(rs.getString("remarks"));
                    bed.setBedNo(rs.getString("bed_no"));
                    list.add(bed);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Bed findById(Integer id) {
        String sql = "SELECT * FROM bed WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Bed bed = new Bed();
                    bed.setId(rs.getInt("id"));
                    bed.setRoomNo(rs.getInt("room_no"));
                    bed.setBedStatus(rs.getInt("bed_status"));
                    bed.setRemarks(rs.getString("remarks"));
                    bed.setBedNo(rs.getString("bed_no"));
                    return bed;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateStatus(Integer id, Integer status) {
        String sql = "UPDATE bed SET bed_status = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, status);
            pstmt.setInt(2, id);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

package com.yiyang.elderlycare.dao.impl;
import com.yiyang.elderlycare.dao.NurseContentDAO;

import com.yiyang.elderlycare.entity.NurseContent;
import com.yiyang.elderlycare.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NurseContentDAOImpl implements NurseContentDAO {

    @Override
    public List<NurseContent> findAll() {
        List<NurseContent> list = new ArrayList<>();
        String sql = "SELECT * FROM nursecontent WHERE is_deleted = 0";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                NurseContent n = new NurseContent();
                n.setId(rs.getInt("id"));
                n.setSerialNumber(rs.getString("serial_number"));
                n.setNursingName(rs.getString("nursing_name"));
                n.setServicePrice(rs.getString("service_price"));
                n.setExecutionCycle(rs.getString("execution_cycle"));
                n.setStatus(rs.getInt("status"));
                n.setIsDeleted(rs.getInt("is_deleted"));
                list.add(n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public NurseContent findById(Integer id) {
        String sql = "SELECT * FROM nursecontent WHERE id = ? AND is_deleted = 0";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    NurseContent n = new NurseContent();
                    n.setId(rs.getInt("id"));
                    n.setSerialNumber(rs.getString("serial_number"));
                    n.setNursingName(rs.getString("nursing_name"));
                    n.setServicePrice(rs.getString("service_price"));
                    n.setExecutionCycle(rs.getString("execution_cycle"));
                    n.setStatus(rs.getInt("status"));
                    n.setIsDeleted(rs.getInt("is_deleted"));
                    return n;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int add(NurseContent item) {
        String sql = "INSERT INTO nursecontent(serial_number, nursing_name, service_price, execution_cycle, status, is_deleted) VALUES(?,?,?,?,?,0)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getSerialNumber());
            pstmt.setString(2, item.getNursingName());
            pstmt.setString(3, item.getServicePrice());
            pstmt.setString(4, item.getExecutionCycle());
            pstmt.setInt(5, item.getStatus());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(NurseContent item) {
        String sql = "UPDATE nursecontent SET serial_number=?, nursing_name=?, service_price=?, execution_cycle=?, status=? WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getSerialNumber());
            pstmt.setString(2, item.getNursingName());
            pstmt.setString(3, item.getServicePrice());
            pstmt.setString(4, item.getExecutionCycle());
            pstmt.setInt(5, item.getStatus());
            pstmt.setInt(6, item.getId());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Integer id) {
        String sql = "UPDATE nursecontent SET is_deleted = 1 WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

package dao;

import entity.Backdown;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BackdownDAOImpl implements BackdownDAO {

    @Override
    public List<Backdown> findAll() {
        List<Backdown> list = new ArrayList<>();
        String sql = "SELECT * FROM backdown";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Backdown b = new Backdown();
                b.setId(rs.getInt("id"));
                b.setBedId(rs.getInt("bed_id"));
                b.setCustomerName(rs.getString("customer_name"));
                b.setBackdownDate(rs.getDate("backdown_date"));
                b.setStatus(rs.getInt("status"));
                b.setApplyTime(rs.getTimestamp("apply_time"));
                b.setAuditTime(rs.getTimestamp("audit_time"));
                b.setAuditorId(rs.getObject("auditor_id") != null ? rs.getInt("auditor_id") : null);
                b.setRemark(rs.getString("remark"));
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int add(Backdown backdown) {
        String sql = "INSERT INTO backdown(bed_id, customer_name, backdown_date, status, apply_time, remark) VALUES(?,?,?,0,NOW(),?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, backdown.getBedId());
            pstmt.setString(2, backdown.getCustomerName());
            pstmt.setDate(3, new java.sql.Date(backdown.getBackdownDate().getTime()));
            pstmt.setString(4, backdown.getRemark());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int audit(Integer id, Integer status, Integer auditorId) {
        String sql = "UPDATE backdown SET status=?, audit_time=NOW(), auditor_id=? WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, status);
            pstmt.setInt(2, auditorId);
            pstmt.setInt(3, id);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

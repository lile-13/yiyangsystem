package dao;

import entity.Outward;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OutwardDAOImpl implements OutwardDAO {

    @Override
    public List<Outward> findAll() {
        List<Outward> list = new ArrayList<>();
        String sql = "SELECT * FROM outward";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Outward o = new Outward();
                o.setId(rs.getInt("id"));
                o.setBedId(rs.getInt("bed_id"));
                o.setCustomerName(rs.getString("customer_name"));
                o.setOutwardDate(rs.getDate("outward_date"));
                o.setReturnDate(rs.getDate("return_date"));
                o.setStatus(rs.getInt("status"));
                o.setApplyTime(rs.getTimestamp("apply_time"));
                o.setAuditTime(rs.getTimestamp("audit_time"));
                o.setAuditorId(rs.getObject("auditor_id") != null ? rs.getInt("auditor_id") : null);
                o.setRemark(rs.getString("remark"));
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int add(Outward outward) {
        String sql = "INSERT INTO outward(bed_id, customer_name, outward_date, return_date, status, apply_time, remark) VALUES(?,?,?,?,0,NOW(),?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, outward.getBedId());
            pstmt.setString(2, outward.getCustomerName());
            pstmt.setDate(3, new java.sql.Date(outward.getOutwardDate().getTime()));
            pstmt.setDate(4, new java.sql.Date(outward.getReturnDate().getTime()));
            pstmt.setString(5, outward.getRemark());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int audit(Integer id, Integer status, Integer auditorId) {
        String sql = "UPDATE outward SET status=?, audit_time=NOW(), auditor_id=? WHERE id=?";
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

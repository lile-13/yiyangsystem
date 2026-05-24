package dao;

import entity.NurseRecord;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NurseRecordDAOImpl implements NurseRecordDAO {

    @Override
    public List<NurseRecord> findAll() {
        List<NurseRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM nurserecord WHERE is_deleted = 0";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public NurseRecord findById(Integer id) {
        String sql = "SELECT * FROM nurserecord WHERE id = ? AND is_deleted = 0";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<NurseRecord> findByCustomerId(Integer customerId) {
        List<NurseRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM nurserecord WHERE customer_id = ? AND is_deleted = 0 ORDER BY nursing_time DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<NurseRecord> findByUserId(Integer userId) {
        List<NurseRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM nurserecord WHERE user_id = ? AND is_deleted = 0 ORDER BY nursing_time DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int add(NurseRecord record) {
        String sql = "INSERT INTO nurserecord(is_deleted, customer_id, item_id, nursing_time, nursing_content, nursing_count, user_id) VALUES(0,?,?,NOW(),?,?,?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, record.getCustomerId());
            pstmt.setInt(2, record.getItemId());
            pstmt.setString(3, record.getNursingContent());
            pstmt.setInt(4, record.getNursingCount());
            pstmt.setInt(5, record.getUserId());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Integer id) {
        String sql = "UPDATE nurserecord SET is_deleted = 1 WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private NurseRecord mapRow(ResultSet rs) throws SQLException {
        NurseRecord r = new NurseRecord();
        r.setId(rs.getInt("id"));
        r.setIsDeleted(rs.getInt("is_deleted"));
        r.setCustomerId(rs.getInt("customer_id"));
        r.setItemId(rs.getInt("item_id"));
        r.setNursingTime(rs.getTimestamp("nursing_time"));
        r.setNursingContent(rs.getString("nursing_content"));
        r.setNursingCount(rs.getInt("nursing_count"));
        r.setUserId(rs.getInt("user_id"));
        return r;
    }
}

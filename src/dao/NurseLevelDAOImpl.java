package dao;

import entity.NurseLevel;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NurseLevelDAOImpl implements NurseLevelDAO {

    @Override
    public List<NurseLevel> findAll() {
        List<NurseLevel> list = new ArrayList<>();
        String sql = "SELECT * FROM nurselevel WHERE is_deleted = 0";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                NurseLevel n = new NurseLevel();
                n.setId(rs.getInt("id"));
                n.setLevelName(rs.getString("level_name"));
                n.setLevelDesc(rs.getString("level_desc"));
                n.setMonthlyFee(rs.getBigDecimal("monthly_fee"));
                n.setIsDeleted(rs.getInt("is_deleted"));
                list.add(n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public NurseLevel findById(Integer id) {
        String sql = "SELECT * FROM nurselevel WHERE id = ? AND is_deleted = 0";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    NurseLevel n = new NurseLevel();
                    n.setId(rs.getInt("id"));
                    n.setLevelName(rs.getString("level_name"));
                    n.setLevelDesc(rs.getString("level_desc"));
                    n.setMonthlyFee(rs.getBigDecimal("monthly_fee"));
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
    public int add(NurseLevel level) {
        String sql = "INSERT INTO nurselevel(level_name, level_desc, monthly_fee, is_deleted) VALUES(?,?,?,0)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, level.getLevelName());
            pstmt.setString(2, level.getLevelDesc());
            pstmt.setBigDecimal(3, level.getMonthlyFee());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(NurseLevel level) {
        String sql = "UPDATE nurselevel SET level_name=?, level_desc=?, monthly_fee=? WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, level.getLevelName());
            pstmt.setString(2, level.getLevelDesc());
            pstmt.setBigDecimal(3, level.getMonthlyFee());
            pstmt.setInt(4, level.getId());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Integer id) {
        String sql = "UPDATE nurselevel SET is_deleted = 1 WHERE id = ?";
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

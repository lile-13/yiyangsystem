package dao;

import entity.Role;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {

    @Override
    public List<Role> findAll() {
        List<Role> list = new ArrayList<>();
        String sql = "SELECT * FROM role WHERE is_deleted = 0";
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
    public Role findById(Integer id) {
        String sql = "SELECT * FROM role WHERE id = ? AND is_deleted = 0";
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
    public int add(Role role) {
        String sql = "INSERT INTO role(create_time, is_deleted, name) VALUES(NOW(),0,?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, role.getName());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Role role) {
        String sql = "UPDATE role SET update_time=NOW(), update_by=?, name=? WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, role.getUpdateBy() != null ? role.getUpdateBy() : 1);
            pstmt.setString(2, role.getName());
            pstmt.setInt(3, role.getId());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Integer id) {
        String sql = "UPDATE role SET is_deleted = 1 WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Role mapRow(ResultSet rs) throws SQLException {
        Role r = new Role();
        r.setId(rs.getInt("id"));
        r.setCreateTime(rs.getTimestamp("create_time"));
        r.setUpdateTime(rs.getTimestamp("update_time"));
        r.setUpdateBy(rs.getObject("update_by") != null ? rs.getInt("update_by") : null);
        r.setIsDeleted(rs.getInt("is_deleted"));
        r.setName(rs.getString("name"));
        return r;
    }
}

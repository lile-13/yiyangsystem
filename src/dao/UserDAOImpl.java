package dao;

import entity.User;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE is_deleted = 0";
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
    public User findById(Integer id) {
        String sql = "SELECT * FROM user WHERE id = ? AND is_deleted = 0";
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
    public User findByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ? AND is_deleted = 0";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
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
    public User login(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ? AND is_deleted = 0";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
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
    public List<User> findByRoleId(Integer roleId) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE role_id = ? AND is_deleted = 0";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, roleId);
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
    public int add(User user) {
        String sql = "INSERT INTO user(create_time, create_by, is_deleted, nickname, username, password, sex, email, phone_number, role_id) VALUES(NOW(),?,0,?,?,?,?,?,?,?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, user.getCreateBy() != null ? user.getCreateBy() : 1);
            pstmt.setString(2, user.getNickname());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, user.getSex() != null ? user.getSex() : 1);
            pstmt.setString(6, user.getEmail());
            pstmt.setString(7, user.getPhoneNumber());
            pstmt.setInt(8, user.getRoleId());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(User user) {
        String sql = "UPDATE user SET update_time=NOW(), update_by=?, nickname=?, username=?, password=?, sex=?, email=?, phone_number=?, role_id=? WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, user.getUpdateBy() != null ? user.getUpdateBy() : 1);
            pstmt.setString(2, user.getNickname());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, user.getSex());
            pstmt.setString(6, user.getEmail());
            pstmt.setString(7, user.getPhoneNumber());
            pstmt.setInt(8, user.getRoleId());
            pstmt.setInt(9, user.getId());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Integer id) {
        String sql = "UPDATE user SET is_deleted = 1 WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private User mapRow(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setCreateTime(rs.getTimestamp("create_time"));
        u.setCreateBy(rs.getInt("create_by"));
        u.setUpdateTime(rs.getTimestamp("update_time"));
        u.setUpdateBy(rs.getObject("update_by") != null ? rs.getInt("update_by") : null);
        u.setIsDeleted(rs.getInt("is_deleted"));
        u.setNickname(rs.getString("nickname"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setSex(rs.getInt("sex"));
        u.setEmail(rs.getString("email"));
        u.setPhoneNumber(rs.getString("phone_number"));
        u.setRoleId(rs.getInt("role_id"));
        return u;
    }
}

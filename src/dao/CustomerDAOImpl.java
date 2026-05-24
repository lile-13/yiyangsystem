package dao;

import entity.Customer;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customer";
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
    public Customer findById(int id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
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
    public List<Customer> findByName(String name) {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE name LIKE ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + name + "%");
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
    public List<Customer> findByUserId(int userId) {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE user_id = ?";
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
    public void add(Customer customer) {
        String sql = "INSERT INTO customer(name, age, id_card, contact_tel, bed_id, user_id, level_id) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getName());
            pstmt.setInt(2, customer.getAge());
            pstmt.setString(3, customer.getIdCard());
            pstmt.setString(4, customer.getContactTel());
            pstmt.setInt(5, customer.getBedId());
            if (customer.getUserId() != null) {
                pstmt.setInt(6, customer.getUserId());
            } else {
                pstmt.setNull(6, Types.INTEGER);
            }
            if (customer.getLevelId() != null) {
                pstmt.setInt(7, customer.getLevelId());
            } else {
                pstmt.setNull(7, Types.INTEGER);
            }
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE customer SET name=?, age=?, id_card=?, contact_tel=?, bed_id=?, user_id=?, level_id=? WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getName());
            pstmt.setInt(2, customer.getAge());
            pstmt.setString(3, customer.getIdCard());
            pstmt.setString(4, customer.getContactTel());
            pstmt.setInt(5, customer.getBedId());
            if (customer.getUserId() != null) {
                pstmt.setInt(6, customer.getUserId());
            } else {
                pstmt.setNull(6, Types.INTEGER);
            }
            if (customer.getLevelId() != null) {
                pstmt.setInt(7, customer.getLevelId());
            } else {
                pstmt.setNull(7, Types.INTEGER);
            }
            pstmt.setInt(8, customer.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM customer WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int updateSteward(int customerId, int userId) {
        String sql = "UPDATE customer SET user_id = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, customerId);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Customer mapRow(ResultSet rs) throws SQLException {
        Customer c = new Customer();
        c.setId(rs.getInt("id"));
        c.setName(rs.getString("name"));
        c.setAge(rs.getInt("age"));
        c.setIdCard(rs.getString("id_card"));
        c.setContactTel(rs.getString("contact_tel"));
        c.setBedId(rs.getInt("bed_id"));
        c.setUserId(rs.getObject("user_id") != null ? rs.getInt("user_id") : null);
        c.setLevelId(rs.getObject("level_id") != null ? rs.getInt("level_id") : null);
        return c;
    }
}

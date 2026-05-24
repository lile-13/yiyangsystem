package dao;

import entity.CustomerNurseItem;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerNurseItemDAOImpl implements CustomerNurseItemDAO {

    @Override
    public List<CustomerNurseItem> findAll() {
        List<CustomerNurseItem> list = new ArrayList<>();
        String sql = "SELECT * FROM customernurseitem WHERE is_deleted = 0";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                CustomerNurseItem item = new CustomerNurseItem();
                item.setId(rs.getInt("id"));
                item.setItemId(rs.getInt("item_id"));
                item.setCustomerId(rs.getInt("customer_id"));
                item.setLevelId(rs.getInt("level_id"));
                item.setNurseNumber(rs.getInt("nurse_number"));
                item.setBuyTime(rs.getDate("buy_time").toLocalDate());
                item.setMaturityTime(rs.getDate("maturity_time").toLocalDate());
                item.setIsDeleted(rs.getInt("is_deleted"));
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public CustomerNurseItem findById(Integer id) {
        String sql = "SELECT * FROM customernurseitem WHERE id = ? AND is_deleted = 0";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    CustomerNurseItem item = new CustomerNurseItem();
                    item.setId(rs.getInt("id"));
                    item.setItemId(rs.getInt("item_id"));
                    item.setCustomerId(rs.getInt("customer_id"));
                    item.setLevelId(rs.getInt("level_id"));
                    item.setNurseNumber(rs.getInt("nurse_number"));
                    item.setBuyTime(rs.getDate("buy_time").toLocalDate());
                    item.setMaturityTime(rs.getDate("maturity_time").toLocalDate());
                    item.setIsDeleted(rs.getInt("is_deleted"));
                    return item;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int add(CustomerNurseItem item) {
        String sql = "INSERT INTO customernurseitem(item_id, customer_id, level_id, nurse_number, buy_time, maturity_time, is_deleted) VALUES(?,?,?,?,?,?,0)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, item.getItemId());
            pstmt.setInt(2, item.getCustomerId());
            pstmt.setInt(3, item.getLevelId());
            pstmt.setInt(4, item.getNurseNumber());
            pstmt.setDate(5, java.sql.Date.valueOf(item.getBuyTime()));
            pstmt.setDate(6, java.sql.Date.valueOf(item.getMaturityTime()));
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(CustomerNurseItem item) {
        String sql = "UPDATE customernurseitem SET item_id=?, customer_id=?, level_id=?, nurse_number=?, buy_time=?, maturity_time=? WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, item.getItemId());
            pstmt.setInt(2, item.getCustomerId());
            pstmt.setInt(3, item.getLevelId());
            pstmt.setInt(4, item.getNurseNumber());
            pstmt.setDate(5, java.sql.Date.valueOf(item.getBuyTime()));
            pstmt.setDate(6, java.sql.Date.valueOf(item.getMaturityTime()));
            pstmt.setInt(7, item.getId());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Integer id) {
        String sql = "UPDATE customernurseitem SET is_deleted = 1 WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<CustomerNurseItem> findByCustomerId(Integer customerId) {
        List<CustomerNurseItem> list = new ArrayList<>();
        String sql = "SELECT * FROM customernurseitem WHERE customer_id = ? AND is_deleted = 0";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    CustomerNurseItem item = new CustomerNurseItem();
                    item.setId(rs.getInt("id"));
                    item.setItemId(rs.getInt("item_id"));
                    item.setCustomerId(rs.getInt("customer_id"));
                    item.setLevelId(rs.getInt("level_id"));
                    item.setNurseNumber(rs.getInt("nurse_number"));
                    item.setBuyTime(rs.getDate("buy_time").toLocalDate());
                    item.setMaturityTime(rs.getDate("maturity_time").toLocalDate());
                    item.setIsDeleted(rs.getInt("is_deleted"));
                    list.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

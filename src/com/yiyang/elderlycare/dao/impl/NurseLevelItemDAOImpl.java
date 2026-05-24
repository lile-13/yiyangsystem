package com.yiyang.elderlycare.dao.impl;
import com.yiyang.elderlycare.dao.NurseLevelItemDAO;

import com.yiyang.elderlycare.entity.NurseLevelItem;
import com.yiyang.elderlycare.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NurseLevelItemDAOImpl implements NurseLevelItemDAO {

    @Override
    public List<NurseLevelItem> findAll() {
        List<NurseLevelItem> list = new ArrayList<>();
        String sql = "SELECT * FROM nurselevelitem";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                NurseLevelItem item = new NurseLevelItem();
                item.setId(rs.getInt("id"));
                item.setLevelId(rs.getInt("level_id"));
                item.setItemId(rs.getInt("item_id"));
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<NurseLevelItem> findByLevelId(Integer levelId) {
        List<NurseLevelItem> list = new ArrayList<>();
        String sql = "SELECT * FROM nurselevelitem WHERE level_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, levelId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    NurseLevelItem item = new NurseLevelItem();
                    item.setId(rs.getInt("id"));
                    item.setLevelId(rs.getInt("level_id"));
                    item.setItemId(rs.getInt("item_id"));
                    list.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<NurseLevelItem> findByItemId(Integer itemId) {
        List<NurseLevelItem> list = new ArrayList<>();
        String sql = "SELECT * FROM nurselevelitem WHERE item_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, itemId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    NurseLevelItem item = new NurseLevelItem();
                    item.setId(rs.getInt("id"));
                    item.setLevelId(rs.getInt("level_id"));
                    item.setItemId(rs.getInt("item_id"));
                    list.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

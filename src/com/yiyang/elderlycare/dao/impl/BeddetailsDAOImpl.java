package com.yiyang.elderlycare.dao.impl;
import com.yiyang.elderlycare.dao.BeddetailsDAO;

import com.yiyang.elderlycare.entity.Beddetails;
import com.yiyang.elderlycare.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeddetailsDAOImpl implements BeddetailsDAO {

    @Override
    public List<Beddetails> findAll() {
        List<Beddetails> list = new ArrayList<>();
        String sql = "SELECT * FROM beddetails";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Beddetails d = new Beddetails();
                d.setId(rs.getInt("id"));
                d.setStartDate(rs.getDate("start_date"));
                d.setEndDate(rs.getDate("end_date"));
                d.setBedDetails(rs.getString("bed_details"));
                d.setCustomerId(rs.getInt("customer_id"));
                d.setBedId(rs.getInt("bed_id"));
                d.setIsDeleted(rs.getInt("is_deleted"));
                list.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Beddetails> findByCustomerId(Integer customerId) {
        List<Beddetails> list = new ArrayList<>();
        String sql = "SELECT * FROM beddetails WHERE customer_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Beddetails d = new Beddetails();
                    d.setId(rs.getInt("id"));
                    d.setStartDate(rs.getDate("start_date"));
                    d.setEndDate(rs.getDate("end_date"));
                    d.setBedDetails(rs.getString("bed_details"));
                    d.setCustomerId(rs.getInt("customer_id"));
                    d.setBedId(rs.getInt("bed_id"));
                    d.setIsDeleted(rs.getInt("is_deleted"));
                    list.add(d);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Beddetails> findByBedId(Integer bedId) {
        List<Beddetails> list = new ArrayList<>();
        String sql = "SELECT * FROM beddetails WHERE bed_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bedId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Beddetails d = new Beddetails();
                    d.setId(rs.getInt("id"));
                    d.setStartDate(rs.getDate("start_date"));
                    d.setEndDate(rs.getDate("end_date"));
                    d.setBedDetails(rs.getString("bed_details"));
                    d.setCustomerId(rs.getInt("customer_id"));
                    d.setBedId(rs.getInt("bed_id"));
                    d.setIsDeleted(rs.getInt("is_deleted"));
                    list.add(d);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int add(Beddetails details) {
        String sql = "INSERT INTO beddetails(start_date, end_date, bed_details, customer_id, bed_id, is_deleted) VALUES (?,?,?,?,?,0)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(details.getStartDate().getTime()));
            if (details.getEndDate() != null) {
                pstmt.setDate(2, new java.sql.Date(details.getEndDate().getTime()));
            } else {
                pstmt.setNull(2, Types.DATE);
            }
            pstmt.setString(3, details.getBedDetails());
            pstmt.setInt(4, details.getCustomerId());
            pstmt.setInt(5, details.getBedId());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

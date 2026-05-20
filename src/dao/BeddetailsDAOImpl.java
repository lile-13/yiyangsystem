package dao;

import entity.Beddetails;
import util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BeddetailsDAOImpl {
    public List<Beddetails> findAll() {
        List<Beddetails> list = new ArrayList<>();
        String sql = "SELECT * FROM beddetails";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Beddetails details = new Beddetails();
                details.setId(rs.getInt("id"));
                details.setBedId(rs.getInt("bed_id"));
                details.setCustomerId(rs.getInt("customer_id"));
                details.setStartDate(rs.getDate("start_date"));
                details.setEndDate(rs.getDate("end_date"));
                list.add(details);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
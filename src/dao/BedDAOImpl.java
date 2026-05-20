package dao;

import entity.Bed;
import util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BedDAOImpl {
    public List<Bed> findAll() {
        List<Bed> list = new ArrayList<>();
        String sql = "SELECT * FROM bed";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Bed bed = new Bed();
                bed.setBedId(rs.getInt("bed_id"));
                bed.setBedNo(rs.getString("bed_no"));
                bed.setBedStatus(rs.getInt("bed_status"));
                list.add(bed);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
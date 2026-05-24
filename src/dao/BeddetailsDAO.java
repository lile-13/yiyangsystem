package dao;

import entity.Beddetails;
import java.util.List;

public interface BeddetailsDAO {
    List<Beddetails> findAll();
    List<Beddetails> findByCustomerId(Integer customerId);
    List<Beddetails> findByBedId(Integer bedId);
    int add(Beddetails details);
}

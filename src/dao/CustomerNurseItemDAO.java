package dao;

import entity.CustomerNurseItem;
import java.util.List;

public interface CustomerNurseItemDAO {
    List<CustomerNurseItem> findAll();
    CustomerNurseItem findById(Integer id);
    int add(CustomerNurseItem item);
    int update(CustomerNurseItem item);
    int delete(Integer id);
    List<CustomerNurseItem> findByCustomerId(Integer customerId);
}

package dao;

import entity.Customer;
import java.util.List;

public interface CustomerDAO {
    List<Customer> findAll();
    void add(Customer customer);
    void delete(int id);
}
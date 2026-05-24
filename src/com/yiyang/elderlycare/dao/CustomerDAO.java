package com.yiyang.elderlycare.dao;

import com.yiyang.elderlycare.entity.Customer;
import java.util.List;

public interface CustomerDAO {
    List<Customer> findAll();
    Customer findById(int id);
    List<Customer> findByName(String name);
    List<Customer> findByUserId(int userId);
    void add(Customer customer);
    void update(Customer customer);
    void delete(int id);
    int updateSteward(int customerId, int userId);
}

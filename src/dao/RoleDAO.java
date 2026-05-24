package dao;

import entity.Role;
import java.util.List;

public interface RoleDAO {
    List<Role> findAll();
    Role findById(Integer id);
    int add(Role role);
    int update(Role role);
    int delete(Integer id);
}

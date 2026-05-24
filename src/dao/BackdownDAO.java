package dao;

import entity.Backdown;
import java.util.List;

public interface BackdownDAO {
    List<Backdown> findAll();
    int add(Backdown backdown);
    int audit(Integer id, Integer status, Integer auditorId);
}

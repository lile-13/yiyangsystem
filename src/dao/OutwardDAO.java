package dao;

import entity.Outward;
import java.util.List;

public interface OutwardDAO {
    List<Outward> findAll();
    int add(Outward outward);
    int audit(Integer id, Integer status, Integer auditorId);
}

package com.yiyang.elderlycare.dao;

import com.yiyang.elderlycare.entity.Backdown;
import java.util.List;

public interface BackdownDAO {
    List<Backdown> findAll();
    int add(Backdown backdown);
    int audit(Integer id, Integer status, Integer auditorId);
}

package com.yiyang.elderlycare.dao;

import com.yiyang.elderlycare.entity.Bed;
import java.util.List;

public interface BedDAO {
    List<Bed> findAll();
    List<Bed> findByStatus(Integer status);
    Bed findById(Integer id);
    int updateStatus(Integer id, Integer status);
}

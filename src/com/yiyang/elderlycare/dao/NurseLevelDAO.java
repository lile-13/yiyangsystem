package com.yiyang.elderlycare.dao;

import com.yiyang.elderlycare.entity.NurseLevel;
import java.util.List;

public interface NurseLevelDAO {
    List<NurseLevel> findAll();
    NurseLevel findById(Integer id);
    int add(NurseLevel level);
    int update(NurseLevel level);
    int delete(Integer id);
}

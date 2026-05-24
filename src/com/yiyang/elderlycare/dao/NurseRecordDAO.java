package com.yiyang.elderlycare.dao;

import com.yiyang.elderlycare.entity.NurseRecord;
import java.util.List;

public interface NurseRecordDAO {
    List<NurseRecord> findAll();
    NurseRecord findById(Integer id);
    List<NurseRecord> findByCustomerId(Integer customerId);
    List<NurseRecord> findByUserId(Integer userId);
    int add(NurseRecord record);
    int delete(Integer id);
}

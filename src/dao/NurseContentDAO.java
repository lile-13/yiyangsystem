package dao;

import entity.NurseContent;
import java.util.List;

public interface NurseContentDAO {
    List<NurseContent> findAll();
    NurseContent findById(Integer id);
    int add(NurseContent item);
    int update(NurseContent item);
    int delete(Integer id);
}

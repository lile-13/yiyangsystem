package dao;

import entity.NurseLevelItem;
import java.util.List;

public interface NurseLevelItemDAO {
    List<NurseLevelItem> findByLevelId(Integer levelId);
    List<NurseLevelItem> findByItemId(Integer itemId);
    List<NurseLevelItem> findAll();
}

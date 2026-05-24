package entity;

public class NurseLevelItem {
    private Integer id;
    private Integer levelId;
    private Integer itemId;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getLevelId() { return levelId; }
    public void setLevelId(Integer levelId) { this.levelId = levelId; }

    public Integer getItemId() { return itemId; }
    public void setItemId(Integer itemId) { this.itemId = itemId; }

    @Override
    public String toString() {
        return "关联ID:" + id + " | 级别ID:" + levelId + " | 项目ID:" + itemId;
    }
}

package com.yiyang.elderlycare.entity;

public class Customer {
    private int id;
    private String name;
    private int age;
    private String idCard;
    private String contactTel;
    private int bedId;
    private Integer userId;
    private Integer levelId;

    public Customer() {}

    public Customer(String name, int age, String idCard, String contactTel, int bedId) {
        this.name = name;
        this.age = age;
        this.idCard = idCard;
        this.contactTel = contactTel;
        this.bedId = bedId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getIdCard() { return idCard; }
    public void setIdCard(String idCard) { this.idCard = idCard; }
    public String getContactTel() { return contactTel; }
    public void setContactTel(String contactTel) { this.contactTel = contactTel; }
    public int getBedId() { return bedId; }
    public void setBedId(int bedId) { this.bedId = bedId; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getLevelId() { return levelId; }
    public void setLevelId(Integer levelId) { this.levelId = levelId; }

    @Override
    public String toString() {
        String steward = userId != null && userId > 0 ? "管家ID:" + userId : "无管家";
        return "ID:"+id+" 姓名:"+name+" 年龄:"+age+" 电话:"+contactTel+" 床位:"+bedId+" "+steward;
    }
}
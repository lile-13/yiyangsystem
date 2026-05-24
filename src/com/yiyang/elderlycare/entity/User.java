package com.yiyang.elderlycare.entity;

import java.util.Date;

public class User {
    private Integer id;
    private Date createTime;
    private Integer createBy;
    private Date updateTime;
    private Integer updateBy;
    private Integer isDeleted;
    private String nickname;
    private String username;
    private String password;
    private Integer sex;
    private String email;
    private String phoneNumber;
    private Integer roleId;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    public Integer getCreateBy() { return createBy; }
    public void setCreateBy(Integer createBy) { this.createBy = createBy; }

    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }

    public Integer getUpdateBy() { return updateBy; }
    public void setUpdateBy(Integer updateBy) { this.updateBy = updateBy; }

    public Integer getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Integer isDeleted) { this.isDeleted = isDeleted; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Integer getSex() { return sex; }
    public void setSex(Integer sex) { this.sex = sex; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Integer getRoleId() { return roleId; }
    public void setRoleId(Integer roleId) { this.roleId = roleId; }

    @Override
    public String toString() {
        String sexText = sex != null && sex == 1 ? "男" : "女";
        String roleText = roleId != null && roleId == 1 ? "管理员" : "健康管家";
        return "用户ID:" + id + " | 账号:" + username + " | 姓名:" + nickname
                + " | 性别:" + sexText + " | 电话:" + phoneNumber
                + " | 角色:" + roleText;
    }
}

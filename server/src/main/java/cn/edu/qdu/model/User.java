package cn.edu.qdu.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 'id', '用户名', '邮箱', '积分', '状态', '创建时间','备注'
 * Created by Adam on 2019/5/11 14:51.
 */
public class User {

    private int id;
    private String username;
    private String email;
    private int point;
    private int state;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    private String note;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User() {
    }

    public User(int id, int state, String note) {
        this.id = id;
        this.state = state;
        this.note = note;
    }

    public User(int id, String username, String email, int point, int state, Date createTime, String note) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.point = point;
        this.state = state;
        this.createTime = createTime;
        this.note = note;
    }
}

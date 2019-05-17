package cn.edu.qdu.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * id	code	createTime	model	note
 * point	stock	title	inputUser_id
 * username	masterPic_id	p_url	url
 * Created by Adam on 2019/5/14 17:31.
 */
public class Product {
    private Integer id;
    //private String code;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    private Integer model;
    private String note;
    private Integer point;
    //private Integer stock;
    private String title;
    private String username;
    //private Integer masterPic_id;
    private String pictureUrl;
    private String fileUrl;
    private Integer state;

    public Product() {
    }

    public Product(Integer id, Integer state) {
        this.id = id;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}

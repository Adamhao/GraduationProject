package cn.edu.qdu.model.vo;

import cn.edu.qdu.model.ProductType;
import cn.edu.qdu.model.User;

import java.util.Date;

public class ProductShow {
    
    private String title;//名称
    private Integer point;//价格
    private ProductType model;//类别
    private Long stock;//热度
    private String orderNumber;//订单编号
    private User user;//关联客户
    private Date createTime;//创建时间
    private Date payTime;//付款时间


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public ProductType getModel() {
        return model;
    }

    public void setModel(ProductType model) {
        this.model = model;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}

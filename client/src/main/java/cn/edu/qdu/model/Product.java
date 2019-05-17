package cn.edu.qdu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@DynamicUpdate
@Table(name = "t_product")
public class Product implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String title;//名称
    private Integer point;//价格
    @JsonIgnore
    private Picture masterPic;//主图
    private String note;//描述
    private Date createTime;//创建时间
    private ProductType model;//类别
    private Long stock;//热度
    @JsonIgnore
    private User inputUser;//创建人
    private String url;//下载地址
    private String postfix;//后缀名

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Lob
    @Column(columnDefinition = "TEXT")
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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "model")
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

    @ManyToOne
    @JoinColumn
    public User getInputUser() {
        return inputUser;
    }

    public void setInputUser(User inputUser) {
        this.inputUser = inputUser;
    }

    @ManyToOne
    @JoinColumn
    public Picture getMasterPic() {
        return masterPic;
    }

    public void setMasterPic(Picture masterPic) {
        this.masterPic = masterPic;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", point=" + point +
                ", masterPic=" + masterPic +
                ", note='" + note + '\'' +
                ", createTime=" + createTime +
                ", model='" + model + '\'' +
                ", stock=" + stock +
                ", inputUser=" + inputUser +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }
}

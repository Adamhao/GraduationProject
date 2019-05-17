package cn.edu.qdu.util;

import cn.edu.qdu.model.Product;


public class CartItem {
    private Product product;//商品
    private Integer total;//数量

    public CartItem(Product product, Integer total) {
        this.product = product;
        this.total = total;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}

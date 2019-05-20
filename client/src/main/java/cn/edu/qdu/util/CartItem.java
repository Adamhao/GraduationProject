package cn.edu.qdu.util;

import cn.edu.qdu.model.Product;


public class CartItem {
    private Product product;//商品

    public CartItem(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}

package cn.edu.qdu.util;

import cn.edu.qdu.model.OrderItem;
import cn.edu.qdu.common.Constants;
import cn.edu.qdu.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CartUtil {
    public static final String CART = Constants.CART;
    private static Logger logger = LoggerFactory.getLogger(CartUtil.class);

    /**
     * 添加商品到购物车中
     *
     * @param session
     * @param product
     * @param total
     */
    public static synchronized void saveProductToCart(HttpSession session, Product product, Integer total) {
        Map<Integer, CartItem> cartItemMap = (HashMap<Integer, CartItem>) session.getAttribute(CART);
        CartItem ci = new CartItem(product);
        if (cartItemMap == null) {
            cartItemMap = new HashMap < Integer, CartItem > ();
        }
        //判断当前购物车中是否包含此商品
        if (cartItemMap.containsKey(product.getId())) {
        } else {
            cartItemMap.put(product.getId(), ci);
        }
        session.setAttribute(CART, cartItemMap);
    }

    /**
     * 删除购物车中的商品
     *
     * @param session
     * @param productId
     */
    public static synchronized void deleteProductFromCart(HttpSession session, Integer productId) {
        Map<Integer, CartItem> cartItemMap = (HashMap<Integer, CartItem>) session.getAttribute(CART);
        if(cartItemMap!=null) {
            cartItemMap.remove(productId);
        }
        session.setAttribute(CART, cartItemMap);
    }

    /**
     * 清空购物车
     *
     * @param session
     */
    public static synchronized void cleanCart(HttpSession session) {
        Map<Integer, CartItem> cartItemMap = (HashMap<Integer, CartItem>) session.getAttribute(CART);
        if(cartItemMap!=null){
            cartItemMap.clear();
        }
        logger.debug("清空购物车 ： cart :"+cartItemMap);
        session.setAttribute(CART, cartItemMap);
    }

    public static List<OrderItem> getOrderItemFromCart(HttpSession session){
        Map<Integer, CartItem> cartItemMap = (HashMap<Integer, CartItem>) session.getAttribute(CART);
        logger.debug("获取商品信息 ： cart :"+cartItemMap);
        if(cartItemMap==null) cartItemMap = new HashMap<Integer, CartItem>();
        List<OrderItem> oiList = new ArrayList<OrderItem>();
        for(CartItem ci:cartItemMap.values()){
            OrderItem oi = new OrderItem();
            oi.setProduct(ci.getProduct());
            oiList.add(oi);
        }
        return oiList;
    }
}

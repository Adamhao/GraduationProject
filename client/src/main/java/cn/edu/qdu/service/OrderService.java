/**
 *
 */
package cn.edu.qdu.service;

import cn.edu.qdu.common.Constants;
import cn.edu.qdu.common.Page;
import cn.edu.qdu.model.Order;
import cn.edu.qdu.model.OrderItem;
import cn.edu.qdu.model.Product;
import cn.edu.qdu.model.User;
import cn.edu.qdu.repository.OrderItemRepository;
import cn.edu.qdu.repository.OrderRepository;
import cn.edu.qdu.repository.ProductRepository;
import cn.edu.qdu.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class OrderService {

    @Autowired
    OrderRepository orderDao;
    @Autowired
    OrderItemRepository orderItemDao;
    @Autowired
    ProductRepository productDao;
    @Autowired
    UserService userService;

    /**
     * 新建订单
     *
     * @param order
     * @param orderItemList
     */
    public Order addOrder(Order order, List<OrderItem> orderItemList) {
        Order save = save(order);
        for (OrderItem orderItem : orderItemList) {
            orderItem.setStatus(1);
            orderItemDao.save(orderItem);
        }
        return save;
    }

    public Order save(Order order) {
        return orderDao.save(order);
    }

    public Order findById(Integer id) {
        return orderDao.findOne(id);
    }

    public List<Order> findAll() {
        return orderDao.findAll();
    }

    public List<Order> findOrders(Page<Order> page) {
        page.setResult(orderDao.findAll(page.getPageable()).getContent());
        page.setTotalCount(orderDao.count());
        return page.getResult();
    }

    public List<Order> findOrders(Page<Order> page,Integer userId){
        page.setResult(orderDao.findByUserId(userId,page.getPageable()).getContent());
        page.setTotalCount(orderDao.countByUserId(userId));
        return null;
    }

    /**
     * 删除订单以及订单相关信息
     *
     * @param id 订单ID
     */
    public void deleteOrder(Integer id) {
        orderItemDao.deleteByOrderId(id);
        orderDao.delete(id);
    }

    /**
     * 修改订单状态
     *
     * @param orderID
     * @param status
     */
    public void updateOrderStatus(Integer orderID, Integer status) {
        Order order = orderDao.findOne(orderID);
        order.setStatus(status);
        //状态修改时修改相应时间数据
        if (status == Constants.OrderStatus.PAYED) {
            order.setPayTime(new Date());
        }  else if (status == Constants.OrderStatus.ENDED) {
            order.setConfirmTime(new Date());
        }
        orderDao.save(order);
    }

    /**
     * 验证订单归属人
     *
     * @param orderId
     * @param userId
     * @return
     */
    public boolean checkOwned(Integer orderId, Integer userId) {
        return orderDao.findOne(orderId).getUser().getId().equals(userId);
    }

    public boolean pay(Integer orderId, User user, HttpSession session) {
        Long point = user.getPoint();
        Order order = orderDao.findOne(orderId);
        if(point>=order.getFinalPrice()){
            user.setPoint((long) (user.getPoint()-order.getFinalPrice()));
            userService.save(user);
            UserUtil.saveUserToSession(session,user);
            order.setStatus(Constants.OrderStatus.ENDED);
            order.setPayTime(new Date());
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem oi : orderItems) {
                Product p = oi.getProduct();
                User inputUser = p.getInputUser();
                inputUser.setPoint(inputUser.getPoint()+p.getPoint());
                userService.save(user);
                p.setStock(p.getStock()+5);
                productDao.save(p);
            }
            orderDao.save(order);
            return true;
        }else {
            return false;
        }

    }

    public List<OrderItem> getProductByOderByUserId(Page<OrderItem> page, Integer userId){
        org.springframework.data.domain.Page<OrderItem> list = orderItemDao.findByUserId(userId, page.getPageable());
        page.setResult(list.getContent());
        page.setTotalCount(list.getTotalElements());
        return page.getResult();
    }

}

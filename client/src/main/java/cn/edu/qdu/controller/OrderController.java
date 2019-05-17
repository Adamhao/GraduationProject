package cn.edu.qdu.controller;

import cn.edu.qdu.common.Constants;
import cn.edu.qdu.common.Page;
import cn.edu.qdu.common.web.JsonResult;
import cn.edu.qdu.model.Order;
import cn.edu.qdu.model.OrderItem;
import cn.edu.qdu.model.Product;
import cn.edu.qdu.model.User;
import cn.edu.qdu.repository.ProductRepository;
import cn.edu.qdu.service.OrderService;
import cn.edu.qdu.service.UserService;
import cn.edu.qdu.util.CartUtil;
import cn.edu.qdu.util.UserUtil;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    ProductRepository productDao;

    /**
     * 订单列表
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model, HttpSession session, HttpServletRequest request) {
        User user = UserUtil.getUserFromSession(session);
        Page<Order> page = new Page<Order>(request);
        orderService.findOrders(page, user.getId());
        model.addAttribute("page", page);
        return "order/orderList";
    }

    /**
     * 订单确认
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/purchase", method = RequestMethod.GET)
    public String purchase(Model model, HttpSession session) {
        User user = userService.findOne(UserUtil.getUserFromSession(session).getId());
        return "order/orderPurchase";
    }

    /**
     * 下单
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/ordering", method = RequestMethod.POST)
    public String ordering( HttpSession session,Model model) {
        User user = UserUtil.getUserFromSession(session);

        Order order = new Order();
        order.setCreateTime(new Date());
        order.setOrderNumber(new DateTime().toString("yyyyMMddHHmmSS"));
        order.setStatus(Constants.OrderStatus.WAIT_PAY);
        List<OrderItem> oiList = CartUtil.getOrderItemFromCart(session);
        BigDecimal totalSum = new BigDecimal("0");
        for (OrderItem oi : oiList) {
            Product p = oi.getProduct();
            p.setStock(p.getStock()+5);
            productDao.save(p);
            totalSum = totalSum.add(new BigDecimal(oi.getProduct().getPoint() * oi.getQuantity()));
            oi.setOrder(order);
        }
        order.setTotalPrice(totalSum.doubleValue());
        order.setFinalPrice(totalSum.doubleValue());
        order.setOrderItems(oiList);
        order.setUser(UserUtil.getUserFromSession(session));
        //地址保存
        Order saveOrder = orderService.addOrder(order, oiList);
        CartUtil.cleanCart(session);
        model.addAttribute("orderId",saveOrder.getId());
        return "order/orderingSuccess";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String viewOrder(@PathVariable Integer id, Model model) {
        model.addAttribute("order", orderService.findById(id));
        return "order/orderDetail";
    }

    @RequestMapping(value = "/pay/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult pay(@PathVariable(value = "id") Integer orderId, HttpSession session) {
        User user = UserUtil.getUserFromSession(session);
        JsonResult result = new JsonResult();
        //验证订单是否归当前人员所有
        if (orderService.checkOwned(orderId, UserUtil.getUserFromSession(session).getId())) {
            boolean pay = orderService.pay(orderId, user,session);
            if(pay){
                result.setToSuccess();
            }else {
                result.setToFail();
                result.setMessage("您的余额不足请前往个人中心充值");
            }
        }else{
            result.setToFail();
        }
        return result;
    }

    /**
     * 取消订单
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/cancel/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult cancel(@PathVariable(value = "id") Integer orderId) {
        //TODO 验证是否拥有此订单
        orderService.updateOrderStatus(orderId, Constants.OrderStatus.DELETED);

        JsonResult result = new JsonResult();
        result.setToSuccess();
        return result;
    }




    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String product(HttpSession session,HttpServletRequest request,Model model) {
        User user = UserUtil.getUserFromSession(session);
        if(user==null){
            return "redirect:/user/login";
        }
        Page<Product> page = new Page<>(request);
        orderService.getProductByOderByUserId(page, user.getId());
        model.addAttribute("page", page);
        return "order/orderProduct";
    }
}

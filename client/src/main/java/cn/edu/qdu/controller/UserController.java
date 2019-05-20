package cn.edu.qdu.controller;

import cn.edu.qdu.common.AppConfig;
import cn.edu.qdu.common.Page;
import cn.edu.qdu.model.*;
import cn.edu.qdu.model.vo.ProductShow;
import cn.edu.qdu.repository.ProductTypeRepository;
import cn.edu.qdu.service.*;
import cn.edu.qdu.util.CookieUtil;
import cn.edu.qdu.util.Msg;
import cn.edu.qdu.util.UserUtil;
import cn.edu.qdu.util.ZipUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipOutputStream;


@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    AppConfig appConfig;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    RememberService rememberService;

    @Autowired
    ProductService productService;

    @Autowired
    PictureService pictureService;

    @Autowired
    ProductTypeRepository productTypeDao;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "user/index";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String reg() {
        return "user/userReg";
    }

    /*@RequestMapping("/updatePassword")
    public String goUpdatePassword(){
        return "redirect:/user/userUpdatePassword";
    }*/

    @RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
    public String updatePwd() {
        return "user/userUpdatePassword";
    }

    @RequestMapping(value = "/updatePwdByTel", method = RequestMethod.GET)
    public String updatePwdByTel() {
        return "user/userUpdatePasswordByTel";
    }

    @RequestMapping(value = "/doUpdatePwdByTel", method = RequestMethod.POST)
    public String doUpdatePwdByTel(User user) {
        try {
            userService.updatePwdByTel(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/login";
    }

    @RequestMapping(value = "/updatePwdByEmail", method = RequestMethod.GET)
    public String updatePwdByEmail() {
        return "user/userUpdatePasswordByEmail";
    }

    @RequestMapping(value = "/doUpdatePwdByEmail", method = RequestMethod.POST)
    public String doUpdatePwdByEmail(User user) {
        try {
            userService.updatePwdByEmail(user);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "redirect:/user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(HttpServletRequest request, HttpSession session) {
        String uuid;
        if (StringUtils.isNotBlank(uuid = CookieUtil.getCookieValue(request, appConfig.USER_COOKIE_NAME))) {
            Remember remember = rememberService.findById(uuid);
            if (remember != null && remember.getUser() != null) {
                if (userService.checkLogin(remember.getUser())) {
                    UserUtil.saveUserToSession(session, remember.getUser());
                    logger.info("用户[{}]使用cookie登录成功.", remember.getUser().getUsername());
                    return "redirect:/";
                }
            }
        }
        return "user/userLogin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(User user, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        if (userService.checkLogin(user)) {
            user = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
            Integer state = user.getState();
            if (state==0) {
                return "redirect:/user/login?state=0";
            }
            UserUtil.saveUserToSession(session, user);
            logger.info("是否记住登录用户：{}",request.getParameter("remember"));

            if ("on".equals(request.getParameter("remember"))) {
                String uuid = UUID.randomUUID().toString();
                Remember remember = new Remember();
                remember.setId(uuid);
                remember.setUser(user);
                remember.setAddTime(new Date());
                rememberService.add(remember);
                CookieUtil.addCookie(response, appConfig.USER_COOKIE_NAME, uuid, appConfig.USER_COOKIE_AGE);
            } else {
                CookieUtil.removeCookie(response, appConfig.USER_COOKIE_NAME);
            }
            logger.info("用户[{}]登陆成功",user.getUsername());
            return "redirect:/";
        }
        return "redirect:/user/login?state=1";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session,HttpServletResponse response) {
        UserUtil.deleteUserFromSession(session);
        CookieUtil.removeCookie(response, appConfig.USER_COOKIE_NAME);
        return "redirect:/";
    }

    @RequestMapping(value = "/profile")
    public String profile(HttpSession session, Model model) {
        User user = UserUtil.getUserFromSession(session);
        if (user == null) {
            return "redirect:/user/login?timeout=true";
        }
        model.addAttribute("user", user);
        return "user/userProfile";
    }

    /**
     * 订单列表
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String orderList(Model model, HttpSession session, HttpServletRequest request) {
        User user = UserUtil.getUserFromSession(session);
        org.springframework.util.Assert.notNull(user,"未登录用户，非法操作");
        Page<Order> page = new Page<>(request);
        orderService.findOrders(page, user.getId());
        model.addAttribute("page", page);
        return "order/orderList";
    }

    /**
     * 订单查看
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public String orderView(@PathVariable Integer id, Model model, HttpSession session, HttpServletRequest request) {
        User user = UserUtil.getUserFromSession(session);
        org.springframework.util.Assert.notNull(user,"未登录用户，非法操作");
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        return "order/orderView";
    }


    @RequestMapping(value = "/userProfile/update", method = RequestMethod.POST)
    public String doUpdateUserProfile(User user,HttpSession session ,Model model,long time) {
        user.setCreateTime(new Date(time));
        userService.save(user);
        UserUtil.saveUserToSession(session,user);
        model.addAttribute("user", user);
        return "user/userProfile";
    }

    @RequestMapping(value = "/recharge")
    public String goRecharge(HttpSession session, Model model) {
        User user = UserUtil.getUserFromSession(session);
        if (user == null) {
            return "redirect:/user/login?timeout=true";
        }
        model.addAttribute("user", user);
        return "user/userRecharge";
    }

    @RequestMapping(value = "/recharge/{id}/{num}",method = RequestMethod.POST)
    @ResponseBody
    public Msg doCharge(@PathVariable("id") Integer id, @PathVariable("num") Integer num, HttpSession session, Model model) {
        User user = userService.findOne(id);
        user.setPoint(user.getPoint()+num);
        userService.save(user);
        UserUtil.saveUserToSession(session,user);
        model.addAttribute("user", user);
        return Msg.success();
    }

    @RequestMapping(value = "/storage", method = RequestMethod.GET)
    public ModelAndView goStorage(HttpSession session, ModelAndView modelAndView, HttpServletRequest request,
                                  String titile, Integer model) {
        Page<Product> page = new Page<>(request);
        User user = UserUtil.getUserFromSession(session);
        if(user==null){
            modelAndView.setViewName("redirect:/user/login");
            return modelAndView;
        }
        productService.findProductsByInputUser(user.getId(),page,titile,model);
        modelAndView.addObject("page", page);
        modelAndView.addObject("types", productTypeDao.findAll());
        modelAndView.setViewName("user/product/productUser");
        return modelAndView;
    }

    @RequestMapping(value = "/product/new", method = RequestMethod.GET)
    public String newForm(HttpSession session,Model model) {
        if (UserUtil.getUserFromSession(session) == null) {
            return "redirect:/user/login";
        }
        List<ProductType> types = productTypeDao.findAll();
        model.addAttribute("types",types);
        return "user/product/productNew";
    }

    @RequestMapping(value = "/product/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(ModelAndView model, @PathVariable Integer id) {
        Product product = productService.findById(id);
        model.addObject("product", product);
        model.addObject("types",productTypeDao.findAll());
        model.setViewName("user/product/productEdit");
        return model;
    }

    @RequestMapping(value = "/product/new", method = RequestMethod.POST)
    public String doNew(Product product, HttpSession session, @RequestParam("imgFile") MultipartFile imgFile,@RequestParam("file") MultipartFile file) {
        if (imgFile!=null&&!imgFile.isEmpty()) {
            userService.uploadImage(product, session, imgFile);
        }
        product.setInputUser(UserUtil.getUserFromSession(session));
        product.setCreateTime(new Date());
        if(file!=null&&!file.isEmpty()){
            userService.uploadFile(product,session,file);
        }
        productService.save(product);
        return "redirect:/user/storage";
    }

    @RequestMapping(value = "/product/edit", method = RequestMethod.POST)
    public ModelAndView doEdit(ModelAndView model, Product product, HttpSession session, @RequestParam(name = "imgFile",required = false) MultipartFile imgFile,@RequestParam(name = "file",required = false) MultipartFile file,long time) {
        product.setCreateTime(new Date(time));
        product.setModel(productTypeDao.findOne(product.getModel().getId()));
        if (imgFile!=null&&!imgFile.isEmpty()) {
            userService.uploadImage(product, session, imgFile);
        }
        product.setInputUser(UserUtil.getUserFromSession(session));
        if(file!=null&&!file.isEmpty()){
            userService.uploadFile(product,session,file);
        }
        productService.save(product);
        model.setViewName("redirect:/user/storage");
        return model;
    }

    @RequestMapping(value = "/product/download", method = RequestMethod.GET)
    public void download(String url,HttpServletRequest request,HttpServletResponse response)  {
        String realPath = request.getSession().getServletContext().getRealPath("/upload/"+url);
        String postfix = userService.getpostfix(url);
        response.setHeader("Content-Disposition","attachment;filename="+new Date().getTime()+postfix);
        String mimeType = request.getSession().getServletContext().getMimeType(url);
        response.setContentType(mimeType);
        ServletOutputStream out = null;
        FileInputStream in = null;
        try {
            out = response.getOutputStream();
            in = new FileInputStream(realPath);
            byte[] data = new byte[1024];
            int len  = 0;
            while((len=in.read(data))!=-1){
                out.write(data,0,len);
            }
        }catch (IOException e){

        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 打包压缩下载文件
     */
    @RequestMapping(value = "/order/downLoadZipFile/{id}",method = RequestMethod.GET)
    public void downLoadZipFile(HttpServletRequest request,HttpServletResponse response,@PathVariable Integer id) throws IOException{
        Order order = orderService.findById(id);
        List<OrderItem> orderItems = order.getOrderItems();
        String zipName = new Date().getTime()+".zip";
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition","attachment; filename="+zipName);
        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
        try {
            for (OrderItem orderItem : orderItems) {
                String url = orderItem.getProduct().getUrl();
                String realPath = request.getSession().getServletContext().getRealPath("/upload/"+url);
                ZipUtils.doCompress(realPath,out);
                response.flushBuffer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            out.close();
        }
    }

    @RequestMapping(value = "/product/show/{id}", method = RequestMethod.GET)
    public ModelAndView productShow(HttpServletRequest request,ModelAndView model, @PathVariable("id") Integer id) {
        Page<ProductShow> page = new Page<>(request);
        page.setPageSize(5);
        productService.findProductShowByProductId(id,page);
        model.addObject("page", page);
        model.setViewName("/user/product/productShow");
        return model;
    }

    @PostMapping("/addUser")
    public String addUser(User user){
        try {
            user.setState(0);
            userService.insert(user);
        }  catch (MessagingException e) {
            e.printStackTrace();
        }
        return "redirect:/user/login";
    }

    //检查用户名是否重复
    @GetMapping("/checkName")
    @ResponseBody
    public String checkName(String username){
        User user = userService.queryByUsername(username);
        if(user == null){
            return "true";
        }
        return "false";
    }

    //检查用户名是否存在
    @GetMapping("/checkNameIsTrue")
    @ResponseBody
    public String checkNameIsTrue(String username){//验证用户是否存在
        User user = userService.queryByUsername(username);
        if(user == null){
            return "false";
        }
        return "true";
    }

    //检查手机号是否匹配
    @GetMapping("/checkPhoneIsTrue")
    @ResponseBody
    public String checkPhoneIsTrue(String username,String phone){
        User user = userService.queryByUsername(username);
        if(phone.equals(user.getPhone())){
            return "true";
        }
        return "false";
    }

    //检查邮箱是否匹配
    @GetMapping("/checkEmail")
    @ResponseBody
    public String checkEmail(String username,String email){
        User user = userService.queryByUsername(username);
        if(email.equals(user.getEmail())){
            return "true";
        }
        return "false";
    }

    @PostMapping("/checkPhone")
    @ResponseBody
    public JSONObject checkPhone(String phone, HttpSession session){
        String checkCode = vcode();
        System.out.println(checkCode);
//        JSONObject result = JuHe.mobileQuery(phone,checkCode);
        JSONObject result = new JSONObject();
//        if (result != null && result.getInteger("error_code")==0){
            session.setAttribute("checkCode",checkCode);
//        }
        return result;
    }

    @GetMapping("/checkCode")
    @ResponseBody
    public String checkCode(String proveCode,HttpSession session){
        String code = (String) session.getAttribute("checkCode");
        if(proveCode.equals(code)){
            return "true";
        }
        return "false";
    }

    @GetMapping("/activation")
    public String activation (String username,String activationCode,Model model){
        User user = userService.queryByUsername(username);
        if (user.getActivation_code().equals(activationCode)){
            userService.updateStatus(user.getId());
            model.addAttribute("status","激活成功，请登录");
        }else {
            model.addAttribute("status","激活失败，请到邮箱内点击链接进行账号激活");
        }
        return "redirect:/user/login";
    }

    public String vcode(){
        StringBuffer vcode = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            vcode.append((int)(Math.random() * 9));
        }
        return vcode.toString();
    }

}

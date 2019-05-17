package cn.edu.qdu.controller;

import cn.edu.qdu.common.Page;
import cn.edu.qdu.model.Product;
import cn.edu.qdu.repository.ProductTypeRepository;
import cn.edu.qdu.service.ProductService;
import cn.edu.qdu.util.ConvertToPDF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Socket;


@Controller
@RequestMapping(value = "/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @Autowired
    ProductTypeRepository productTypeDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView listProduct(ModelAndView modelAndView, HttpServletRequest request,String title,Integer model) {
        Page<Product> page = new Page<Product>(request);
        page.setPageSize(8);
        productService.findProducts(page,title,model);
        modelAndView.addObject("types",productTypeDao.findAll());
        modelAndView.addObject("page", page);
        modelAndView.setViewName("product/productList");
        return modelAndView;
    }

    @RequestMapping(value = "/{id}")
    public String showInfo(@PathVariable Integer id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product/productView";
    }

    @ResponseBody
    @RequestMapping(value = "/convertpdf",method = RequestMethod.GET)//文档转化接口
    public void converttopdf(HttpServletRequest request, HttpServletResponse response,String url) {
        System.out.println("converttopdf start .... ");

        //多线程调用
        ConvertToPDF convert1 = new ConvertToPDF(url,8100);
        Thread thread1 = new Thread( convert1 );
        thread1.start();


        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Sleep : " + e);
        }
        int port = 8100;
        //端口是否占用的监听
        for( int i=0 ;i < 8;i++) {
            Boolean nflag = true;
            try {
                long timestart = System.currentTimeMillis();
                Socket socket = new Socket("127.0.0.1", port);
                long timeend = System.currentTimeMillis();
                System.out.println("port ：" + port +" is Occupying " + " usedTime = " + (timeend-timestart)/1000);
            } catch (Exception e) {
                nflag = false;
                System.out.println("port ：" + port +" is Free " );
            }
            port++;
        }

//        try {
//            Thread.sleep(20000);
//        } catch (Exception e) {
//            System.out.println("Sleep : " + e);
//        }
        System.out.println("converttopdf end ....");
        System.out.println("关闭office转换服务....");
    }

    @RequestMapping(value = "/devDoc",method = RequestMethod.GET)
    public void devDoc(HttpServletRequest request,HttpServletResponse response,@RequestParam("url") String url) throws IOException, InterruptedException {
        String name = url.substring(0,url.indexOf("."));
        String realPath = request.getSession().getServletContext().getRealPath("/upload/"+name+".pdf");
        File file2 = new File(realPath);
        boolean exists = file2.exists();
        if(!exists){
            realPath = request.getSession().getServletContext().getRealPath("/upload/"+url);
            converttopdf(request,response,realPath);
            Thread.sleep(8000);
        }
        InputStream input=null;
        OutputStream out=null;
        File file = new File(realPath);
        boolean exists1 = file.exists();
        if(!exists1){
            PrintWriter out2 = response.getWriter();
            out2.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
            out2.println("<HTML>");
            out2.println("  <HEAD><TITLE>服务器波动</TITLE></HEAD>");
            out2.println("  <BODY>");
            out2.print("Server fluctuations, please try again<hr/>");
            out2.println("  </BODY>");
            out2.println("</HTML>");
            out2.flush();
            out2.close();
        }else{
            System.out.println("发现pdf。开始输出");
            input=new FileInputStream(file);
            response.setContentType("application/pdf");
            out = response.getOutputStream();
            byte[] b = new byte[512];
            if(out!=null) {
                if(input!=null) {
                    while ((input.read(b))!=-1) {
                        out.write(b);
                    }
                }else {
                    System.out.println("InputStream为空。。。");
                }
            }else {
                System.out.println("OutputStream为空。。。");
            }
            out.flush();
            input.close();
            out.close();
        }

    }

}

package cn.edu.qdu.controller;

import cn.edu.qdu.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	@Autowired
    ProductService productService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model = new ModelAndView("/index");
		return model;
	}

}

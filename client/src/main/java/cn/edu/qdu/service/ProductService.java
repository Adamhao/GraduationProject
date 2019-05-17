/**
 * 
 */
package cn.edu.qdu.service;

import cn.edu.qdu.common.Page;
import cn.edu.qdu.model.OrderItem;
import cn.edu.qdu.model.Product;
import cn.edu.qdu.model.ProductType;
import cn.edu.qdu.model.vo.ProductShow;
import cn.edu.qdu.repository.OrderItemRepository;
import cn.edu.qdu.repository.ProductRepository;
import cn.edu.qdu.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ProductService {

	@Autowired
    ProductTypeRepository productTypeDao;

	@Autowired
	ProductRepository productDao;

	@Autowired
	OrderItemRepository orderItemDao;

	public void saveType(ProductType type) {
		productTypeDao.save(type);
	}

	public List<ProductType> findType() {
		return productTypeDao.findAll();
	}

	public void save(Product product) {
		productDao.save(product);
	}

	public Product findById(Integer id) {
		return productDao.findOne(id);
	}

	public List<Product> findAll() {
		return productDao.findAll();
	}

	public List<Product> findNew() {
		return productDao.findByOrderByCreateTimeDesc();
	}
    public List<Product> findOld() {
        return productDao.findByOrderByCreateTimeAsc();
    }
	
	public List<Product> findPop(){
		return productDao.findPopProducts();
	}

    public List<Product> findProducts(Page<Product> page, String title, Integer model) {
		if(title==null&&model==null){
			page.setResult(productDao.findAllOrderByHot(page.getPageable()).getContent());
			page.setTotalCount(productDao.count());
			return page.getResult();
		}
		org.springframework.data.domain.Page<Product> byOption = productDao.findAllOrderByHotByOption(title,model,page.getPageable());
		page.setResult(byOption.getContent());
		page.setTotalCount(byOption.getTotalElements());
		return page.getResult();

    }

	public List<Product> findProductsByInputUser(Integer id, Page<Product> page,
												 String title, Integer model) {
		if(title==null&&model==null){
			org.springframework.data.domain.Page<Product> byInputUser = productDao.findByInputUser(id,page.getPageable());
			page.setResult(byInputUser.getContent());
			page.setTotalCount(byInputUser.getTotalElements());
			return page.getResult();
		}
		org.springframework.data.domain.Page<Product> byInputUser = productDao.findByInputUserByOption(id, title, model, page.getPageable());
		page.setResult(byInputUser.getContent());
		page.setTotalCount(byInputUser.getTotalElements());
		return page.getResult();
	}

	public List<ProductShow> findProductShowByProductId(Integer pid, Page<ProductShow> page) {
		List<OrderItem> lists = orderItemDao.findByProductId(pid);
		List<ProductShow> pss = new ArrayList<>();
		for (OrderItem orderItem : lists) {
			ProductShow ps = new ProductShow();
			ps.setTitle(orderItem.getProduct().getTitle());
			ps.setPoint(orderItem.getProduct().getPoint());
			ps.setModel(orderItem.getProduct().getModel());
			ps.setStock(orderItem.getProduct().getStock());
			ps.setOrderNumber(orderItem.getOrder().getOrderNumber());
			ps.setUser(orderItem.getOrder().getUser());
			ps.setCreateTime(orderItem.getOrder().getCreateTime());
			ps.setPayTime(orderItem.getOrder().getPayTime());
			pss.add(ps);
		}
		page.setResult(pss);
		page.setTotalCount(pss.size());
		return page.getResult();
	}

}

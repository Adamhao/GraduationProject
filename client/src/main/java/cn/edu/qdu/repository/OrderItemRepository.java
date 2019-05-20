package cn.edu.qdu.repository;

import cn.edu.qdu.model.OrderItem;
import cn.edu.qdu.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import java.util.List;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    void deleteByOrderId(Integer orderId);

    @Query(value = "From OrderItem o where o.product.id = :pid")
    List<OrderItem> findByProductId(@Param("pid")Integer pid);

    @Query(value = "select oi.status from OrderItem oi where oi.product = :product and oi.id = :id ")
    Integer findStatusByProduct(@Param("product") Product product, @Param("id") Integer userId);

    @Query(value = "From OrderItem o where o.order.user.id = :id group by o.product.id")
    Page<OrderItem> findByUserId(@Param("id") Integer id,Pageable pageable);
}

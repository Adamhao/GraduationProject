package cn.edu.qdu.repository;

import cn.edu.qdu.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    void deleteByOrderId(Integer orderId);

    @Query(value = "From OrderItem o where o.product.id = :pid")
    List<OrderItem> findByProductId(@Param("pid")Integer pid);
}
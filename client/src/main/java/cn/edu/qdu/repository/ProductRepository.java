package cn.edu.qdu.repository;

import cn.edu.qdu.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByOrderByCreateTimeDesc();

    List<Product> findByOrderByCreateTimeAsc();


    @Query("FROM Product p")
    List<Product> findPopProducts();

    @Query("from Product p where p.inputUser.id = ?1")
    Page<Product> findByInputUser(@Param("id") Integer id , Pageable pageable);

    @Query(value = "select distinct o.product from OrderItem o where o.order.user.id = :id")
    Page<Product> findByOrderByUserId(@Param("id")Integer id,Pageable pageable);

    @Query("from Product p where p.inputUser.id = :id and (:title is  null or p.title like CONCAT('%',:title,'%') ) and (:model is  null or  p.model.id like CONCAT('%',:model,'%')) ")
    Page<Product> findByInputUserByOption(@Param("id") Integer id, @Param("title") String title,@Param("model") Integer model, Pageable pageable);

    @Query("from Product p order by p.stock desc")
    Page<Product> findAllOrderByHot(Pageable pageable);

    @Query("from Product p  where (:title is  null or p.title like CONCAT('%',:title,'%') ) and (:model is  null or  p.model.id like CONCAT('%',:model,'%')) order by p.stock desc ")
    Page<Product> findAllOrderByHotByOption(@Param("title") String title,@Param("model") Integer model, Pageable pageable);
}

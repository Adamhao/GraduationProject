package cn.edu.qdu.repository;

import cn.edu.qdu.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
}

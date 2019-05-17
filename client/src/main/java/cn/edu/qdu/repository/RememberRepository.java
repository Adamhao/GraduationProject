package cn.edu.qdu.repository;

import cn.edu.qdu.model.Remember;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RememberRepository extends JpaRepository<Remember,String> {
}

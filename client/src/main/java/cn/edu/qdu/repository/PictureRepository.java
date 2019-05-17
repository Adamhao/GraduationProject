package cn.edu.qdu.repository;

import cn.edu.qdu.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {
}

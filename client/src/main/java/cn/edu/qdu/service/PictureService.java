package cn.edu.qdu.service;

import cn.edu.qdu.model.Picture;
import cn.edu.qdu.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PictureService {

    @Autowired
    PictureRepository pictureDao;

    public void save(Picture picture) {
        pictureDao.save(picture);
    }

    public List<Picture> findAll() {
        return pictureDao.findAll();
    }

}

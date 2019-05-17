package cn.edu.qdu.service;

import cn.edu.qdu.model.Remember;
import cn.edu.qdu.repository.RememberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RememberService {

    @Autowired
    RememberRepository rememberRepository;

    public void add(Remember remember) {
        rememberRepository.save(remember);
    }

    public void delete(String uuid){
        rememberRepository.delete(uuid);
    }

    public Remember findById(String uuid){
        return rememberRepository.findOne(uuid);
    }
}

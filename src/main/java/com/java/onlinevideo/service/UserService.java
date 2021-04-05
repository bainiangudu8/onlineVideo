package com.java.onlinevideo.service;

import com.java.onlinevideo.model.domain.User;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author changchen
 * @site
 * @company
 * @create 2021-03-14 21:39
 */
@Service
public interface UserService {
    int save(Map<String,String> userInfo);

    String findByPhoneAndPwd(String phone, String pwd);

    User findUserInfoById(Integer userId);
}

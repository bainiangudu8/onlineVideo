package com.java.onlinevideo.service.impl;

import com.java.onlinevideo.model.domain.User;
import com.java.onlinevideo.mapper.UserMapper;
import com.java.onlinevideo.service.UserService;
import com.java.onlinevideo.utils.CommonUtils;
import com.java.onlinevideo.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * @author changchen
 * @site
 * @company
 * @create 2021-03-14 21:49
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(Map<String,String> userInfo) {
        User user = parseToUser(userInfo);
        if(user!=null){
            return userMapper.save(user);
        }else{
            return -1;
        }
    }

    @Override
    public String findByPhoneAndPwd(String phone, String pwd) {
        User user = userMapper.findByPhoneAndPwd(phone,CommonUtils.MD5(pwd));
        if(user==null){
            return null;
        }else{
            String token = JWTUtils.geneJsonWebToken(user);
            return token;
        }
    }

    @Override
    public User findUserInfoById(Integer userId) {
        User user = userMapper.findUserInfoById(userId);
        return user;
    }


    public User parseToUser(Map<String,String> userInfo){
        if(userInfo.containsKey("name") && userInfo.containsKey("pwd") && userInfo.containsKey("phone")){
            User user = new User();
            user.setName(userInfo.get("name"));
            user.setPhone(userInfo.get("phone"));
            user.setHeadImg(getRandomImg());
            user.setCreateTime(new Date());
            String pwd = userInfo.get("pwd");
            //MD5加密
            user.setPwd(CommonUtils.MD5(pwd));

            return user;
        }else{
            return null;
        }
    }

    private static final String [] headImg = {
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/12.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/11.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/13.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/14.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/15.jpeg"
    };

    private String getRandomImg(){
        int size = headImg.length;
        Random random = new Random();
        int index = random.nextInt(size);
        return headImg[index];
    }
}

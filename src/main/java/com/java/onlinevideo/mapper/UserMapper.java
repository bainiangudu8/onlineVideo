package com.java.onlinevideo.mapper;

import com.java.onlinevideo.model.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author changchen
 * @site
 * @company
 * @create 2021-03-14 21:48
 */
public interface UserMapper {

    int save(User user);

    User findByPhone(@Param("phone") String phone);

    User findByPhoneAndPwd(@Param("phone") String phone, @Param("pwd") String pwd);

    User findUserInfoById(@Param("userId") Integer userId);
}

package com.java.onlinevideo.controller;

import com.java.onlinevideo.model.domain.User;
import com.java.onlinevideo.model.request.LoginRequest;
import com.java.onlinevideo.service.UserService;
import com.java.onlinevideo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author changchen
 * @site
 * @company
 * @create 2021-03-14 20:53
 */
@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    private JsonData register(@RequestBody Map<String,String> userInfo){

        int rows = userService.save(userInfo);
        return rows == 1? JsonData.buildSuccess():JsonData.buildError("注册失败，请重试");

    }

    @PostMapping("login")
    private JsonData login(@RequestBody LoginRequest loginRequest){

        String token = userService.findByPhoneAndPwd(loginRequest.getPhone(),loginRequest.getPwd());
        return token == null?JsonData.buildError("登陆失败,请检查账号密码"):JsonData.buildSuccess(token);

    }

    @GetMapping("find_user_token")
    private JsonData findUserInfoByToken(HttpServletRequest request){

        Integer userId = (Integer) request.getAttribute("id");

        if(userId == null){
            return JsonData.buildError("查询失败");
        }

        User user = userService.findUserInfoById(userId);
        return JsonData.buildSuccess(user);

    }
}

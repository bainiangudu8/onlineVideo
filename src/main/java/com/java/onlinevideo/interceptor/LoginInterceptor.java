package com.java.onlinevideo.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.onlinevideo.utils.JWTUtils;
import com.java.onlinevideo.utils.JsonData;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author changchen
 * @site
 * @company
 * @create 2021-03-16 3:49
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 进入controller之前的方法
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {
            String accesToken = request.getHeader("token");
            if(accesToken == null){
                accesToken = request.getParameter("token");
            }

            if(StringUtils.isNotBlank(accesToken)){
                Claims claims = JWTUtils.checkJWT(accesToken);
                if(claims == null){
                    //判断claims是否为空，为空响应json数据给前端，登录过期，重新登陆
                    sendJsonMessage(response, JsonData.buildError("登录过期，重新登陆"));
                    return false;
                }

                Integer id = (Integer) claims.get("id");
                String name = (String) claims.get("name");

                request.setAttribute("user_id",id);
                request.setAttribute("user_name",name);

                return true;
            }
        }catch (Exception e){
        }
        sendJsonMessage(response,JsonData.buildError("登录过期，重新登陆"));
        return false;
    }

    /**
     * 响应json数据给前端
     * @param response
     * @param object
     */
    public static void sendJsonMessage(HttpServletResponse response, Object object){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(object));
            writer.close();
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.java.onlinevideo.controller;

import com.java.onlinevideo.model.domain.VideoOrder;
import com.java.onlinevideo.model.request.VideoOrderRequest;
import com.java.onlinevideo.service.VideoOrderService;
import com.java.onlinevideo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author changchen
 * @site
 * @company
 * @create 2021-03-17 9:24
 */
@RestController
@RequestMapping("api/v1/pri/order")
public class VideoOrderController {

    @Autowired
    private VideoOrderService videoOrderService;

    /**
     * 下单接口
     * @return
     */
    @RequestMapping("save")
    private JsonData saveOrder(@RequestBody VideoOrderRequest videoOrderRequest, HttpServletRequest request){

        Integer userId = (Integer) request.getAttribute("user_id");

        int rows = videoOrderService.save(userId,videoOrderRequest.getVideoId());
        return rows == 0?JsonData.buildError("下单失败"):JsonData.buildSuccess("下单成功");
    }

    @RequestMapping("list")
    public JsonData list(HttpServletRequest request){

        Integer userId = (Integer) request.getAttribute("user_id");
        List<VideoOrder> videoOrderList = videoOrderService.listVideoOrder(userId);
        return JsonData.buildSuccess(videoOrderList);
    }
}

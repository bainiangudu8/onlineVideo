package com.java.onlinevideo.service;

import com.java.onlinevideo.model.domain.VideoOrder;

import java.util.List;

/**
 * @author changchen
 * @site
 * @company
 * @create 2021-03-17 9:29
 */
public interface VideoOrderService {

    int save(int userId, int videoId);

    List<VideoOrder> listVideoOrder(Integer userId);
}

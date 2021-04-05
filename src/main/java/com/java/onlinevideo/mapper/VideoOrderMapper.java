package com.java.onlinevideo.mapper;

import com.java.onlinevideo.model.domain.VideoOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author changchen
 * @site
 * @company
 * @create 2021-03-17 9:31
 */
public interface VideoOrderMapper {

    /**
     * 查询用户是否购买过此商品
     * @param userId
     * @param videoId
     * @param state
     * @return
     */
    VideoOrder findByUserIdAndVideoIdAndState(@Param("user_id") int userId, @Param("video_id") int videoId, @Param("state") int state);

    /**
     * 下单
     * @param videoOrder
     * @return
     */
    int saveOrder(VideoOrder videoOrder);

    List<VideoOrder> listVideoOrder(@Param("user_id") Integer userId);
}

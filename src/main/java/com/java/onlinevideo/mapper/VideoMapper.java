package com.java.onlinevideo.mapper;

import com.java.onlinevideo.model.domain.Video;
import com.java.onlinevideo.model.domain.VideoBanner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author changchen
 * @site
 * @company
 * @create 2021-03-13 17:32
 */
public interface VideoMapper {

    /**
     * 查询视频列表
     * @return
     */
    List<Video> listVideo();

    /**
     * 查询轮播列表
     * @return
     */
    List<VideoBanner> listBanner();

    /**
     * 根据视频id查询视频信息包括章，节
     * @param videoId
     * @return
     */
    Video findDetailById(@Param("video_id") int videoId);

    /**
     * 查询视频简单信息
     * @param videoId
     * @return
     */
    Video findById(@Param("video_id") int videoId);
}

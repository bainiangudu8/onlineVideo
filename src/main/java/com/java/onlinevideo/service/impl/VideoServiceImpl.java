package com.java.onlinevideo.service.impl;

import com.java.onlinevideo.config.CacheKeyManager;
import com.java.onlinevideo.model.domain.Video;
import com.java.onlinevideo.model.domain.VideoBanner;
import com.java.onlinevideo.mapper.VideoMapper;
import com.java.onlinevideo.model.domain.VideoOrder;
import com.java.onlinevideo.service.VideoService;
import com.java.onlinevideo.utils.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author changchen
 * @site
 * @company
 * @create 2021-03-13 18:08
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private BaseCache baseCache;

    @Override
    public List<Video> listVideo() {
        try {
            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_VIDEL_LIST,()->{
                List<Video> videoList = videoMapper.listVideo();
                System.out.println("从数据库里面找视频列表");
                return videoList;
            });
            if(cacheObj instanceof List){
                List<Video> videosList = (List<Video>)cacheObj;
                System.out.println("查询完成");
                return videosList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //可以返回兜底数据，业务系统降级-》SpringCloud专题课程
        return null;
    }

    @Override
    public List<VideoBanner> listBanner() {

        try {
            //单独构建一个缓存key，每个视频的key是不一样的
            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER_KEY,()->{
                List<VideoBanner> bannerList = videoMapper.listBanner();
                System.out.println("从数据库里面找轮播图列表");
                return bannerList;
            });
            if(cacheObj instanceof List){
                List<VideoBanner> bannerList = (List<VideoBanner>)cacheObj;
                return bannerList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Video findDetailById(int videoId) {

        String videoDetailKey = String.format(CacheKeyManager.VIDEO_DETAIL,videoId);

        try {
            Object cacheObj = baseCache.getTenMinuteCache().get(videoDetailKey,()->{
                // 需要使用mybaits关联复杂查询
                Video video = videoMapper.findDetailById(videoId);
                System.out.println("从数据库里面找视频详情");
                return video;
            });
            if(cacheObj instanceof Video){
                Video video = (Video)cacheObj;
                return video;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

package com.java.onlinevideo.controller;

import com.java.onlinevideo.model.domain.Video;
import com.java.onlinevideo.model.domain.VideoBanner;
import com.java.onlinevideo.service.VideoService;
import com.java.onlinevideo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author changchen
 * @site
 * @company
 * @create 2021-03-13 18:13
 */
@RestController
@RequestMapping("api/v1/pub/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @RequestMapping("list_banner")
    public JsonData indexBanner(){

        List<VideoBanner> bannerList = videoService.listBanner();
        return JsonData.buildSuccess(bannerList);

    }

    @RequestMapping("list")
    public JsonData listVideo(){

        List<Video> videoList = videoService.listVideo();
        return JsonData.buildSuccess(videoList);

    }

    /**
     * 查询视频详情，包括章，节信息
     * @param id
     * @return
     */
    @GetMapping("find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value = "video_id",required = true) int id){

        Video video = videoService.findDetailById(id);
        return JsonData.buildSuccess(video);

    }
}

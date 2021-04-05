package com.java.onlinevideo.service;

import com.java.onlinevideo.model.domain.Video;
import com.java.onlinevideo.model.domain.VideoBanner;

import java.util.List;

/**
 * @author changchen
 * @site
 * @company
 * @create 2021-03-13 18:07
 */
public interface VideoService {

    List<Video> listVideo();

    List<VideoBanner> listBanner();

    Video findDetailById(int videoId);
}

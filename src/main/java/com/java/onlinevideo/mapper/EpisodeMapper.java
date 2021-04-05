package com.java.onlinevideo.mapper;

import com.java.onlinevideo.model.domain.Episode;
import org.apache.ibatis.annotations.Param;

/**
 * @author changchen
 * @site
 * @company
 * @create 2021-03-17 14:08
 */
public interface EpisodeMapper {

    /**
     * 查询视频第一节
     * @param videoId
     * @return
     */
    Episode findFirstByVideoId(@Param("video_id") int videoId);

}

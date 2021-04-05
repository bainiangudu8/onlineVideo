package com.java.onlinevideo.service.impl;

import com.java.onlinevideo.exception.CHException;
import com.java.onlinevideo.mapper.EpisodeMapper;
import com.java.onlinevideo.mapper.PlayRecordMapper;
import com.java.onlinevideo.mapper.VideoMapper;
import com.java.onlinevideo.mapper.VideoOrderMapper;
import com.java.onlinevideo.model.domain.Episode;
import com.java.onlinevideo.model.domain.PlayRecord;
import com.java.onlinevideo.model.domain.Video;
import com.java.onlinevideo.model.domain.VideoOrder;
import com.java.onlinevideo.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author changchen
 * @site
 * @company
 * @create 2021-03-17 9:30
 */
@Service
public class VideoOrderServiceImpl implements VideoOrderService {

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private EpisodeMapper episodeMapper;

    @Autowired
    private PlayRecordMapper playRecordMapper;

    /**
     * 下单操作
     * @param userId
     * @param videoId
     * @return
     */
    @Override
    @Transactional
    public int save(int userId, int videoId) {

        //判断是否已经支付，视频只需购买一次
        VideoOrder videoOrder = videoOrderMapper.findByUserIdAndVideoIdAndState(userId,videoId,1);

        if(videoOrder!=null){ return 0;}

        Video video = videoMapper.findById(videoId);
        VideoOrder newVideoOrder = new VideoOrder();
        newVideoOrder.setOutTradeNo(UUID.randomUUID().toString());
        newVideoOrder.setState(1);
        newVideoOrder.setCreateTime(new Date());
        newVideoOrder.setTotalFee(video.getPrice());
        newVideoOrder.setUserId(userId);

        newVideoOrder.setVideoId(videoId);
        newVideoOrder.setVideoImg(video.getCoverImg());
        newVideoOrder.setVideoTitle(video.getTitle());

        int rows = videoOrderMapper.saveOrder(newVideoOrder);

        if(rows==1){
            PlayRecord playRecord = new PlayRecord();
            playRecord.setCreateTime(new Date());
            playRecord.setUserId(userId);
            playRecord.setVideoId(videoId);

            Episode firstEpisode = episodeMapper.findFirstByVideoId(videoId);
            if(firstEpisode==null){
                throw new CHException(-1,"该视频没有集，请运维人员查看");
            }
            playRecord.setEpisodeId(firstEpisode.getId());
            playRecord.setCurrentNum(firstEpisode.getNum());

            playRecordMapper.save(playRecord);
        }
        return rows;
    }

    /**
     * 查询订单列表
     * @return
     * @param userId
     */
    @Override
    public List<VideoOrder> listVideoOrder(Integer userId) {

        return videoOrderMapper.listVideoOrder(userId);

    }
}

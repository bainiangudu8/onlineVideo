package com.java.onlinevideo.mapper;

import com.java.onlinevideo.model.domain.PlayRecord;
import org.apache.ibatis.annotations.Param;

/**
 * @author changchen
 * @site
 * @company
 * @create 2021-03-17 14:14
 */
public interface PlayRecordMapper {

    /**
     * 添加播放记录
     * @param playRecord
     * @return
     */
    int save(PlayRecord playRecord);
}

package com.java.onlinevideo.config;

/**
 * @author changchen
 * @site
 * @company
 * @create 2021-03-18 22:22
 */
/**
 * 缓存key管理类
 */
public class CacheKeyManager {

    /**
     * 首页轮播图缓存key
     */
    public static final String INDEX_BANNER_KEY = "index:banner:list";

    /**
     * 视频列表缓存key
     */
    public static final String INDEX_VIDEL_LIST = "index:video:list";

    /**
     * 视频详情缓存key
     */
    public static final String VIDEO_DETAIL = "video:detail:%s";
}

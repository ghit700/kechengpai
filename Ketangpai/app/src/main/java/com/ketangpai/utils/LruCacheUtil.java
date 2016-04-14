package com.ketangpai.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

/**
 * Created by Administrator on 2016/3/30.
 *
 */
public class LruCacheUtil {

    /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
    private static LruCacheUtil instance = null;
    private LruCache<String, Bitmap> mMemoryCache;

    /* 私有构造方法，防止被实例化 */
    private LruCacheUtil() {

        // 获取到可用内存的最大值，使用内存超出这个值会引起OutOfMemory异常。
        // LruCache通过构造函数传入缓存值，以KB为单位。
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        // 使用最大可用内存值的1/8作为缓存的大小。
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // 重写此方法来衡量每张图片的大小，默认返回图片数量。
                return bitmap.getByteCount() / 1024;
            }
        };

    }

    /* 1:懒汉式，静态工程方法，创建实例 */
    public static LruCacheUtil getInstance() {
        if (instance == null) {
            instance = new LruCacheUtil();
        }
        return instance;
    }

    /**
     * 清除缓存
     */
    public void clearCache() {
        if (mMemoryCache != null) {
            if (mMemoryCache.size() > 0) {
                Log.d("CacheUtils", "mMemoryCache.size() " + mMemoryCache.size());
                mMemoryCache.evictAll();
                Log.d("CacheUtils", "mMemoryCache.size()" + mMemoryCache.size());
            }
            mMemoryCache = null;
        }
    }

    /**
     * 将图片加入缓存
     * @param key 图片在缓存中的key
     * @param bitmap 要添加的图片
     */
    public synchronized void addBitmapToMemoryCache(String key, Bitmap bitmap) {

        if (mMemoryCache.get(key) == null) {
            if ( bitmap != null){
                mMemoryCache.put(key, bitmap);
            }
        }
    }

    /**
     * 从缓存中获取图片
     * @param key 图片的key
     * @return mMemoryCache.get(key) bitmap对象
     */
    public synchronized Bitmap getBitmapFromMemCache(String key) {

        return mMemoryCache.get(key);
    }

    /**
     * 移除缓存
     *
     * @param key 图片在缓存中的key
     */
    public synchronized void removeImageCache(String key) {

        if (key != null) {
            if (mMemoryCache != null) {
                Bitmap bm = mMemoryCache.remove(key);
                if (bm != null) bm.recycle();
            }
        }
    }
}


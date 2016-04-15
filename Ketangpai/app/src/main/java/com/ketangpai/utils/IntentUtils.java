package com.ketangpai.utils;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;

import com.ketangpai.base.BaseFragment;
import com.ketangpai.fragment.AddNoticeFragment;
import com.ketangpai.fragment.CourseTabFragment;

/**
 * Created by Administrator on 2016/4/15.
 */
public class IntentUtils {

    /**
     * 读取设备文档和视频,返回文件的Uri
     *
     * @param fragment
     */
    public static void openDocument(BaseFragment fragment) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //仅返回可以打开流的文件
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/video/audio/*");
        fragment.startActivityForResult(intent, CourseTabFragment.OPEN_DOCUMENT_REQUEST);
    }
}

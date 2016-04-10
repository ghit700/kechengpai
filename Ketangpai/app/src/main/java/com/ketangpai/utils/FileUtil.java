package com.ketangpai.utils;

import android.net.Uri;
import android.util.Log;

/**
 * Created by nan on 2016/4/10.
 */
public class FileUtil {
    private final static String[] imageFormat = new String[]{"jpg", "png", ".bmp", "jpeg", "gif"};
    private final static String[] videoFormat = new String[]{"mp4", "avi", "3gp", "rmvb", "mov", "wmv", "fly"};
    private final static String[] documentFormat = new String[]{"txt", "pdf", "xlsx", "doc", "ppt", "pptx", "wps", "docx", "xls"};

    public final static int TYPE_FILE_IMAGE = 0;
    public final static int TYPE_FILE_VIDEO = 1;
    public final static int TYPE_FILE_DOCUMENT = 2;


    public static String getFileName(Uri uri) {
        String fileName = null;
        String[] path = uri.getPath().split("/");
        fileName = path[path.length - 1];
        return fileName;
    }

    public static int getFileType(String fileName) {
        String type = fileName.substring(fileName.lastIndexOf(".")+1);
        Log.i("wu",type);
        for (String format : documentFormat) {
            if (format.equals(type)) {
                return TYPE_FILE_DOCUMENT;
            }

        }

        for (String format : imageFormat) {
            if (format.equals(type)) {
                return TYPE_FILE_IMAGE;
            }

        }

        for (String format : videoFormat) {
            if (format.equals(type)) {
                return TYPE_FILE_VIDEO;
            }

        }
        return -1;
    }


}

package com.ketangpai.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.ketangpai.base.BaseAdapter;
import com.ketangpai.bean.DocumentFile;
import com.ketangpai.nan.ketangpai.R;
import com.ketangpai.utils.BitmapUtils;
import com.ketangpai.utils.LruCacheUtils;

import java.util.List;

/**
 * Created by nan on 2016/3/17.
 */
public class CourseDataAdapter extends BaseAdapter<DocumentFile> {

    public CourseDataAdapter(Context mContext, List<DocumentFile> mDataList) {
        super(mContext, mDataList);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_course_data;
    }

    @Override
    protected void bindData(ViewHolder holder, int position, DocumentFile item) {
        TextView mFileNameText = (TextView) holder.getViewById(R.id.tv_share_fileName);
        ImageView mFilePicImg = (ImageView) holder.getViewById(R.id.img_share_picImg);
        TextView mFileSize = (TextView) holder.getViewById(R.id.tv_share_fileSize);

        holder.itemView.setBackgroundResource(typedValue.resourceId);
        mFileNameText.setText(item.getName());
        mFileSize.setText(item.getSize() + "MB");
        loadDataBitmap(mFilePicImg, item.getPath());

    }

    /**
     * 加载图片
     *
     * @param mFilePicImg
     * @param path
     */
    private void loadDataBitmap(ImageView mFilePicImg, String path) {
        Bitmap bitmap;
        bitmap = LruCacheUtils.getInstance().getBitmapFromMemCache(path);
        if (null == bitmap) {
            bitmap = BitmapUtils.getBitmap(path,45,45);
            LruCacheUtils.getInstance().addBitmapToMemoryCache(path, bitmap);
        }
        mFilePicImg.setImageBitmap(bitmap);
    }

}

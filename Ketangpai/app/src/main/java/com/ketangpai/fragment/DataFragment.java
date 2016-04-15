package com.ketangpai.fragment;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ketangpai.base.BaseFragment;
import com.ketangpai.nan.ketangpai.R;

/**
 * Created by Administrator on 2016/4/15.
 */
public class DataFragment extends BaseFragment {

    //view
    private ImageView img_data_fileImg;
    private TextView tv_data_name;
    private TextView tv_data_size;
    private Button btn_data_delete;
    private Button btn_data_preview;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_data;
    }

    @Override
    protected void initView() {
        img_data_fileImg = (ImageView) view.findViewById(R.id.img_data_fileImg);
        tv_data_name = (TextView) view.findViewById(R.id.tv_data_name);
        tv_data_size = (TextView) view.findViewById(R.id.tv_data_size);
        btn_data_delete = (Button) view.findViewById(R.id.btn_data_delete);
        btn_data_preview = (Button) view.findViewById(R.id.btn_data_preview);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }
}

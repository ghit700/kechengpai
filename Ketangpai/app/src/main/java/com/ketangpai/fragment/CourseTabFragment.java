package com.ketangpai.fragment;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.ketangpai.adapter.CourseDataAdapter;
import com.ketangpai.adapter.CourseNoticeAdapter;
import com.ketangpai.adapter.CourseTExamAdapter;
import com.ketangpai.adapter.CourseTHomeworkAdapter;
import com.ketangpai.base.BaseAdapter;
import com.ketangpai.base.BaseFragment;
import com.ketangpai.nan.ketangpai.R;

import java.util.List;

/**
 * Created by nan on 2016/3/20.
 */
public class CourseTabFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    //view
    TextView mPublishText;
    RecyclerView mTabList;
    SwipeRefreshLayout mSwipeRefreshLayout;

    //adapter
    BaseAdapter mTabAdapter;

    //变量
    List mTabContents;
    private int position;

    public void setPosition(int position) {
        this.position = position;
    }

    public static CourseTabFragment newInstance(int positon) {
        CourseTabFragment fragment = new CourseTabFragment();
        fragment.setPosition(positon);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_course_tab;
    }

    @Override
    protected void initView() {
        mPublishText = (TextView) view.findViewById(R.id.tv_course_tab_publish);
        mSwipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.fresh_course_tab);
        mTabList = (RecyclerView) view.findViewById(R.id.list_course_tab);
        initTabList();

    }

    @Override
    protected void initData() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        for (int i = 0; i < 10; ++i) {
            mTabAdapter.addItem(i, "ssss");
        }
    }

    @Override
    protected void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void loadData() {

    }

    private void initTabList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mTabList.setLayoutManager(linearLayoutManager);
        changeTabAdaterByPosition(position,mContext);
    }

    public void changeTabAdaterByPosition(int position, Context context) {
        switch (position) {
            case 0:
                mTabAdapter = new CourseTHomeworkAdapter(context, mTabContents);
                mPublishText.setText("发布新作业");
                break;
            case 1:
                mTabAdapter = new CourseDataAdapter(context, mTabContents);
                mPublishText.setText("分享资料");
                break;
            case 2:
                mTabAdapter = new CourseNoticeAdapter(context, mTabContents);
                mPublishText.setText("发布新公告");
                break;
            case 3:
                mTabAdapter = new CourseTExamAdapter(context, mTabContents);
                mPublishText.setText("发布新测试");
                break;
            case 4:
                mTabAdapter = new CourseTExamAdapter(context, mTabContents);
                mPublishText.setText("创建考勤");
                break;

            default:
                break;
        }
        mTabList.setAdapter(mTabAdapter);

    }


    @Override
    public void onRefresh() {

    }
}

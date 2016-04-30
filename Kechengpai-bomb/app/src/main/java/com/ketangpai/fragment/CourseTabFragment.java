package com.ketangpai.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.provider.DocumentFile;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.ketangpai.activity.AddHomeWorkActivity;
import com.ketangpai.activity.AddNoticekActivity;
import com.ketangpai.activity.DataActivity;
import com.ketangpai.activity.NoticeActivity;
import com.ketangpai.adapter.CourseDataAdapter;
import com.ketangpai.adapter.CourseNoticeAdapter;
import com.ketangpai.adapter.CourseTExamAdapter;
import com.ketangpai.adapter.CourseTHomeworkAdapter;
import com.ketangpai.base.BaseAdapter;
import com.ketangpai.base.BasePresenterFragment;
import com.ketangpai.bean.Data;
import com.ketangpai.bean.Homework;
import com.ketangpai.bean.Notice;
import com.ketangpai.listener.OnItemClickListener;
import com.ketangpai.nan.ketangpai.R;
import com.ketangpai.presenter.CourseTabPresenter;
import com.ketangpai.utils.FileUtils;
import com.ketangpai.utils.IntentUtils;
import com.ketangpai.viewInterface.CourseTabViewInterface;
import com.shamanland.fab.ShowHideOnScroll;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nan on 2016/3/20.
 */
public class CourseTabFragment extends BasePresenterFragment<CourseTabViewInterface, CourseTabPresenter> implements SwipeRefreshLayout.OnRefreshListener, CourseTabViewInterface, OnItemClickListener, View.OnClickListener {
    public static final String TAG = "===CourseTabFragment";
    //view
    FloatingActionButton mPublishBtn;
    RecyclerView mTabList;
    SwipeRefreshLayout mSwipeRefreshLayout;

    //adapter
    private BaseAdapter mTabAdapter;

    //变量
    private List mTabContents;
    private int mPosition;
    private Animation mAddCloseAnim, mAddOpenAnim;
    private int type;
    private final int REQUEST = 11;
    private int c_id;
    private ProgressDialog mUploadDialog;

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    private CourseTabFragment getInstance() {
        return this;
    }

    @Override
    protected void initVarious() {
        super.initVarious();
        type = mContext.getSharedPreferences("user", 0).getInt("type", -1);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (type == 0) {
            mPublishBtn.startAnimation(mAddCloseAnim);
        }
    }

    public void setPosition(int position) {
        this.mPosition = position;
    }

    public static CourseTabFragment newInstance(int positon, int c_id) {
        CourseTabFragment fragment = new CourseTabFragment();
        fragment.setPosition(positon);
        fragment.setC_id(c_id);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_course_tab;
    }

    @Override
    protected void initView() {
        mTabList = (RecyclerView) view.findViewById(R.id.list_course_tab);
        if (type == 0) {
            mPublishBtn = (FloatingActionButton) view.findViewById(R.id.btn_course_tab_publish);
            mPublishBtn.setOnClickListener(this);
            mPublishBtn.setVisibility(View.VISIBLE);
            mTabList.setOnTouchListener(new ShowHideOnScroll(mPublishBtn));
            initAnim();
        }
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fresh_course_tab);
        initTabList();
    }

    @Override
    protected void initData() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    protected void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mTabAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    private void initTabList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mTabList.setLayoutManager(linearLayoutManager);
        mTabContents = new ArrayList();
        changeTabAdaterByPosition(mPosition, mContext);
    }

    public void changeTabAdaterByPosition(int position, Context context) {
        switch (position) {
            case 0:
                mTabAdapter = new CourseTHomeworkAdapter(context, mTabContents);
                break;
            case 1:
                mTabAdapter = new CourseDataAdapter(context, mTabContents);
                mPresenter.getDataList(context, c_id);
                break;
            case 2:
                mTabAdapter = new CourseNoticeAdapter(context, mTabContents);
                mPresenter.getNoticeList(context, c_id);
                break;
            case 3:
                mTabAdapter = new CourseTExamAdapter(context, mTabContents);
                break;

            default:
                break;
        }
        mTabList.setAdapter(mTabAdapter);

    }

    private void initAnim() {
        mAddCloseAnim = AnimationUtils.loadAnimation(mContext, R.anim.fab_rotate_close);
        mAddOpenAnim = AnimationUtils.loadAnimation(mContext, R.anim.fab_rotate_open);

        mAddOpenAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent;
                switch (mPosition) {
                    case 0:
                        intent = new Intent(mContext, AddHomeWorkActivity.class);
                        intent.putExtra("c_id", c_id);
                        startActivityForResult(intent, REQUEST);
                        break;
                    case 1:
                        IntentUtils.openDocument(getInstance());
                        break;
                    case 2:
                        intent = new Intent(mContext, AddNoticekActivity.class);
                        intent.putExtra("c_id", c_id);
                        startActivityForResult(intent, REQUEST);
                        break;
                    case 3:
                        break;

                    default:
                        break;
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent;
        switch (mPosition) {

            case 0:

                break;
            case 1:
                intent = new Intent(mContext, DataActivity.class);
                intent.putExtra("name", ((Data) mTabContents.get(position)).getName());
                intent.putExtra("url", ((Data) mTabContents.get(position)).getUrl());
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(mContext, NoticeActivity.class);
                intent.putExtra("notice", (Notice) mTabContents.get(position));
                startActivity(intent);
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_course_tab_publish) {
            mPublishBtn.startAnimation(mAddOpenAnim);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //
        if (requestCode == IntentUtils.OPEN_DOCUMENT_REQUEST && resultCode == getActivity().RESULT_OK) {
            Uri uri = data.getData();
            File file = FileUtils.getFileByUri(getActivity(), uri);
            Data data1 = new Data();
            data1.setC_id(c_id);
            data1.setName(file.getName());
            data1.setSize(FileUtils.getFileSize(file.length()));
            data1.setUrl(file.getAbsolutePath());
            mTabAdapter.addItem(0, data1);
            mPresenter.uploadData(mContext, data1);
            showUploadDialog();
        }

        if (requestCode == REQUEST && resultCode == AddNoticeFragment.RESULT) {
            Notice notice = (Notice) data.getSerializableExtra("notice");
            mTabAdapter.addItem(0, notice);
        }

        mPublishBtn.startAnimation(mAddCloseAnim);

    }

    private void showUploadDialog() {

        mUploadDialog = new ProgressDialog(mContext);
        mUploadDialog.setTitle("文件上传");
        mUploadDialog.setMax(100);
        mUploadDialog.setMessage("文件上传完成百分比");
        mUploadDialog.setCancelable(false);
        mUploadDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mUploadDialog.setIndeterminate(false);
        mUploadDialog.show();
    }

    @Override
    public void getHomeworkListOnComplete(List<Homework> homeworks) {
        mTabContents.addAll(homeworks);
        mTabAdapter.notifyDataSetChanged();
    }

    @Override
    public void uploadOnProgress(int value) {
        mUploadDialog.setProgress(value);
    }

    @Override
    public void uploadDataOnComplete(String url) {
        mUploadDialog.dismiss();
        ((Data) mTabContents.get(0)).setUrl(url);
        ((Data) mTabContents.get(0)).update(mContext);
        mTabAdapter.updateItem(0);
    }

    @Override
    public void getDataListOnComplete(List datas) {
        mTabContents.addAll(datas);
        mTabAdapter.notifyDataSetChanged();

    }

    @Override
    public void getNoticeListOnComplete(List<Notice> notices) {
        mTabContents.addAll(notices);
        mTabAdapter.notifyDataSetChanged();
    }

    @Override
    public void getExamkListOnComplete(List exams) {
        mTabContents.addAll(exams);
        mTabAdapter.notifyDataSetChanged();
    }

    @Override
    protected CourseTabPresenter createPresenter() {
        return new CourseTabPresenter();
    }
}

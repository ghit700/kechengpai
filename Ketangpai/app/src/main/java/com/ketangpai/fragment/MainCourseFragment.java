package com.ketangpai.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ketangpai.activity.CourseActivity;
import com.ketangpai.adapter.CourseSMainCourseAdapter;
import com.ketangpai.adapter.CourseTMainCourseAdapter;
import com.ketangpai.base.BaseAdapter;
import com.ketangpai.base.BaseFragment;
import com.ketangpai.bean.Course;
import com.ketangpai.listener.OnItemClickListener;
import com.ketangpai.nan.ketangpai.R;
import com.shamanland.fab.FloatingActionButton;
import com.shamanland.fab.ShowHideOnScroll;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nan on 2016/3/15.
 */
public class MainCourseFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener, DialogInterface.OnDismissListener, SwipeRefreshLayout.OnRefreshListener {

    //view
    private FloatingActionButton mAddBtn;
    private RecyclerView mMainCourseList;
    private AlertDialog mAddDialog;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    //adapter
    private BaseAdapter mMainCourseAdapter;

    //addBtn的动画
    private Animation mAddOpenAnim;
    private Animation mAddCloseAnim;

    //变量
    //course数组
    private List<Course> mCourses;
    //判断addBtn是否open
    private boolean isBtnOpen = true;
    //判断是老师还是学生
    private int type;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_course;
    }

    @Override
    protected void initVarious() {
        super.initVarious();
        type = mContext.getSharedPreferences("user", 0).getInt("type", -1);
        initAddBtnAnim();

    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fresh_main_course);
        mAddBtn = (FloatingActionButton) view.findViewById(R.id.btn_main_add);
        ininMainCourseList(view);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
    }

    @Override
    protected void initData() {
        
    }

    @Override
    protected void initListener() {
        mAddBtn.setOnClickListener(this);
        mMainCourseList.setOnTouchListener(new ShowHideOnScroll(mAddBtn));
        mMainCourseAdapter.setOnItemClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void loadData() {

        //初次加载数据
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        onRefresh();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_add:
                changeAddBtnAnim();
                showAddDialog();
                break;
            case R.id.btn_addDialog_cancel:
                mAddDialog.dismiss();
                break;
            case R.id.btn_addDialog_create:
                createCourse();
                mAddDialog.dismiss();
                break;

            default:
                break;
        }

    }

    private void createCourse() {

    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(mContext, CourseActivity.class);
        intent.putExtra("course", ((TextView) view.findViewById(R.id.tv_item_courseName)).getText().toString() + position);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    private void showAddDialog() {
        mAddDialog = new AlertDialog.Builder(mContext).create();
        mAddDialog.setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_add, null);
        TextView dialogTitle = (TextView) view.findViewById(R.id.tv_addDialog_title);
        EditText dialogCourse = (EditText) view.findViewById(R.id.et_addDialog_courseName);
        Button btnCancel = (Button) view.findViewById(R.id.btn_addDialog_cancel);
        Button btnCreate = (Button) view.findViewById(R.id.btn_addDialog_create);

        //设置事件监听
        btnCancel.setOnClickListener(this);
        btnCreate.setOnClickListener(this);
        mAddDialog.setOnDismissListener(this);

        if (type == 0) {
            dialogTitle.setText("新建班级");
            dialogCourse.setHint("请输入新建班级名称");
            btnCreate.setText("创建");
        } else {
            dialogTitle.setText("加入班级");
            dialogCourse.setHint("请输入班级邀请码");
            btnCreate.setText("加入");
        }

        btnCancel.setOnClickListener(this);
        btnCreate.setOnClickListener(this);

        mAddDialog.setView(view);
        mAddDialog.show();
    }

    private void ininMainCourseList(View view) {
        mMainCourseList = (RecyclerView) view.findViewById(R.id.list_main_course);
        mMainCourseList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mCourses = new ArrayList<>();
        if (type == 0) {
            mMainCourseAdapter = new CourseTMainCourseAdapter(mContext, mCourses);
        } else {
            mMainCourseAdapter = new CourseSMainCourseAdapter(mContext, mCourses);
        }
        mMainCourseList.setAdapter(mMainCourseAdapter);

    }

    //addAnim
    private void initAddBtnAnim() {
        mAddCloseAnim = AnimationUtils.loadAnimation(mContext, R.anim.fab_rotate_close);
        mAddOpenAnim = AnimationUtils.loadAnimation(mContext, R.anim.fab_rotate_open);
    }

    //AddBtn open or close时设置动画
    private void changeAddBtnAnim() {

        if (isBtnOpen) {
            mAddBtn.startAnimation(mAddOpenAnim);
            isBtnOpen = false;
        } else {
            mAddBtn.startAnimation(mAddCloseAnim);
            isBtnOpen = true;
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        changeAddBtnAnim();
    }

    @Override
    public void onRefresh() {

    }

}

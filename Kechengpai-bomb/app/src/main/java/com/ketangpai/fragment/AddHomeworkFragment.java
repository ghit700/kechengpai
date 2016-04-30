package com.ketangpai.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.provider.DocumentFile;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ketangpai.activity.AddNoticekActivity;
import com.ketangpai.activity.AddTeacher_HomeworkkActivity;
import com.ketangpai.adapter.DataAdapter;
import com.ketangpai.base.BaseFragment;
import com.ketangpai.base.BasePresenterFragment;
import com.ketangpai.bean.Teacher_Homework;
import com.ketangpai.bean.Teacher_Homework;
import com.ketangpai.nan.ketangpai.R;
import com.ketangpai.presenter.AddHomeworkPresenter;
import com.ketangpai.utils.FileUtils;
import com.ketangpai.utils.IntentUtils;
import com.ketangpai.utils.TimeUtils;
import com.ketangpai.view.FullyLinearLayoutManager;
import com.ketangpai.viewInterface.AddHomeworkViewInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by nan on 2016/3/27.
 */
public class AddHomeworkFragment extends BasePresenterFragment<AddHomeworkViewInterface, AddHomeworkPresenter> implements AddHomeworkViewInterface, View.OnClickListener {
    public static final String TAG = "===AddHomeworkFragment";
    //view
    EditText etHomeworkTitle;
    EditText etAddHomeworkContent;
    TextView tvAddHomeworkEndDate;
    TextView tvAddHomeworkEndTime;
    RecyclerView listAddHomeworkData;

    //adapter
    DataAdapter mDataAdapter;

    //变量
    List mDataList;
    List<BmobFile> mFiles;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private int c_id;
    public static final int RESULT = 13;
    private int mValue = 0;
    private Calendar mCalendar;

    @Override
    protected void initVarious() {
        super.initVarious();
        c_id = getActivity().getIntent().getIntExtra("c_id", -1);
    }

    @Override

    protected int getLayoutId() {
        return R.layout.fragment_addhomework;
    }

    protected void initView() {
        etHomeworkTitle = (EditText) view.findViewById(R.id.et_homework_title);
        etAddHomeworkContent = (EditText) view.findViewById(R.id.et_add_homework_content);
        tvAddHomeworkEndDate = (TextView) view.findViewById(R.id.tv_add_homework_endDate);
        tvAddHomeworkEndTime = (TextView) view.findViewById(R.id.tv_add_homework_endTime);
        initDataList();
    }

    @Override
    protected void initData() {
        initCurrentTime();
        tvAddHomeworkEndDate.setText(TimeUtils.getCurrentDateFormat(mCalendar));
        tvAddHomeworkEndTime.setText(TimeUtils.getCurrentTimeFormat(mCalendar));

    }

    private void initCurrentTime() {
        mCalendar = Calendar.getInstance();
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH) + 1;
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = mCalendar.get(Calendar.MINUTE);
    }

    @Override
    protected void initListener() {
        tvAddHomeworkEndDate.setOnClickListener(this);
        tvAddHomeworkEndTime.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_homework_endDate:
                showDateDialog();
                break;
            case R.id.tv_add_homework_endTime:
                showTimeDialog();
                break;
            case R.id.et_homework_title:

                break;
            case R.id.et_add_homework_content:

                break;

            default:
                break;
        }
    }

    /**
     * 初始化附件列表
     */
    private void initDataList() {
        listAddHomeworkData = (RecyclerView) view.findViewById(R.id.list_add_homework_data);
        listAddHomeworkData.setLayoutManager(new FullyLinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mDataList = new ArrayList();
        mDataAdapter = new DataAdapter(mContext, mDataList);
        listAddHomeworkData.setAdapter(mDataAdapter);

    }

    /**
     * 时间选择器
     */
    private void showTimeDialog() {
        new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mHour = hourOfDay;
                mMinute = minute;
                mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
                mCalendar.set(Calendar.MINUTE, mMinute);
                tvAddHomeworkEndTime.setText(hourOfDay + ":" + minute);
            }
        }, mHour, mMinute, true).show();
    }

    /**
     * 日期选择器
     */
    private void showDateDialog() {
        new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.i("===wu", year + " " + monthOfYear + " " + dayOfMonth);
                mYear = year;
                mMonth = monthOfYear + 1;
                mDay = dayOfMonth;
                mCalendar.set(mYear, mMonth, mDay);
                tvAddHomeworkEndDate.setText(mYear + "/" + mMonth + "/" + mDay);
            }
        }, mYear, mMonth - 1, mDay).show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //
        if (requestCode == IntentUtils.OPEN_DOCUMENT_REQUEST && resultCode == getActivity().RESULT_OK) {
            Uri uri = data.getData();
            String fileName = FileUtils.getFileName(uri);
            File file = FileUtils.getFileByUri((Activity) mContext, uri);
            BmobFile bmobFile = new BmobFile(file);
            mPresenter.uploadAttachment(mContext, bmobFile);
            mDataAdapter.addItem(mDataList.size(), file.getName());

        }
    }

    /**
     * 发布作业
     */
    public void sendHomeWork() {
        if (0 != mValue && 100 != mValue) {
            new AlertDialog.Builder(mContext).setTitle("文件还没上传完毕,请稍等...").setPositiveButton("确认", null).show();
        } else if (!etHomeworkTitle.getText().toString().equals("")) {
            Teacher_Homework homework = new Teacher_Homework();
            homework.setC_id(c_id);
            homework.setContent(etAddHomeworkContent.getText().toString());
            homework.setTitle(etHomeworkTitle.getText().toString());
            homework.setP_time(System.currentTimeMillis());
            homework.setE_time(mCalendar.getTimeInMillis());
            homework.setCheck_count(0);
            homework.setNo_check_count(0);
            homework.setNo_header_count(0);
            homework.setFiles(mFiles);
            mPresenter.publishHomework(mContext, homework);
        } else {
            new AlertDialog.Builder(mContext).setTitle("发布公告失败").setMessage("公告标题不能为空")
                    .setPositiveButton("确认", null).create().show();
        }
    }


    @Override
    public void addHomeWorkOnComplete(Teacher_Homework homework) {
        Intent intent = new Intent();
        intent.putExtra("homework", homework);
        ((AddNoticekActivity) mContext).setResult(RESULT, intent);
        new AlertDialog.Builder(mContext).setTitle("发布公告成功").setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((AddNoticekActivity) mContext).finish();
            }
        }).show();
    }

    @Override
    public void uploadAttachmentOnComplete(BmobFile bmobFile) {
        mFiles.add(bmobFile);
    }

    @Override
    public void onProgress(int value) {
        mValue = value;
    }

    @Override
    protected AddHomeworkPresenter createPresenter() {
        return new AddHomeworkPresenter();
    }
}

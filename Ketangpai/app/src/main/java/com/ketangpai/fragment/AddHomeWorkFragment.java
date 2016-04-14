package com.ketangpai.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ketangpai.adapter.AddHomeWorkDataAdapter;
import com.ketangpai.base.BaseFragment;
import com.ketangpai.bean.DocumentFile;
import com.ketangpai.nan.ketangpai.R;
import com.ketangpai.utils.FileUtils;
import com.ketangpai.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by nan on 2016/3/27.
 */
public class AddHomeWorkFragment extends BaseFragment implements View.OnClickListener {

    //view
    EditText etHomeworkTitle;
    EditText etAddHomeworkContent;
    TextView tvAddHomeworkEndDate;
    TextView tvAddHomeworkEndTime;
    RecyclerView listAddHomeworkData;

    //adapter
    AddHomeWorkDataAdapter mAddHomeWorkDataAdapter;

    //变量
    List mDataList;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

    private Calendar mCalendar;

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        listAddHomeworkData.setLayoutManager(linearLayoutManager);
        mDataList = new ArrayList();
        mAddHomeWorkDataAdapter = new AddHomeWorkDataAdapter(mContext, mDataList);
        listAddHomeworkData.setAdapter(mAddHomeWorkDataAdapter);

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

    public void openDocument() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //仅返回可以打开流的文件
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/video/audio/*");
        startActivityForResult(intent, CourseTabFragment.OPEN_DOCUMENT_REQUEST);
    }

    public Calendar getmCalendar() {
        return mCalendar;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //
        if (requestCode == CourseTabFragment.OPEN_DOCUMENT_REQUEST && resultCode == getActivity().RESULT_OK) {
            Uri uri = data.getData();
            String fileName = FileUtils.getFileName(uri);
            int fileType = FileUtils.getFileType(fileName);
            String size = FileUtils.getFileSize(uri);
            DocumentFile file = new DocumentFile(fileType, fileName, size);
            for (int i = 0; i < 10; ++i) {

                mAddHomeWorkDataAdapter.addItem(mDataList.size(), file);
            }

            //需要延迟,不然会获得不了界面
            listAddHomeworkData.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setListViewHeightBasedOnChildren(listAddHomeworkData);
                }
            }, 200);

        }
    }

    /**
     * 设置recyclerview的高度
     *
     * @param recyclerView
     */
    public void setListViewHeightBasedOnChildren(RecyclerView recyclerView) {
        // 获取ListView对应的Adapter
        RecyclerView.Adapter listAdapter = recyclerView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        View listItem = recyclerView.getChildAt(0);
        for (int i = 0, len = listAdapter.getItemCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目

            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = recyclerView.getLayoutParams();
        params.height = totalHeight;
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        recyclerView.setLayoutParams(params);
    }

    /**
     * 发布作业
     */
    public void sendHomeWork() {

    }
}

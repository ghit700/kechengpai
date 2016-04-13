package com.ketangpai.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ketangpai.adapter.AddHomeWorkDataAdapter;
import com.ketangpai.base.BaseFragment;
import com.ketangpai.nan.ketangpai.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_addhomework;
    }

    @Override
    protected void initView() {
        etHomeworkTitle = (EditText) view.findViewById(R.id.et_homework_title);
        etAddHomeworkContent = (EditText) view.findViewById(R.id.et_add_homework_content);
        tvAddHomeworkEndDate = (TextView) view.findViewById(R.id.tv_add_homework_endDate);
        tvAddHomeworkEndTime = (TextView) view.findViewById(R.id.tv_add_homework_endTime);
        initDataList();
    }

    @Override
    protected void initData() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        tvAddHomeworkEndDate.setText(calendar.get(Calendar.YEAR) + "/" + month + "/" + calendar.get(Calendar.DAY_OF_MONTH));
        tvAddHomeworkEndTime.setText(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));

        for (int i = 0; i < 10; ++i) {
            mAddHomeWorkDataAdapter.addItem(i, "111");
        }
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

            default:
                break;
        }
    }

    private void initDataList() {
        listAddHomeworkData = (RecyclerView) view.findViewById(R.id.list_add_homework_data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        listAddHomeworkData.setLayoutManager(linearLayoutManager);
        mDataList = new ArrayList();
        mAddHomeWorkDataAdapter = new AddHomeWorkDataAdapter(mContext, mDataList);
        listAddHomeworkData.setAdapter(mAddHomeWorkDataAdapter);


    }

    private void showTimeDialog() {
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                tvAddHomeworkEndTime.setText(hourOfDay + ":" + minute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                tvAddHomeworkEndDate.setText(year + "/" + monthOfYear + "/" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}

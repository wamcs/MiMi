package com.unique.app.community.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.unique.app.community.R;
import com.unique.app.community.global.Conf;
import com.unique.app.community.utils.TimeUtils;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.carbswang.android.numberpickerview.library.NumberPickerView;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/30/16.
 */
public class DatePickDialog extends Dialog {


    @BindView(R.id.date_pick_dialog_title_text_view)
    TextView mTitleTextView;
    @BindView(R.id.date_pick_dialog_show_date_text_view)
    TextView mShowDateTextView;
    @BindView(R.id.date_pick_dialog_show_time_text_view)
    TextView mShowTimeTextView;
    @BindView(R.id.date_pick_dialog_year_picker)
    NumberPickerView mYearPicker;
    @BindView(R.id.date_pick_dialog_month_picker)
    NumberPickerView mMonthPicker;
    @BindView(R.id.date_pick_dialog_date_picker)
    NumberPickerView mDatePicker;
    @BindView(R.id.date_pick_dialog_hour_picker)
    NumberPickerView mHourPicker;
    @BindView(R.id.date_pick_dialog_min_picker)
    NumberPickerView mMinPicker;


    private String title;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int min;

    private int DEFAULT_YEAR = 2010;

    private OnDateChosenListener listener;

    public DatePickDialog(Context context,String title) {
        super(context);
        this.title = title;
        setContentView(R.layout.dialog_date_pick);
        View view = getWindow().getDecorView();
        ButterKnife.bind(this, view);
        init();
    }

    private void init(){
        mTitleTextView.setText(title);
        initPicker();
    }

    private void initPicker(){

        year = TimeUtils.getCurrentYear();
        month = TimeUtils.getCurrentMonth();
        day = TimeUtils.getCurrentDay();
        hour =TimeUtils.getCurrentHour();
        min = TimeUtils.getCurrentMinute();

        mYearPicker.setMaxValue(Conf.MAX_YEAR);
        mYearPicker.setMinValue(DEFAULT_YEAR);
        mYearPicker.setValue(year);
        mYearPicker.setOnScrollListener((view, scrollState) -> {
            if (NumberPickerView.OnScrollListener.SCROLL_STATE_IDLE == scrollState){
                year = view.getValue();
                setShowDate(year,month,day);
            }
        });

        mMonthPicker.setValue(month);
        mMonthPicker.setOnScrollListener((view, scrollState) -> {
            if (NumberPickerView.OnScrollListener.SCROLL_STATE_IDLE == scrollState){
                month = view.getValue();
                setShowDate(year,month,day);
                initDayPicker();
            }
        });

        mDatePicker.setValue(day);
        mDatePicker.setOnScrollListener((view, scrollState) -> {
            if (NumberPickerView.OnScrollListener.SCROLL_STATE_IDLE == scrollState){
                day = view.getValue();
                setShowDate(year,month,day);
            }
        });

        mHourPicker.setValue(hour);
        mHourPicker.setOnScrollListener((view, scrollState) -> {
            if (NumberPickerView.OnScrollListener.SCROLL_STATE_IDLE == scrollState){
                hour = view.getValue();
                setShowTime(hour,min);
            }
        });

        mMinPicker.setValue(min);
        mMinPicker.setOnScrollListener((view, scrollState) -> {
            if (NumberPickerView.OnScrollListener.SCROLL_STATE_IDLE == scrollState){
                min = view.getValue();
                setShowTime(hour,min);
            }
        });

        setShowDate(year,month,day);
        setShowTime(hour,min);
    }

    private void setShowDate(int year,int month,int day){
        mShowDateTextView.setText(String.format("%d.%d.%d",year,month,day));
    }

    private void setShowTime(int hour,int min){
        mShowTimeTextView.setText(String.format("%d:%d",hour,min));
    }

    private void initDayPicker() {
        int month = mMonthPicker.getValue();
        if (Conf.FEB_MONTH == month) {
            int year = mYearPicker.getValue();
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                mDatePicker.setMaxValue(Conf.FEB_BIG_MONTH_MAX_DAY);
            } else {
                mDatePicker.setMaxValue(Conf.FEB_SMALL_MONTH_MAX_DAY);
            }
        } else {
            if (month == 3 || month == 6 || month == 9 || month == 11) {
                mDatePicker.setMaxValue(Conf.NORMAL_SMALL_MONTH_MAX_DAY);
            } else {
                mDatePicker.setMaxValue(Conf.NORMAL_BIG_MONTH_MAX_DAY);
            }
        }
    }

    @OnClick(R.id.date_pick_dialog_close_button)
    void close(){
        dismiss();
    }

    @OnClick(R.id.date_pick_dialog_finish_button)
    void finish(){
        String time = mShowDateTextView.getText()+" "+mShowTimeTextView.getText();
        if (listener != null){
            listener.onDateChosen(time);
        }
        dismiss();
    }

    private void setListener(OnDateChosenListener listener){
        this.listener = listener;
    }

    public interface OnDateChosenListener{
        void onDateChosen(String date);
    }

}

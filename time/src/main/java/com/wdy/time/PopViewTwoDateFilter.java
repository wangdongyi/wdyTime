package com.wdy.time;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;


/**
 * Created by Tongmeng on 2017/10/19.
 */

public class PopViewTwoDateFilter extends PopViewBase {

    private DatePicker date_picker;
    private TextView tv_begin;
    private TextView tv_end;
    private TextView tv_begin_time;
    private TextView tv_end_time;
    private TextView tv_filter_title;
    private TextView tv_cancel;
    private TextView tv_ok;

    private boolean isBeginCheck = false;
    private boolean isEndCheck = false;
    private int tab = 0;
    private long begin = 0;
    private long end = 0;
    private Context context;

    private OnOkClickListener onOkClickListener;

    public PopViewTwoDateFilter(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View conentView = inflater.inflate(R.layout.pop_two_date_filter, null);
        // 设置CardEnterView的View
        this.setContentView(conentView);
        this.context = context;
        //初始化CardEnterView
        initPopView();
        //初始化view
        initViews(conentView);
        initClicks();
    }

    private void initViews(View conentView) {
        this.setOutsideTouchable(false);
        date_picker = conentView.findViewById(R.id.date_picker);
        tv_begin = conentView.findViewById(R.id.tv_begin);
        tv_end = conentView.findViewById(R.id.tv_end);
        tv_begin_time = conentView.findViewById(R.id.tv_begin_time);
        tv_end_time = conentView.findViewById(R.id.tv_end_time);
        tv_filter_title = conentView.findViewById(R.id.tv_filter_title);
        tv_cancel = conentView.findViewById(R.id.tv_cancel);
        tv_ok = conentView.findViewById(R.id.tv_ok);
        date_picker.init(date_picker.getYear(), date_picker.getMonth(), date_picker.getDayOfMonth(),
                new OnDateChangedListenerImpl());
//        date_picker.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
//        date_picker.setMinDate(StringUtils.getMinTime());
//        date_picker.setMaxDate(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
    }

    private class OnDateChangedListenerImpl implements DatePicker.OnDateChangedListener {

        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String dateStr = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
            long date = StringUtils.convertYYMMDDtoBeginSeconds(dateStr);
            if (isBeginCheck) {
                tv_begin_time.setText(dateStr);
                begin = date;
            }
            if (isEndCheck) {
                tv_end_time.setText(dateStr);
                end = date;
            }
        }
    }

    public void setTab(int tab) {
        this.tab = tab;
    }

    public void setDate(long beginTime, long endTime) {
        this.begin = beginTime;
        this.end = endTime;
        if (begin == 0) {
            begin = System.currentTimeMillis() / 1000;
        }
        if (end == 0) {
            end = System.currentTimeMillis() / 1000;
        }
        if (begin != 0 && end != 0) {
            isBeginCheck = true;
            isEndCheck = false;
            setCheck();
        }
        tv_end_time.setText(StringUtils.convertToYYYYMMDD(end));
    }

    //显示popWindow
    public void show(View parent) {
        if (!this.isShowing()) {
            this.showAtLocation(parent, Gravity.FILL, 0, 0);
        } else {
            this.dismiss();
        }
    }

    //隐藏popWindow
    public void hide() {
        if (this.isShowing()) {
            this.dismiss();
        }
    }

    private void initClicks() {
        tv_begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isBeginCheck = true;
                isEndCheck = false;
                setCheck();
            }
        });
        tv_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isBeginCheck = false;
                isEndCheck = true;
                setCheck();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (begin == 0) {
                    ToastUtils.showToast(context, context.getString(R.string.active_filter_begin_title));
                    return;
                }
                if (end == 0) {
                    ToastUtils.showToast(context, context.getString(R.string.active_filter_end_title));
                    return;
                }
                if (end < begin) {
                    ToastUtils.showToast(context, context.getString(R.string.active_filter_time_error));
                    return;
                }
                if (tab == 1) {
                    if (begin > StringUtils.convertToYesterday(System.currentTimeMillis())) {
                        ToastUtils.showToast(context, context.getString(R.string.active_filter_time_end_error));
                        return;
                    }
                }
                if (onOkClickListener != null) {
                    onOkClickListener.onOkClick(begin, end);
                }
                hide();
            }
        });
    }

    private void setCheck() {
        if (isBeginCheck) {
            tv_begin.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.time_filter_check_bg));
            tv_end.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.time_filter_uncheck_bg));
            tv_filter_title.setText(context.getString(R.string.active_filter_begin_title));
            tv_begin.setTextColor(ColorUtils.getWhite());
            tv_end.setTextColor(ColorUtils.getTimeFilter());
            String[] date = StringUtils.convertToYYYYMMDD(begin).split("-");
            try {
                date_picker.updateDate(Integer.parseInt(date[0]), Integer.parseInt(date[1]) - 1,
                        Integer.parseInt(date[2]));
            } catch (Exception e) {

            }
        } else {
            tv_begin.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.time_filter_uncheck_bg));
            tv_end.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.time_filter_check_bg));
            tv_filter_title.setText(context.getString(R.string.active_filter_end_title));
            tv_begin.setTextColor(ColorUtils.getTimeFilter());
            tv_end.setTextColor(ColorUtils.getWhite());
            String[] date = StringUtils.convertToYYYYMMDD(end).split("-");
            try {
                date_picker.updateDate(Integer.parseInt(date[0]), Integer.parseInt(date[1]) - 1,
                        Integer.parseInt(date[2]));
            } catch (Exception e) {

            }
        }
    }

    public void setOnOkClickListener(OnOkClickListener l) {
        onOkClickListener = l;
    }

    public interface OnOkClickListener {

        public void onOkClick(long begin, long end);

    }

}

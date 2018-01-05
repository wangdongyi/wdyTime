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

public class PopViewOneDateFilter extends PopViewBase {

    private DatePicker date_picker;
    private TextView tv_cancel;
    private TextView tv_ok;
    private boolean isBeginCheck = false;
    private boolean isEndCheck = false;
    private int tab = 0;
    private long date = 0;
    private Context context;

    private OnOkClickListener onOkClickListener;

    public PopViewOneDateFilter(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View conentView = inflater.inflate(R.layout.pop_one_date_filter, null);
        date_picker = conentView.findViewById(R.id.date_picker);
        tv_cancel = conentView.findViewById(R.id.tv_cancel);
        tv_ok = conentView.findViewById(R.id.tv_ok);
        // 设置CardEnterView的View
        this.setContentView(conentView);
        this.context = context;
        //初始化CardEnterView
        initPopView();
        //初始化view
        initViews();
        initClicks();
    }

    private void initViews() {
        this.setOutsideTouchable(false);
        date_picker.init(date_picker.getYear(), date_picker.getMonth(), date_picker.getDayOfMonth(),
                new OnDateChangedListenerImpl());
//        date_picker.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
//        date_picker.setMinDate(StringUtils.getMinTime());
//        date_picker.setMaxDate((System.currentTimeMillis() - 24 * 60 * 60 * 1000));
    }

    private void initClicks() {
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (date == 0) {
                    ToastUtils.showToast(context, context.getString(R.string.sta_time_date_none));
                    return;
                }
                if (date >= System.currentTimeMillis() / 1000) {
                    ToastUtils.showToast(context, context.getString(R.string.sta_time_date_error));
                    return;
                }
                if (onOkClickListener != null) {
                    onOkClickListener.onOkClick(date);
                }
                hide();
            }
        });
    }

    private class OnDateChangedListenerImpl implements DatePicker.OnDateChangedListener {

        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String beginStr = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
            long dateNew = StringUtils.convertYYMMDDtoBeginSeconds(beginStr);
            long yesterday = System.currentTimeMillis() / 1000 - 24 * 60 * 60;
            if (dateNew > yesterday) {
                String[] dateStr = StringUtils.convertToYYYYMMDD(date).split("-");
                try {
                    date_picker.updateDate(Integer.parseInt(dateStr[0]), Integer.parseInt(dateStr[1]) - 1,
                            Integer.parseInt(dateStr[2]));
                } catch (Exception e) {

                }
                return;
            }
            date = dateNew;
        }
    }

    public void setTab(int tab) {
        this.tab = tab;
    }

    public void setDate(long date) {
        String[] dates = StringUtils.convertToYYYYMMDD(date).split("-");
        try {
            date_picker.updateDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]) - 1,
                    Integer.parseInt(dates[2]));
        } catch (Exception e) {

        }
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

    public void setOnOkClickListener(OnOkClickListener l) {
        onOkClickListener = l;
    }

    public interface OnOkClickListener {

        public void onOkClick(long date);

    }

}

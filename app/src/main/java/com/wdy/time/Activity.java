package com.wdy.time;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * 作者：王东一
 * 创建时间：2018/1/5.
 */

public class Activity extends AppCompatActivity {
    private TextView text;
    private ConstraintLayout main;
    private TextView text2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        text = (TextView) findViewById(R.id.text);
        main = (ConstraintLayout) findViewById(R.id.main);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopViewTwoDateFilter popViewTwoDateFilter = new PopViewTwoDateFilter(Activity.this);
                popViewTwoDateFilter.setDate(System.currentTimeMillis() / 1000, System.currentTimeMillis() / 1000);
                popViewTwoDateFilter.show(main);
                popViewTwoDateFilter.setOnOkClickListener(new PopViewTwoDateFilter.OnOkClickListener() {
                    @Override
                    public void onOkClick(long l, long l1) {
                        ToastUtils.showToast(Activity.this, l + "\n" + l1);
                    }
                });
            }
        });
        text2 = (TextView) findViewById(R.id.text2);
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopViewOneDateFilter popViewOneDateFilter = new PopViewOneDateFilter(Activity.this);
                popViewOneDateFilter.setDate(System.currentTimeMillis() / 1000);
                popViewOneDateFilter.show(main);
                popViewOneDateFilter.setOnOkClickListener(new PopViewOneDateFilter.OnOkClickListener() {
                    @Override
                    public void onOkClick(long date) {
                        ToastUtils.showToast(Activity.this, date + "\n");
                    }
                });
            }
        });
    }
}

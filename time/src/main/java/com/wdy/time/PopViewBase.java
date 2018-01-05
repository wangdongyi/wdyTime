package com.wdy.time;

import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by Tongmeng on 2017/10/19.
 */

public class PopViewBase extends PopupWindow {

    protected void initPopView() {
        // 设置CardEnterView弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置CardEnterView弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置CardEnterView弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(ColorUtils.getpopWindowBg());
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // 设置CardEnterView弹出窗体动画效果
//        this.setAnimationStyle(R.style.down_in_out_animation);
        //设置显示键盘上移
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

}

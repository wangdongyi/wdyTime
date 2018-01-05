package com.wdy.time;

import android.content.res.ColorStateList;
import android.graphics.Color;

/**
 * Created by tongmeng on 2016/9/21.
 */
public class ColorUtils {

    private static final int ENABLE_ATTR = android.R.attr.state_enabled;
    private static final int CHECKED_ATTR = android.R.attr.state_checked;
    private static final int PRESSED_ATTR = android.R.attr.state_pressed;

    //登录背景色
    public static int getLoginYellow() {
        return Color.parseColor("#D2AD68");
    }

    //登录背景色
    public static int getCommonYellow() {
        return Color.parseColor("#ddb076");
    }

    //登录背景色
    public static int getCommonPink() {
        return Color.parseColor("#ff8a7e");
    }

    //登录背景色
    public static int getCommonGreen() {
        return Color.parseColor("#86d8af");
    }

    //popWindow背景色
    public static int getpopWindowBg() {
        return Color.parseColor("#88000000");
    }

    //导航页消息背景色
    public static int getMsgBlue() {
        return Color.parseColor("#69cdec");
    }

    //通用条状图颜色
    public static int getTabBlue() {
        return Color.parseColor("#4B86FF");
    }

    //通用条状图颜色
    public static int getBlue() {
        return Color.parseColor("#12B6FE");
    }

    //通用条状图颜色
    public static int getPurple() {
        return Color.parseColor("#4B86FF");
    }

    //通用条状图颜色
    public static int getTabYellow() {
        return Color.parseColor("#FFA942");
    }

    //透明色
    public static int getTransparent() {
        return Color.parseColor("#00000000");
    }

    //白色
    public static int getWhite() {
        return Color.parseColor("#ffffff");
    }

    //灰色
    public static int getCommon_bg() {
        return Color.parseColor("#f0eff5");
    }

    //黄色
    public static int getGzhYellow() {
        return Color.parseColor("#ffa800");
    }

    //绿色
    public static int getGzhGreen() {
        return Color.parseColor("#68ca38");
    }

    //黑色
    public static int getTitleBlack() {
        return Color.parseColor("#333333");
    }

    //红色
    public static int getRed() {
        return Color.parseColor("#FF7B7B");
    }

    //蓝色
    public static int getRingColor() {
        return Color.parseColor("#00B4FE");
    }

    //黄色
    public static int getSectorColor() {
        return Color.parseColor("#FFA942");
    }

    public static int getColor1() {
        return Color.parseColor("#a785e9");
    }

    public static int getRank2() {
        return Color.parseColor("#999999");
    }

    public static int getRank3() {
        return Color.parseColor("#f88a4d");
    }

    public static int getRank() {
        return Color.parseColor("#5f5f5f");
    }

    public static int getGray() {
        return Color.parseColor("#d5d5d7");
    }

    public static int getTimeFilter() {
        return Color.parseColor("#a5adb8");
    }

    public static ColorStateList generateThumbColorWithTintColor(final int tintColor) {
        int[][] states = new int[][]{
                {-ENABLE_ATTR, CHECKED_ATTR},
                {-ENABLE_ATTR},
                {PRESSED_ATTR, -CHECKED_ATTR},
                {PRESSED_ATTR, CHECKED_ATTR},
                {CHECKED_ATTR},
                {-CHECKED_ATTR}
        };

        int[] colors = new int[] {
                tintColor - 0xAA000000,
                0xFFBABABA,
                tintColor - 0x99000000,
                tintColor - 0x99000000,
                0xFFffffff,
                0xFFEEEEEE
        };
        return new ColorStateList(states, colors);
    }

    public static ColorStateList generateBackColorWithTintColor(final int tintColor) {
        int[][] states = new int[][]{
                {-ENABLE_ATTR, CHECKED_ATTR},
                {-ENABLE_ATTR},
                {CHECKED_ATTR, PRESSED_ATTR},
                {-CHECKED_ATTR, PRESSED_ATTR},
                {CHECKED_ATTR},
                {-CHECKED_ATTR}
        };

        int[] colors = new int[] {
                0xFFffffff,
                0x10000000,
                0xFFffffff,
                0x20000000,
                0xFF16beae,
                0x20000000
        };
        return new ColorStateList(states, colors);
    }

}

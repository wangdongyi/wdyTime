package com.wdy.time;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tongmeng on 2016/9/20.
 */
public class StringUtils {

    /**
     * 将字符串转成MD5值
     *
     * @param string
     * @return
     */
    public static String stringToMD5(String string) {
        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }

        return hex.toString();
    }

    public static int getWindowWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metric = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        return width;
    }

    public static int getWindowHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metric = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metric);
        int height = metric.heightPixels;   // 屏幕高度（像素）
        return height;
    }

    /**
     * 获取时间戳
     *
     * @param
     * @return
     */
    public static String getTimestamp() {
        Long tsLong = System.currentTimeMillis();
        String ts = tsLong.toString();
        return ts;
    }

    /**
     * 获取随机数
     *
     * @param
     * @return
     */
    public static String getRandom() {
        String strRand = "";
        for (int i = 0; i < 16; i++) {
            strRand += String.valueOf((int) (Math.random() * 10));
        }
        return strRand;
    }

    public static int[] getwindowWidthHeight(Context context) {
        int wgth[] = new int[2];
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        wgth[0] = width;
        wgth[1] = height;
        return wgth;
    }

    public static int[] getAsDropDownXY(View view, int itemHeight, int windowHeight, int popHeight) {
        int[] xy = new int[2];
        int[] location = new int[2];
        view.getLocationInWindow(location); //获取在当前窗口内的绝对坐标
        int viewY = location[1];
        int bottomY = windowHeight - viewY - itemHeight;
        int x = 0;
        int y = 0;
        if (bottomY < popHeight) {
            x = -250;
            y = -160 + (bottomY - popHeight);
        } else {
            x = -250;
            y = -160;
        }
        xy[0] = x;
        xy[1] = y;
        return xy;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @SuppressLint("SimpleDateFormat")
    public static long convertYYMMDDtoSeconds(String time) {
        String sDt = time;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt2 = null;
        long lTime = 0;
        try {
            dt2 = sdf.parse(sDt);
            lTime = dt2.getTime() / 1000;
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return lTime;
    }

    public static int getLocalVersion(Context context) {
        PackageManager manager;
        PackageInfo info = null;
        manager = context.getPackageManager();
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info.versionCode;
    }

    public static String getLocalVersionName(Context context) {
        PackageManager manager;
        PackageInfo info = null;
        manager = context.getPackageManager();
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info.versionName;
    }

    public static boolean ExistSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else
            return false;
    }

    @SuppressLint("SimpleDateFormat")
    public static String convertSecondsToYYMM(long seconds) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = "";
        try {
            time = sdf.format(new Date(seconds * 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    @SuppressLint("SimpleDateFormat")
    public static String convertMillisToYYMM(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = "";
        try {
            time = sdf.format(new Date(millis));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    @SuppressLint("SimpleDateFormat")
    public static String convertSecondsToYYYYMMDD(long seconds) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd");
        String time = "";
        try {
            time = sdf.format(new Date(seconds * 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    @SuppressLint("SimpleDateFormat")
    public static String convertMillisToMMDD(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        String time = "";
        try {
            time = sdf.format(new Date(millis));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    @SuppressLint("SimpleDateFormat")
    public static String convertMillisToMM(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat("M");
        String time = "";
        try {
            time = sdf.format(new Date(millis));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    @SuppressLint("SimpleDateFormat")
    public static String convertSecondsToYYYYMMDDAll(long begin, long end) {
        SimpleDateFormat currentSdf = new SimpleDateFormat("yyyy年MM月dd日");
        String beginYear = currentSdf.format(new Date(begin * 1000));
        String endYear = currentSdf.format(new Date(end * 1000));
        String time = beginYear + "～" + endYear;
        if (beginYear.equals(endYear)) {
            time = beginYear;
        }
        return time;
    }

    @SuppressLint("SimpleDateFormat")
    public static String convertSecondsToYYYYMMDDDate(long date) {
        SimpleDateFormat currentSdf = new SimpleDateFormat("yyyy年MM月dd日");
        String beginYear = currentSdf.format(new Date(date * 1000));
        String time = beginYear;
        return time;
    }

    @SuppressLint("SimpleDateFormat")
    public static String convertSecondsToYYYYMMDD(long begin, long end) {
        SimpleDateFormat currentSdf = new SimpleDateFormat("yyyy");
        String currentYear = currentSdf.format(new Date(System.currentTimeMillis()));
        String beginYear = currentSdf.format(new Date(begin * 1000));
        String endYear = currentSdf.format(new Date(end * 1000));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");
        if (currentYear.equals(beginYear) && currentYear.equals(endYear)) {
            sdf = new SimpleDateFormat("M月d日");
        }
        String time = "";
        try {
            String beginTime = sdf.format(new Date(begin * 1000));
            String endTime = sdf.format(new Date(end * 1000));
            if (beginTime.equals(endTime)) {
                time = beginTime;
            } else {
                time = beginTime + "～" + endTime;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    @SuppressLint("SimpleDateFormat")
    public static String convertToYYYYMMDD(long seconds) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        String time = "";
        try {
            time = sdf.format(new Date(seconds * 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    @SuppressLint("SimpleDateFormat")
    public static long convertToYesterday(long seconds) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long time = 0;
        try {
            String yesStr = sdf.format(new Date(seconds));
            time = convertYYMMDDtoBeginSeconds(yesStr) - 86400;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    public static String getFromBase64(String s) {
        String result = "";
        try {
            byte b[] = Base64.decode(s, Base64.DEFAULT);
            result = new String(b);
        } catch (Exception e) {
        }
        return result;
    }

    public static long convertYYMMDDtoBeginSeconds(String time) {
        String sDt = time;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt2 = null;
        long lTime = 0;
        try {
            dt2 = sdf.parse(sDt);
            lTime = dt2.getTime() / 1000;
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return lTime;
    }

    public static long convertYYMMDDtoEndSeconds(String time) {
        String sDt = time;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt2 = null;
        long lTime = 0;
        try {
            dt2 = sdf.parse(sDt);
            lTime = dt2.getTime() / 1000;
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return lTime;
    }

    @SuppressLint("SimpleDateFormat")
    public static String convertSecondsToYYMMDD(long seconds) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String time = "";
        try {
            time = sdf.format(new Date(seconds * 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    @SuppressLint("SimpleDateFormat")
    public static String convertSecondsToYYMMDDUNIT(long seconds) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");
        String time = "";
        try {
            time = sdf.format(new Date(seconds * 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    public static long getMinTime() {
        String sDt = "2014-01-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt2 = null;
        long lTime = 0;
        try {
            dt2 = sdf.parse(sDt);
            lTime = dt2.getTime();
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return lTime;
    }

}

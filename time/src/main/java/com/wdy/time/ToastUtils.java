package com.wdy.time;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Tongmeng on 2017/7/24.
 */

public class ToastUtils {

    public static void showToast(Context context, String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_LONG).show();
    }

}

package com.butterknife;

import android.app.Activity;
import android.view.View;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/05/11
 */
public class Utils {
    public static <T extends View> T findViewById(Activity activity, int viewId) {
        return activity.findViewById(viewId);
    }
}

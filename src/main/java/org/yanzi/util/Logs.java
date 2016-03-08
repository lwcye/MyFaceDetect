package org.yanzi.util;

import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import org.yanzi.activity.BaseApplication;

/**
 * Log帮助类
 * Created by lwc on 2015/12/26.
 */
public class Logs {
    static BaseApplication mApplication = BaseApplication.getInstance();
    private static boolean isDebug = true;
    private static String TAG = "lwc";

    /**
     * log.v输出
     * @param string 输出字符
     */
    public static void v(Object string) {
        if (isDebug)
            Log.v(TAG, "" + string);
    }

    /**
     * log.i输出
     * @param string 输出字符
     */
    public static void i(Object string) {
        if (isDebug)
            Log.i(TAG, "" + string);
    }
    /**
     * log.e输出
     * @param string 输出字符
     */
    public static void e(Object string) {
        if (isDebug)
            Log.e(TAG, "" + string);
    }
    /**
     * log.e输出,待class名的
     * @param string 输出字符
     */
    public static void e(Class aClass, Object string) {
        Logs.e(aClass.getName() + ":" + string);
    }

    /**
     * 不定参数的log
     *
     * @param className 当前class
     * @param args      输出参数 按args[0]=args[1],
     */
    public static void e(Class className, Object... args) {
        if (isDebug) {
            StringBuilder sb = new StringBuilder();
            int size = args.length;
            for (int i = 0; i < size; i++) {
                if (i % 2 == 0) {
                    sb.append(args[i]).append("=");
                } else {
                    sb.append(args[i]).append(",");
                }
            }
            Logs.e(className, sb);
        }
    }
    /**
     * Toast弹出。时间为短
     * @param string 输出字符
     */
    public static void ts(Object string) {
        Toast toast= Toast.makeText(mApplication, "" + string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 10);
        toast.show();
    }
}

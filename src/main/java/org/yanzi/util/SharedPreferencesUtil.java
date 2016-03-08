package org.yanzi.util;

import android.content.Context;

import org.yanzi.Config.ShareConstants;
import org.yanzi.activity.BaseApplication;


/**
 * 储存xml简单数据
 * Created by lwc on 2015/12/11.
 */
public class SharedPreferencesUtil {
    private android.content.SharedPreferences share;
    private static SharedPreferencesUtil SharedPreferences;

    private SharedPreferencesUtil() {
        BaseApplication mApplication = BaseApplication.getInstance();
        share = mApplication.getSharedPreferences(ShareConstants.PREFERENTNAME, Context.MODE_PRIVATE);
    }

    public boolean contains(String paramString) {
        return share.contains(paramString);
    }

    public static SharedPreferencesUtil getInstance() {
        if (SharedPreferences == null) {
            SharedPreferences = new SharedPreferencesUtil();
        }
        return SharedPreferences;
    }

    public boolean getBoolean(String paramString) {
        return share.getBoolean(paramString, false);
    }

    public int getInt(String paramString) {
        return share.getInt(paramString, -1);
    }

    public String getString(String paramString) {
        return share.getString(paramString, null);
    }

    public long getLong(String paramString) {
        return share.getLong(paramString, -1);
    }

    public void put(String Key, String value) {
        android.content.SharedPreferences.Editor localEditor = share.edit();
        localEditor.putString(Key, value);
        localEditor.apply();
    }


    public void putBoolean(String Key, Boolean value) {
        android.content.SharedPreferences.Editor localEditor = share.edit();
        localEditor.putBoolean(Key, value);
        localEditor.apply();
    }

    public void putInt(String Key, int value) {
        android.content.SharedPreferences.Editor localEditor = share.edit();
        localEditor.putInt(Key, value);
        localEditor.apply();
    }
    public void putLong(String Key, long value) {
        android.content.SharedPreferences.Editor localEditor = share.edit();
        localEditor.putLong(Key, value);
        localEditor.apply();
    }
}

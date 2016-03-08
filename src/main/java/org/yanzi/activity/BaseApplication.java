package org.yanzi.activity;

import android.app.Application;

import com.google.gson.Gson;

import org.json.JSONObject;
import org.yanzi.Config.ShareConstants;
import org.yanzi.mode.Group;
import org.yanzi.util.DetectUtil;
import org.yanzi.util.SharedPreferencesUtil;
import org.yanzi.util.TimeUtil;


/**
 * 这个类的作用
 * Created by lwc on 2016/2/22.
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;
    //保存数据
    public static Group group=new Group();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //所有的工具的初始化
        init();
    }

    /**
     * 所有工具的初始化
     */
    private void init() {
        if (!SharedPreferencesUtil.getInstance().getBoolean(ShareConstants.IS_FACE_SET)) {
            DetectUtil.faceset_create(new DetectUtil.DetectCallBack() {
                @Override
                public void success(JSONObject jsonObject) {
                    SharedPreferencesUtil.getInstance().putBoolean(ShareConstants.IS_FACE_SET, true);
                    SharedPreferencesUtil.getInstance().put(ShareConstants.FACE_SET_TIME, TimeUtil.getInstance().getCurrentLongTime() + "");
                }
            });
        }
        if (!SharedPreferencesUtil.getInstance().getBoolean(ShareConstants.IS_GROUP_SET)) {

            DetectUtil.group_create(new DetectUtil.DetectCallBack() {
                @Override
                public void success(JSONObject jsonObject) {
                    Gson gson = new Gson();
                    Group group = gson.fromJson(jsonObject.toString(), Group.class);
                    SharedPreferencesUtil.getInstance().putBoolean(ShareConstants.IS_GROUP_SET, true);
                    SharedPreferencesUtil.getInstance().put(ShareConstants.GROUP_SET_TIME, TimeUtil.getInstance().getCurrentLongTime() + "");
                    SharedPreferencesUtil.getInstance().putInt(ShareConstants.GROUP_SET_NUMS, group.getAdded_person());
                    SharedPreferencesUtil.getInstance().put(ShareConstants.GROUP_SET_ID, group.getGroup_id());
                }
            });
        }
    }

    /**
     * BaseApplication
     * 获得Application的实例,可以一次运行中保持不变化的数据
     *
     * @return BaseApplication
     */
    public static BaseApplication getInstance() {
        return instance;
    }
}

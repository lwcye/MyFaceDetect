package org.yanzi.util;

import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.yanzi.Config.ShareConstants;
import org.yanzi.activity.MainActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {
    private static final String TAG = "FileUtil";
    private static final File parentPath = Environment.getExternalStorageDirectory();
    private static String storagePath = "";
    private static final String DST_FOLDER_NAME = "PlayCamera";

    /**
     * 初始化路径
     *
     * @return
     */
    private static String initPath() {
        if (storagePath.equals("")) {
            storagePath = parentPath.getAbsolutePath() + "/" + DST_FOLDER_NAME;
            File f = new File(storagePath);
            if (!f.exists()) {
                f.mkdir();
            }
        }
        return storagePath;
    }

    /**
     * bitmap保存到Sdcard上
     *
     * @param b
     */
    public static void saveBitmap(Bitmap b) {

        String path = initPath();
        long dataTake = System.currentTimeMillis();
        final String jpegName = path + "/" + dataTake + ".jpg";
        Log.e(TAG, "saveBitmap:jpegName = " + jpegName);
        try {
            FileOutputStream fout = new FileOutputStream(jpegName);
            BufferedOutputStream bos = new BufferedOutputStream(fout);
            b.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
            Log.e(TAG, "saveBitmap成功");
            Logs.ts("保存图片成功");
            DetectUtil.group_get_info();
            SystemClock.sleep(1000);
            if (SharedPreferencesUtil.getInstance().getInt(ShareConstants.GROUP_SET_NUMS) <= 0) {
                DetectUtil.detection_detect(jpegName, new DetectUtil.DetectCallBack() {
                    @Override
                    public void success(JSONObject jsonObject) {
                        JSONArray face = null;
                        try {
                            face = jsonObject.getJSONArray("face");
                            if (face.length() <= 0) {
                                return;
                            }
                            for (int i = 0; i < face.length(); i++) {
                                jsonObject = (JSONObject) face.get(i);
                                DetectUtil.person_create(jsonObject.getString("face_id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                DetectUtil.recognition_identify(jpegName, new DetectUtil.recognitionIdentifyCallback() {
                    @Override
                    public void success(double confidence, String personId, String faceId, int position) {
                        boolean isSave = false;
                        if (confidence < MainActivity.confidence) {
                            isSave = true;
                        }
                        if (isSave) {
                            DetectUtil.person_create(faceId);
                        } else {
                            DetectUtil.person_add_face(faceId, personId);
                        }
                        Looper.prepare();
                        Logs.ts("这是检测到的第" + (position + 1) + "张人脸,最高相似度:" + confidence + "%," + (isSave ? "保存" : "不保存"));
                        Looper.loop();// 进入loop中的循环，查看消息队列
                    }
                });
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e(TAG, "saveBitmap:失败");
            e.printStackTrace();
        }

    }


}

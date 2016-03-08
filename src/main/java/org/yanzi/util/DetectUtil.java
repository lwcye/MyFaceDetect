package org.yanzi.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.yanzi.Config.ShareConstants;
import org.yanzi.activity.BaseApplication;
import org.yanzi.mode.Group;

import java.io.ByteArrayOutputStream;

/**
 * Detect++网络请求类
 * Created by lwc on 2016/2/22.
 */
public class DetectUtil {
    private static final String apiKey = "01e7d0d1df1cf0c982cec483499942ea";
    private static final String apiSecret = "msuvF1FePeoms_l6WEXZtZAsdMR9BPde";

    public interface DetectCallBack {
        void success(JSONObject jsonObject);
    }

    public static void baseHttp(final DetectCallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpRequests requests = new HttpRequests(apiKey, apiSecret, true, true);
                    PostParameters parameters = new PostParameters();
                    JSONObject object = requests.detectionDetect(parameters);
                    Logs.e(getClass(), "object", object);
                    if (callBack != null) {
                        callBack.success(object);
                    }
                } catch (FaceppParseException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void detection_detect(final Bitmap bitmap, final DetectCallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpRequests requests = new HttpRequests(apiKey, apiSecret, true, true);
                    Bitmap bmSmall = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmSmall.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] arrays = stream.toByteArray();
                    PostParameters parameters = new PostParameters();
                    parameters.setImg(arrays);
                    Logs.e(getClass(), "start");
                    JSONObject object = requests.detectionDetect(parameters);
                    Logs.e(getClass(), "object", object);
                    if (callBack != null) {
                        callBack.success(object);
                    }
                } catch (FaceppParseException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void detection_detect(final String filePath, final DetectCallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpRequests requests = new HttpRequests(apiKey, apiSecret, true, true);
                    Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                    Bitmap bmSmall = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmSmall.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] arrays = stream.toByteArray();
                    PostParameters parameters = new PostParameters();
                    parameters.setImg(arrays);
                    Logs.e(getClass(), "file start");
                    JSONObject object = requests.detectionDetect(parameters);
                    Logs.e(getClass(), "file object", object);
                    if (callBack != null) {
                        callBack.success(object);
                    }
                } catch (FaceppParseException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void detection_detect(final byte[] data, final DetectCallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpRequests requests = new HttpRequests(apiKey, apiSecret, true, true);
                    PostParameters parameters = new PostParameters();
                    parameters.setImg(data);
                    Logs.e(getClass(), "data start");
                    JSONObject object = requests.detectionDetect(parameters);
                    Logs.e(getClass(), "data object", object);
                    if (callBack != null) {
                        callBack.success(object);
                    }
                } catch (FaceppParseException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void recognition_identify(final String filePath,final recognitionIdentifyCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpRequests requests = new HttpRequests(apiKey, apiSecret, true, true);
                    Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                    Bitmap bmSmall = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmSmall.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] arrays = stream.toByteArray();
                    PostParameters parameters = new PostParameters();
                    parameters.setImg(arrays);
                    parameters.setGroupId(SharedPreferencesUtil.getInstance().getString(ShareConstants.GROUP_SET_ID));
                    JSONObject object = requests.recognitionIdentify(parameters);
                    Logs.e(getClass(), "recognition_identify", object);
                    double confidence;
                    String faceId;
                    String personId;
                    if (callback!=null){
                        JSONArray array=object.getJSONArray("face");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject face=new JSONObject();
                            face=array.getJSONObject(i);
                            JSONArray candidate= face.getJSONArray("candidate");
                            confidence= candidate.getJSONObject(0).getDouble("confidence");
                            personId= candidate.getJSONObject(0).getString("person_id");
                            faceId=face.getString("face_id");
                            callback.success(confidence,personId,faceId,i);
                        }
                    }
                } catch (FaceppParseException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public interface recognitionIdentifyCallback {
        void success(double confidence,String personId,String faceId,int position);
    }
    public static void person_create(final String faceId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpRequests requests = new HttpRequests(apiKey, apiSecret, true, true);
                    PostParameters parameters = new PostParameters();
                    parameters.setPersonName(TimeUtil.getInstance().getCurrentLongTime() + "");
                    parameters.setFaceId(faceId);
                    parameters.setGroupId(SharedPreferencesUtil.getInstance().getString(ShareConstants.GROUP_SET_ID));
                    Logs.e(getClass(), "person_create start");
                    JSONObject object = requests.personCreate(parameters);
                    Logs.e(getClass(), "person_create", object);
                    train_identify(SharedPreferencesUtil.getInstance().getString(ShareConstants.GROUP_SET_ID));
                    Looper.prepare();
                    Logs.ts("建立人物成功");
                    Looper.loop();// 进入loop中的循环，查看消息队列
                } catch (FaceppParseException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public static void person_add_face(final String faceId,final String personId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpRequests requests = new HttpRequests(apiKey, apiSecret, true, true);
                    PostParameters parameters = new PostParameters();
                    parameters.setPersonId(personId);
                    parameters.setFaceId(faceId);
                    JSONObject object = requests.personAddFace(parameters);
                    Logs.e(getClass(), "person_add_face", object);
                    train_verify(personId);
                    Looper.prepare();
                    Logs.ts("更新人物，添加face成功");
                    Looper.loop();// 进入loop中的循环，查看消息队列
                } catch (FaceppParseException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public static void faceset_create(final DetectCallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpRequests requests = new HttpRequests(apiKey, apiSecret, true, true);
                    PostParameters parameters = new PostParameters();
                    parameters.setFacesetName("2016年02月22日");
                    JSONObject object = requests.facesetCreate(parameters);
                    Logs.e(getClass(), "faceset_create", object);
                    if (callBack != null) {
                        callBack.success(object);
                    }
                } catch (FaceppParseException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void group_create(final DetectCallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpRequests requests = new HttpRequests(apiKey, apiSecret, true, true);
                    PostParameters parameters = new PostParameters();
                    parameters.setGroupName("2016年02月22日");
                    JSONObject object = requests.groupCreate(parameters);
                    Logs.e(getClass(), "group_create", object);
                    if (callBack != null) {
                        callBack.success(object);
                    }
                } catch (FaceppParseException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void group_get_info() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpRequests requests = new HttpRequests(apiKey, apiSecret, true, true);
                    PostParameters parameters = new PostParameters();
                    parameters.setGroupId(SharedPreferencesUtil.getInstance().getString(ShareConstants.GROUP_SET_ID));
                    JSONObject object = requests.groupGetInfo(parameters);
                    Logs.e(getClass(), "group_get_info", object);
                    Gson gson = new Gson();
                    BaseApplication.group = gson.fromJson(object.toString(), Group.class);
                    SharedPreferencesUtil.getInstance().putInt(ShareConstants.GROUP_SET_NUMS, BaseApplication.group.getPerson().size());
                    Logs.e(getClass(), "GROUP_SET_NUMS", SharedPreferencesUtil.getInstance().getInt(ShareConstants.GROUP_SET_NUMS));
                } catch (FaceppParseException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void train_verify(final String person_id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpRequests requests = new HttpRequests(apiKey, apiSecret, true, true);
                    PostParameters parameters = new PostParameters();
                    parameters.setPersonId(person_id);
                    JSONObject object = requests.trainVerify(parameters);
                    Logs.e(getClass(), "train_verify", object);
                } catch (FaceppParseException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void train_search(final String faceset_id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpRequests requests = new HttpRequests(apiKey, apiSecret, true, true);
                    PostParameters parameters = new PostParameters();
                    parameters.setFacesetId(faceset_id);
                    JSONObject object = requests.trainSearch(parameters);
                    Logs.e(getClass(), "train_search", object);
                } catch (FaceppParseException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void train_identify(final String group_id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpRequests requests = new HttpRequests(apiKey, apiSecret, true, true);
                    PostParameters parameters = new PostParameters();
                    parameters.setGroupId(group_id);
                    JSONObject object = requests.trainIdentify(parameters);
                    Logs.e(getClass(), "train_identify", object);
                } catch (FaceppParseException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

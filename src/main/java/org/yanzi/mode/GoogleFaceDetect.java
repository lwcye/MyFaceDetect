package org.yanzi.mode;

import android.hardware.Camera;
import android.hardware.Camera.Face;
import android.hardware.Camera.FaceDetectionListener;
import android.os.Handler;
import android.os.Message;

import org.yanzi.util.EventUtil;

public class GoogleFaceDetect implements FaceDetectionListener {
	private Handler mHander;
	public GoogleFaceDetect(Handler handler){
		mHander = handler;
	}
	@Override
	public void onFaceDetection(Face[] faces, Camera camera) {

//		Log.i(TAG, "onFaceDetection...");
		if(faces != null){
			Message m = mHander.obtainMessage();
			m.what = EventUtil.UPDATE_FACE_RECT;
			m.obj = faces;
			m.sendToTarget();
		}
	}
	
/*	private Rect getPropUIFaceRect(Rect r){
		Log.i(TAG, "�������  = " + r.flattenToString());
		Matrix m = new Matrix();
		boolean mirror = false;
		m.setScale(mirror ? -1 : 1, 1);
		Point p = DisplayUtil.getScreenMetrics(mContext);
		int uiWidth = p.x;
		int uiHeight = p.y;
		m.postScale(uiWidth/2000f, uiHeight/2000f);
		int leftNew = (r.left + 1000)*uiWidth/2000;
		int topNew = (r.top + 1000)*uiHeight/2000;
		int rightNew = (r.right + 1000)*uiWidth/2000;
		int bottomNew = (r.bottom + 1000)*uiHeight/2000;
		
		return new Rect(leftNew, topNew, rightNew, bottomNew);
	}*/

}

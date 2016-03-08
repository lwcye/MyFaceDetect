package org.yanzi.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class ImageUtil {

	/**
	 * ��תBitmap
	 * @param b
	 * @param rotateDegree
	 * @return
	 */
	public static Bitmap getRotateBitmap(Bitmap b, float rotateDegree){
		Matrix matrix = new Matrix();
		matrix.postRotate((float)rotateDegree);
		Bitmap rotaBitmap = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), matrix, false);
		return rotaBitmap;
	}

	/**
	 * 根据路径压缩照片
	 */
	public static Bitmap resizeImage(String filePath) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;

		BitmapFactory.decodeFile(filePath,options);
		double ratio =Math.max(options.outWidth * 1.0d/1024f,options.outHeight*1.0d/1024f);
		options.inSampleSize= (int) Math.ceil(ratio);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filePath,options);
	}
	/**
	 * 根据2进制压缩照片
	 */
	public static Bitmap resizeImage(byte[] data) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;

		BitmapFactory.decodeByteArray(data,0,data.length,options);
		double ratio =Math.max(options.outWidth * 1.0d/1024f,options.outHeight*1.0d/1024f);
		Logs.e(ImageUtil.class,"options.outWidth",options.outWidth,"options.outHeight",options.outHeight,"options.outWidth * 1.0d/1024f",options.outWidth * 1.0d/1024f,"options.outHeight*1.0d/1024f",options.outHeight*1.0d/1024f,"ratio",ratio);
		options.inSampleSize= (int) Math.ceil(ratio);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeByteArray(data,0,data.length,options);
	}
}

package com.gyiscas.bbsmp.formlibraryforandroid.Utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.gyiscas.bbsmp.formlibraryforandroid.R;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	/**
	 * 判断邮箱
	 */
	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * 将一个字符串转化为输入流
	 */
	public static InputStream getStringStream(String sInputString) {
		if (sInputString != null && !sInputString.trim().equals("")) {
			try {
				ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
				return tInputStringStream;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 将一个输入流转化为字符串
	 */
	public static String getStreamString(InputStream tInputStream) {
		if (tInputStream != null) {
			try {
				BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(tInputStream));
				StringBuffer tStringBuffer = new StringBuffer();
				String sTempOneLine = new String("");
				while ((sTempOneLine = tBufferedReader.readLine()) != null) {
					tStringBuffer.append(sTempOneLine.trim());
				}
				return tStringBuffer.toString().trim();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 验证是否是手机号码
	 *
	 * @param str
	 * @return
	 */
	public static boolean isMobile(String str) {
		Pattern pattern = Pattern.compile("1[0-9]{10}");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}


	/**
	 * Toast弹出对话框
	 *
	 * @param context
	 * @param info
	 */
	public static void toastLong(Context context, String info) {
		Toast.makeText(context, info, Toast.LENGTH_LONG).show();
	}

	public static void toastShort(Context context, String info) {
		Toast.makeText(context, info, Toast.LENGTH_LONG).show();
	}


	/**
	 * 分割字符串
	 *
	 * @param res
	 * @param rex
	 * @return
	 */
	public static String spiltString(String res, String rex) {
		String temp[] = res.split(rex);
		return temp[0];
	}


	/**
	 * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
	 *
	 * @param v
	 * @param event
	 * @return
	 */
	public static boolean isShouldHideInput(View v, MotionEvent event) {
		if (v != null && (v instanceof EditText)) {
			int[] l = {0, 0};
			v.getLocationInWindow(l);
			int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
					+ v.getWidth();
			return !(event.getX() > left && event.getX() < right
					&& event.getY() > top && event.getY() < bottom);
		}
		// 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
		return false;
	}

	/**
	 * 多种隐藏软件盘方法的其中一种
	 *
	 * @param token
	 */
	public static void hideSoftInput(Context context, IBinder token) {
		if (token != null) {
			InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			im.hideSoftInputFromWindow(token,
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}




	/**
	 * 动态获取listview高度
	 *
	 * @param listView
	 * @return
	 */
	public static int getTotalHeightofListView(ListView listView) {
		ListAdapter mAdapter = listView.getAdapter();
		if (mAdapter == null) {
			return 0;
		}
		int totalHeight = 0;
		for (int i = 0; i < mAdapter.getCount(); i++) {
			try {
				View mView = mAdapter.getView(i, null, listView);
				mView.measure(
						View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
						View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
				totalHeight += mView.getMeasuredHeight();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return totalHeight + (listView.getDividerHeight() * (mAdapter.getCount() - 1));
	}

	/**
	 * 动态获取Gridview高度
	 *
	 * @param gridView
	 * @return
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public static int getTotalHeightofListView(GridView gridView) {
		ListAdapter mAdapter = gridView.getAdapter();
		if (mAdapter == null) {
			return 0;
		}
		int totalHeight = 0;
		int rows = mAdapter.getCount() / 2;
		int s = mAdapter.getCount() % 2;
		if (s > 0) {
			rows = rows + 1;
		}
		for (int i = 0; i < rows; i++) {
			try {
				View mView = mAdapter.getView(i, null, gridView);
				mView.measure(
						View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
						View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
				totalHeight += mView.getMeasuredHeight();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return totalHeight + (gridView.getVerticalSpacing() * rows);
	}

	public static Object changeBooleanToObject(boolean b) {
		String s = new String("false");
		if (b) {
			s = new String("true");
		}
		return s;
	}

	public static boolean changeStringObjectToBoolean(Object s) {
		boolean b = false;
		if (s != null && "true".equals(s.toString())) {
			b = true;
		}
		return b;
	}

	public static int getWordCount(String s) {

		s = s.replaceAll("[^\\x00-\\xff]", "**");
		int length = s.length();
		return length;
	}


	public static String byte2hex(byte[] b) // 二进制转字符串
	{
		StringBuffer sb = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1) {
				sb.append("0" + stmp);
			} else {
				sb.append(stmp);
			}

		}
		return sb.toString();
	}


	/**
	 * bitmap转为base64
	 *
	 * @param bitmap
	 * @return
	 */
	public static String bitmapToBase64(Bitmap bitmap) {

		String result = null;
		ByteArrayOutputStream baos = null;
		try {
			if (bitmap != null) {
				baos = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
				baos.flush();
				baos.close();
				result = Utils.byte2hex(baos.toByteArray());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null) {
					baos.flush();
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


	public String imageToString(String filePath) {
		// 将图片转换成字符串
		File imgFile = new File(filePath);
		FileInputStream fis = null;
		byte[] bytes = null;
		try {
			fis = new FileInputStream(imgFile);
			bytes = new byte[fis.available()];
			fis.read(bytes);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Utils.byte2hex(bytes);
	}


	public static String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState()
				.equals(Environment.MEDIA_MOUNTED); //判断sd卡是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();//获取跟目录
		}
		return sdDir.toString();
	}


	public static boolean fileIsExists(String filePath) {
		try {
			File f = new File(filePath);
			if (!f.exists()) {
				return false;
			}

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}


	/**
	 * ImageView 回收
	 *
	 * @param imageView
	 */
	public static void releaseImageViewResouce(ImageView imageView) {
		if (imageView == null) return;
		Drawable drawable = imageView.getDrawable();
		if (drawable != null && drawable instanceof BitmapDrawable) {
			BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
			Bitmap bitmap = bitmapDrawable.getBitmap();
			if (bitmap != null && !bitmap.isRecycled()) {
				bitmap.recycle();
			}

		}
	}
}

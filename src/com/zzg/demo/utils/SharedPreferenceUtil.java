/**
 * 
 */
package com.zzg.demo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author acer Descrption:TODO WHAT 2015-4-9 下午3:02:15 用于存储用户系统设置信息
 */
public class SharedPreferenceUtil {

	private SharedPreferences mSharedPreferences;
	private static SharedPreferences.Editor editor;

	private String shareInfo = "_zzg_demo_IM";

	public SharedPreferenceUtil(Context context, String userInfo) {
		mSharedPreferences = context.getSharedPreferences(
				userInfo += shareInfo, Context.MODE_PRIVATE);
		editor = mSharedPreferences.edit();
	}

	private final String SHARE_NOTIFY = "shared_key_notify";
	private final String SHARE_VOICE = "shared_key_voice";
	private final String VIBRATE = "shared_key_vibrate";

	/**
	 * 允许接受通知
	 */
	public boolean isNotifyAllowed() {
		// TODO Auto-generated method stub
		return mSharedPreferences.getBoolean(SHARE_NOTIFY, true);
	}
	
	/**
	 * 设置接受通知
	 */
	public void setNotifyEnable(boolean isCheck) {
		// TODO Auto-generated method stub
		editor.putBoolean(SHARE_NOTIFY, isCheck);
		editor.commit();
	}

	/**
	 * 允许有声音
	 */
	public boolean isVoiceAllowed() {
		// TODO Auto-generated method stub
		return mSharedPreferences.getBoolean(SHARE_VOICE, true);
	}
	/**
	 * 设置声音
	 */
	public void setVoiceEnable(boolean isCheck) {
		// TODO Auto-generated method stub
		editor.putBoolean(SHARE_VOICE, isCheck);
		editor.commit();
	}
	/**
	 * 允许有震动
	 */
	public boolean isVibrateAllowed() {
		// TODO Auto-generated method stub
		return mSharedPreferences.getBoolean(VIBRATE, true);
	}
	/**
	 * 设置震动
	 */
	public void setVibrateEnable(boolean isCheck) {
		// TODO Auto-generated method stub
		editor.putBoolean(VIBRATE, isCheck);
		editor.commit();
	}
}

package com.zzg.demo.ui;

import com.zzg.demo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

/**
 * @author acer Descrption:TODO WHAT 2015-4-8 下午3:02:25
 */
public class SplashActivity extends FragmentActivity {

	final int GOTO_MAINACTIVITY = 100;
	final int GOTO_LOGINACTIVITY = 200;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		// 判断是否登录并发送message
		if (checkLogin()) {
			mHandler.sendEmptyMessageDelayed(GOTO_MAINACTIVITY, 2000);// what 和
																		// 延时时间
		} else {
			mHandler.sendEmptyMessageDelayed(GOTO_LOGINACTIVITY, 2000);// what 和
																		// 延时时间
		}
	}

	/**
	 * 判断是否已经登录
	 */
	private boolean checkLogin() {
		// TODO Auto-generated method stub
		return true;
	}

	Handler mHandler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			// 处理跳转
			switch (msg.what) {
			// 跳转到主页面
			case GOTO_MAINACTIVITY:
				startActivity(new Intent(SplashActivity.this,
						MainActivity.class));
				finish();
				break;
			// 跳转到登录页面
			case GOTO_LOGINACTIVITY:
				startActivity(new Intent(SplashActivity.this,
						LoginActivity.class));
				finish();
				break;
			default:
				break;
			}

		};
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}

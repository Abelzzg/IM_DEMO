package com.zzg.demo.ui;

import com.zzg.demo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

/**
 * @author acer Descrption:TODO WHAT 2015-4-8 ����3:02:25
 */
public class SplashActivity extends FragmentActivity {

	final int GOTO_MAINACTIVITY = 100;
	final int GOTO_LOGINACTIVITY = 200;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		// �ж��Ƿ��¼������message
		if (checkLogin()) {
			mHandler.sendEmptyMessageDelayed(GOTO_MAINACTIVITY, 2000);// what ��
																		// ��ʱʱ��
		} else {
			mHandler.sendEmptyMessageDelayed(GOTO_LOGINACTIVITY, 2000);// what ��
																		// ��ʱʱ��
		}
	}

	/**
	 * �ж��Ƿ��Ѿ���¼
	 */
	private boolean checkLogin() {
		// TODO Auto-generated method stub
		return true;
	}

	Handler mHandler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			// ������ת
			switch (msg.what) {
			// ��ת����ҳ��
			case GOTO_MAINACTIVITY:
				startActivity(new Intent(SplashActivity.this,
						MainActivity.class));
				finish();
				break;
			// ��ת����¼ҳ��
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

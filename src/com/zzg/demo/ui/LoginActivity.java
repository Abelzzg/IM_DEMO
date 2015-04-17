/**
 * 
 */
package com.zzg.demo.ui;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.zzg.demo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author acer Descrption:TODO WHAT 2015-4-8 ����3:34:09
 */
@ContentView(value = R.layout.activity_login)
public class LoginActivity extends FragmentActivity {

	/**
	 * �û���
	 */
	@ViewInject(value = R.id.et_username)
	EditText et_username;

	/**
	 * ����
	 */
	@ViewInject(value = R.id.et_password)
	EditText et_password;

	/**
	 * ��¼
	 */
	@ViewInject(value = R.id.btn_login)
	Button btn_login;

	/**
	 * ΢����¼
	 */
	@ViewInject(value = R.id.ib_weibo)
	TextView ib_weibo;

	/**
	 * QQ��¼
	 */
	@ViewInject(value = R.id.ib_tencent)
	TextView ib_tencent;

	/**
	 * ����ע��
	 */
	@ViewInject(value = R.id.btn_register)
	TextView btn_register;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		ViewUtils.inject(this);
		init();
	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void init() {

	}

	/**
	 * 
	 */
	@OnClick(value = { R.id.btn_login, R.id.btn_register })
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.btn_login:
			// ���������Ϣ
			if (checkInfo()) {
				// ��¼�¼�
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.btn_register:
			// ע���¼�
			Intent intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	/**
	 * ����¼��Ϣ�Ƿ���ȷ
	 * 
	 * @return
	 */
	private boolean checkInfo() {
		// TODO Auto-generated method stub
		return false;
	}

}

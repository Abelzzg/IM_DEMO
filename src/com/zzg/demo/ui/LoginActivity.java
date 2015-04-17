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
 * @author acer Descrption:TODO WHAT 2015-4-8 下午3:34:09
 */
@ContentView(value = R.layout.activity_login)
public class LoginActivity extends FragmentActivity {

	/**
	 * 用户名
	 */
	@ViewInject(value = R.id.et_username)
	EditText et_username;

	/**
	 * 密码
	 */
	@ViewInject(value = R.id.et_password)
	EditText et_password;

	/**
	 * 登录
	 */
	@ViewInject(value = R.id.btn_login)
	Button btn_login;

	/**
	 * 微博登录
	 */
	@ViewInject(value = R.id.ib_weibo)
	TextView ib_weibo;

	/**
	 * QQ登录
	 */
	@ViewInject(value = R.id.ib_tencent)
	TextView ib_tencent;

	/**
	 * 立即注册
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
	 * 初始化控件
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
			// 检查输入信息
			if (checkInfo()) {
				// 登录事件
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.btn_register:
			// 注册事件
			Intent intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	/**
	 * 检查登录信息是否正确
	 * 
	 * @return
	 */
	private boolean checkInfo() {
		// TODO Auto-generated method stub
		return false;
	}

}

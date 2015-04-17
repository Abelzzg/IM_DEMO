/**
 * 
 */
package com.zzg.demo.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.EditText;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.zzg.demo.R;

/**
 * @author acer Descrption:TODO WHAT 2015-4-17 下午2:22:19
 */
@ContentView(value = R.layout.activity_register)
public class RegisterActivity extends FragmentActivity {
	
	/**
	 * 用戶名
	 */
	@ViewInject(value = R.id.et_username)
	EditText et_username;
	
	/**
	 * 密碼
	 */
	@ViewInject(value = R.id.et_password)
	EditText et_password;
	
	/**
	 * 邮件
	 */
	@ViewInject(value = R.id.et_email)
	EditText et_email;
	
	/**
	 * 注册
	 */
	@ViewInject(value = R.id.btn_register)
	Button btn_register;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		ViewUtils.inject(this);
	}
}

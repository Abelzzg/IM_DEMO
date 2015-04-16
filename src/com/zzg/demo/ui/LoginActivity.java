/**
 * 
 */
package com.zzg.demo.ui;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.zzg.demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author acer Descrption:TODO WHAT 2015-4-8 ÏÂÎç3:34:09
 */
@ContentView(value = R.layout.activity_login)
public class LoginActivity extends FragmentActivity {

	/**
	 * ÓÃ»§Ãû
	 */
	@ViewInject(value = R.id.et_username)
	EditText et_username;
	/**
	 * ÃÜÂë
	 */
	@ViewInject(value = R.id.et_password)
	EditText et_password;
	
	/**
	 * Î¢²©µÇÂ¼
	 */
	@ViewInject(value = R.id.ib_weibo)
	TextView ib_weibo;
	
	/**
	 * QQµÇÂ¼
	 */
	@ViewInject(value = R.id.ib_tencent)
	TextView ib_tencent;

	/**
	 * Á¢¼´×¢²á
	 */
	@ViewInject(value = R.id.btn_register)
	TextView btn_register;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		ViewUtils.inject(this);
	}

}

/**
 * 
 */
package com.zzg.demo.ui;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.zzg.demo.R;
import com.zzg.demo.ui.BlackListActivity.OnLeftButtonClickListener;
import com.zzg.demo.view.HeaderLayout;
import com.zzg.demo.view.HeaderLayout.LeftButtonOnclickListener;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author acer Descrption:TODO WHAT 2015-4-13 下午3:48:44
 */
@ContentView(value = R.layout.activity_set_info)
public class PersonInfoActivity extends FragmentActivity {

	// @ViewInject(value = R.id.common_actionbar)
	HeaderLayout headerLayout;

	/**
	 * 发起聊天
	 */
	@ViewInject(value = R.id.btn_chat)
	Button btn_Chat;

	/**
	 * 添加到黑名单
	 */
	@ViewInject(value = R.id.btn_black)
	Button btn_Black;

	/**
	 * 已添加到黑名单
	 */
	@ViewInject(value = R.id.layout_black_tips)
	RelativeLayout black_tips;

	/**
	 * 昵称
	 */
	@ViewInject(value = R.id.tv_set_nick)
	TextView tv_nick;

	/**
	 * 账号
	 */
	@ViewInject(value = R.id.tv_set_name)
	TextView tv_name;
	
	/**
	 * 性别
	 */
	@ViewInject(value = R.id.tv_set_gender)
	TextView tv_gender;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		ViewUtils.inject(this);
		// setContentView(R.layout.activity_set_info);
		String from = getIntent().getStringExtra("from");
		initView(from);
	}

	// 左边按钮的点击事件
	public class OnLeftButtonClickListener implements LeftButtonOnclickListener {
		@Override
		public void onClick() {
			finish();
		}
	}

	/**
	 * 根据传进来的“from”来判断是用户自己信息还是他人信息
	 */
	private void initView(String from) {
		headerLayout = (HeaderLayout) findViewById(R.id.common_actionbar);
		// 如果是自己资料
		if (from.equals("me")) {
			headerLayout.init(R.drawable.base_action_bar_back_bg_selector,
					"个人资料", null, new OnLeftButtonClickListener(), null);
			// 隐藏下面的按钮
			btn_Chat.setVisibility(View.GONE);
			btn_Black.setVisibility(View.GONE);
		}
		// 如果是他人资料
		else {
			headerLayout.init(R.drawable.base_action_bar_back_bg_selector,
					"详细资料", null, new OnLeftButtonClickListener(), null);
			// 如果已经在黑名单，显示“已添加至黑名单，你将不再收到对方的消息!”
			if (isInBlackList()) {
				btn_Black.setVisibility(View.GONE);
				black_tips.setVisibility(View.VISIBLE);
			} else {
				black_tips.setVisibility(View.GONE);
			}
		}
		// 设置个人信息
		tv_nick.setText("nickName");
		tv_gender.setText("男");
		tv_name.setText("userName");
	}

	/**
	 * @return
	 */
	private boolean isInBlackList() {

		return false;
	}
}

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
 * @author acer Descrption:TODO WHAT 2015-4-13 ����3:48:44
 */
@ContentView(value = R.layout.activity_set_info)
public class PersonInfoActivity extends FragmentActivity {

	// @ViewInject(value = R.id.common_actionbar)
	HeaderLayout headerLayout;

	/**
	 * ��������
	 */
	@ViewInject(value = R.id.btn_chat)
	Button btn_Chat;

	/**
	 * ��ӵ�������
	 */
	@ViewInject(value = R.id.btn_black)
	Button btn_Black;

	/**
	 * ����ӵ�������
	 */
	@ViewInject(value = R.id.layout_black_tips)
	RelativeLayout black_tips;

	/**
	 * �ǳ�
	 */
	@ViewInject(value = R.id.tv_set_nick)
	TextView tv_nick;

	/**
	 * �˺�
	 */
	@ViewInject(value = R.id.tv_set_name)
	TextView tv_name;
	
	/**
	 * �Ա�
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

	// ��߰�ť�ĵ���¼�
	public class OnLeftButtonClickListener implements LeftButtonOnclickListener {
		@Override
		public void onClick() {
			finish();
		}
	}

	/**
	 * ���ݴ������ġ�from�����ж����û��Լ���Ϣ����������Ϣ
	 */
	private void initView(String from) {
		headerLayout = (HeaderLayout) findViewById(R.id.common_actionbar);
		// ������Լ�����
		if (from.equals("me")) {
			headerLayout.init(R.drawable.base_action_bar_back_bg_selector,
					"��������", null, new OnLeftButtonClickListener(), null);
			// ��������İ�ť
			btn_Chat.setVisibility(View.GONE);
			btn_Black.setVisibility(View.GONE);
		}
		// �������������
		else {
			headerLayout.init(R.drawable.base_action_bar_back_bg_selector,
					"��ϸ����", null, new OnLeftButtonClickListener(), null);
			// ����Ѿ��ں���������ʾ������������������㽫�����յ��Է�����Ϣ!��
			if (isInBlackList()) {
				btn_Black.setVisibility(View.GONE);
				black_tips.setVisibility(View.VISIBLE);
			} else {
				black_tips.setVisibility(View.GONE);
			}
		}
		// ���ø�����Ϣ
		tv_nick.setText("nickName");
		tv_gender.setText("��");
		tv_name.setText("userName");
	}

	/**
	 * @return
	 */
	private boolean isInBlackList() {

		return false;
	}
}

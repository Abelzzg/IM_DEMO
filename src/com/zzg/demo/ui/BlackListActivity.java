/**
 * 
 */
package com.zzg.demo.ui;

import com.zzg.demo.R;
import com.zzg.demo.view.HeaderLayout;
import com.zzg.demo.view.HeaderLayout.LeftButtonOnclickListener;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * @author acer Descrption:TODO WHAT 2015-4-13 ����10:06:10
 */
public class BlackListActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_blacklist);
		initView();
	}

	HeaderLayout headerLayout;

	// ��߰�ť�ĵ���¼�
	public class OnLeftButtonClickListener implements LeftButtonOnclickListener {

		@Override
		public void onClick() {
			finish();
		}
	}

	/**
	 * 
	 */
	private void initView() {
		// header
		headerLayout = (HeaderLayout) findViewById(R.id.common_actionbar);
		headerLayout.init(R.drawable.base_action_bar_back_bg_selector, "������",
				null, new OnLeftButtonClickListener(), null);
		//listView��ʼ��
		
	}

}

/**
 * 
 */
package com.zzg.demo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zzg.demo.R;
import com.zzg.demo.fragment.ContactFragment;
import com.zzg.demo.fragment.SettingFragment;
import com.zzg.demo.fragment.TalkFragment;

/**
 * @author acer Descrption:TODO WHAT 2015-4-8 下午3:33:08
 */
public class MainActivity extends FragmentActivity {
	private Button[] mTabs;// 主页下的三个按钮（会话、联系人、设置）
	private ImageView iv_recent_tips, iv_contact_tips;// 消息提示
	private ContactFragment contactFragment;// 联系人
	private SettingFragment settingFragment;// 设置
	private TalkFragment talkFragment;// 会话
	private Fragment[] fragments;
	private int index;
	private int currentTabIndex;// 现在显示的frament

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initTabs();// 初始化主页按钮
		initFragments();// 初始化三个按钮对应的三个Fragment
	}

	/**
	 * 初始化三个按钮对应的三个Fragment
	 */
	private void initFragments() {
		// TODO Auto-generated method stub
		contactFragment = new ContactFragment();
		settingFragment = new SettingFragment();
		talkFragment = new TalkFragment();

		fragments = new Fragment[] { talkFragment, contactFragment,
				settingFragment };
		// 同时加入两个frament,但是默认显示talkFragment
		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_container, talkFragment)
				.add(R.id.fragment_container, contactFragment)
				.hide(contactFragment).show(talkFragment).commit();
		currentTabIndex = 0;// 设置当前fragment下标
	}

	/**
	 * 初始化主页按钮
	 */
	private void initTabs() {
		// TODO Auto-generated method stub
		mTabs = new Button[3];
		mTabs[0] = (Button) findViewById(R.id.btn_message);// 会话
		mTabs[1] = (Button) findViewById(R.id.btn_contract);// 联系人
		mTabs[2] = (Button) findViewById(R.id.btn_set);// 设置

		iv_recent_tips = (ImageView) findViewById(R.id.iv_msg_tips);// 会话小红点
		iv_contact_tips = (ImageView) findViewById(R.id.iv_contact_tips);// 联系人小红点

		// 默认第一个会话按钮被选中
		mTabs[0].setSelected(true);
	}

	/**
	 * button点击事件
	 * 
	 * @param view
	 */
	public void onTabSelect(View view) {
		switch (view.getId()) {
		case R.id.btn_message:
			index = 0;
			break;
		case R.id.btn_contract:
			index = 1;
			break;
		case R.id.btn_set:
			index = 2;
			break;
		}
		// 如果点击的button不是现在显示的
		if (currentTabIndex != index) {
			FragmentTransaction trx = getSupportFragmentManager()
					.beginTransaction();
			trx.hide(fragments[currentTabIndex]);
			// fragments对应下标的frament加入到FramentTrasaction
			if (!fragments[index].isAdded()) {
				trx.add(R.id.fragment_container, fragments[index]);
			}
			trx.show(fragments[index]).commit();
		}
		// 把点击之前的按钮设置未被选中状态
		mTabs[currentTabIndex].setSelected(false);
		// 把当前tab设为选中状态
		mTabs[index].setSelected(true);
		currentTabIndex = index;
	}

}

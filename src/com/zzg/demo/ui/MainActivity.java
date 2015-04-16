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
 * @author acer Descrption:TODO WHAT 2015-4-8 ����3:33:08
 */
public class MainActivity extends FragmentActivity {
	private Button[] mTabs;// ��ҳ�µ�������ť���Ự����ϵ�ˡ����ã�
	private ImageView iv_recent_tips, iv_contact_tips;// ��Ϣ��ʾ
	private ContactFragment contactFragment;// ��ϵ��
	private SettingFragment settingFragment;// ����
	private TalkFragment talkFragment;// �Ự
	private Fragment[] fragments;
	private int index;
	private int currentTabIndex;// ������ʾ��frament

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initTabs();// ��ʼ����ҳ��ť
		initFragments();// ��ʼ��������ť��Ӧ������Fragment
	}

	/**
	 * ��ʼ��������ť��Ӧ������Fragment
	 */
	private void initFragments() {
		// TODO Auto-generated method stub
		contactFragment = new ContactFragment();
		settingFragment = new SettingFragment();
		talkFragment = new TalkFragment();

		fragments = new Fragment[] { talkFragment, contactFragment,
				settingFragment };
		// ͬʱ��������frament,����Ĭ����ʾtalkFragment
		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_container, talkFragment)
				.add(R.id.fragment_container, contactFragment)
				.hide(contactFragment).show(talkFragment).commit();
		currentTabIndex = 0;// ���õ�ǰfragment�±�
	}

	/**
	 * ��ʼ����ҳ��ť
	 */
	private void initTabs() {
		// TODO Auto-generated method stub
		mTabs = new Button[3];
		mTabs[0] = (Button) findViewById(R.id.btn_message);// �Ự
		mTabs[1] = (Button) findViewById(R.id.btn_contract);// ��ϵ��
		mTabs[2] = (Button) findViewById(R.id.btn_set);// ����

		iv_recent_tips = (ImageView) findViewById(R.id.iv_msg_tips);// �ỰС���
		iv_contact_tips = (ImageView) findViewById(R.id.iv_contact_tips);// ��ϵ��С���

		// Ĭ�ϵ�һ���Ự��ť��ѡ��
		mTabs[0].setSelected(true);
	}

	/**
	 * button����¼�
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
		// ��������button����������ʾ��
		if (currentTabIndex != index) {
			FragmentTransaction trx = getSupportFragmentManager()
					.beginTransaction();
			trx.hide(fragments[currentTabIndex]);
			// fragments��Ӧ�±��frament���뵽FramentTrasaction
			if (!fragments[index].isAdded()) {
				trx.add(R.id.fragment_container, fragments[index]);
			}
			trx.show(fragments[index]).commit();
		}
		// �ѵ��֮ǰ�İ�ť����δ��ѡ��״̬
		mTabs[currentTabIndex].setSelected(false);
		// �ѵ�ǰtab��Ϊѡ��״̬
		mTabs[index].setSelected(true);
		currentTabIndex = index;
	}

}

/**
 * 
 */
package com.zzg.demo.fragment;

import com.zzg.demo.R;
import com.zzg.demo.ui.BlackListActivity;
import com.zzg.demo.ui.LoginActivity;
import com.zzg.demo.ui.PersonInfoActivity;
import com.zzg.demo.utils.SharedPreferenceUtil;
import com.zzg.demo.view.HeaderLayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author acer Descrption:TODO WHAT 2015-4-8 ����4:07:09
 */
public class SettingFragment extends Fragment implements OnClickListener {

	SharedPreferenceUtil mSPUtil;

	String userName = "zzg";

	/**
	 * ͷ����
	 */
	private HeaderLayout mHeaderLayout;

	/**
	 * ������
	 */
	private RelativeLayout layout_blacklist;

	/**
	 * ��������
	 */
	private RelativeLayout layout_info;

	private TextView tv_name;

	/**
	 * ����
	 */
	private RelativeLayout rl_switch_voice;
	/**
	 * ��
	 */
	private RelativeLayout rl_switch_vibrate;
	/**
	 * ����֪ͨ
	 */
	private RelativeLayout rl_switch_notify;

	private ImageView open_notify;

	private ImageView close_notify;

	private ImageView open_voice;

	private ImageView close_voice;

	private ImageView open_vibrate;

	private ImageView close_vibrate;

	/**
	 * �˳���¼
	 */
	private Button btn_logout;

	private View view1;

	private View view2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_set, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initViews();
	}

	/**
	 * ��ʼ��fragment�ĸ��ֿؼ�
	 */
	private void initViews() {
		// TODO Auto-generated method stub
		// ��ʼ��actionBar
		initTopBarForOnlyTitle("����");
		// ��ʼ��listView���������ϣ��������ȸ������ã�
		layout_info = (RelativeLayout) findViewById(R.id.layout_info);//��������
		//�����û���
		tv_name = (TextView) findViewById(R.id.tv_set_name);
		tv_name.setText(userName);
		layout_blacklist = (RelativeLayout) findViewById(R.id.layout_blacklist);//������

		rl_switch_notify = (RelativeLayout) findViewById(R.id.rl_switch_notification);// ����֪ͨ
		rl_switch_voice = (RelativeLayout) findViewById(R.id.rl_switch_voice);// ����
		rl_switch_vibrate = (RelativeLayout) findViewById(R.id.rl_switch_vibrate);// ��

		// ���ü�����
		rl_switch_notify.setOnClickListener(this);
		rl_switch_voice.setOnClickListener(this);
		rl_switch_vibrate.setOnClickListener(this);
		layout_info.setOnClickListener(this);
		layout_blacklist.setOnClickListener(this);

		// ���ֿ���
		open_notify = (ImageView) findViewById(R.id.iv_open_notification);
		close_notify = (ImageView) findViewById(R.id.iv_close_notification);
		open_voice = (ImageView) findViewById(R.id.iv_open_voice);
		close_voice = (ImageView) findViewById(R.id.iv_close_voice);
		open_vibrate = (ImageView) findViewById(R.id.iv_open_vibrate);
		close_vibrate = (ImageView) findViewById(R.id.iv_close_vibrate);
		//�˳���¼
		btn_logout = (Button) findViewById(R.id.btn_logout);
		btn_logout.setOnClickListener(this);

		// �����ָ��ߣ������رս���֪ͨʱ��Ҫ���غ���ʾ
		view1 = (View) findViewById(R.id.view1);
		view2 = (View) findViewById(R.id.view2);

		// ��ʼ��ϵͳ���õ�״̬��������Ҫ��SharedPrefrence���洢ϵͳ���ò���
		mSPUtil = new SharedPreferenceUtil(getActivity(), userName);
		// �����������֪ͨ
		if (mSPUtil.isNotifyAllowed()) {
			openNotify();
			if (mSPUtil.isVoiceAllowed()) {
				openVoice();
			} else {
				closeVoice();
			}
			if (mSPUtil.isVibrateAllowed()) {
				openVibrate();
			} else {
				closeVibrate();
			}
		} else {
			closeNotify();
		}

	}

	/**
	 * ����
	 */
	private void closeVibrate() {
		open_vibrate.setVisibility(View.INVISIBLE);
		close_vibrate.setVisibility(View.VISIBLE);
		mSPUtil.setVibrateEnable(false);
	}

	/**
	 * ����
	 */
	private void openVibrate() {
		open_vibrate.setVisibility(View.VISIBLE);
		close_vibrate.setVisibility(View.INVISIBLE);
		mSPUtil.setVibrateEnable(true);
	}

	/**
	 * ������
	 */
	private void closeVoice() {
		open_voice.setVisibility(View.INVISIBLE);
		close_voice.setVisibility(View.VISIBLE);
		mSPUtil.setVoiceEnable(false);
	}

	/**
	 * ������
	 */
	private void openVoice() {
		open_voice.setVisibility(View.VISIBLE);
		close_voice.setVisibility(View.INVISIBLE);
		mSPUtil.setVibrateEnable(true);
	}

	/**
	 * ��֪ͨ
	 */
	private void closeNotify() {
		open_notify.setVisibility(View.INVISIBLE);
		close_notify.setVisibility(View.VISIBLE);
		view1.setVisibility(View.GONE);
		view2.setVisibility(View.GONE);
		rl_switch_voice.setVisibility(View.GONE);
		rl_switch_vibrate.setVisibility(View.GONE);
		mSPUtil.setNotifyEnable(false);
	}

	/**
	 * ��֪ͨ
	 */
	private void openNotify() {
		open_notify.setVisibility(View.VISIBLE);
		close_notify.setVisibility(View.INVISIBLE);
		view1.setVisibility(View.VISIBLE);
		view2.setVisibility(View.VISIBLE);
		rl_switch_voice.setVisibility(View.VISIBLE);
		rl_switch_vibrate.setVisibility(View.VISIBLE);
		mSPUtil.setNotifyEnable(true);
	}

	// fragment�в���ֱ��ʹ��findViewById����Ҫ����getView���������õ�activity
	public View findViewById(int paramInt) {
		return getView().findViewById(paramInt);
	}

	/**
	 * ��ʼ��ֻ���м�����header
	 * 
	 * @param titleName
	 */
	public void initTopBarForOnlyTitle(String titleName) {
		mHeaderLayout = (HeaderLayout) findViewById(R.id.common_actionbar);
		mHeaderLayout.init(null, titleName, null, null, null);
	}

	/*
	 * ����¼���ת����
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.layout_info:// ��������������ҳ��
			Intent intent = new Intent(getActivity(), PersonInfoActivity.class);
			intent.putExtra("from", "me");
			startActivity(intent);
			break;
		case R.id.layout_blacklist:// ������������ҳ��
			startActivity(new Intent(getActivity(), BlackListActivity.class));
			break;
		case R.id.rl_switch_notification:// ������Ϣ����
			if (open_notify.getVisibility() == View.VISIBLE) {
				closeNotify();
			} else {
				openNotify();
			}
			break;
		case R.id.rl_switch_voice:// ����
			if (open_notify.getVisibility() == View.VISIBLE) {
				if (open_voice.getVisibility() == View.VISIBLE) {
					closeVoice();
				} else {
					openVoice();
				}
			}
			break;
		case R.id.rl_switch_vibrate:// ��
			if (open_notify.getVisibility() == View.VISIBLE) {
				if (open_vibrate.getVisibility() == View.VISIBLE) {
					closeVibrate();
				} else {
					openVibrate();
				}
			}
			break;
		case R.id.btn_logout:// �˳���¼
			getActivity().finish();
			startActivity(new Intent(getActivity(), LoginActivity.class));
			break;
		}
	}

}

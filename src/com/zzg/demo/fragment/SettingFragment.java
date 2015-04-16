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
 * @author acer Descrption:TODO WHAT 2015-4-8 下午4:07:09
 */
public class SettingFragment extends Fragment implements OnClickListener {

	SharedPreferenceUtil mSPUtil;

	String userName = "zzg";

	/**
	 * 头布局
	 */
	private HeaderLayout mHeaderLayout;

	/**
	 * 黑名单
	 */
	private RelativeLayout layout_blacklist;

	/**
	 * 个人资料
	 */
	private RelativeLayout layout_info;

	private TextView tv_name;

	/**
	 * 声音
	 */
	private RelativeLayout rl_switch_voice;
	/**
	 * 震动
	 */
	private RelativeLayout rl_switch_vibrate;
	/**
	 * 接受通知
	 */
	private RelativeLayout rl_switch_notify;

	private ImageView open_notify;

	private ImageView close_notify;

	private ImageView open_voice;

	private ImageView close_voice;

	private ImageView open_vibrate;

	private ImageView close_vibrate;

	/**
	 * 退出登录
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
	 * 初始化fragment的各种控件
	 */
	private void initViews() {
		// TODO Auto-generated method stub
		// 初始化actionBar
		initTopBarForOnlyTitle("设置");
		// 初始化listView（个人资料，黑名单等各种设置）
		layout_info = (RelativeLayout) findViewById(R.id.layout_info);//个人资料
		//设置用户名
		tv_name = (TextView) findViewById(R.id.tv_set_name);
		tv_name.setText(userName);
		layout_blacklist = (RelativeLayout) findViewById(R.id.layout_blacklist);//黑名单

		rl_switch_notify = (RelativeLayout) findViewById(R.id.rl_switch_notification);// 接受通知
		rl_switch_voice = (RelativeLayout) findViewById(R.id.rl_switch_voice);// 声音
		rl_switch_vibrate = (RelativeLayout) findViewById(R.id.rl_switch_vibrate);// 震动

		// 设置监听器
		rl_switch_notify.setOnClickListener(this);
		rl_switch_voice.setOnClickListener(this);
		rl_switch_vibrate.setOnClickListener(this);
		layout_info.setOnClickListener(this);
		layout_blacklist.setOnClickListener(this);

		// 各种开关
		open_notify = (ImageView) findViewById(R.id.iv_open_notification);
		close_notify = (ImageView) findViewById(R.id.iv_close_notification);
		open_voice = (ImageView) findViewById(R.id.iv_open_voice);
		close_voice = (ImageView) findViewById(R.id.iv_close_voice);
		open_vibrate = (ImageView) findViewById(R.id.iv_open_vibrate);
		close_vibrate = (ImageView) findViewById(R.id.iv_close_vibrate);
		//退出登录
		btn_logout = (Button) findViewById(R.id.btn_logout);
		btn_logout.setOnClickListener(this);

		// 两根分割线，开启关闭接受通知时需要隐藏和显示
		view1 = (View) findViewById(R.id.view1);
		view2 = (View) findViewById(R.id.view2);

		// 初始化系统设置的状态，这里需要用SharedPrefrence来存储系统设置参数
		mSPUtil = new SharedPreferenceUtil(getActivity(), userName);
		// 如果开启接受通知
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
	 * 关震动
	 */
	private void closeVibrate() {
		open_vibrate.setVisibility(View.INVISIBLE);
		close_vibrate.setVisibility(View.VISIBLE);
		mSPUtil.setVibrateEnable(false);
	}

	/**
	 * 开震动
	 */
	private void openVibrate() {
		open_vibrate.setVisibility(View.VISIBLE);
		close_vibrate.setVisibility(View.INVISIBLE);
		mSPUtil.setVibrateEnable(true);
	}

	/**
	 * 关声音
	 */
	private void closeVoice() {
		open_voice.setVisibility(View.INVISIBLE);
		close_voice.setVisibility(View.VISIBLE);
		mSPUtil.setVoiceEnable(false);
	}

	/**
	 * 开声音
	 */
	private void openVoice() {
		open_voice.setVisibility(View.VISIBLE);
		close_voice.setVisibility(View.INVISIBLE);
		mSPUtil.setVibrateEnable(true);
	}

	/**
	 * 关通知
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
	 * 开通知
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

	// fragment中不能直接使用findViewById，需要先用getView（）方法得到activity
	public View findViewById(int paramInt) {
		return getView().findViewById(paramInt);
	}

	/**
	 * 初始化只有中间标题的header
	 * 
	 * @param titleName
	 */
	public void initTopBarForOnlyTitle(String titleName) {
		mHeaderLayout = (HeaderLayout) findViewById(R.id.common_actionbar);
		mHeaderLayout.init(null, titleName, null, null, null);
	}

	/*
	 * 点击事件跳转处理
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.layout_info:// 启动到个人资料页面
			Intent intent = new Intent(getActivity(), PersonInfoActivity.class);
			intent.putExtra("from", "me");
			startActivity(intent);
			break;
		case R.id.layout_blacklist:// 启动到黑名单页面
			startActivity(new Intent(getActivity(), BlackListActivity.class));
			break;
		case R.id.rl_switch_notification:// 接受消息提醒
			if (open_notify.getVisibility() == View.VISIBLE) {
				closeNotify();
			} else {
				openNotify();
			}
			break;
		case R.id.rl_switch_voice:// 声音
			if (open_notify.getVisibility() == View.VISIBLE) {
				if (open_voice.getVisibility() == View.VISIBLE) {
					closeVoice();
				} else {
					openVoice();
				}
			}
			break;
		case R.id.rl_switch_vibrate:// 震动
			if (open_notify.getVisibility() == View.VISIBLE) {
				if (open_vibrate.getVisibility() == View.VISIBLE) {
					closeVibrate();
				} else {
					openVibrate();
				}
			}
			break;
		case R.id.btn_logout:// 退出登录
			getActivity().finish();
			startActivity(new Intent(getActivity(), LoginActivity.class));
			break;
		}
	}

}

/**
 * 
 */
package com.zzg.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.zzg.demo.bean.User;
import com.zzg.demo.utils.CustomDialog;
import com.zzg.demo.utils.CustomDialog.CallBack;

/**
 * @author acer Descrption:TODO WHAT 2015-4-10 ����4:48:55
 */
public class MyApplication extends Application {

	public static MyApplication mInstance;
	private SDKReceiver mReceiver;
	public LocationClient mLocationClient = null;

	private List<User> users = new ArrayList<User>();

	public User user;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mInstance = this;
		SDKInitializer.initialize(this);
		// ע�� SDK �㲥������
		IntentFilter iFilter = new IntentFilter();
		iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
		iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
		mReceiver = new SDKReceiver();
		registerReceiver(mReceiver, iFilter);
	}

	/**
	 * ��ʱ�ȴ��ֻ���ϵ����ȡ��ϵ��
	 */
	public List<User> getUsers() {
		User user = new User("zhangsan", "123456789", "123456789@qq.com");
		users.add(new User("B2222", "222222222", "123456@qq.com"));
		users.add(new User("b2222", "222222222", "123456@qq.com"));
		users.add(new User("��û��", "222222222", "123456@qq.com"));
		users.add(new User("����û��", "222222222", "123456@qq.com"));
		users.add(new User("����û��", "222222222", "123456@qq.com"));
		users.add(new User("a1111", "123456789", "123456@qq.com"));
		users.add(new User("A2222", "222222222", "123456@qq.com"));
		return users;
	}

	/**
	 * 
	 */
	public static MyApplication getInstance() {
		// TODO Auto-generated method stub
		return mInstance;
	}

	/**
	 * ����㲥�����࣬���� SDK key ��֤�Լ������쳣�㲥
	 */
	public class SDKReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			CustomDialog customDialog = new CustomDialog(context);
			String s = intent.getAction();
			if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
				customDialog.createWarnDialog("��ʾ",
						"key ��֤����! ���� AndroidManifest.xml �ļ��м�� key ����",
						new CallBack() {

							@Override
							public void isConfirm(DialogInterface arg0,
									boolean flag) {
								// TODO Auto-generated method stub
								arg0.dismiss();
							}
						});
			} else if (s
					.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
				customDialog.createWarnDialog("��ʾ", "�������", new CallBack() {

					@Override
					public void isConfirm(DialogInterface arg0, boolean flag) {
						// TODO Auto-generated method stub
						arg0.dismiss();
					}
				});
			}
		}
	}

	/**
	 * @return
	 * 
	 */
	public BDLocation getLocation() {
		// TODO Auto-generated method stub
		return user.getLocation();
	}

	public class MyLocationListener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return;
			StringBuffer sb = new StringBuffer(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getLocType());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			sb.append("\nradius : ");
			sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation) {
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
			}
			System.out.println("��λ��λ��======��" + sb.toString());
		}
	}
}

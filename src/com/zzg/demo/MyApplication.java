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
import com.baidu.mapapi.model.LatLng;
import com.zzg.demo.bean.User;
import com.zzg.demo.utils.CustomDialog;
import com.zzg.demo.utils.CustomDialog.CallBack;

/**
 * @author acer Descrption:TODO WHAT 2015-4-10 下午4:48:55
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
		// 注册 SDK 广播监听者
		IntentFilter iFilter = new IntentFilter();
		iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
		iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
		mReceiver = new SDKReceiver();
		registerReceiver(mReceiver, iFilter);
	}

	/**
	 * 暂时先从手机联系人中取联系人
	 */
	public List<User> getUsers() {
		BDLocation location = new BDLocation();
		location.setLatitude(39.963175);
		location.setLongitude(116.400244);
		
		BDLocation location1 = new BDLocation();
		location1.setLatitude(39.942821);
		location1.setLongitude(116.369199);
		
		BDLocation location2 = new BDLocation();
		location2.setLatitude(39.939723);
		location2.setLongitude(116.425541);
		
		BDLocation location3 = new BDLocation();
		location3.setLatitude(39.963175);
		location3.setLongitude(116.400244);
		
		BDLocation location4 = new BDLocation();
		location4.setLatitude(39.906965);
		location4.setLongitude(116.400244);
		
		BDLocation location5 = new BDLocation();
		location5.setLatitude(39.963175);
		location5.setLongitude(116.400244);
		
		BDLocation location6 = new BDLocation();
		location6.setLatitude(39.942821);
		location6.setLongitude(116.400244);
		
		BDLocation location7 = new BDLocation();
		location7.setLatitude(39.942821);
		location7.setLongitude(116.425541);
		
		
		
		User user = new User("zhangsan", "123456789", "123456789@qq.com",location);
		users.add(new User("B2222", "222222222", "123456@qq.com",location1));
		users.add(new User("b2222", "222222222", "123456@qq.com",location2));
		users.add(new User("在没变", "222222222", "123456@qq.com",location3));
		users.add(new User("波在没变", "222222222", "123456@qq.com",location4));
		users.add(new User("啊在没变", "222222222", "123456@qq.com",location5));
		users.add(new User("a1111", "123456789", "123456@qq.com",location6));
		users.add(new User("A2222", "222222222", "123456@qq.com",location7));
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
	 * 构造广播监听类，监听 SDK key 验证以及网络异常广播
	 */
	public class SDKReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			CustomDialog customDialog = new CustomDialog(context);
			String s = intent.getAction();
			if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
				customDialog.createWarnDialog("提示",
						"key 验证出错! 请在 AndroidManifest.xml 文件中检查 key 设置",
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
				customDialog.createWarnDialog("提示", "网络出错", new CallBack() {

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
			System.out.println("定位的位置======》" + sb.toString());
		}
	}
}

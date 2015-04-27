/**
 * 
 */
package com.zzg.demo.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.zzg.demo.MyApplication;
import com.zzg.demo.R;
import com.zzg.demo.base.BaseMapActivity;
import com.zzg.demo.bean.User;

/**
 * @author acer Descrption:TODO WHAT 2015-4-14 下午2:18:11
 */
public class NearFriendsActivity extends BaseMapActivity {

	private Marker mMarker;//

	List<User> users = MyApplication.getInstance().getUsers();// 得到所有用户

	BitmapDescriptor bdA = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_gcoding);

	private InfoWindow mInfoWindow;// 信息展示栏

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置地图中marker标记点击处理监听器
		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			public boolean onMarkerClick(Marker marker) {
				User user = (User) markerMap.get(marker);// marker对应的user 哈哈哈哈
				LatLng position = marker.getPosition();// 得到点击的marker的坐标
				Button button = new Button(getApplicationContext());

				// 根据点击不同marker显示不同的infowindow
				button.setText(user.getName());

				OnInfoWindowClickListener listener = null;// 信息展示栏点击事件
				listener = new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick() {
						// 打开用户对应的信息页
						Intent intent = new Intent();
						intent.putExtra("from", "other");
						startActivity(intent);
					}
				};
				/**
				 * mInfoWindow marker点击之后显示的信息窗 bd - InfoWindow 展示的bitmap
				 * position - InfoWindow 显示的地理位置 yOffset - InfoWindow Y 轴偏移量
				 * listener - InfoWindow 点击监听者
				 */
				mInfoWindow = new InfoWindow(BitmapDescriptorFactory
						.fromView(button), position, -47, listener);// 1.控件 2.位置
																	// 3.偏移量(一般往左偏移，所以是负数)
				mBaiduMap.showInfoWindow(mInfoWindow);// 显示到地图上

				return false;
			}
		});
	}

	Map markerMap = new HashMap();
	BitmapDescriptor bitmap;
	Marker[] markers = new Marker[users.size()];// 对应每一个marker
	OverlayOptions[] options = new OverlayOptions[users.size()];

	/**
	 * Map map 中存放user 和 user于“我”的距离
	 * 
	 * @return 排序完成的list
	 * 
	 */
	private List<Entry<User, Double>> sort(Map map) {
		// TODO Auto-generated method stub
		List<Map.Entry<User, Double>> list_Data = new ArrayList<Map.Entry<User, Double>>(
				map.entrySet());

//		Collections.sort(list_Data, new Comparator<Map.Entry<User, Double>>() {
//			
//			public int compare(Map.Entry<User, Double> o1,
//					Map.Entry<User, String> o2) {
//				double d2 = Double.valueOf(o2.getValue());
//				double d1 = Double.valueOf(o1.getValue());
//				if (o2.getValue() != null && o1.getValue() != null
//						&& o2.getValue().compareTo(o1.getValue()) > 0) {
//					return 1;
//				} else {
//					return -1;
//				}
//			}
//		});
		return list_Data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zzg.demo.base.BaseMapActivity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		bitmap.recycle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zzg.demo.base.BaseMapActivity#initOverLay(com.baidu.location.BDLocation
	 * )
	 */
	@Override
	public void initOverLay(BDLocation location) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Map map = new HashMap<User, Integer>();
		Map distanceMap = new HashMap();
		double[] distances = new double[users.size()];
		
		bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			BDLocation userlocation = user.getLocation();
			LatLng userLatLng = new LatLng(userlocation.getLatitude(),
					userlocation.getLongitude());
			LatLng myLatLng = new LatLng(location.getLatitude(),
					location.getLongitude());
			distances[i] = DistanceUtil.getDistance(userLatLng, myLatLng);
			distanceMap.put(user, distances[i]);
			
			//设置覆盖图
			System.out.println("坐标"+i+"==========>>>>>>"+user.getLocation().getLatitude()+"||||"+user
					.getLocation().getLongitude());
			// 定义Maker坐标点
			LatLng point = new LatLng(user.getLocation().getLatitude(), user
					.getLocation().getLongitude());
			options[i] = new MarkerOptions().position(point).icon(bitmap);
			// 在地图上添加Marker，并显示
			markers[i] = (Marker) mBaiduMap.addOverlay(options[i]);// 显示到地图上
			markers[i].setTitle(user.getName());
			markerMap.put(markers[i], user);
		}
		List<Entry<User, Double>> userlist = sort(distanceMap);

		
		// 由进到远设置覆盖图
//		for (int i = 0; i < userlist.size(); i++) {
//			User user = userlist.get(i).getKey();
//			map.put(user, i);
//
//			System.out.println("坐标"+i+"==========>>>>>>"+user.getLocation().getLatitude()+"||||"+user
//					.getLocation().getLongitude());
//			// 定义Maker坐标点
//			LatLng point = new LatLng(user.getLocation().getLatitude(), user
//					.getLocation().getLongitude());
//			// 构建MarkerOption，用于在地图上添加Marker
//			options[i] = new MarkerOptions().position(point).icon(bitmap);
//			// 在地图上添加Marker，并显示
//			markers[i] = (Marker) mBaiduMap.addOverlay(options[i]);// 显示到地图上
//			markers[i].setTitle(user.getName());
//			markerMap.put(markers[i], user);
//		}
	}
}

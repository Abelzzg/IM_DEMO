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
 * @author acer Descrption:TODO WHAT 2015-4-14 ����2:18:11
 */
public class NearFriendsActivity extends BaseMapActivity {

	private Marker mMarker;//

	List<User> users = MyApplication.getInstance().getUsers();// �õ������û�

	BitmapDescriptor bdA = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_gcoding);

	private InfoWindow mInfoWindow;// ��Ϣչʾ��

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���õ�ͼ��marker��ǵ�����������
		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			public boolean onMarkerClick(Marker marker) {
				User user = (User) markerMap.get(marker);// marker��Ӧ��user ��������
				LatLng position = marker.getPosition();// �õ������marker������
				Button button = new Button(getApplicationContext());

				// ���ݵ����ͬmarker��ʾ��ͬ��infowindow
				button.setText(user.getName());

				OnInfoWindowClickListener listener = null;// ��Ϣչʾ������¼�
				listener = new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick() {
						// ���û���Ӧ����Ϣҳ
						Intent intent = new Intent();
						intent.putExtra("from", "other");
						startActivity(intent);
					}
				};
				/**
				 * mInfoWindow marker���֮����ʾ����Ϣ�� bd - InfoWindow չʾ��bitmap
				 * position - InfoWindow ��ʾ�ĵ���λ�� yOffset - InfoWindow Y ��ƫ����
				 * listener - InfoWindow ���������
				 */
				mInfoWindow = new InfoWindow(BitmapDescriptorFactory
						.fromView(button), position, -47, listener);// 1.�ؼ� 2.λ��
																	// 3.ƫ����(һ������ƫ�ƣ������Ǹ���)
				mBaiduMap.showInfoWindow(mInfoWindow);// ��ʾ����ͼ��

				return false;
			}
		});
	}

	Map markerMap = new HashMap();
	BitmapDescriptor bitmap;
	Marker[] markers = new Marker[users.size()];// ��Ӧÿһ��marker
	OverlayOptions[] options = new OverlayOptions[users.size()];

	/**
	 * Map map �д��user �� user�ڡ��ҡ��ľ���
	 * 
	 * @return ������ɵ�list
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
			
			//���ø���ͼ
			System.out.println("����"+i+"==========>>>>>>"+user.getLocation().getLatitude()+"||||"+user
					.getLocation().getLongitude());
			// ����Maker�����
			LatLng point = new LatLng(user.getLocation().getLatitude(), user
					.getLocation().getLongitude());
			options[i] = new MarkerOptions().position(point).icon(bitmap);
			// �ڵ�ͼ�����Marker������ʾ
			markers[i] = (Marker) mBaiduMap.addOverlay(options[i]);// ��ʾ����ͼ��
			markers[i].setTitle(user.getName());
			markerMap.put(markers[i], user);
		}
		List<Entry<User, Double>> userlist = sort(distanceMap);

		
		// �ɽ���Զ���ø���ͼ
//		for (int i = 0; i < userlist.size(); i++) {
//			User user = userlist.get(i).getKey();
//			map.put(user, i);
//
//			System.out.println("����"+i+"==========>>>>>>"+user.getLocation().getLatitude()+"||||"+user
//					.getLocation().getLongitude());
//			// ����Maker�����
//			LatLng point = new LatLng(user.getLocation().getLatitude(), user
//					.getLocation().getLongitude());
//			// ����MarkerOption�������ڵ�ͼ�����Marker
//			options[i] = new MarkerOptions().position(point).icon(bitmap);
//			// �ڵ�ͼ�����Marker������ʾ
//			markers[i] = (Marker) mBaiduMap.addOverlay(options[i]);// ��ʾ����ͼ��
//			markers[i].setTitle(user.getName());
//			markerMap.put(markers[i], user);
//		}
	}
}

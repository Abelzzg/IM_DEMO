/**
 * 
 */
package com.zzg.demo.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.zzg.demo.R;
import com.zzg.demo.base.BaseMapActivity;

/**
 * @author acer Descrption:TODO WHAT 2015-4-14 下午2:18:11
 */
public class NearFriendsActivity extends BaseMapActivity {
	protected BMapManager manager;// 地图的核心管理者
	protected MapController controller;// 地图的控制器
	protected MapView mapView;
	protected GeoPoint point;
	protected int latitude;
	protected int longitude;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
		System.out.println("getApplicationContext()===="+(getApplicationContext()==null));
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setAddrType("all");// 返回的定位结果包含地址信息
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(5000);// 设置发起定位请求的间隔时间为5000ms
		option.disableCache(true);// 禁止启用缓存定位
		option.setPoiNumber(5); // 最多返回POI个数
		option.setPoiDistance(1000); // poi查询距离
		option.setPoiExtraInfo(true); // 是否需要POI的电话和地址等详细信息
		mLocationClient.setLocOption(option);
		mLocationClient.registerLocationListener(myListener); // 注册监听函数
		mLocationClient.start();
		
		if (mLocationClient != null && mLocationClient.isStarted())
			mLocationClient.requestLocation();
		else 
			Log.d("LocSDK5", "locClient is null or not started");
	}

	public LocationClient mLocationClient = null;
	public BDLocationListener myListener = new MyLocationListener();

	public class MyLocationListener implements BDLocationListener {
		// 定位出了位置
		@Override
		public void onReceiveLocation(BDLocation location) {
			System.out.println("定位有返回值");
			LocationData data = new LocationData();
			data.latitude = location.getLatitude();
			data.longitude = location.getLongitude();
			MyLocationOverlay overlay = new MyLocationOverlay(mapView);

			overlay.setData(data);
			mapView.getOverlays().add(overlay);
			controller.setCenter(new GeoPoint(
					(int) (location.getLatitude() * 1E6), (int) (location
							.getLongitude() * 1E6)));

			mapView.refresh();
		}

		// 接受周围的兴趣点
		@Override
		public void onReceivePoi(BDLocation arg0) {

		}

	}
}

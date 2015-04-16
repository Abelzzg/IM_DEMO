/**
 * 
 */
package com.zzg.demo.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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
import com.zzg.demo.R;

/**
 * @author acer Descrption:TODO WHAT 2015-4-14 下午2:18:11
 */
public class NearFriendsActivity extends FragmentActivity {
	protected BMapManager manager;// 地图的核心管理者
	protected MapController controller;// 地图的控制器
	protected MapView mapView;
	protected GeoPoint point;
	protected int latitude;
	protected int longitude;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		manager = new BMapManager(getApplicationContext());
		// 校验key是否存在 是否合法
		manager.init(getResources().getString(R.string.key),
				new MKGeneralListener() {
					// API 校验返回信息
					@Override
					public void onGetPermissionState(int error) {
						if (error == MKEvent.ERROR_PERMISSION_DENIED) {
							Toast.makeText(getApplicationContext(), "百度服务器忙", 1)
									.show();
						}
					}

					// 联网反馈信息
					@Override
					public void onGetNetworkState(int error) {
						if (error == MKEvent.ERROR_NETWORK_CONNECT) {
							Toast.makeText(getApplicationContext(),
									"您的手机网络不太给力", 0).show();
						}
					}
				});
		setContentView(R.layout.activity_near);
		// 初始化mapView
		mapView = (MapView) findViewById(R.id.mv);
		mapView.setBuiltInZoomControls(true);// 显示内置缩放控件

		controller = mapView.getController();
		controller.setZoom(14);// 3-19

		latitude = (int) (40.051 * 1E6);
		longitude = (int) (116.303 * 1E6);
		point = new GeoPoint(latitude, longitude);
		controller.setCenter(point);

		controller.enableClick(true);// 点就会响应点击事件

		mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
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
	}

	@Override
	protected void onStart() {
		super.onStart();
		manager.start();
	}

	@Override
	protected void onStop() {
		super.onStop();
		manager.stop();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
		mLocationClient.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.destroy();
		manager.destroy();// 2.0版本不写没问题
	}

	protected class BaseSearchAdapter implements MKSearchListener {

		@Override
		public void onGetAddrResult(MKAddrInfo arg0, int arg1) {

		}

		@Override
		public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {

		}

		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult arg0, int arg1) {

		}

		@Override
		public void onGetPoiDetailSearchResult(int arg0, int arg1) {

		}

		@Override
		public void onGetPoiResult(MKPoiResult arg0, int arg1, int arg2) {

		}

		@Override
		public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {

		}

		@Override
		public void onGetTransitRouteResult(MKTransitRouteResult arg0, int arg1) {

		}

		@Override
		public void onGetWalkingRouteResult(MKWalkingRouteResult arg0, int arg1) {

		}

	}

	public LocationClient mLocationClient = null;
	public BDLocationListener myListener = new MyLocationListener();

	public class MyLocationListener implements BDLocationListener {
		// 定位出了位置
		@Override
		public void onReceiveLocation(BDLocation location) {

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

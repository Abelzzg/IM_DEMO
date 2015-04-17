package com.zzg.demo.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
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
import com.zzg.demo.ui.NearFriendsActivity.MyLocationListener;

public class BaseMapActivity extends FragmentActivity {
	protected BMapManager manager;// ��ͼ�ĺ��Ĺ�����
	protected MapController controller;// ��ͼ�Ŀ�����
	protected MapView mapView;
	protected GeoPoint point;
	protected int latitude;
	protected int longitude;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		manager = new BMapManager(getApplicationContext());
		// У��key�Ƿ���� �Ƿ�Ϸ�
		manager.init(getResources().getString(R.string.key),
				new MKGeneralListener() {
					// API У�鷵����Ϣ
					@Override
					public void onGetPermissionState(int error) {
						if (error == MKEvent.ERROR_PERMISSION_DENIED) {
							Toast.makeText(getApplicationContext(), "�ٶȷ�����æ", 1)
									.show();
						}
					}

					// ����������Ϣ
					@Override
					public void onGetNetworkState(int error) {
						if (error == MKEvent.ERROR_NETWORK_CONNECT) {
							Toast.makeText(getApplicationContext(),
									"�����ֻ����粻̫����", 0).show();
						}
					}
				});
		
		setContentView(R.layout.activity_near);
		// ��ʼ��mapView
		mapView = (MapView) findViewById(R.id.mv);
		mapView.setBuiltInZoomControls(true);// ��ʾ�������ſؼ�

		
		controller = mapView.getController();
		controller.setZoom(14);// 3-19

//		latitude = (int) (40.051 * 1E6);
//		longitude = (int) (116.303 * 1E6);
//		point = new GeoPoint(latitude, longitude);
//		controller.setCenter(point);
		
		controller.enableClick(true);// ��ͻ���Ӧ����¼�
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
		manager.destroy();// 2.0�汾��дû����
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
}

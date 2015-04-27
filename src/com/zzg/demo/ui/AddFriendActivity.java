/**
 * 
 */
package com.zzg.demo.ui;

import java.util.List;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.Marker;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.zzg.demo.MyApplication;
import com.zzg.demo.R;
import com.zzg.demo.bean.User;
import com.zzg.demo.view.ClearEditText;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * @author acer Descrption:TODO WHAT 2015-4-14 ÉÏÎç10:58:09
 */
@ContentView(value = R.layout.activity_add_contact)
public class AddFriendActivity extends FragmentActivity {

	
	
	@ViewInject(value = R.id.et_msg_search)
	ClearEditText et_msg_search;

	
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		ViewUtils.inject(this);
	}



	

}

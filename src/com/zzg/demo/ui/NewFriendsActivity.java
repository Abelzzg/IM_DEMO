/**
 * 
 */
package com.zzg.demo.ui;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.zzg.demo.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * @author acer Descrption:TODO WHAT 2015-4-14 обнГ2:15:54
 */
@ContentView(value = R.layout.activity_new_friend)
public class NewFriendsActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		ViewUtils.inject(this);
	}
}

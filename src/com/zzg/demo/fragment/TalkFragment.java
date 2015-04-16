/**
 * 
 */
package com.zzg.demo.fragment;

import com.zzg.demo.R;
import com.zzg.demo.view.HeaderLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author acer Descrption:TODO WHAT 2015-4-8 下午4:07:09
 */
public class TalkFragment extends Fragment {

	private HeaderLayout mHeaderLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_recent, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initTopBarForOnlyTitle("会话");
	}

	// fragment中不能直接使用findViewById，需要先用getView（）方法得到activity
	public View findViewById(int paramInt) {
		return getView().findViewById(paramInt);
	}

	public void initTopBarForOnlyTitle(String titleName) {
		mHeaderLayout = (HeaderLayout) findViewById(R.id.common_actionbar);
		mHeaderLayout.init(null, titleName, null, null, null);
	}

}

/**
 * 
 */
package com.zzg.demo.fragment;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Filter.FilterListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzg.demo.MyApplication;
import com.zzg.demo.R;
import com.zzg.demo.adapter.ContactAdapter;
import com.zzg.demo.bean.User;
import com.zzg.demo.ui.AddFriendActivity;
import com.zzg.demo.ui.NearFriendsActivity;
import com.zzg.demo.ui.NewFriendsActivity;
import com.zzg.demo.utils.CharacterParser;
import com.zzg.demo.utils.SortChineseName;
import com.zzg.demo.view.ClearEditText;
import com.zzg.demo.view.HeaderLayout;
import com.zzg.demo.view.HeaderLayout.RightButtonOnclickListener;
import com.zzg.demo.view.MyLetterView;
import com.zzg.demo.view.MyLetterView.OnTouchingLetterChangedListener;

/**
 * @author acer Descrption:TODO WHAT 2015-4-8 下午4:07:09
 */
public class ContactFragment extends Fragment implements OnItemClickListener,
		OnItemLongClickListener {

	/**
	 * header
	 */
	private HeaderLayout mHeaderLayout;

	/**
	 * 父类list
	 */
	RelativeLayout layout_list;

	/**
	 * 搜索框
	 */
	// @ViewInject(value = R.id.et_msg_search)
	ClearEditText et_msg_search;

	/**
	 * 联系人list
	 */
	// @ViewInject(value = R.id.list_friends)
	ListView list_friends;

	/**
	 * 屏幕中间大字母
	 */
	// @ViewInject(value = R.id.dialog)
	TextView dialog;

	MyLetterView right_letter;

	private LayoutInflater mInflater;

	/**
	 * 联系人列表适配器
	 */
	ContactAdapter contact;
	/**
	 * 新朋友 附近的人
	 */
	RelativeLayout headView;
	/**
	 * 新朋友布局
	 */
	LinearLayout layout_new;
	FrameLayout frame_new;
	/**
	 * 新朋友提示红点
	 */
	ImageView iv_msg_tips;
	/**
	 * 新朋友
	 */
	TextView tv_new_name;
	/**
	 * 附近的人
	 */
	LinearLayout layout_near;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_contacts, container,
				false);
		// ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initTopBarForOnlyTitle("联系人");
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		// 填充进来 新朋友 附近的人 并将其放入list中
		mInflater = LayoutInflater.from(getActivity());
		headView = (RelativeLayout) mInflater.inflate(
				R.layout.include_new_friend, null);

		layout_new = (LinearLayout) headView.findViewById(R.id.layout_new);// 新朋友那一行
		frame_new = (FrameLayout) headView.findViewById(R.id.frame_new);// 图片
		iv_msg_tips = (ImageView) headView.findViewById(R.id.iv_msg_tips);// 红点
		tv_new_name = (TextView) headView.findViewById(R.id.tv_new_name);// 新朋友
		layout_near = (LinearLayout) headView.findViewById(R.id.layout_near);// 附近的人哪一样
		// 新朋友
		layout_new.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 跳转到处理添加新朋友布局
				Intent intent = new Intent(getActivity(),
						NewFriendsActivity.class);
				intent.putExtra("from", "contact");
				startActivity(intent);
			}
		});
		// 附近
		layout_near.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 跳转到地图布局
				startActivity(new Intent(getActivity(),
						NearFriendsActivity.class));
			}
		});

		layout_list = (RelativeLayout) findViewById(R.id.layout_list);
		// 搜索框初始化
		et_msg_search = (ClearEditText) findViewById(R.id.et_msg_search);
		et_msg_search.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				// 根据输入框的字符来更新listView
				filterList(s);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		list_friends = (ListView) findViewById(R.id.list_friends);

		// 联系人列表设置
		// 添加新朋友和附近的人
		list_friends.addHeaderView(headView);
		users = new ArrayList<User>();
		users.add(new User("B2222", "222222222", "123456@qq.com"));
		users.add(new User("b2222", "222222222", "123456@qq.com"));
		users.add(new User("在没变", "222222222", "123456@qq.com"));
		users.add(new User("波在没变", "222222222", "123456@qq.com"));
		users.add(new User("啊在没变", "222222222", "123456@qq.com"));
		users.add(new User("a1111", "123456789", "123456@qq.com"));
		users.add(new User("A2222", "222222222", "123456@qq.com"));
		contact = new ContactAdapter(getActivity(), users);
		list_friends.setAdapter(contact);
		// 左侧字母导航
		dialog = (TextView) findViewById(R.id.dialog);
		right_letter = (MyLetterView) findViewById(R.id.right_letter);
		right_letter.setTextView(dialog);
		right_letter
				.setOnTouchingLetterChangedListener(new mOnTouchingLetterChangedListener());
	}

	List<User> users;

	CharacterParser characterParser;

	/**
	 * 根据搜索框内容实时更新list
	 */
	private void filterList(CharSequence s) {
		// TODO Auto-generated method stub
		List<User> newUsers = new ArrayList<User>();
		if (TextUtils.isEmpty(s)) {
			newUsers = users;
		} else {
			newUsers.clear();
			for (User user : users) {
				String name = user.getName();
				if (!TextUtils.isEmpty(name)) {
					String selling = characterParser.getSelling(name);
					if (selling.contains(s)) {
						newUsers.add(user);
					}
				}
			}
		}
		Collections.sort(newUsers, new SortChineseName());
		contact.updateUserList(newUsers);
	}

	private class mOnTouchingLetterChangedListener implements
			OnTouchingLetterChangedListener {

		/*
		 * 触摸右侧字母导航事件,变化list的显示的item
		 */
		@Override
		public void onTouchingLetterChanged(String s) {
			// TODO Auto-generated method stub
			int position = contact.getPositionForSection(s.charAt(0));
			if (position != -1) {
				list_friends.setSelection(position);
			}
		}

	}

	// fragment中不能直接使用findViewById，需要先用getView（）方法得到activity
	public View findViewById(int paramInt) {
		return getView().findViewById(paramInt);
	}

	// 左边按钮的点击事件
	public class OnRightButtonClickListener implements
			RightButtonOnclickListener {
		@Override
		public void onClick() {
			// TODO Auto-generated method stub
			startActivity(new Intent(getActivity(), AddFriendActivity.class));
		}
	}

	public void initTopBarForOnlyTitle(String titleName) {
		mHeaderLayout = (HeaderLayout) findViewById(R.id.common_actionbar);
		mHeaderLayout.init(null, titleName,
				R.drawable.base_action_bar_add_bg_selector, null,
				new OnRightButtonClickListener());
	}

	/*
	 * 
	 */
	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget
	 * .AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

}

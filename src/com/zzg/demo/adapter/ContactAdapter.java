/**
 * 
 */
package com.zzg.demo.adapter;

import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.zzg.demo.R;
import com.zzg.demo.bean.User;
import com.zzg.demo.utils.SortChineseName;

/**
 * @author acer Descrption:TODO WHAT 2015-4-14 下午12:15:53
 */
public class ContactAdapter extends BaseAdapter implements SectionIndexer {

	private Context context;
	private List<User> users;
	private TextView alpha;

	public ContactAdapter(Context context, List<User> users) {
		this.context = context;
		Collections.sort(users, new SortChineseName());
		for (int i = 0; i < users.size(); i++) {
			System.out.println("users"+i+"======"+users.get(i).getAlpha());
		}
		this.users = users;
	}

	/**
	 * 根据新传进来的users对象更新列表
	 */
	public void updateUserList(List<User> users) {
		// TODO Auto-generated method stub
		this.users = users;
		notifyDataSetChanged();
	}

	public void remove(User user) {
		this.users.remove(user);
		notifyDataSetChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return users.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return users.get(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	static class ViewHolder {
		TextView alpha;// 首字母提示
		ImageView avatar;
		TextView name;
		TextView phone;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_user_friend, null);
			holder.name = (TextView) convertView
					.findViewById(R.id.tv_friend_name);
			holder.phone = (TextView) convertView
					.findViewById(R.id.tv_friend_phone);
			holder.avatar = (ImageView) convertView
					.findViewById(R.id.img_friend_avatar);
			holder.alpha = (TextView) convertView.findViewById(R.id.alpha);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		User user_position = users.get(position);
		holder.name.setText(user_position.getName());
		holder.phone.setText(user_position.getPhone());

		// 判断是否要显示该条信息所在的部分a-z
		int section = getSectionForPosition(position);
		if (position == getPositionForSection(section)) {
			// 设置section显示为首字母
			holder.alpha.setVisibility(View.VISIBLE);
			char text = user_position.getAlpha().charAt(0);
			holder.alpha.setText(String.valueOf(text));
		} else {
			holder.alpha.setVisibility(View.GONE);
		}
		return convertView;
	}

	/*
	 * 
	 */
	@Override
	public int getPositionForSection(int sectionIndex) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = users.get(i).getAlpha();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == sectionIndex) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * 
	 */
	@Override
	public int getSectionForPosition(int position) {
		return users.get(position).getAlpha().charAt(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.SectionIndexer#getSections()
	 */
	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return null;
	}

}

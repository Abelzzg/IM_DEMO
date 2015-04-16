/**
 * 
 */
package com.zzg.demo;

import java.util.ArrayList;
import java.util.List;

import com.zzg.demo.bean.User;

import android.app.Application;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts.Data;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;

/**
 * @author acer Descrption:TODO WHAT 2015-4-10 下午4:48:55
 */
public class MyApplication extends Application {

	public static MyApplication mInstance;

	public static List<User> users;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mInstance = this;
	}

	/**
	 * 暂时先从手机联系人中取联系人
	 */
	private List<User> getUsers() {
		//
		final List<User> list = new ArrayList<User>();
		// new Thread(new Runnable() {
		// @Override
		// public void run() {
		// // TODO Auto-generated method stub
		// ContentResolver resolver = getContentResolver();
		//
		// Uri uri = Uri
		// .parse("content://com.android.contacts/raw_contacts");
		// Uri dateUri = Uri.parse("content://com.android.contacts/data");
		//
		// Cursor cursor = resolver.query(uri,
		// new String[] { "contact_id" }, null, null, null);
		//
		// while (cursor.moveToNext()) {
		// String contact_id = cursor.getString(0);
		// if (contact_id != null) {
		// // 具体的某一个联系人
		// User user = new User();
		// Cursor dataCursor = resolver.query(dateUri,
		// new String[] { "data1", "mimetype" },
		// "contact_id=?", new String[] { contact_id },
		// null);
		//
		// while (dataCursor.moveToNext()) {
		// String data1 = dataCursor.getString(0);
		// String mimetype = dataCursor.getString(1);
		// System.out.println("data1==" + data1
		// + ",mimetype==" + mimetype);
		// if ("vnd.android.cursor.item/name".equals(mimetype)) {
		// // 姓名
		// user.setName(data1);
		// } else if ("vnd.android.cursor.item/phone_v2"
		// .equals(mimetype)) {
		// // 电话号码
		// user.setPhone(data1);
		// }
		// list.add(user);
		// }
		// dataCursor.close();
		// }
		// }
		// cursor.close();
		// }
		// }).start();
		User user = new User("zhangsan", "123456789", "123456789@qq.com");
		list.add(user);
		list.add(user);
		list.add(user);
		return list;
	}

	/**
	 * 
	 */
	public static MyApplication getInstance() {
		// TODO Auto-generated method stub
		return mInstance;
	}
}

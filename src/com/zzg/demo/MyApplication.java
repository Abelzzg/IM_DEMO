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

	private  List<User> users = new ArrayList<User>();

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mInstance = this;
	}

	/**
	 * 暂时先从手机联系人中取联系人
	 */
	public List<User> getUsers() {
		User user = new User("zhangsan", "123456789", "123456789@qq.com");
		users.add(new User("B2222", "222222222", "123456@qq.com"));
		users.add(new User("b2222", "222222222", "123456@qq.com"));
		users.add(new User("在没变", "222222222", "123456@qq.com"));
		users.add(new User("波在没变", "222222222", "123456@qq.com"));
		users.add(new User("啊在没变", "222222222", "123456@qq.com"));
		users.add(new User("a1111", "123456789", "123456@qq.com"));
		users.add(new User("A2222", "222222222", "123456@qq.com"));
		return users;
	}

	/**
	 * 
	 */
	public static MyApplication getInstance() {
		// TODO Auto-generated method stub
		return mInstance;
	}
}

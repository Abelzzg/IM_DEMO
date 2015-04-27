/**
 * 
 */
package com.zzg.demo.bean;

import com.baidu.location.BDLocation;
import com.zzg.demo.utils.CharacterParser;

/**
 * @author acer Descrption:TODO WHAT 2015-4-14 ����2:22:38
 */
public class User {

	private String name;

	/**
	 * ��������ĸ
	 */
	private String alpha;

	private String email;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public User() {
	}

	public User(String name, String phone, String email,BDLocation location) {
		setName(name);
		setPhone(phone);
		setEmail(email);
		// �������������һ����ĸ
		CharacterParser characterParser = CharacterParser.getInstance();
		String getSelling = characterParser.getSelling(name);
		System.out.println("getSelling===========>>>"
				+ getSelling.toUpperCase().charAt(0));
		setAlpha(String.valueOf(getSelling.toUpperCase().charAt(0)));
		setLocation(location);
	}

	/**
	 * @return the alpha
	 */
	public String getAlpha() {
		return alpha;
	}

	/**
	 * @param c
	 *            the alpha to set
	 */
	public void setAlpha(String c) {
		this.alpha = c;
	}

	/**
	 * @return the location
	 */
	public BDLocation getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(BDLocation location) {
		this.location = location;
	}

	/**
	 * ����
	 */
	private BDLocation location;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the number
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the number to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * �绰����
	 */
	private String phone;
	/**
	 * �Ա�
	 */
	private String sex;

}

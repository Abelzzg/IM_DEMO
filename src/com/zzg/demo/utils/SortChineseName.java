/**
 * 
 */
package com.zzg.demo.utils;

import java.text.Collator;
import java.util.Comparator;

import com.zzg.demo.bean.User;

/**
 * @author acer
 * Descrption:TODO WHAT
 * 2015-4-16 обнГ4:02:04  
 */
public class SortChineseName implements Comparator<User> {
	Collator cmp = Collator.getInstance(java.util.Locale.CHINA);

	@Override
	public int compare(User o1, User o2) {
		if (cmp.compare(o1.getAlpha(), o2.getAlpha()) > 0) {
			return 1;
		} else if (cmp.compare(o1.getAlpha(), o2.getAlpha()) < 0) {
			return -1;
		}
		return 0;
	}
}


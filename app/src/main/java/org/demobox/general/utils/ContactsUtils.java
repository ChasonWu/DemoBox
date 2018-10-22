package org.demobox.general.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import org.demobox.general.bean.ContactsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 用来获取手机联系人的工具类
 * @author Chason Wu
 */
public class ContactsUtils {
	
	private ContactsUtils() {}
	
	/**
	 * 获取手机上所有的联系人
	 * @param context
	 * @return
	 */
	public static List<ContactsBean> getAllContacts(Context context) {
		ContentResolver resolver = context.getContentResolver();
		
		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		String[] projection = new String[] {
				ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Phone.NUMBER,
				ContactsContract.CommonDataKinds.Phone.CONTACT_ID
		};
		
		List<ContactsBean> contactsList = new ArrayList<ContactsBean>();
		Cursor cursor = resolver.query(uri, projection, null, null, null);
		if (cursor != null) {
			while(cursor.moveToNext()) {
				ContactsBean contact = new ContactsBean();
				contact.setName(cursor.getString(0));
				contact.setNumber(cursor.getString(1));
				contact.setContactId(cursor.getLong(2));
				contactsList.add(contact);
			}
		}
		cursor.close();
		
		return contactsList;
	}

}

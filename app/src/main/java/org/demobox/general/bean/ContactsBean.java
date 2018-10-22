package org.demobox.general.bean;

/**
 * 手机联系人
 * @author Chason Wu
 */
public class ContactsBean {
	
	private int icon;
	private String name;
	private String number;
	private long contactId;
	
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public long getContactId() {
		return contactId;
	}
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

}

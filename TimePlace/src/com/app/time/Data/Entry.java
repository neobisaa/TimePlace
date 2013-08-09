package com.app.time.Data;


/**
 * Represents 1 entry in DB
 * Holds all needed fields
 * 
 * @author San4 *
 */
public class Entry {

	// private members
	private String name;
	private String icon;
	private String info;
	
	// values TODO
	
	// Public API
	public Entry(String entryName, String entryIcon, String entryInfo)
	{
		name = entryName;
		icon = entryIcon;
		info = entryInfo;
		
		// TODO pass & add values
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
}

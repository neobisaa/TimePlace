package com.app.time.Data;

import android.util.Log;

/**
 * Represents a question 
 * Composed of 3 entries
 * Info regarding correct ordering
 * 
 * @author San4
 */
public class Question {

	private Entry[] m_entries;
	
	
	/**
	 * Ctor
	 */
	public Question()
	{	
		m_entries = new Entry[3];
	}
	
	/**
	 * Used to set the entry by controller
	 * 
	 * @param index
	 * @param entry
	 * @return
	 */
	public boolean SetEntry(int index, Entry entry)
	{
		if(index>=0 && index<=2)
		{
			Log.v("LOG", "Entry added");
			m_entries[index] = entry;
			return true;
		}
		Log.v("LOG", "Couldn't add entry, index out of bounds");
		return false;
	}
	
	public Entry GetEntry(int index)
	{
		if(index>=0 && index<=2)
		{
			Log.v("LOG", "Entry get");			
			return m_entries[index];
		}
		Log.v("LOG", "Couldn't get entry, index out of bounds");
		return null;
	}
	
	
	
	
}

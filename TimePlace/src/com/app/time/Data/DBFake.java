package com.app.time.Data;

import java.util.ArrayList;
import java.util.Random;

public class DBFake implements IDBHandler {

	private ArrayList<Entry> entries;
	
	/**
	 * Ctor
	 */
	public DBFake() {
		entries = new ArrayList<Entry>();
		
		// add values
		entries.add(new Entry("Shakespear", "shakespear", "tratratratra"));
		entries.add(new Entry("Jack London", "jlondon", "blalblablabla"));
		entries.add(new Entry("Stephen King", "sking", "kikikikkiki"));
		entries.add(new Entry("J.R.R. Martin", "jrrmartin", "marmarmarm"));
		entries.add(new Entry("Dean Koontz", "dkoontz", "kukkukuk"));
	}
	
	@Override
	public Question GetNextQuestion(String topic) {				
		// question to fill in
		Question que = new Question();
		
		// choose 3 random questions
		Random generator = new Random();
		int i, j, k;
		
		i = generator.nextInt(entries.size());
		que.SetEntry(0, entries.get(i));
		
		// get second
		do {					
			j = generator.nextInt(entries.size());
		}
		while (j==i);
		que.SetEntry(1, entries.get(j));
		
		// get third
		do {					
			k = generator.nextInt(entries.size());
		}
		while (k == i || k == j);
		que.SetEntry(2, entries.get(k));
		
		return que;
	}

	
}

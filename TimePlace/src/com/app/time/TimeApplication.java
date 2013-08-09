package com.app.time;

import android.app.Application;
import android.content.res.Configuration;

import com.app.time.Control.IControl;
import com.app.time.Data.DBFake;
import com.app.time.Data.IDBHandler;
import com.app.time.Data.Question;

public class TimeApplication extends Application implements IControl{

	private IDBHandler database;	
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
 
	@Override
	public void onCreate() {
		database = new DBFake();
		super.onCreate();
	}
 
	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}
 
	@Override
	public void onTerminate() {
		super.onTerminate();
	}
	
	


	@Override
	public void ProcessResult(boolean correct) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SetNextQuestion(IQuestionView view) {
		Question q = database.GetNextQuestion("writers");
		view.SetQuestion(q);
	}
	
}

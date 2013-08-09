package com.app.time;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

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
		Log.v(Constants.log_string, "Processing result");
		  
	}

	@Override
	public void SetNextQuestion(IQuestionView view) {
		Question q = database.GetNextQuestion("writers");
		view.SetQuestion(q);
	}
	
}

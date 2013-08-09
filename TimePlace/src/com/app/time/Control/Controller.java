package com.app.time.Control;

import android.util.Log;

import com.app.time.Constants;
import com.app.time.IQuestionView;
import com.app.time.Data.IDBHandler;

public class Controller implements IControl{

	IDBHandler DBHandler;

	@Override
	public void ProcessResult(boolean correct) {		
		Log.v(Constants.log_string, "Processing Result");		
	}

	@Override
	public void SetNextQuestion(IQuestionView view) {
		// TODO Auto-generated method stub
		
	}
	
	
}

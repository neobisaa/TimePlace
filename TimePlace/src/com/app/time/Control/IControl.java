package com.app.time.Control;

import com.app.time.IQuestionView;


public interface IControl {

	public void ProcessResult(boolean correct);		
	
	public void SetNextQuestion(IQuestionView view);
	
}

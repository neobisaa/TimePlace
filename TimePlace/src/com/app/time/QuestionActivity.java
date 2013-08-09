package com.app.time;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

import com.app.time.Data.Question;

public class QuestionActivity extends Activity implements IQuestionView{

	private Question quest;
	
	public QuestionActivity() {
		Log.v(Constants.log_string, "CTOR QUESTION ACT");
		
	}
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
		Log.v(Constants.log_string, "ON CREATE QUESTION ACT");

		TimeApplication app = (TimeApplication) getApplication();
        app.SetNextQuestion((IQuestionView)this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.question, menu);
        return true;
    }

    private void setEntry(int index) {
    	ImageView img = (ImageView) findViewById(R.id.entryImg1);
    	
    }

	@Override
	public void SetQuestion(Question question) {
    	Log.v(Constants.log_string, "SetNextQuestion");
		quest = question;
		// add pics and data
		String topic = "";
		// First Image
		String icoPath = question.GetEntry(0).getIcon();
    	int imageResource = getResources().getIdentifier("drawable/"+topic+icoPath, null, getPackageName());    	
		ImageView imageView = (ImageView) findViewById(R.id.entryImg1);
		Drawable image = getResources().getDrawable(imageResource);		
    	imageView.setImageDrawable(image);
		
    	// SecondImage
    	icoPath = question.GetEntry(1).getIcon();
    	imageResource = getResources().getIdentifier("drawable/"+topic+icoPath, null, getPackageName());    	
		imageView = (ImageView) findViewById(R.id.entryImg2);
		image = getResources().getDrawable(imageResource);		
    	imageView.setImageDrawable(image);
		
    	// SecondImage
    	icoPath = question.GetEntry(2).getIcon();
    	imageResource = getResources().getIdentifier("drawable/"+topic+icoPath, null, getPackageName());    	
		imageView = (ImageView) findViewById(R.id.entryImg3);
		image = getResources().getDrawable(imageResource);		
    	imageView.setImageDrawable(image);
    	
	}
    
}

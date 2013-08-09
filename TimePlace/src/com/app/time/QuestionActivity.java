package com.app.time;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.time.Data.Question;

public class QuestionActivity extends Activity implements IQuestionView {

	private Question quest;
	private  int[] pressOrder;
	private  int[] correctOrder;
	private int currentIndex;
	TimeApplication app;
	
	private void CallResult()
	{		
		int errors = 0;
		for(int i=0;i<3;i++)
		{
			if (correctOrder[i] != pressOrder [i])
				errors++;		
		}				
		
		app.ProcessResult(errors == 0);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Your answer was:")
        .setMessage((errors == 0) ? "Correct" : "Incorrect")
        .setCancelable(false)
        .setNegativeButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	                
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
        this.finish();
        
    }
	
	
	public QuestionActivity() {
		Log.v(Constants.log_string, "CTOR QUESTION ACT");
		pressOrder = new int[3];	
		correctOrder = new int[3];
		correctOrder[0]=1;
		correctOrder[1]=2;
		correctOrder[2]=3;
		currentIndex = 0;		
	}	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
		Log.v(Constants.log_string, "ON CREATE QUESTION ACT");

		app = (TimeApplication) getApplication();
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
    	TextView txtView = (TextView) findViewById(R.id.entryText1);
    	txtView.setText(question.GetEntry(0).getName());
    	// add listener
    	imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.v(Constants.log_string, "Pressed 1");
				
				pressOrder[currentIndex] = 1;				
				//v.setClickable(false);
				Animation rotation = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotate_around_center);
				v.startAnimation(rotation);
				
				if(currentIndex == 2)
				{
					Log.v(Constants.log_string, "ALL Pressed!!!");
					CallResult();
				}
				else
					currentIndex++;
				
				
				
				
			}
		});
		
    	// SecondImage
    	icoPath = question.GetEntry(1).getIcon();
    	imageResource = getResources().getIdentifier("drawable/"+topic+icoPath, null, getPackageName());    	
		imageView = (ImageView) findViewById(R.id.entryImg2);
		image = getResources().getDrawable(imageResource);		
    	imageView.setImageDrawable(image);
    	txtView = (TextView) findViewById(R.id.entryText2);
    	txtView.setText(question.GetEntry(1).getName());
		
    	// add listener
    	imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.v(Constants.log_string, "Pressed 2");
				
				pressOrder[currentIndex] = 2;				
				//v.setClickable(false);
				Animation rotation = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotate_around_center);
				v.startAnimation(rotation);

				if(currentIndex == 2)
				{
					Log.v(Constants.log_string, "ALL Pressed!!!");
					CallResult();
				}
				else
					currentIndex++;
				
			}
		});
    	
    	// SecondImage
    	icoPath = question.GetEntry(2).getIcon();
    	imageResource = getResources().getIdentifier("drawable/"+topic+icoPath, null, getPackageName());    	
		imageView = (ImageView) findViewById(R.id.entryImg3);
		image = getResources().getDrawable(imageResource);		
    	imageView.setImageDrawable(image);
    	txtView = (TextView) findViewById(R.id.entryText2);
    	txtView.setText(question.GetEntry(1).getName());
    	
    	// add listener
    	imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.v(Constants.log_string, "Pressed 3");
				
				pressOrder[currentIndex] = 3;				
				//v.setClickable(false);
				Animation rotation = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotate_around_center);
				v.startAnimation(rotation);
				
				if(currentIndex == 2)
				{
					Log.v(Constants.log_string, "ALL Pressed!!!");
					CallResult();
				}
				else
					currentIndex++;
				
			}
		});

	}
	
	
    
}


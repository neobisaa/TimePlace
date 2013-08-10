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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.time.Data.Question;

public class QuestionActivity extends Activity implements IQuestionView
{

	private Question quest;
	private int[] pressOrder;
	private int[] correctOrder;
	private int currentIndex;
	TimeApplication app;

	private class IndexedCLickListener implements OnClickListener
	{
		private int index;

		IndexedCLickListener(int i)
		{
			index = i;
		}

		@Override
		public void onClick(View v)
		{
			Log.v(Constants.log_string, "Pressed");

			pressOrder[currentIndex] = index;
			// v.setClickable(false);
			Animation rotation = AnimationUtils.loadAnimation(v.getContext(),
					R.anim.rotate_around_center);
			v.startAnimation(rotation);

			if (currentIndex == 2)
				CallResult();
			else
				currentIndex++;
		}

	}

	/**
	 * Checks user ordering and calls Control's ProcessResult
	 */
	private void CallResult()
	{
		// count errors
		int errors = 0;
		for (int i = 0; i < 3; i++)
		{
			if (correctOrder[i] != pressOrder[i])
				errors++;
		}

		// call Process Results
		app.ProcessResult(errors == 0);
		
		
		// Show dialog
		// TODO change to correct answer dialog
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Your answer was:")
				.setMessage((errors == 0) ? "Correct" : "Incorrect")
				.setCancelable(false)
				.setNegativeButton("OK", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{
						// close activity
						finish();
					}
				});		
		AlertDialog alert = builder.create();
		alert.show();
	}

	public QuestionActivity()
	{
		Log.v(Constants.log_string, "CTOR QUESTION ACT");
		pressOrder = new int[3];
		correctOrder = new int[3];
		correctOrder[0] = 1;
		correctOrder[1] = 2;
		correctOrder[2] = 3;
		currentIndex = 0;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);
		Log.v(Constants.log_string, "ON CREATE QUESTION ACT");

		app = (TimeApplication) getApplication();
		app.SetNextQuestion((IQuestionView) this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question, menu);
		return true;
	}

	private void setEntry(int index)
	{
		ImageView img = (ImageView) findViewById(R.id.entryImg1);

	}

	/**
	 * Will add entry to the screen
	 * 
	 * @param index
	 *            Entry index - 1:3
	 */
	private void AddEntry(int index, int imgID, int textID)
	{
		assert (index >= 0);
		assert (index <= 2);

		// TODO add when folders are used
		String topic = "";

		// Get main entries container layout
		// LinearLayout mainLayout = (LinearLayout)
		// findViewById(R.id.entryContainerLayout);

		// TODO
		// Create and add LinearLayout to contain entry

		String iconPath = quest.GetEntry(index).getIcon();
		int imageResource = getResources().getIdentifier(
				"drawable/" + topic + iconPath, null, getPackageName());
		ImageView imageView = (ImageView) findViewById(imgID);
		Drawable image = getResources().getDrawable(imageResource);
		imageView.setImageDrawable(image);
		TextView txtView = (TextView) findViewById(textID);
		txtView.setText(quest.GetEntry(index).getName());
		// add listener
		imageView.setOnClickListener(new IndexedCLickListener(index));
	
	}

	@Override
	public void SetQuestion(Question question)
	{
		quest = question;
		
		// add entries		
		AddEntry(0, R.id.entryImg1, R.id.entryText1);
		AddEntry(1, R.id.entryImg2, R.id.entryText2);
		AddEntry(2, R.id.entryImg3, R.id.entryText3);
	}

}

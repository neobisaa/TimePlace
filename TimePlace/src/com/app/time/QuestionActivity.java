package com.app.time;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.time.Data.Question;

public class QuestionActivity extends Activity implements IQuestionView
{

	private Question quest;
	private int[] pressOrder;
	private int[] correctOrder;
	private int currentIndex;
	TimeApplication app;
	private Dialog dialog;

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

		// Show error if needed dialog
		if (errors != 0)
		{
			dialog = new Dialog(this);
			dialog.setContentView(R.layout.answer_dialog);
			dialog.setTitle("Correct answer");
			
			
			AddEntry(correctOrder[0], R.id.answerEntryImg1,
					R.id.answerEntryText1, false);
			AddEntry(correctOrder[1], R.id.answerEntryImg2,
					R.id.answerEntryText2, false);
			AddEntry(correctOrder[2], R.id.answerEntryImg3,
					R.id.answerEntryText3, false);

			Button dialogButton = (Button) dialog
					.findViewById(R.id.answerDialogButton);
			// if button is clicked, close the custom dialog
			dialogButton.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					dialog.dismiss();
					finish();
				}
			});
			
			
			
			dialog.show();
		} else
		{

		}
		
		
	}

	public QuestionActivity()
	{
		Log.v(Constants.log_string, "CTOR QUESTION ACT");
		pressOrder = new int[3];
		correctOrder = new int[3];
		correctOrder[0] = 0;
		correctOrder[1] = 1;
		correctOrder[2] = 2;
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

	/**
	 * Will add entry to the screen
	 * 
	 * @param index
	 *            Entry index - 0:2
	 */
	private void AddEntry(int index, int imgID, int textID, boolean clickable)
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
		ImageView imageView;
		TextView txtView;
		if(clickable) 
		{
		 imageView = (ImageView) findViewById(imgID);
		 txtView = (TextView) findViewById(textID);
		}
		else
		{
			 imageView = (ImageView) dialog.findViewById(imgID);
			 txtView = (TextView) dialog.findViewById(textID);
		}
		Drawable image = getResources().getDrawable(imageResource);
		imageView.setImageDrawable(image);
		
		txtView.setText(quest.GetEntry(index).getName());

		// add listener
		if (clickable)
		{
			imageView.setOnClickListener(new IndexedCLickListener(index));
		}

	}

	@Override
	public void SetQuestion(Question question)
	{
		quest = question;

		// add entries
		AddEntry(0, R.id.entryImg1, R.id.entryText1, true);
		AddEntry(1, R.id.entryImg2, R.id.entryText2, true);
		AddEntry(2, R.id.entryImg3, R.id.entryText3, true);
	}

}

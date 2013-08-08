package com.app.time;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
			
		// rotate the images
		ImageView backImg1 = (ImageView) this.findViewById(R.id.BackGroundWheel1);
        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate_around_center);
        backImg1.startAnimation(rotation);
        
        ImageView backImg2 = (ImageView) this.findViewById(R.id.BackGroundWheel2);
        Animation rotation2 = AnimationUtils.loadAnimation(this, R.anim.rotate_around_center_counter);
        backImg2.startAnimation(rotation2);
        
        // add single play listener
        Button singleplayerButton = (Button) findViewById(R.id.singleplayerButton);
        singleplayerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent questionActivity = new Intent(MainActivity.this, QuestionActivity.class);
				MainActivity.this.startActivity(questionActivity);
			}
		});	
        Button multiplayerButton = (Button) findViewById(R.id.multiplayerButton);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	
}

package com.example.googleimagesearcher;

import com.loopj.android.image.SmartImageView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FullImage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_full_image);
		ImageResult res = (ImageResult) getIntent().getSerializableExtra("imageResult");
		SmartImageView ivResult = (SmartImageView) findViewById(R.id.ivResult);
		ivResult.setImageUrl(res.getFullUrl());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.full_image, menu);
		return true;
	}

}

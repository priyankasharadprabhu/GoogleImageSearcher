package com.example.googleimagesearcher;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.loopj.android.image.SmartImageView;

public class ImageArrayAdapter extends ArrayAdapter<ImageResult> {
	public ImageArrayAdapter(Context context, List<ImageResult> imageResults) {
		super(context, R.layout.item_image_view, imageResults);
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageResult imageInfo = this.getItem(position);
		SmartImageView ivImage;
		if(convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			ivImage = (SmartImageView) inflater.inflate(R.layout.item_image_view, parent, false);
		}
		else {
			ivImage = (SmartImageView) convertView;
			ivImage.setImageResource(android.R.color.transparent);
		}
		ivImage.setImageUrl(imageInfo.getThumbUrl());
		return ivImage;
	}
}

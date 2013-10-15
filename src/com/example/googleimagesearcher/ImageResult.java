package com.example.googleimagesearcher;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ImageResult implements Serializable {
	
	private static final long serialVersionUID = 4925971058638730411L;
	public ImageResult(JSONObject jObj) {
		try {
			this.fullUrl = jObj.getString("url");
			this.thumbUrl = jObj.getString("tbUrl");
		}
		catch(JSONException je) {
			Log.d("DEBUG", "Caught json exception at 1");
			this.fullUrl = null;
			this.thumbUrl = null;
		}
		
	}
	public String getThumbUrl() {
		return thumbUrl;
	}
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}
	public String getFullUrl() {
		return fullUrl;
	}
	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}
	
	public String toString() {
		return this.thumbUrl;
	}
	String thumbUrl;
	String fullUrl;
	public static ArrayList<ImageResult> fromJsonArray(
			JSONArray jsonImageResultsArr) {
		ArrayList<ImageResult> imgResultList = new ArrayList<ImageResult>();
		for(int i = 0; i< jsonImageResultsArr.length(); i++) {
			try {
				JSONObject jObj = jsonImageResultsArr.getJSONObject(i);
				imgResultList.add(new ImageResult(jObj) );
			}
			catch(JSONException je) {
				Log.d("DEBUG", "JsonException at 2");
				je.printStackTrace();
			}
		}
		return imgResultList;
	}
	

}

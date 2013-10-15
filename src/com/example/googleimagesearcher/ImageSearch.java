package com.example.googleimagesearcher;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class ImageSearch extends Activity {

	EditText etImageQuery;
	Button btnImageSearch;
	GridView gvImageList;
	String imageSearchApiPrefix = "https://ajax.googleapis.com/ajax/services/search/images?rsz=8&v=1.0";
	String settings_params = "";
	ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	ImageArrayAdapter imageArrayAdapter;
	int REQUEST_CODE= 1234;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search);
        setUpViews();
        imageArrayAdapter = new ImageArrayAdapter(this, imageResults);
        gvImageList.setAdapter(imageArrayAdapter);
        gvImageList.setOnItemClickListener( new OnItemClickListener() {
        
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
			Intent i = new Intent(getApplicationContext(), FullImage.class);
			ImageResult res = imageResults.get(position);
			i.putExtra("imageResult", res);
			startActivity(i);
				
			}
		}
        		
        		);
    }

    public void setUpViews() {
    	etImageQuery = (EditText) findViewById(R.id.etImageQuery);
    	btnImageSearch = (Button) findViewById(R.id.btnImageSearch);
    	gvImageList = (GridView) findViewById(R.id.gvImageList);
    	
    }
    
    public void onImageBtnSearch(View v) {
    	String query = etImageQuery.getText().toString();
    	String apiUrl = imageSearchApiPrefix + "&start=" + 0 + "&q=" + Uri.encode(query) + settings_params;
    	Toast.makeText(this, "Searching for " + apiUrl, Toast.LENGTH_SHORT).show();
    	getClientReponseAndPopulateGrid(apiUrl);
    }
    
    public void getClientReponseAndPopulateGrid(String apiUrl) {
    	AsyncHttpClient client = new AsyncHttpClient();
    	
    	Log.d("DEBUG", "apiUrl is " + apiUrl);
    	client.get( apiUrl
    			, new JsonHttpResponseHandler() {
    		@Override
    		public void onSuccess(org.json.JSONObject response) {
    			Log.d("DEBUG", "In onSuccess ");
    			JSONArray jsonImageResultsArr = null;
    			try {
    				jsonImageResultsArr = response.getJSONObject("responseData").getJSONArray("results");
    				Log.d("DEBUG", "Result json arr size is " +jsonImageResultsArr.length());
    				imageResults.clear();
    				imageArrayAdapter.addAll(ImageResult.fromJsonArray(jsonImageResultsArr));
    				//Log.d("DEBUG", imageResults.toString());
    			}
    			catch(JSONException e) {
    				Log.d("DEBUG", "JsonException in OnSuccess");
    				e.printStackTrace();
    			}
    			
    		};
    	}
    	);
    }
    
    public void onSettingsClick(MenuItem mi) {
    	Toast.makeText(this, "Clicked on settings", Toast.LENGTH_SHORT).show();
    	 Intent i = new Intent(getApplicationContext(), ImageSettingsActivity.class);
    	 startActivityForResult(i, REQUEST_CODE); // brings up the second activity
    }
    
    // FirstActivity, time to handle the result of the sub-activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
    	 settings_params =  data.getExtras().getString("api_params");
         Toast.makeText(this, settings_params,
            Toast.LENGTH_LONG).show();

      }
      
    } 
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.image_search, menu);
        return true;
    }
    
    
    
}

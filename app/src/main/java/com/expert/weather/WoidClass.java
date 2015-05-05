package com.expert.weather;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class WoidClass extends AsyncTask<String, String , String>{

	String data , result , Woied;
	Context mContext;
	ProgressDialog pd;
	Activity activity;
	
	public WoidClass(String search, MainActivity mainActivity, ProgressDialog pd2 , MainActivity mActivity) {
		data = search;
		mContext = mainActivity;
		pd = pd2;
		activity = mActivity;
	}

	@Override
	protected String doInBackground(String... params) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(data);

		InputStream is = null;
		HttpResponse httpResponse;
		try {
			httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();    
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
           
        } catch (Exception e) {
            Log.v("Buffer Error", "Error converting result " + e.toString());
        }
		
		if(result != null){
			JSONObject jObj;
			try {
				jObj = new JSONObject(result);
				JSONObject data = jObj.getJSONObject("query");
				int no = data.getInt("count");
				if(no != 0){
					if(data.has("results")){
						JSONObject result = data.getJSONObject("results");
						if(result.has("place")){
						JSONArray arr = result.getJSONArray("place");
						JSONObject ob = arr.getJSONObject(0);
						Woied = ob.getString("woeid");
						}
						else{
							return null;
						}
					}
					else{
						return null;
					}
				}
				else{
					return null;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		else{
			return null;
		}
		return Woied;
	}

}

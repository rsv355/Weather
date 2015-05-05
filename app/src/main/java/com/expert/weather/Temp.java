package com.expert.weather;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class Temp extends AsyncTask<String, String, String>{

	String Woid;
	String url = "http://weather.yahooapis.com/forecastrss?w=";
	Context context;
	ProgressDialog pd;

	public Temp(String woied, Context mContext , ProgressDialog pds) {
		Woid = woied;
		context = mContext;
		pd = pds;
	}

	@Override
	protected String doInBackground(String... params) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpPost = new HttpGet(url+Woid+"&u=c&#8221");

		InputStream is = null;
		HttpResponse httpResponse;
		try {
			httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();    
		}  catch (Exception e) {
			Log.v("er3", "three");
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
            String result = sb.toString();
            
            if(result !=null){
            	
            	Intent i = new Intent(context.getApplicationContext(),Show.class);
            	i.putExtra("result", result);
            	context.startActivity(i);
            	pd.cancel();
            }
            else{
            	pd.cancel();
            }
        } catch (Exception e) {
            Log.v("Buffer Error", "Error converting result " + e.toString());
        }
		
		return null;
	}

}

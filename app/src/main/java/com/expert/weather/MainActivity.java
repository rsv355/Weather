package com.expert.weather;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Timer;
import java.util.TimerTask;
import android.util.Log;
public class MainActivity extends Activity implements View.OnClickListener{

	String PACKAGE_NAME;
	private EditText edt;
	private Button btn;
	String url = "http://query.yahooapis.com/v1/public/yql?q=select*from%20geo.places%20where%20text=%22";
	ProgressDialog pd;
	int bannerNo;
	ImageView Imgbanner;
	InterstitialAd interstitial;
	com.google.android.gms.ads.AdRequest adRequest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);



		interstitial = new InterstitialAd(MainActivity.this);
		interstitial.setAdUnitId("ca-app-pub-1878227272753934/8361723600");
		adRequest = new com.google.android.gms.ads.AdRequest.Builder()
				.build();


		AdView adView = (AdView) this.findViewById(R.id.adView);
		// Request for Ads
		adRequest = new AdRequest.Builder().build();
		// Load ads into Banner Ads
		adView.loadAd(adRequest);
		// adview ends



			edt = (EditText) findViewById(R.id.inputCountry);
		btn = (Button) findViewById(R.id.btnSearch);

		Imgbanner = (ImageView)findViewById(R.id.Imgbanner);
		Imgbanner.setOnClickListener(this);

		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String s = edt.getText().toString();

				if (s != null) {

					s = s.replaceAll(" ", "");


					pd = new ProgressDialog(MainActivity.this);
					pd.setMessage("Searching Please Wait");
					pd.show();

					String search = url + s + "%22&format=json";

					String result;
					try {
						result = new WoidClass(search, MainActivity.this, pd, MainActivity.this).execute().get();

						if (result != null) {
							new Temp(result, MainActivity.this, pd).execute();
						} else {
							Toast.makeText(getApplicationContext(), "No Result Found", Toast.LENGTH_LONG).show();
							pd.cancel();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					Toast.makeText(getApplicationContext(), "Please input Text", Toast.LENGTH_LONG).show();
				}
			}
		});		
	}


	public void displayInterstitial() {

		// If Ads are loaded, show Interstitial else show nothing.
		if (interstitial.isLoaded()) {
			interstitial.show();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if(checkInternet()) {
							Imgbanner.setVisibility(View.VISIBLE);
							changeBackground();
						}
						else {
							Imgbanner.setVisibility(View.GONE);
						}
					}
				});

			}
		}, 0, 1000 * 30);


	}


	private boolean checkInternet(){
		ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(MainActivity.this.CONNECTIVITY_SERVICE);
		if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
				connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
			return true;
		} else {
			return false;
		}


	}

	private void changeBackground() {

		/*interstitial.loadAd(adRequest);
		// Prepare an Interstitial Ad Listener
		interstitial.setAdListener(new AdListener() {
			public void onAdLoaded() {
				displayInterstitial();

			}
		});*/

		Log.e("On time task", "calling bG");
		SharedPreferences preferences1 = getSharedPreferences("user", MODE_PRIVATE);
		bannerNo = preferences1.getInt("banner", 2);

		if(bannerNo == 1){
			Log.e("On condintion"," 1");
			PACKAGE_NAME = "com.app.kidsbookapp";
			Imgbanner.setImageResource(R.drawable.kidsbookbanner);
			bannerNo += 1;
		}else if(bannerNo == 2){
			Log.e("On condintion"," 2");
			PACKAGE_NAME = "com.app.namesofallah";
			Imgbanner.setImageResource(R.drawable.namesofallahbanner);
			bannerNo += 1;
		}else if(bannerNo == 3){
			Log.e("On condintion"," 3");
			PACKAGE_NAME = "com.currencyapp.currencyconverter";
			Imgbanner.setImageResource(R.drawable.currecnybanner);
			bannerNo += 1;
		}else if(bannerNo == 4){
			Log.e("On condintion"," 4");
			PACKAGE_NAME = "com.app.urduqaida";
			Imgbanner.setImageResource(R.drawable.urdubanner);
			bannerNo += 1;

		}else if(bannerNo == 5){
			Log.e("On condintion"," 5");
			PACKAGE_NAME = "com.app.LocationFinder";
			Imgbanner.setImageResource(R.drawable.locationbanner);
			bannerNo = 1;
		}




		SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt("banner", bannerNo);
		editor.commit();


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){

			case R.id.Imgbanner:
				final String appPackageName = PACKAGE_NAME; // getPackageName() from Context or Activity object
				try {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
				} catch (android.content.ActivityNotFoundException anfe) {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
				}



				break;
			/*case R.id.img1:

				final String appPackageName = "com.app.kidsbookapp"; // getPackageName() from Context or Activity object
				try {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
				} catch (android.content.ActivityNotFoundException anfe) {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
				}



				break;
			case R.id.img2:

				final String appPackageName2 = "com.app.LocationFinder"; // getPackageName() from Context or Activity object
				try {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName2)));
				} catch (android.content.ActivityNotFoundException anfe) {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName2)));
				}
				break;
			case R.id.img3:

				final String appPackageName3 = "com.app.islamicduaapp"; // getPackageName() from Context or Activity object
				try {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName3)));
				} catch (android.content.ActivityNotFoundException anfe) {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName3)));
				}
				break;
			case R.id.img4:

				final String appPackageName4 = "com.currencyapp.currencyconverter"; // getPackageName() from Context or Activity object
				try {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName4)));
				} catch (android.content.ActivityNotFoundException anfe) {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName4)));
				}
				break;
			case R.id.img5:

				final String appPackageName5 = "com.app.urduqaida"; // getPackageName() from Context or Activity object
				try {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName5)));
				} catch (android.content.ActivityNotFoundException anfe) {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName5)));
				}
				break;*/
		}
	}
}

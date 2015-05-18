package com.expert.weather;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.expert.weather.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Show extends Activity{

	private TextView weather_location , weather_temp , weather_humidity , weather_visibility,
	weather_date , dayone_name , dayone_temp , daytwo_name , daytwo_temp , daythree_name ,
	daythree_temp;

	private ImageView img_weather;

	class MyWeather{

		String description, date , city, region, country;

		String atmosphereHumidity, atmosphereVisibility;

		String sunrise, sunset ;

		String conditiontext, conditiontemp;

		String forecastday_one , forecasthigh_one ,forecastday_two , forecasthigh_two,
		forecastday_three , forecasthigh_three;

		public String toString(){

			return "\n- " + description + " -\n\n"
					+ date + " -\n\n"
					+ "city: " + city + "\n"
					+ "region: " + region + "\n"
					+ "country: " + country + "\n\n"

	  + "Atmosphere\n"
	  + "humidity: " + atmosphereHumidity + "\n"
	  + "visibility: " + atmosphereVisibility + "\n\n"

	  + "Sunrise: " + sunrise + "\n"
	  + "Sunset: " + sunset + "\n\n"

	  + "Condition: " + conditiontext + "\n"
	  + conditiontemp +"\n\n"

	  + "forecast " + forecastday_one + "\n"
	  + forecasthigh_one +"\n" + forecastday_two + "\n"
	  + forecasthigh_two +"\n" + forecastday_three + "\n"
	  + forecasthigh_three +"\n";
			

		}
	}
	private String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.temp);

		AdView adView = (AdView) this.findViewById(R.id.adView);
		// Request for Ads
		AdRequest adRequest = new AdRequest.Builder().build();
		// Load ads into Banner Ads
		adView.loadAd(adRequest);
		// adview ends

		weather_location = (TextView)findViewById(R.id.location);
		weather_temp = (TextView)findViewById(R.id.temperature);
		weather_humidity = (TextView)findViewById(R.id.humidityValue);
		weather_visibility = (TextView)findViewById(R.id.visiValue);
		weather_date = (TextView)findViewById(R.id.dateTime);
		dayone_name = (TextView)findViewById(R.id.dayone_name);
		dayone_temp = (TextView)findViewById(R.id.dayone_temp);
		daytwo_name = (TextView)findViewById(R.id.daytwo_name);
		daytwo_temp = (TextView)findViewById(R.id.daytwo_temp);
		daythree_name = (TextView)findViewById(R.id.daythree_name);
		daythree_temp = (TextView)findViewById(R.id.daythree_temp);

		img_weather = (ImageView) findViewById(R.id.weather_icon);

		url = getIntent().getStringExtra("result");
		String weatherString = url;
		Document weatherDoc = convertStringToDocument(weatherString);

		MyWeather weatherResult = parseWeather(weatherDoc);

		//weather.setText(weatherResult.toString());

		String number = weatherResult.conditiontemp.toString();
		setImage(number);

		weather_location.setText(weatherResult.city);
		weather_temp.setText(weatherResult.conditiontemp+(char) 0x00B0+"C");
		weather_humidity.setText(weatherResult.atmosphereHumidity+" %");
		weather_visibility.setText(weatherResult.atmosphereVisibility+" mi");
		weather_date.setText(weatherResult.date);
		dayone_name.setText(weatherResult.forecastday_one);
		dayone_temp.setText(weatherResult.forecasthigh_one+(char) 0x00B0+"C");
		daytwo_name.setText(weatherResult.forecastday_two);
		daytwo_temp.setText(weatherResult.forecasthigh_two+(char) 0x00B0+"C");
		daythree_name.setText(weatherResult.forecastday_three);
		daythree_temp.setText(weatherResult.forecasthigh_three+(char) 0x00B0+"C");

	}
	private void setImage(String no) {
		int ii = Integer.parseInt(no);
		switch (ii) {
		case 0 :
			img_weather.setImageResource(R.drawable.a0);
			break;
		case 1:
			img_weather.setImageResource(R.drawable.a2);
			break;
		case 2:
			img_weather.setImageResource(R.drawable.a2);
			break;
		case 3:
			img_weather.setImageResource(R.drawable.a2);
			break;
		case 4:
			img_weather.setImageResource(R.drawable.a2);
			break;
		case 5:
			img_weather.setImageResource(R.drawable.a5);
			break;
		case 6:
			img_weather.setImageResource(R.drawable.a5);
			break;
		case 7:
			img_weather.setImageResource(R.drawable.a5);
			break;
		case 8:
			img_weather.setImageResource(R.drawable.a8);
			break;
		case 9:
			img_weather.setImageResource(R.drawable.a9);
			break;
		case 10:
			img_weather.setImageResource(R.drawable.a9);
			break;
		case 11:
			img_weather.setImageResource(R.drawable.a8);
			break;
		case 12:
			img_weather.setImageResource(R.drawable.a8);
			break;
		case 13:
			img_weather.setImageResource(R.drawable.a13);
			break;
		case 14:
			img_weather.setImageResource(R.drawable.a13);
			break;
		case 15:
			img_weather.setImageResource(R.drawable.a13);
			break;
		case 16:
			img_weather.setImageResource(R.drawable.a13);
			break;
		case 17:
			img_weather.setImageResource(R.drawable.a19);
			break;
		case 18:
			img_weather.setImageResource(R.drawable.a19);
			break;
		case 19:
			img_weather.setImageResource(R.drawable.a19);
			break;
		case 20:
			img_weather.setImageResource(R.drawable.a19);
			break;
		case 21:
			img_weather.setImageResource(R.drawable.a19);
			break;
		case 22:
			img_weather.setImageResource(R.drawable.a19);
			break;
		case 23:
			img_weather.setImageResource(R.drawable.a19);
			break;
		case 24:
			img_weather.setImageResource(R.drawable.a24);
			break;
		case 25:
			img_weather.setImageResource(R.drawable.a25);
			break;
		case 26:
			img_weather.setImageResource(R.drawable.a26);
			break;
		case 27:
			img_weather.setImageResource(R.drawable.a27);
			break;
		case 28:
			img_weather.setImageResource(R.drawable.a28);
			break;
		case 29:
			img_weather.setImageResource(R.drawable.a29);
			break;
		case 30:
			img_weather.setImageResource(R.drawable.a30);
			break;
		case 31:
			img_weather.setImageResource(R.drawable.a31);
			break;
		case 32:
			img_weather.setImageResource(R.drawable.a32);
			break;
		case 33:
			img_weather.setImageResource(R.drawable.a33);
			break;
		case 34:
			img_weather.setImageResource(R.drawable.a34);
			break;
		case 35:
			img_weather.setImageResource(R.drawable.a35);
			break;
		case 36:
			img_weather.setImageResource(R.drawable.a36);
			break;
		case 37:
			img_weather.setImageResource(R.drawable.a2);
			break;
		case 38:
			img_weather.setImageResource(R.drawable.a2);
			break;
		case 39:
			img_weather.setImageResource(R.drawable.a2);
			break;
		case 40:
			img_weather.setImageResource(R.drawable.a2);
			break;
		case 41:
			img_weather.setImageResource(R.drawable.a41);
			break;
		case 42:
			img_weather.setImageResource(R.drawable.a41);
			break;
		case 43:
			img_weather.setImageResource(R.drawable.a41);
			break;
		case 44:
			img_weather.setImageResource(R.drawable.a44);
			break;
		case 45:
			img_weather.setImageResource(R.drawable.a45);
			break;
		case 46:
			img_weather.setImageResource(R.drawable.a46);
			break;
		case 47:
			img_weather.setImageResource(R.drawable.a46);
			break;
		case 3200:
			img_weather.setImageResource(R.drawable.a3200);
			break;

		default:
			break;
		}
	}

	private MyWeather parseWeather(Document srcDoc){

		MyWeather myWeather = new MyWeather();

		//<description>Yahoo! Weather for New York, NY</description>
		myWeather.description = srcDoc.getElementsByTagName("description")
				.item(0)
				.getTextContent();

		myWeather.date = srcDoc.getElementsByTagName("lastBuildDate")
				.item(0)
				.getTextContent();

		//<yweather:location city="New York" region="NY" country="United States"/>
		Node locationNode = srcDoc.getElementsByTagName("yweather:location").item(0);
		myWeather.city = locationNode.getAttributes()
				.getNamedItem("city")
				.getNodeValue()
				.toString();


		myWeather.region = locationNode.getAttributes()
				.getNamedItem("region")
				.getNodeValue()
				.toString();
		myWeather.country = locationNode.getAttributes()
				.getNamedItem("country")
				.getNodeValue()
				.toString();

		Node atmosphereNode = srcDoc.getElementsByTagName("yweather:atmosphere").item(0);
		myWeather.atmosphereHumidity = atmosphereNode.getAttributes()
				.getNamedItem("humidity")
				.getNodeValue()
				.toString();
		myWeather.atmosphereVisibility = atmosphereNode.getAttributes()
				.getNamedItem("visibility")
				.getNodeValue()
				.toString();


		//<yweather:astronomy sunrise="6:52 am" sunset="7:10 pm"/>
		Node astronomyNode = srcDoc.getElementsByTagName("yweather:astronomy").item(0);
		myWeather.sunrise = astronomyNode.getAttributes()
				.getNamedItem("sunrise")
				.getNodeValue()
				.toString();
		myWeather.sunset = astronomyNode.getAttributes()
				.getNamedItem("sunset")
				.getNodeValue()
				.toString();

		//<yweather:condition text="Fair" code="33" temp="60" date="Fri, 23 Mar 2012 8:49 pm EDT"/>
		Node conditionNode = srcDoc.getElementsByTagName("yweather:condition").item(0);
		myWeather.conditiontext = conditionNode.getAttributes()
				.getNamedItem("text")
				.getNodeValue()
				.toString();
		myWeather.conditiontemp = conditionNode.getAttributes()
				.getNamedItem("temp")
				.getNodeValue()
				.toString();

		Node forecastNode1 = srcDoc.getElementsByTagName("yweather:forecast").item(1);
		myWeather.forecastday_one = forecastNode1.getAttributes()
				.getNamedItem("day")
				.getNodeValue()
				.toString();
		myWeather.forecasthigh_one = forecastNode1.getAttributes()
				.getNamedItem("high")
				.getNodeValue()
				.toString();
		
		Node forecastNode2 = srcDoc.getElementsByTagName("yweather:forecast").item(2);
		myWeather.forecastday_two = forecastNode2.getAttributes()
				.getNamedItem("day")
				.getNodeValue()
				.toString();
		myWeather.forecasthigh_two = forecastNode2.getAttributes()
				.getNamedItem("high")
				.getNodeValue()
				.toString();
		
		Node forecastNode3 = srcDoc.getElementsByTagName("yweather:forecast").item(3);
		myWeather.forecastday_three = forecastNode3.getAttributes()
				.getNamedItem("day")
				.getNodeValue()
				.toString();
		myWeather.forecasthigh_three = forecastNode3.getAttributes()
				.getNamedItem("high")
				.getNodeValue()
				.toString();

		return myWeather;
	}

	private Document convertStringToDocument(String src){
		Document dest = null;

		DocumentBuilderFactory dbFactory =
				DocumentBuilderFactory.newInstance();
		DocumentBuilder parser;

		try {
			parser = dbFactory.newDocumentBuilder();
			dest = parser.parse(new ByteArrayInputStream(src.getBytes()));
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
			Toast.makeText(Show.this,
					e1.toString(), Toast.LENGTH_LONG).show();
		} catch (SAXException e) {
			e.printStackTrace();
			Toast.makeText(Show.this,
					e.toString(), Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(Show.this,
					e.toString(), Toast.LENGTH_LONG).show();
		}

		return dest;
	}

}
package com.example.dalert;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class MapActivity extends FragmentActivity implements LocationListener  {


	GoogleMap googleMap;
	LatLng myPosition;
	static boolean check = true;
	static ArrayList<CircleOptions> list=new ArrayList<CircleOptions>();

	// add all necessary things

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		// if  Google Play Services are available then

		// Getting reference to the SupportMapFragment of activity_main.xml
		SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

		// Getting GoogleMap object from the fragment
		googleMap = fm.getMap();

		// Enabling MyLocation Layer of Google Map
		googleMap.setMyLocationEnabled(true);

		// Getting LocationManager object from System Service LOCATION_SERVICE
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		// Creating a criteria object to retrieve provider
		Criteria criteria = new Criteria();

		// Getting the name of the best provider
		String provider = locationManager.getBestProvider(criteria, true);

		// Getting Current Location
		Location location = locationManager.getLastKnownLocation(provider);

		if(location!=null){
			// Getting latitude of the current location
			double latitude = location.getLatitude();

			// Getting longitude of the current location
			double longitude = location.getLongitude();

			// Creating a LatLng object for the current location
			LatLng latLng = new LatLng(latitude, longitude);
			myPosition = new LatLng(latitude, longitude);
			//googleMap.addMarker(new MarkerOptions().position(myPosition).title("You are here"));
			if (check){
				InputStream f = null;
				try {
					f = getAssets().open("dengue");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ArrayList<Location> list1 = DiseaseDetect.readCSV(f);
				for (Location l:list1){
					CircleOptions circleOptions = new CircleOptions().center(new LatLng(l.getLatitude(), l.getLongitude())).radius(200) // In meters
							.fillColor(0x1AFF0000)//90% transparent red
							.strokeColor(Color.TRANSPARENT);//dont show the border to the circle
					list.add(circleOptions);
				}

				check = false;
			}
			plotPoint(list);
		}


	}

	public void plotPoint (ArrayList<CircleOptions> list){
		for (CircleOptions l:list){
			googleMap.addCircle(l);
		}
//		googleMap.
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
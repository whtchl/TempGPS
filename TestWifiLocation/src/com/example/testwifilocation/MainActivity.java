package com.example.testwifilocation;

import java.io.IOException;
import java.util.List;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	private Geocoder gc;
	 LocationManager lm;
	private Location getLastKnownLocation() {
		lm = (LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
	    List<String> providers = lm.getProviders(true);
	    Location bestLocation = null;
	    for (String provider : providers) {
	        Location l = lm.getLastKnownLocation(provider);
	        if (l == null) {
	            continue;
	        }
	        if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
	            // Found best last known location: %s", l);
	            bestLocation = l;
	        }
	    }
	    return bestLocation;
	}
	
	
	private Location getLastKnownLocation2() {
		lm = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
	    List<String> providers = lm.getProviders(true);
	    Location bestLocation = null;
	    for (String provider : providers) {
	    	Log.d("tchl","provider:"+provider);
	     /*   if (ActionBarActivity.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
	            // TODO: Consider calling
	            //    ActivityCompat#requestPermissions
	            // here to request the missing permissions, and then overriding
	            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
	            //                                          int[] grantResults)
	            // to handle the case where the user grants the permission. See the documentation
	            // for ActivityCompat#requestPermissions for more details.
	            return null;
	        }*/
	        Location l = lm.getLastKnownLocation(provider);
	        if (l == null) {
	            continue;
	        }
	        if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
	            // Found best last known location: %s", l);
	            bestLocation = l;
	        }
	    }
	    return bestLocation;
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         Context ctx = this;
         gc = new Geocoder(ctx);
         lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 
         Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);//
         //Location location  = getLastKnownLocation();
        
         if(lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ){
        	 Log.d("tchl","get GPS provider");
         }
         
         if(lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
        	 Log.d("tchl","get NETWORK_PROVIDER provider");
         }
         
         final TextView cord = (TextView)findViewById(R.id.gpslocationcordinate);
         final TextView addr = (TextView)findViewById(R.id.gpsLocationAdress);
         
         double longitude ;//= location.getLongitude();
         double latitude ;//= location.getLatitude();
     
         if (location != null) {
        	 Log.d("tchl","location is not null");
        	
        	 longitude = location.getLongitude();
        	 latitude = location.getLatitude();
        	 
        	 try {
              	cord.setText( "Latitude : "+location.getLatitude()+" Longitude : "+location.getLongitude());
              	Toast.makeText(getApplicationContext(), "Latitude : "+location.getLatitude()+" Longitude : "+location.getLongitude(), Toast.LENGTH_SHORT).show() ;
           		if(lm.isProviderEnabled(WIFI_SERVICE))
           		{
              	List<Address> locationList = gc.getFromLocation(location.getLatitude(),location.getLongitude(), 1);
           		addr.setText( locationList.get(0).getFeatureName()+" "+locationList.get(0).getSubLocality()+" "+locationList.get(0).getSubAdminArea()+
                          " "+locationList.get(0).getLocality()+" "+locationList.get(0).getAdminArea()+" "+locationList.get(0).getCountryName());
           		}
                 
      		} catch (IOException e) {
      			// TODO Auto-generated catch block
      			e.printStackTrace();
      		}
         }else {
        	 Log.d("tchl","location is  null");
         }
      
         
         
         
         

         final LocationListener locationListener = new LocationListener() {
             public void onLocationChanged(Location location) {
               try {
            	    Log.d("tchl","onLocationChanged:"+"Latitude : "+location.getLatitude()+" Longitude : "+location.getLongitude());
             		cord.setText( "Latitude : "+location.getLatitude()+" Longitude : "+location.getLongitude());
                 	Toast.makeText(getApplicationContext(), "Latitude : "+location.getLatitude()+" Longitude : "+location.getLongitude(), Toast.LENGTH_SHORT).show() ;
                 	if(lm.isProviderEnabled(WIFI_SERVICE))
              		{
                 	List<Address> locationList = gc.getFromLocation(location.getLatitude(),location.getLongitude(), 1);
              		addr.setText( locationList.get(0).getFeatureName()+" "+locationList.get(0).getSubLocality()+" "+locationList.get(0).getSubAdminArea()+
                             " "+locationList.get(0).getLocality()+" "+locationList.get(0).getAdminArea()+" "+locationList.get(0).getCountryName());
              		}
 	              
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
              
             }

 			public void onProviderDisabled(String provider) {
 				// TODO Auto-generated method stub
 				Log.d("tchl","onProviderDisabled");
 				
 			}

 		     /**
 	         */
 	        public void onStatusChanged(String provider, int status, Bundle extras) {
 	            switch (status) {
 	            //GPS状态为可见时
 	            case LocationProvider.AVAILABLE:
 	                Log.i("tchl", "当前G状态为可见状态");
 	                break;
 	            //GPS状态为服务区外时
 	            case LocationProvider.OUT_OF_SERVICE:
 	                Log.i("tchl", "当前状态为服务区外状态");
 	                break;
 	            //GPS状态为暂停服务时
 	            case LocationProvider.TEMPORARILY_UNAVAILABLE:
 	                Log.i("tchl", "当前状态为暂停服务状态");
 	                break;
 	            }
 	        }
 	   
 	        /**
 	         * GPS开启时触发
 	         */
 	        public void onProviderEnabled(String provider) {
 	            Location location=lm.getLastKnownLocation(provider);
 	        }
         };
         
         //Change to LocationManager.GPS_PROVIDER to access GPS cordinates, Recommend way is to use Crietria
         //The update frequency is set to 500 which will drain battery change it to higher value for increasing
         //update time
         
         lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}

# TempGPS

TestGPS 是gps模块进行定位。在室外能够搜索到卫星。
TestWifiLoacation：是network模式进行定位。
LocaionManager-master.zip 是集合个各种定位的功能。先进性gps定位，gps失败用network定位。


=======================================
有些设备定位总是失败。应为location = null，可以用下面的函数来得到location，但是算出来的经度维度不对

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
  
  <img src="https://raw.githubusercontent.com/whtchl/TempGPS/master/art/1.jpg"/>
  <img src="https://raw.githubusercontent.com/whtchl/TempGPS/master/art/2.jpg"/>

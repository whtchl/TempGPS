TestGPS ��gpsģ����ж�λ���������ܹ����������ǡ�
TestWifiLoacation����networkģʽ���ж�λ��
LocaionManager-master.zip �Ǽ��ϸ����ֶ�λ�Ĺ��ܡ��Ƚ���gps��λ��gpsʧ����network��λ��


=======================================
��Щ�豸��λ����ʧ�ܡ�ӦΪlocation = null������������ĺ������õ�location������������ľ���ά�Ȳ���

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
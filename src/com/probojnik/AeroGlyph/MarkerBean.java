package com.probojnik.AeroGlyph;

import android.util.Log;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MarkerBean {
	private Marker marker;
    private String categoryid;
    private String customerid;
    private int state;
    private double longitude;
    private double latitude;
    private int code;
    private String posid;
    private boolean added; // false

    /**
     * MarkerBean
     * @param categoryid
     * @param customerid
     * @param state
     * @param longitude
     * @param latitude
     * @param code
     * @param posid
     */
    public MarkerBean(String categoryid, String customerid, Integer state, Double longitude, Double latitude, int code, String posid) {
        this.categoryid = categoryid;
        this.customerid = customerid;
        this.state = state;
        this.longitude = longitude;
        this.latitude = latitude;
        this.code = code;
        this.posid = posid;
    }

    public boolean addMarker(GoogleMap map){
        if(!added){
            LatLng latLng = new LatLng(latitude, longitude);
            if(latLng != null && state <= 2){
                Log.d("log123","addMarker " + latLng);
                added = true;
                addMarker(map, latLng, "", posid);
            } else Log.e("log123","latLng " + latLng);
        }

        return false;
    }

    public void addMarker(GoogleMap map, LatLng latLng, String title, String snippet){
		marker = map.addMarker(new MarkerOptions()
		.position(latLng)
		.title(title)
		.snippet(snippet)
		.icon( BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher) )
		.alpha(0.7f)
        .rotation(30.0f) ); 
	}

	public void updMarker(String title, String snippet){
    	marker.setTitle(title);
    	marker.setSnippet(snippet);	
    	marker.hideInfoWindow();
    	marker.showInfoWindow();
	}
	
	public void delMarker(){
    	marker.remove();
	}
	
	public Marker getMarker() {
		return marker;
	}
	
	public enum MB {
//		INDEX("index"),
//		TITLE("title"),
//		SNIPPET("snippet"),
//		LATLNG("latLng");
        CATID("categoryid"),
        CUSID("customerid"),
        STATE("state"),
        LONG("longitude"),
        LAT("latitude"),
        CODE("code"),
        POSID("posid");

		final String v;

		MB(String v) {
			this.v = v;
		}
	}
	
}
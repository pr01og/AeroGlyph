package com.probojnik.AeroGlyph;

import android.app.Activity;
import android.app.FragmentManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author probojnik.com
 */
public class MainActivity extends Activity implements IAsyncTask {
    GoogleMap map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        if(status == ConnectionResult.SUCCESS){
            FragmentManager fragmentManager = getFragmentManager();
            MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);
            map = mapFragment.getMap();
            if(map != null){
                Log.d("log123", "OK!");
                new MyAsyncTask(this).execute();


            } else Log.e("log123", "map " + map);
        } else Log.e("log1", "status " + status);
    }

    @Override
    public void cbAddMarkers(List<MarkerBean> markers){
        for(MarkerBean mb: markers){
            mb.addMarker(map);
        }
    }

}
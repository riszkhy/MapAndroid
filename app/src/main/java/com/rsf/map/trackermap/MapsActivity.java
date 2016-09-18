package com.rsf.map.trackermap;

import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener{



    private GoogleMap mMap;
    double latitude;
    double longitude;
    private int PROXIMITY_RADIUS = 10000;
    GoogleApiClient mGoogleApiClient;
    Location mLocation;
    Marker marker;
    LocationRequest mloLocationRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button btnRest = (Button) findViewById(R.id.btnRest);
        final EditText edFrom = (EditText) findViewById(R.id.edFrom);
        final EditText edTo = (EditText) findViewById(R.id.edTo);

        btnRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("onClick","Button REst klik");
                mMap.clear();
                String url = getURL(edFrom.getText().toString(),edTo.getText().toString());
                Object[] dt = new Object[1];
                dt[0] = url;
                GrabLocation grabLocation = new GrabLocation();
                grabLocation.execute(dt);
                LatLng sydney = new LatLng(-7.5729678, 112.2863735);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Kota"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));
                Toast.makeText(MapsActivity.this,url,Toast.LENGTH_LONG).show();
            }
        });
    }

    private String getURL(String origin,String dest){
        String key = getResources().getString(R.string.key_json);
        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/directions/json?");
        googlePlacesUrl.append("origin=" + origin);
        googlePlacesUrl.append("&destination=" + dest);
        googlePlacesUrl.append("&key=" + key);
        Log.d("url",googlePlacesUrl.toString());
        return googlePlacesUrl.toString();
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Inisialisasi ke Google Play Services
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED){
                mMap.setMyLocationEnabled(true);
            }
        }
        LatLng sydney = new LatLng(-6.226959, 106.857671);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Kota"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(14),2000,null);
//        mMap.setMinZoomPreference(15);


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

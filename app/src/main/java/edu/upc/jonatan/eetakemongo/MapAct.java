package edu.upc.jonatan.eetakemongo;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;
import edu.upc.jonatan.eetakemongo.API.APIClient;
import edu.upc.jonatan.eetakemongo.Entity.EtakemonsPosition;
import edu.upc.jonatan.eetakemongo.Entity.etakemons;

public class MapAct extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, ResultCallback<Status> {
    final String TAG = "CAZAR";

    private GoogleApiClient mGoogleApiClient;

    etakemons etk = new etakemons();
    int id;
    LocationRequest mLocationRequest;
    List<Geofence> mGeofenceList;
    PendingIntent mGeofencePendingIntent;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        createLocationRequest();
        mGeofenceList = new ArrayList<>();
        Bundle intentdata = getIntent().getExtras();
        if(intentdata!=null) {
            id = intentdata.getInt("idUser");
        }
    }
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }
    protected void onStop(){
        stopLocationUpdates();
        mGoogleApiClient.disconnect();
        super.onStop();
    }
    private void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }
    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                        mMap.getUiSettings().setMyLocationButtonEnabled(true);
                        if (getMyLatLng() != null) {
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(getMyLatLng(), 17f));
                        }
                        Log.i(TAG, "My Location enabled");
                    }
                } else {
                    Log.i(TAG, "" + ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION));
                    Log.i(TAG, "" + PackageManager.PERMISSION_GRANTED);
                    Log.e(TAG, "My Location Errors");
                }
            }
        }
    }
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(this)
                        .setTitle("title_location_permission")
                        .setMessage("text_location_permission")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(MapAct.this,
                                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }
   @Override
    public void onConnected(Bundle connectionHint) {
        setMyLocationEnabled();
        startLocationUpdates();
    }
    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onResult(@NonNull Status status) {
    }

    @Override
    public void onLocationChanged(Location loc) {

        String longitude = "Longitude: " + loc.getLongitude();
        Log.v(TAG, longitude);
        String latitude = "Latitude: " + loc.getLatitude();
        Log.v(TAG, latitude);

        /*------- To get city name from coordinates -------- */
        String cityName = null;
        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(loc.getLatitude(),
                    loc.getLongitude(), 1);
            if (addresses.size() > 0) {
                System.out.println(addresses.get(0).getLocality());
                cityName = addresses.get(0).getLocality();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void startLocationUpdates() {
        if (checkLocationPermission()) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }
    private void setMyLocationEnabled() {
        if (checkLocationPermission()) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            if (getMyLatLng() != null) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(getMyLatLng(), 17f));
            }
            Log.i(TAG, "My Location enabled");
        }
    }

    public LatLng getMyLatLng() {

        if (checkLocationPermission()) {
            Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (location != null) {
                return new LatLng(location.getLatitude(), location.getLongitude());
            }
        }
        return null;
    }
    private void addGeofences() {
        if(checkLocationPermission()) {
            try {
                LocationServices.GeofencingApi.addGeofences(mGoogleApiClient, getGeofencingRequest(), getGeofencePendingIntent());
            } catch(IllegalStateException e) {
                Log.e(TAG, "GoogleApiClient not connected yet");
                mGoogleApiClient.connect();
                addGeofences();
            }
        }
    }

    private GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(mGeofenceList);
        return builder.build();
    }

    private PendingIntent getGeofencePendingIntent() {
        if (mGeofencePendingIntent != null) {
            return mGeofencePendingIntent;
        }
        Intent intent = new Intent(this, GeofenceTransitionsIntentService.class);
        mGeofencePendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return mGeofencePendingIntent;
    }
    /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the User will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the User has
         * installed Google Play services and returned to the app.
         */
   @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
       EtakemonsPosition etPos = new EtakemonsPosition();
       etPos.setLat((float)41.275465); //41.275465, 1.986374
       etPos.setLng((float)1.9863746);

       APIClient.post(this, "/etakemon/getPosition", APIClient.getObjectAsStringEntity(etPos), "application/json", new TextHttpResponseHandler() {
                   @Override
                   public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                   }

                   @Override
                   public void onSuccess(int statusCode, Header[] headers, String responseString) {
                       Log.i(TAG, "Showing TopUsers");
                       Type listType = new TypeToken<ArrayList<EtakemonsPosition>>(){}.getType();
                       List<EtakemonsPosition> list = new Gson().fromJson(responseString, listType);
                        for(EtakemonsPosition data : list){
                            LatLng marker = new LatLng(data.getLat(), data.getLng());
                            if (data.getTipoetakemon().equals("alumno")){
                                mMap.addMarker(new MarkerOptions().position(marker).title(data.getTipoetakemon()).visible(true)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.alumnoooo)));
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
                            }else if (data.getTipoetakemon().equals("alumna")){
                                mMap.addMarker(new MarkerOptions().position(marker).title(data.getTipoetakemon()).visible(true)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.lisa)));
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
                            }else if (data.getTipoetakemon().equals("profesora")){
                                mMap.addMarker(new MarkerOptions().position(marker).title(data.getTipoetakemon()).visible(true)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.carapapel)));
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
                            }else if (data.getTipoetakemon().equals("profesor")){
                                mMap.addMarker(new MarkerOptions().position(marker).title(data.getTipoetakemon()).visible(true)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.profe)));
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
                            }else if (data.getTipoetakemon().equals("director")){
                                mMap.addMarker(new MarkerOptions().position(marker).title(data.getTipoetakemon()).visible(true)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.skynner)));
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
                            }else {
                                mMap.addMarker(new MarkerOptions().position(marker).title(data.getTipoetakemon()).visible(true)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
                            }
                        }
                   }
               });
               mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                   @Override
                   public boolean onMarkerClick(Marker marker) {

                       LatLng position = marker.getPosition();
                       EtakemonsPosition etakem = new EtakemonsPosition();
                       etakem.setLat((float)position.latitude);
                       etakem.setLng((float)position.longitude);

                       APIClient.post(getApplicationContext(), "/etakemon/getEtakemonByPosition", APIClient.getObjectAsStringEntity(etakem), "application/json", new TextHttpResponseHandler() {
                           @Override
                           public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                               System.out.println("fallaaaaaaa");
                           }

                           @Override
                           public void onSuccess(int statusCode, Header[] headers, String responseString) {
                               Log.i(TAG, "Showing TopUsers");
                               Gson json = new Gson();
                               etk = json.fromJson(responseString, etakemons.class);
                               System.out.println("holaaaa    " + etk.getTipo());
                           }
                       });

                       double a = CalculationByDistance(getMyLatLng(), position);
                       boolean isCerca = a > (0.01);
                       if (!isCerca) {
                           AlertDialog.Builder builder1 = new AlertDialog.Builder(getApplicationContext());
                           builder1.setMessage("CAPURALO!");
                           builder1.setCancelable(true);
                           Toast.makeText(getApplicationContext(), "VES A POR ÉL!! ", Toast.LENGTH_SHORT).show();
                           Intent Cazar = new Intent(MapAct.this, CazarAct.class);
                           if(etk.getId()!=0)
                               Cazar.putExtra("etakemonid",etk.getId());
                           if(id!=0)
                               Cazar.putExtra(("iduser"),id);
                           if (etk.getTipo()!= null)
                               Cazar.putExtra("etaketipo",etk.getTipo());
                           System.out.println("tipoo22222:  " + etk.getTipo());
                           startActivity(Cazar);
                       } else {
                           AlertDialog.Builder builder1 = new AlertDialog.Builder(getApplicationContext());
                           builder1.setMessage("Todavia estas lejos.");
                           builder1.setCancelable(true);
                           Toast.makeText(getApplicationContext(), "Todavia estas lejos... ", Toast.LENGTH_SHORT).show();
                       }
                       return false;
                   }
               });
    }

    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);
        return meter;
    }
}

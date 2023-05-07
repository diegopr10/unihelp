package com.example.unihelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.widget.ImageButton;


public class location extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;

    private FusedLocationProviderClient fusedLocationClient;

    private static final int LOCATION_REQUEST_CODE = 0;

    private ImageButton btUniversidad;
    private ImageButton btCalcularDis;
    private LatLng currentLocation;

    private LatLng Uc3mLocation = new LatLng(40.332306, -3.765627);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        createFragment();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        btUniversidad = findViewById(R.id.btUniversidad);
        btCalcularDis = findViewById(R.id.btCalcularDis);
        btUniversidad.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                zoomToLocation(Uc3mLocation);
            }
        });
        btCalcularDis.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (currentLocation ==null){
                    //Esto dolo pasa si todavia no se ha inicializado, y esto se debe porque no ha dado los permisos para obtebner su ubi;
                    Toast.makeText(this, "No se pudo encontrar tu ubicacion actual", Toast.LENGTH_LONG).show();
                }else{
                    float distance = calculateDisToUc3m(currentLocation);
                    Toast.makeText(this, "Estas a "+ distance+ "m de distancia de la Uc3m", Toast.LENGTH_LONG).show();


                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        createMarker();
        enableMyLocation();

    }

    private void createMarker() {
        MarkerOptions marker = new MarkerOptions().position(Uc3mLocation).title("UC3M");
        map.addMarker(marker);
        zoomToLocation(Uc3mLocation);
    }
    private void createFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMap);
        mapFragment.getMapAsync(this);
    }

    private boolean isPermissionsGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void enableMyLocation() {
        if (map == null) return; // Si el mapa no esta inicializado vete

        if (isPermissionsGranted()) {
            map.setMyLocationEnabled(true);// Si los permisos de localizacion estan activos, activa la localizacion en tiempo real
            getDeviceLocation();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);//Si no, pide los permisos
        }
    }

    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

            //Si ya le hemos perdido los permisos y los ha rechazado, le decimos que vaya a ajustes a cambiarlo
            Toast.makeText(this, "Ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show();

        } else {
            //Si no, se los pedimos
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
        }
    }

    private void getDeviceLocation() {
        try {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                        } else {
                            Toast.makeText(this, "No se pudo obtener la ubicación actual", Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (SecurityException e) {
            Toast.makeText(this, "No se pudieron obtener los permisos de ubicación", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case LOCATION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocation();
                } else {
                    Toast.makeText(this, "Para activar la localización ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }
    public void zoomToLocation(LatLng ubicacion){

        //Esta fncion solo hace un zoom a una localizacion
        map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(ubicacion, 17f),
                4000,
                null
        );

    }
    private float calculateDisToUc3m(LatLng myLocation ){

        // Creamos dos objetos Location a partir de los LatLng
        Location locationUc3m = new Location("Location Uc3m");
        locationUc3m.setLatitude(Uc3mLocation.latitude);
        locationUc3m.setLongitude(Uc3mLocation.longitude);

        Location locationNew = new Location("Location New");
        locationNew.setLatitude(myLocation.latitude);
        locationNew.setLongitude(myLocation.longitude);

        // Calculamos la distancia entre los dos puntos en metros
        float distance = locationNew.distanceTo(locationUc3m);
        return distance;
    }
}
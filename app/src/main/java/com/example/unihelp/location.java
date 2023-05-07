package com.example.unihelp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class location extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;

    private static final int LOCATION_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        createFragment();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        createMarker();
        enableMyLocation();
    }

    private void createMarker() {
        LatLng Uc3mLocation = new LatLng(40.332306, -3.765627);
        MarkerOptions marker = new MarkerOptions().position(Uc3mLocation).title("UC3M");
        map.addMarker(marker);
        map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(Uc3mLocation, 17f),
                4000,
                null
        );
    }
    private void createFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMap);
        mapFragment.getMapAsync(this);
    }

    private boolean isPermissionsGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void enableMyLocation() {
        if (map != null) return; // Si el mapa no esta inicializado vete

        if (isPermissionsGranted()) {
            map.setMyLocationEnabled(true);// Si los permisos de localizacion estan activos, activa la localizacion en tiempo real

        } else {
            requestLocationPermission();//Si no, pide los permisos
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //Si le pedimos los permisos y los acepta, le le activamos la localizacion
                map.setMyLocationEnabled(true);

            } else {

                //Si despues de pedirselos no los acpeta que se vaya  a activar la loscalizacion en ajustes
                Toast.makeText(this, "Para activar la localización ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show();
            }
        }//Esto es para comprobar que no haya aceptado otro permiso, aunque no deberia pasar
    }
}
package com.example.inmobiliariaapp.ui.Ubicacion;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class UbicacionViewModel extends ViewModel {

    private static final LatLng COORDENADAS = new LatLng(-32.89013316890412, -68.84486987270684);
    private MutableLiveData<MapaActual> mapaActual;

    public LiveData<MapaActual> getMapa() {
        if (mapaActual == null) {
            mapaActual = new MutableLiveData<MapaActual>();
        }
        return mapaActual;
    }

    public void marcar() { this.mapaActual.setValue(new MapaActual()); }
    public class MapaActual implements OnMapReadyCallback {
        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.addMarker(new MarkerOptions().position(COORDENADAS).title("Inmobiliaria"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(COORDENADAS)
                    .zoom(15)
                    .bearing(45)
                    .tilt(70)
                    .build();

            CameraUpdate update = CameraUpdateFactory.newCameraPosition(cameraPosition);
            googleMap.animateCamera(update);
        }
    }


}
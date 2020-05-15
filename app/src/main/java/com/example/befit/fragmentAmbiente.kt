package com.example.befit

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style


class fragmentAmbiente : Fragment(), OnMapReadyCallback, PermissionsListener {

    private lateinit var mapView: MapView
    lateinit var btn_start : Button
    lateinit var btn_stop : Button
    lateinit var btn_reset : Button
    lateinit var chrono : Chronometer
    private var permissionsManager: PermissionsManager = PermissionsManager(this)
    private lateinit var mapboxMap: MapboxMap

     var correr : Boolean = false
    var detener : Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Mapbox.getInstance(requireContext(), getString(R.string.acces_token))
        var mLinearLayout = inflater.inflate(R.layout.fragment_ambiente, container, false)

        btn_start = mLinearLayout.findViewById(R.id.btn_start)
        btn_stop = mLinearLayout.findViewById(R.id.btn_stop)
        chrono = mLinearLayout.findViewById(R.id.chronometer)

        mapView = mLinearLayout.findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        btn_start.setOnClickListener {
            btn_start.visibility = View.GONE
            btn_stop.visibility = View.VISIBLE
            Toast.makeText(requireContext(), "Temps en marcha", Toast.LENGTH_SHORT).show()
            startChronometer()
        }



       btn_stop.setOnClickListener {
            Toast.makeText(requireContext(), "Good job!", Toast.LENGTH_SHORT).show()
            btn_stop.visibility = View.GONE
            btn_start.visibility = View.VISIBLE
           stopChronometer()
        }
                // Inflate the layout for this fragment
        return mLinearLayout
    }

    private fun stopChronometer() {
        if(correr){
            chrono.stop()
            detener = SystemClock.elapsedRealtime() - chrono.base  //Para detener el chrono con los numeros que tiene
            correr = false
        }
    }

    private fun startChronometer() {
        if(!correr){
            chrono.base = SystemClock.elapsedRealtime() - detener
            chrono.start()
            correr=true
        }
    }


// Mapbox access token is configured here. This needs to be called either in your application
// object or in the same activity which contains the mapview.


// This contains the MapView in XML and needs to be called after the access token is configured.


    override fun onMapReady(mapboxMap: MapboxMap) {
        this.mapboxMap = mapboxMap
        mapboxMap.setStyle(
            Style.Builder().fromUri(
                "mapbox://styles/mapbox/cjerxnqt3cgvp2rmyuxbeqme7")) {

// Map is set up and the style has loaded. Now you can add data or make other map adjustments
            enableLocationComponent(it)
        }
    }

    @SuppressLint("MissingPermission")
    private fun enableLocationComponent(loadedMapStyle: Style) {
// Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(requireContext())) {

// Create and customize the LocationComponent's options
            val customLocationComponentOptions = LocationComponentOptions.builder(requireContext())
                .trackingGesturesManagement(true)
                .accuracyColor(ContextCompat.getColor(requireContext(), R.color.Orange))
                .build()

            val locationComponentActivationOptions = LocationComponentActivationOptions.builder(requireContext(), loadedMapStyle)
                .locationComponentOptions(customLocationComponentOptions)
                .build()

// Get an instance of the LocationComponent and then adjust its settings
            mapboxMap.locationComponent.apply {

// Activate the LocationComponent with options
                activateLocationComponent(locationComponentActivationOptions)

// Enable to make the LocationComponent visible
                isLocationComponentEnabled = true

// Set the LocationComponent's camera mode
                cameraMode = com.mapbox.mapboxsdk.location.modes.CameraMode.TRACKING

// Set the LocationComponent's render mode
                renderMode = com.mapbox.mapboxsdk.location.modes.RenderMode.COMPASS
            }
        } else {
            permissionsManager = PermissionsManager(this)
            permissionsManager.requestLocationPermissions(requireActivity())
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onExplanationNeeded(permissionsToExplain: List<String>) {
        /*   Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show()
       }

       override fun onPermissionResult(granted: Boolean) {
           if (granted) {
               enableLocationComponent(mapboxMap.style!!)
           } else {
               Toast.makeText(this, R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show()
               finish()
           }*/
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            enableLocationComponent(mapboxMap.style!!)
        } else {
            //Toast.makeText(this, R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show()
           // finish()
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}

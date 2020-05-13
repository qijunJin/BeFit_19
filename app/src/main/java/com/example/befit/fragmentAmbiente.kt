package com.example.befit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapView


class fragmentAmbiente : Fragment() {

    private lateinit var mapView: MapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var mLinearLayout = inflater.inflate(R.layout.fragment_ambiente, container, false)
        Mapbox.getInstance(requireActivity().applicationContext, getString(R.string.acces_token))
        mapView = mLinearLayout.findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
                // Inflate the layout for this fragment
        return mLinearLayout
    }

}
